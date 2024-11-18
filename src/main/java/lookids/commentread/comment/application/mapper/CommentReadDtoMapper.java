package lookids.commentread.comment.application.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.application.port.dto.CommentListSaveDto;
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
			.createdAt(commentForRead.getCreatedAt())
			.parentCommentCode(commentForRead.getParentCommentCode())
			.build();
	}

	public CommentListSaveDto toCommentListSaveDto(CommentForList commentForList) {
		return CommentListSaveDto.builder()
			.commentCode(commentForList.getCommentCode())
			.feedCode(commentForList.getFeedCode())
			.build();
	}
}
