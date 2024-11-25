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
	private String uuid;
	private String content;
	private LocalDateTime createdAt;

	@Builder
	public CommentEvent(String commentCode, String feedCode, String uuid, String content, LocalDateTime createdAt) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.uuid = uuid;
		this.content = content;
		this.createdAt = createdAt;
	}

}
