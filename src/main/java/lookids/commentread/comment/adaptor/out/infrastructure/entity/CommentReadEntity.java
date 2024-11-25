package lookids.commentread.comment.adaptor.out.infrastructure.entity;

import java.time.Instant;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.commentread.comment.domain.model.ReplyForRead;

@Getter
@NoArgsConstructor
@Document(collection = "comment_entity")
public class CommentReadEntity {

	@Id
	private String id;

	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String nickname;
	private String profileImg;
	private String content;
	private Instant createdAt;

	private List<ReplyForRead> replyList;

	@LastModifiedDate
	private Instant updatedAt;

	@Builder
	public CommentReadEntity(String id, String commentCode, String feedCode, String userUuid, String nickname,
		String profileImg, String content, Instant createdAt, List<ReplyForRead> replyList) {
		this.id = id;
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.profileImg = profileImg;
		this.content = content;
		this.createdAt = createdAt;
		this.replyList = replyList;
	}
}
