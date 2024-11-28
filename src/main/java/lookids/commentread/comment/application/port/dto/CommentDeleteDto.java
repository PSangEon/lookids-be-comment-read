package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteDto {
	private String commentCode;
	private String feedCode;

	@Builder
	public CommentDeleteDto(String commentCode, String feedCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
	}
}
