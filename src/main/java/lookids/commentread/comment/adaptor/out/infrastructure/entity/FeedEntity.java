package lookids.commentread.comment.adaptor.out.infrastructure.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Document(collection = "feed_entity")
public class FeedEntity {
	@Id
	private String id;

	private String feedCode;
	private Integer totalCommentCount;

	@CreatedDate
	private Instant createdAt;

	@LastModifiedDate
	private Instant updatedAt;

	@Builder
	public FeedEntity(String feedCode, Integer totalCommentCount) {
		this.feedCode = feedCode;
		this.totalCommentCount = totalCommentCount;
	}
}
