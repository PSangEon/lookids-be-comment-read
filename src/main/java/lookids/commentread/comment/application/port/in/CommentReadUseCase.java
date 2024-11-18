package lookids.commentread.comment.application.port.in;

import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;

public interface CommentReadUseCase {

	void createCommentRead(CommentCreateEventDto commentCreateEventDto);

	void updateCommentList(CommentCreateEventDto commentCreateEventDto);

}
