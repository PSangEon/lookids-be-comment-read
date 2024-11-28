package lookids.commentread.comment.application.port.in;

import lookids.commentread.comment.application.port.dto.CommentDeleteDto;
import lookids.commentread.comment.application.port.dto.ReplyDeleteDto;

public interface CommentDeleteUseCase {
	void deleteComment(CommentDeleteDto commentDeleteDto);

	void deleteReply(ReplyDeleteDto replyDeleteDto);

}
