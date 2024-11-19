package lookids.commentread.comment.adaptor.in.kafka.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo;
import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;

@Component
public class CommentKafkaVoMapper {

	public CommentCreateEventDto toCommentCreateEventDto(CommentEventVo commentEventVo) {
		return CommentCreateEventDto.builder()
			.commentCode(commentEventVo.getCommentCode())
			.feedCode(commentEventVo.getFeedCode())
			.userUuid(commentEventVo.getUserUuid())
			.content(commentEventVo.getContent())
			.parentCommentCode(commentEventVo.getParentCommentCode())
			.createdAt(commentEventVo.getCreatedAt())
			.nickname(commentEventVo.getNickname())
			.tag(commentEventVo.getTag())
			.image(commentEventVo.getImage())
			.build();
	}

	public CommentEvent toCommentEvent(CommentEventVo commentEvent) {
		return CommentEvent.builder()
			.commentCode(commentEvent.getCommentCode())
			.feedCode(commentEvent.getFeedCode())
			.userUuid(commentEvent.getUserUuid())
			.content(commentEvent.getContent())
			.createdAt(commentEvent.getCreatedAt())
			.parentCommentCode(commentEvent.getParentCommentCode())
			.build();
	}
}
