package lookids.commentread.comment.application.port.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentCreateEventDto {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private String parentCommentCode;
	private Instant createdAt;
	private String nickname;
	private String image;

	@Builder
	public CommentCreateEventDto(String commentCode, String feedCode, String userUuid, String content,
		String parentCommentCode, LocalDateTime createdAt, String nickname, String image) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.parentCommentCode = parentCommentCode;
		this.createdAt = createdAt.atZone(ZoneId.of("UTC")).toInstant();
		this.nickname = nickname;
		this.image = image;
	}
}
