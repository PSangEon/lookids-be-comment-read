package lookids.commentread.comment.application.port.dto;

import java.time.Instant;

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
	private Integer replyCount;
	private Instant createdAt;

	@Builder
	public CommentReadResponseDto(String commentCode, String userUuid, String content, Instant createdAt,
		String nickname, String image, String tag, Integer replyCount) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
		this.replyCount = replyCount;
	}
}
