package lookids.commentread.comment.domain.model;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyForRead {
	private String commentCode;
	private String userUuid;
	private String nickname;
	private String tag;
	private String profileImg;
	private String content;
	private Instant createdAt;

	@Builder
	public ReplyForRead(String commentCode, String userUuid, String nickname, String profileImg, String content,
		Instant createdAt, String tag) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tag = tag;
		this.profileImg = profileImg;
		this.content = content;
		this.createdAt = createdAt;
	}
}
