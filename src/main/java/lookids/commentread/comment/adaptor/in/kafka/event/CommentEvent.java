package lookids.commentread.comment.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class CommentEvent {

	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public CommentEvent(String commentCode, String feedCode, String userUuid, String content, LocalDateTime createdAt,
		String parentCommentCode) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
	}

}
