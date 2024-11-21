package lookids.commentread.comment.application.port.in;

import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.comment.application.port.dto.ReplyCreateEventDto;

public interface CommentReadCreateUseCase {

	void createCommentRead(CommentCreateEventDto commentCreateEventDto);

	void createReplyRead(ReplyCreateEventDto replyCreateEventDto);
}
