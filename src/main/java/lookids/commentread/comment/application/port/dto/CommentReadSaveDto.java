package lookids.commentread.comment.application.port.dto;

import java.time.Instant;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.commentread.comment.domain.model.ReplyForRead;

@Getter
@NoArgsConstructor
public class CommentReadSaveDto {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private Instant createdAt;
	private String nickname;
	private String tag;
	private String image;
	private Integer replyCount;
	private List<ReplyForRead> replyForReadList;

	@Builder
	public CommentReadSaveDto(String commentCode, String feedCode, String userUuid, String content, Instant createdAt,
		String nickname, String image, List<ReplyForRead> replyForReadList, String tag, Integer replyCount) {
		this.commentCode = commentCode;
		this.feedCode = feedCode;
		this.userUuid = userUuid;
		this.content = content;
		this.createdAt = createdAt;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
		this.replyForReadList = replyForReadList;
		this.replyCount = replyCount;
	}
}
