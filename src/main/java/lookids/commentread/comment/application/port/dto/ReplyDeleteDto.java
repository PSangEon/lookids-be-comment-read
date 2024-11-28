package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyDeleteDto {
	private String commentCode;
	private String parentCommentCode;

	@Builder
	public ReplyDeleteDto(String commentCode, String parentCommentCode) {
		this.commentCode = commentCode;
		this.parentCommentCode = parentCommentCode;
	}
}
