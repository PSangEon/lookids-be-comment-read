package lookids.commetread.comment.application.port.out;

import lookids.commetread.comment.application.port.dto.CommentReadCreateDto;

public interface CommentRepositoryPort {

	void createComment(CommentReadCreateDto commentReadCreateDto);
}
