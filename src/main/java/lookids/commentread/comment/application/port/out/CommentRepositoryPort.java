package lookids.commentread.comment.application.port.out;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lookids.commentread.comment.application.port.dto.CommentDeleteSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadUpdateDto;
import lookids.commentread.comment.application.port.dto.ReplyDeleteDto;
import lookids.commentread.comment.application.port.dto.UserProfileUpdateSaveDto;
import lookids.commentread.comment.domain.model.CommentForRead;

public interface CommentRepositoryPort {

	void createComment(CommentReadSaveDto commentReadSaveDto);

	void updateComment(CommentReadUpdateDto commentReadUpdateDto);

	void updateUserProfile(UserProfileUpdateSaveDto userProfileUpdateSaveDto);

	Page<CommentForRead> readCommentList(String feedCode, Pageable pageable);

	CommentForRead readComment(String commentCode);

	void deleteComment(CommentDeleteSaveDto commentDeleteSaveDto);

	void deleteReply(ReplyDeleteDto replyDeleteDto);
}