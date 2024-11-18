package lookids.commetread.comment.adaptor.out.infrasture.entity;

import java.time.Instant;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "comment_entity")
public class CommentEntity {

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

}
