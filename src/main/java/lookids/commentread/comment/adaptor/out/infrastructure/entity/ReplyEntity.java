package lookids.commentread.comment.adaptor.out.infrastructure.entity;

import java.time.Instant;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ReplyEntity {

	private String commentCode;
	private String userUuid;
	private String nickname;
	private String profileImg;
	private String content;
	private Instant createdAt;

	@Builder
	public ReplyEntity(String commentCode, String userUuid, String nickname, String profileImg, String content,
		Instant createdAt) {
		this.commentCode = commentCode;
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.content = content;
		this.createdAt = createdAt;
	}
}
