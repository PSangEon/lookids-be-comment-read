package lookids.commentread.comment.application.port.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyCreateEventDto {
	private String commentCode;
	private String parentCommentCode;
	private String userUuid;
	private String content;
	private Instant createdAt;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public ReplyCreateEventDto(String commentCode, String parentCommentCode, String userUuid, String content,
		LocalDateTime createdAt, String nickname, String image, String tag) {
		this.commentCode = commentCode;
		this.parentCommentCode = parentCommentCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt.atZone(ZoneId.of("UTC")).toInstant();
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}
}
