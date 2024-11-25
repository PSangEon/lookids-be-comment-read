package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileImageDto {
	private String userUuid;
	private String image;

	@Builder
	public UserProfileImageDto(String userUuid, String image) {
		this.userUuid = userUuid;
		this.image = image;
	}
}
