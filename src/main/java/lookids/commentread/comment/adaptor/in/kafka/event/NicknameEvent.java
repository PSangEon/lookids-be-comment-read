package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class NicknameEvent {
	private String uuid;
	private String nickname;
	private String tag;

}
