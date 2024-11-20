package lookids.commentread.comment.application.port.in;

import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.common.dto.PageResponseDto;

public interface CommentReadUseCase {

	void createCommentRead(CommentCreateEventDto commentCreateEventDto);

	void updateCommentList(CommentCreateEventDto commentCreateEventDto);

	PageResponseDto readCommentList(String feedCode, int page, int size);

	PageResponseDto readReplyList(String parentCommentCode, int page, int size);

}
