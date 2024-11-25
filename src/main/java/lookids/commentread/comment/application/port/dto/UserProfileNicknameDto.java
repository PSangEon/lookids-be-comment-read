package lookids.commentread.comment.application.port.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileNicknameDto {
	private String userUuid;
	private String nickname;
	private String tag;

	@Builder
	public UserProfileNicknameDto(String userUuid, String nickname, String tag) {
		this.userUuid = userUuid;
		this.nickname = nickname;
		this.tag = tag;
	}
}
