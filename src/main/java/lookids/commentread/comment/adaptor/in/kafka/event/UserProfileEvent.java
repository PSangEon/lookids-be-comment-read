package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UserProfileEvent {
	private String userUuid;
	private String nickname;
	private String tag;
	private String image;

	@Builder
	public UserProfileEvent(String userUuid, String nickname, String tag, String image) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

}
