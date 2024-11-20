package lookids.commentread.comment.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.port.dto.CommentListSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;

public interface CommentRepositoryPort {

	void createComment(CommentReadSaveDto commentReadSaveDto);

	void updateCommentList(CommentListSaveDto commentListSaveDto);

	Page<CommentReadEntity> readRelyList(String parentCommentCode, Pageable pageable);

	Page<CommentReadEntity> readCommentList(String feedCode, Pageable pageable);
}
