package lookids.commentread.comment.domain.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentForRead {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private String parentCommentCode;
	private Instant createdAt;
	private String updatedAt;

	@Builder
	public CommentForRead(String commentCode, String feedCode, String userUuid, String content, Instant createdAt,
		String parentCommentCode, String updatedAt) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
		this.updatedAt = updatedAt;
	}

}
