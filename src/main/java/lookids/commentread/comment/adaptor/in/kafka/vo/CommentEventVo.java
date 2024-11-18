package lookids.commentread.comment.adaptor.in.kafka.vo;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommentEventVo {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
}
