package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCountResponseDto {

	private String feedCode;
	private Integer commentCount;

	@Builder
	public CommentCountResponseDto(String feedCode, Integer commentCount) {
		this.feedCode = feedCode;
		this.commentCount = commentCount;
	}
}
