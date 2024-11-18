package lookids.commetread.comment.domain.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Comment {
	private Long id;
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private LocalDateTime createdAt;
	private String parentCommentCode;
	private boolean commentStatus;
}
