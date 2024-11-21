package lookids.commentread.comment.application.port.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReadSaveDto {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private Instant createdAt;
	private String nickname;
	private String image;

	@Builder
	public CommentReadSaveDto(String commentCode, String feedCode, String userUuid, String content, Instant createdAt,
		String nickname, String image) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.nickname = nickname;
		this.image = image;
	}
}
