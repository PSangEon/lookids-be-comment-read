package lookids.commentread.comment.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.dto.CommentDeleteDto;
import lookids.commentread.comment.application.port.dto.ReplyDeleteDto;
import lookids.commentread.comment.application.port.in.CommentDeleteUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForRead;

@RequiredArgsConstructor
@Service
public class CommentDeleteService implements CommentDeleteUseCase {

	private final CommentRepositoryPort commentRepositoryPort;

	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public void deleteComment(CommentDeleteDto commentDeleteDto) {
		// 댓글 조회
		CommentForRead comment = commentRepositoryPort.readComment(commentDeleteDto.getCommentCode());

		if (comment != null) {
			int totalToDelete = 1 + (comment.getReplyForReadList() != null ? comment.getReplyForReadList().size() : 0);

			// 댓글 삭제
			commentRepositoryPort.deleteComment(
				commentReadDtoMapper.toCommentDeleteSaveDto(commentDeleteDto, totalToDelete));
		}
	}

	@Override
	public void deleteReply(ReplyDeleteDto replyDeleteDto) {
		commentRepositoryPort.deleteReply(replyDeleteDto);
	}
}
