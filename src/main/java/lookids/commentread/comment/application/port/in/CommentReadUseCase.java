package lookids.commentread.comment.application.port.in;

import java.util.List;

import org.springframework.data.domain.Page;

import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;

public interface CommentReadUseCase {

	Page<CommentReadResponseDto> readCommentList(String feedCode, int page, int size);

	List<CommentReadResponseDto> readReplyList(String parentCommentCode);

}
