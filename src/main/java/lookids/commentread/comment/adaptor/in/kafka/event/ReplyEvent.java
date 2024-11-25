package lookids.commentread.comment.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class ReplyEvent {
	private String commentCode;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;

	@Builder
	public ReplyEvent(String commentCode, String uuid, String content, LocalDateTime createdAt,
		String parentCommentCode) {
		this.commentCode = commentCode;
		this.uuid = uuid;
		this.content = content;
		this.createdAt = createdAt;
		this.parentCommentCode = parentCommentCode;
	}
}
