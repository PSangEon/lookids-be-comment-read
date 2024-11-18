package lookids.commentread.comment.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentForList {
	private String commentCode;
	private String feedCode;

	@Builder
	public CommentForList(String commentCode, String feedCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
	}
}
