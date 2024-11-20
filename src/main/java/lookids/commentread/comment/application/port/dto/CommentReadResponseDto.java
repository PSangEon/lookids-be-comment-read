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
	private String image;
	private String content;
	private String parentCommentCode;
	private Instant createdAt;

	@Builder
	public CommentReadResponseDto(String commentCode, String userUuid, String content, String parentCommentCode,
		Instant createdAt, String nickname, String image) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.content = content;
		this.parentCommentCode = parentCommentCode;
		this.createdAt = createdAt.atZone(ZoneId.of("UTC")).toInstant();
		this.nickname = nickname;
		this.image = image;
	}
}
