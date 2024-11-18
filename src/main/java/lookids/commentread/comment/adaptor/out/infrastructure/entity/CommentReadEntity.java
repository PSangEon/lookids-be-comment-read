package lookids.commentread.comment.adaptor.out.infrastructure.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document(collection = "comment_entity")
public class CommentReadEntity {

	@Id
	private String id;

	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private String parentCommentCode;
	private Boolean commentStatus;
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

	@Builder
	public CommentReadEntity(String commentCode, String feedCode, String userUuid, String content, Instant createdAt,
		String parentCommentCode, Boolean commentStatus) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
		this.commentStatus = commentStatus;
	}
}
