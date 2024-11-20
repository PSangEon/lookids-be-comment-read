package lookids.commentread.comment.application.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.port.dto.CommentListSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.domain.model.CommentForList;
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
			.parentCommentCode(commentForRead.getParentCommentCode())
			.nickname(commentForRead.getNickname())
			.image(commentForRead.getImage())
			.build();
	}

	public CommentListSaveDto toCommentListSaveDto(CommentForList commentForList) {
		return CommentListSaveDto.builder()
			.commentCode(commentForList.getCommentCode())
			.feedCode(commentForList.getFeedCode())
			.build();
	}

	public CommentReadResponseDto toCommentReadResponseDto(CommentReadEntity commentReadEntity) {
		return CommentReadResponseDto.builder()
			.commentCode(commentReadEntity.getCommentCode())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.parentCommentCode(commentReadEntity.getParentCommentCode())
			.userUuid(commentReadEntity.getUserUuid())
			.nickname(commentReadEntity.getNickname())
			.image(commentReadEntity.getProfileImg())
			.build();
	}
}
