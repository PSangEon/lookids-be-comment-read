package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserProfileEvent {
	private String uuid;
	private String nickname;
	private String image;
	private String tag;

}
