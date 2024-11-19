package lookids.commentread.comment.adaptor.out.infrastructure.mapper;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;

@Slf4j
@Component
public class CommentEntityMapper {

	public CommentReadEntity toEntity(CommentReadSaveDto commentReadSaveDto) {
		return CommentReadEntity.builder()
			.commentCode(commentReadSaveDto.getCommentCode())
			.feedCode(commentReadSaveDto.getFeedCode())
			.userUuid(commentReadSaveDto.getUserUuid())
			.content(commentReadSaveDto.getContent())
			.parentCommentCode(commentReadSaveDto.getParentCommentCode())
			.createdAt(commentReadSaveDto.getCreatedAt())
			.nickname(commentReadSaveDto.getNickname())
			.tag(commentReadSaveDto.getTag())
			.profileImg(commentReadSaveDto.getImage())
			.build();

	}
}
