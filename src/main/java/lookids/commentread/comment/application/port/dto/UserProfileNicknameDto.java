package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileNicknameDto {
	private String userUuid;
	private String nickname;

	@Builder
	public UserProfileNicknameDto(String userUuid, String nickname) {
		this.userUuid = userUuid;
		this.nickname = nickname;
	}
}
