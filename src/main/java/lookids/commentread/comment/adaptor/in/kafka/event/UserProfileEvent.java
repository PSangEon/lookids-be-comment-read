package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class UserProfileEvent {
	private String uuid;
	private String nickname;
	private String image;
	private String tag;

	@Builder
	public UserProfileEvent(String uuid, String nickname, String image, String tag) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
		this.image = image;
	}

}
