package lookids.commentread.comment.adaptor.in.kafka.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.UserProfileEvent;
import lookids.commentread.comment.adaptor.in.kafka.mapper.CommentKafkaVoMapper;
import lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo;
import lookids.commentread.comment.application.port.in.CommentReadUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumerController {

	private final CommentReadUseCase commentReadUseCase;

	private final CommentKafkaVoMapper commentKafkaVoMapper;

	private final ConcurrentHashMap<String, CompletableFuture<CommentEvent>> commentEventFutureMap = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<String, CompletableFuture<UserProfileEvent>> userProfileEventFutureMap = new ConcurrentHashMap<>();

	@KafkaListener(topics = "comment-create", groupId = "comment-read-group", containerFactory = "commentEventListenerContainerFactory")
	public void consumeCommentEvent(CommentEvent commentEvent) {
		String userUuid = commentEvent.getUserUuid();
		CompletableFuture<CommentEvent> feedEventFuture = commentEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		// CompletableFuture가 완료되었음을 나타냅니다.
		feedEventFuture.complete(commentEvent);
		log.info("consumeFeedEvent: {}", commentEvent);

		checkAndCreateFeedEventListener(userUuid);
	}

	@KafkaListener(topics = "comment-create-join-userprofile", groupId = "comment-read-group", containerFactory = "userProfileEventListenerContainerFactory")
	public void createUserProfile(UserProfileEvent userProfileEvent) {
		String userUuid = userProfileEvent.getUuid();
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.computeIfAbsent(userUuid,
			key -> new CompletableFuture<>());

		// CompletableFuture가 완료되었음을 나타냅니다.
		userProfileEventFuture.complete(userProfileEvent);
		log.info("createUserProfile: {}", userProfileEvent);

		checkAndCreateFeedEventListener(userUuid);
	}

	private void checkAndCreateFeedEventListener(String userUuid) {
		CompletableFuture<UserProfileEvent> userProfileEventFuture = userProfileEventFutureMap.get(userUuid);
		CompletableFuture<CommentEvent> feedEventFuture = commentEventFutureMap.get(userUuid);

		if (userProfileEventFuture != null && feedEventFuture != null) {
			userProfileEventFuture.thenCombine(feedEventFuture, (userProfileEvent, commentEvent) -> {
				commentReadUseCase.createCommentRead(commentKafkaVoMapper.toCommentCreateEventDto(
					CommentEventVo.builder()
						.commentCode(commentEvent.getCommentCode())
						.feedCode(commentEvent.getFeedCode())
						.content(commentEvent.getContent())
						.userUuid(commentEvent.getUserUuid())
						.createdAt(commentEvent.getCreatedAt())
						.parentCommentCode(commentEvent.getParentCommentCode())
						.nickname(userProfileEvent.getNickname())
						.image(userProfileEvent.getImage())
						.build()));

				// 작업이 완료되면 `Map`에서 해당 키 제거
				commentEventFutureMap.remove(userUuid);
				userProfileEventFutureMap.remove(userUuid);

				return null;
			});
		}
	}
}
