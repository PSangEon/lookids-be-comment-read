package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class NicknameEvent {
	private String uuid;
	private String nickname;
	private String tag;

	@Builder
	public NicknameEvent(String uuid, String nickname, String tag) {
		this.uuid = uuid;
		this.nickname = nickname;
		this.tag = tag;
	}
}
