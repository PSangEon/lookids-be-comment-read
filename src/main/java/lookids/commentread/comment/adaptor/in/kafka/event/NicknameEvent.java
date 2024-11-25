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

	@Builder
	public NicknameEvent(String uuid, String nickname) {
		this.uuid = uuid;
		this.nickname = nickname;
	}
}
