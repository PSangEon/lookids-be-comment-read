package lookids.commentread.comment.application.port.in;

import lookids.commentread.comment.application.port.dto.UserProfileImageDto;
import lookids.commentread.comment.application.port.dto.UserProfileNicknameDto;

public interface UserProfileUpdateUseCase {

	void updateNickname(UserProfileNicknameDto userProfileNicknameDto);

	void updateProfileImage(UserProfileImageDto userProfileImageDto);
}
