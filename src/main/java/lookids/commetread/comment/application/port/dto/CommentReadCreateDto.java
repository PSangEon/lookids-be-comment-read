package lookids.commetread.comment.application.port.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentReadCreateDto {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private String parentCommentCode;
	private Boolean commentStatus;
	private Instant createdAt;

	@Builder
	public CommentReadCreateDto(String commentCode, String feedCode, String userUuid, String content,
		String parentCommentCode, Boolean commentStatus, LocalDateTime createdAt) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.parentCommentCode = parentCommentCode;
		this.commentStatus = commentStatus;
		this.createdAt = createdAt.atZone(ZoneId.of("UTC")).toInstant();
	}
}
