package lookids.commentread.comment.domain.model;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentForRead {
	private String id;
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private Instant createdAt;
	private String updatedAt;
	private String nickname;
	private String tag;
	private String image;
	private List<ReplyForRead> replyForReadList;

	@Builder
	public CommentForRead(String id, String commentCode, String feedCode, String userUuid, String content,
		Instant createdAt, String updatedAt, String nickname, String image, List<ReplyForRead> replyForReadList,
		String tag) {
		this.id = id;
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
		this.replyForReadList = replyForReadList;
	}

}
