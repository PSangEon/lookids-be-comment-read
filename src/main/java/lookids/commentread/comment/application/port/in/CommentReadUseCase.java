package lookids.commentread.comment.application.port.in;

import java.util.List;

import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.common.dto.PageResponseDto;

public interface CommentReadUseCase {

	PageResponseDto readCommentList(String feedCode, int page, int size);

	List<CommentReadResponseDto> readReplyList(String parentCommentCode);

}
