package lookids.commentread.comment.adaptor.in.kafka.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.NicknameEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.ProfileImageEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.UserProfileEvent;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.in.CommentDeleteUseCase;
import lookids.commentread.comment.application.port.in.CommentReadCreateUseCase;
import lookids.commentread.comment.application.port.in.UserProfileUpdateUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumerController {

	private final CommentReadCreateUseCase commentReadCreateUseCase;

	private final UserProfileUpdateUseCase userProfileUpdateUseCase;

	private final CommentDeleteUseCase commentDeleteUseCase;

	private final CommentReadDtoMapper commentReadDtoMapper;

	private final ConcurrentHashMap<String, CompletableFuture<CommentEvent>> commentEventFutureMap = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, CompletableFuture<UserProfileEvent>> userProfileEventFutureMap = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, CompletableFuture<ReplyEvent>> replyCommentEventFutureMap = new ConcurrentHashMap<>(); // 대댓글을 위한 맵 추가

	@KafkaListener(topics = "userprofile-nickname-update", groupId = "comment-read-group", containerFactory = "nicknameEventListenerContainerFactory")
	public void consumeNicknameEvent(NicknameEvent nicknameEvent) {
		userProfileUpdateUseCase.updateNickname(commentReadDtoMapper.toNicknameDto(nicknameEvent));
	}

	@KafkaListener(topics = "userprofile-image-update", groupId = "comment-read-group", containerFactory = "profileImageEventListenerContainerFactory")
	public void consumeNicknameEvent(ProfileImageEvent profileImageEvent) {
		userProfileUpdateUseCase.updateProfileImage(commentReadDtoMapper.toProfileImageDto(profileImageEvent));
	}

	@KafkaListener(topics = "comment-create", groupId = "comment-read-group", containerFactory = "commentEventListenerContainerFactory")
	public void consumeCommentEvent(CommentEvent commentEvent) {
		String userUuid = commentEvent.getUuid();
		CompletableFuture<CommentEvent> feedEventFuture = commentEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		// CompletableFuture가 완료되었음을 나타냅니다.
		feedEventFuture.complete(commentEvent);
		log.info("consumeFeedEvent: {}", commentEvent);

		checkAndCreateCommentEventListener(userUuid);
	}

	@KafkaListener(topics = "comment-create-join-userprofile", groupId = "comment-read-group", containerFactory = "userProfileEventListenerContainerFactory")
	public void consumeCommentJoinEvent(UserProfileEvent userProfileEvent) {
		String userUuid = userProfileEvent.getUuid();
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		userProfileEventFuture.complete(userProfileEvent);
		log.info("userProfileEvent: {}", userProfileEvent);

		checkAndCreateCommentEventListener(userUuid);
	}

	// 대댓글을 처리하는 리스너
	@KafkaListener(topics = "comment-reply-create", groupId = "comment-read-group", containerFactory = "replyEventListenerContainerFactory")
	public void consumeReplyEvent(ReplyEvent replyEvent) {
		String userUuid = replyEvent.getUuid();
		CompletableFuture<ReplyEvent> replyEventFuture = replyCommentEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		replyEventFuture.complete(replyEvent);
		log.info("consumeReplyCommentEvent: {}", replyEvent);

		checkAndCreateReplyEventListener(userUuid);
	}

	@KafkaListener(topics = "comment-delete", groupId = "comment-read-group", containerFactory = "commentEventListenerContainerFactory")
	public void consumeCommentDeleteEvent(CommentEvent commentEvent) {
		log.info("commentEvent: {}", commentEvent);
		commentDeleteUseCase.deleteComment(commentReadDtoMapper.toCommentDeleteDto(commentEvent));
	}

	@KafkaListener(topics = "comment-reply-delete", groupId = "comment-read-group", containerFactory = "replyEventListenerContainerFactory")
	public void consumeReplyDeleteEvent(ReplyEvent replyEvent) {
		log.info("replyEvent: {}", replyEvent);
		commentDeleteUseCase.deleteReply(commentReadDtoMapper.toReplyDeleteDto(replyEvent));
	}

	@KafkaListener(topics = "comment-reply-create-join-userprofile", groupId = "comment-read-group", containerFactory = "userProfileEventListenerContainerFactory")
	public void consumeReplyJoinEvent(UserProfileEvent userProfileEvent) {
		String userUuid = userProfileEvent.getUuid();
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		userProfileEventFuture.complete(userProfileEvent);
		log.info("createUserProfile: {}", userProfileEvent);

		checkAndCreateReplyEventListener(userUuid);
	}

	private void checkAndCreateCommentEventListener(String userUuid) {
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.get(userUuid);
		CompletableFuture<CommentEvent> commentEventFuture = commentEventFutureMap.get(userUuid);

		if (userProfileEventFuture != null && commentEventFuture != null) {
			userProfileEventFuture.thenCombine(commentEventFuture, (userProfileEvent, commentEvent) -> {
				// 부모 댓글 처리
				commentReadCreateUseCase.createCommentRead(
					commentReadDtoMapper.toCommentCreateEventDto(commentEvent, userProfileEvent));

				// 작업이 완료되면 `Map`에서 해당 키 제거
				commentEventFutureMap.remove(userUuid);
				userProfileEventFutureMap.remove(userUuid);

				return null;
			});
		}
	}

	private void checkAndCreateReplyEventListener(String userUuid) {
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.get(userUuid);
		CompletableFuture<ReplyEvent> replyEventFuture = replyCommentEventFutureMap.get(userUuid); // 대댓글 처리

		if (userProfileEventFuture != null && replyEventFuture != null) {
			userProfileEventFuture.thenCombine(replyEventFuture, (userProfileEvent, replyEvent) -> {
				// 대댓글 처리
				commentReadCreateUseCase.createReplyRead(
					commentReadDtoMapper.toReplyCreateEventDto(replyEvent, userProfileEvent));

				// 작업이 완료되면 `Map`에서 해당 키 제거
				replyCommentEventFutureMap.remove(userUuid);
				userProfileEventFutureMap.remove(userUuid);

				return null;
			});
		}
	}
}