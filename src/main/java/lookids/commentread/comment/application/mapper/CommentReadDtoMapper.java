package lookids.commentread.comment.application.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.UserProfileEvent;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.dto.ReplyCreateEventDto;
import lookids.commentread.comment.domain.model.CommentForRead;

@Component
public class CommentReadDtoMapper {
	public CommentReadSaveDto toCommentReadSaveDto(CommentForRead commentForRead) {
		return CommentReadSaveDto.builder()
			.commentCode(commentForRead.getCommentCode())
			.feedCode(commentForRead.getFeedCode())
			.content(commentForRead.getContent())
			.userUuid(commentForRead.getUserUuid())
			.createdAt(commentForRead.getCreatedAt())
			.nickname(commentForRead.getNickname())
			.image(commentForRead.getImage())
			.build();
	}

	public CommentReadResponseDto toCommentReadResponseDto(CommentReadEntity commentReadEntity) {
		return CommentReadResponseDto.builder()
			.commentCode(commentReadEntity.getCommentCode())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.userUuid(commentReadEntity.getUserUuid())
			.nickname(commentReadEntity.getNickname())
			.image(commentReadEntity.getProfileImg())
			.build();
	}

	public CommentCreateEventDto toCommentCreateEventDto(CommentEvent commentEvent, UserProfileEvent userProfileEvent) {
		return CommentCreateEventDto.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.userUuid(commentEvent.getUserUuid())
			.content(commentEvent.getContent())
			.createdAt(commentEvent.getCreatedAt())
			.nickname(userProfileEvent.getNickname())
			.image(userProfileEvent.getImage())
			.build();
	}

	public ReplyCreateEventDto toReplyCreateEventDto(ReplyEvent replyEvent, UserProfileEvent userProfileEvent) {
		return ReplyCreateEventDto.builder()
			.commentCode(replyEvent.getCommentCode())
			.parentCommentCode(replyEvent.getParentCommentCode())
			.userUuid(replyEvent.getUserUuid())
			.content(replyEvent.getContent())
			.createdAt(replyEvent.getCreatedAt())
			.nickname(userProfileEvent.getNickname())
			.image(userProfileEvent.getImage())
			.build();
	}
}
