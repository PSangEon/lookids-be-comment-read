package lookids.commentread.comment.adaptor.in.kafka.event;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ReplyEvent {
	private String commentCode;
	private String feedCode;
	private String feedUuid;
	private String uuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
