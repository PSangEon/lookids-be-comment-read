package lookids.commentread.comment.application.port.dto;

import org.springframework.data.mongodb.core.query.Update;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserProfileUpdateSaveDto {
	private String userUuid;
	private Update update;

	@Builder
	public UserProfileUpdateSaveDto(String userUuid, Update update) {
		this.userUuid = userUuid;
		this.update = update;
	}
}
