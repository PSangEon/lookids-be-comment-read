package lookids.commentread.comment.application.port.in;

import lookids.commentread.common.dto.PageResponseDto;

public interface CommentReadUseCase {

	PageResponseDto readCommentList(String feedCode, int page, int size);

	PageResponseDto readReplyList(String parentCommentCode, int page, int size);

}
