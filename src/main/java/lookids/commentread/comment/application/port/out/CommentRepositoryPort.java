package lookids.commentread.comment.application.port.out;

import lookids.commentread.comment.application.port.dto.CommentListSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;

public interface CommentRepositoryPort {

	void createComment(CommentReadSaveDto commentReadSaveDto);

	void updateCommentList(CommentListSaveDto commentListSaveDto);
}
