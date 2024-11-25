package lookids.commentread.comment.application.port.dto;

import java.time.Instant;
import java.time.ZoneId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReadResponseDto {
	private String commentCode;
	private String userUuid;
	private String nickname;
	private String tag;
	private String image;
	private String content;
	private Instant createdAt;

	@Builder
	public CommentReadResponseDto(String commentCode, String userUuid, String content, Instant createdAt,
		String nickname, String image, String tag) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt.atZone(ZoneId.of("UTC")).toInstant();
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}
}
