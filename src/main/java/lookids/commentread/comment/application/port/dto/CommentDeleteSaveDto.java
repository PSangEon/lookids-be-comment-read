package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDeleteSaveDto {
	private String commentCode;
	private String feedCode;
	private Integer totalToDelete;

	@Builder
	public CommentDeleteSaveDto(String commentCode, String feedCode, Integer totalToDelete) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.totalToDelete = totalToDelete;
	}
}
