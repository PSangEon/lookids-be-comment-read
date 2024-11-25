package lookids.commentread.comment.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.application.port.dto.UserProfileImageDto;
import lookids.commentread.comment.application.port.dto.UserProfileNicknameDto;
import lookids.commentread.comment.application.port.in.UserProfileUpdateUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;

@RequiredArgsConstructor
@Service
public class UserProfileUpdateService implements UserProfileUpdateUseCase {

	private final CommentRepositoryPort commentRepositoryPort;

	public void updateNickname(UserProfileNicknameDto userProfileNicknameDto) {
		commentRepositoryPort.updateUserNickname(userProfileNicknameDto);
	}

	public void updateProfileImage(UserProfileImageDto userProfileImageDto) {
		commentRepositoryPort.updateUserImage(userProfileImageDto);
	}
}
