package lookids.commentread.comment.adaptor.in.kafka.event;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
public class ProfileImageEvent {
	private String uuid;
	private String image;

	@Builder
	public ProfileImageEvent(String uuid, String image) {
		this.uuid = uuid;
		this.image = image;
	}
}
