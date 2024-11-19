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
	private String parentCommentCode;
	private Instant createdAt;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public CommentReadSaveDto(String commentCode, String feedCode, String userUuid, String content,
		String parentCommentCode, Instant createdAt, String nickname, String tag, String image) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.parentCommentCode = parentCommentCode;
		this.createdAt = createdAt;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}
}
