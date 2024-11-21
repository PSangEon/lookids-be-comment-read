package lookids.commentread.comment.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.ReplyEntity;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.comment.application.port.dto.ReplyCreateEventDto;
import lookids.commentread.comment.application.port.in.CommentReadCreateUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForRead;
import lookids.commentread.common.entity.BaseResponseStatus;
import lookids.commentread.common.exception.BaseException;

@RequiredArgsConstructor
@Service
public class CommentReadCreateService implements CommentReadCreateUseCase {
	private final CommentRepositoryPort commentRepositoryPort;
	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public void createCommentRead(CommentCreateEventDto commentCreateEventDto) {
		CommentForRead commentForRead = CommentForRead.builder()
			.commentCode(commentCreateEventDto.getCommentCode())
			.feedCode(commentCreateEventDto.getFeedCode())
			.content(commentCreateEventDto.getContent())
			.createdAt(commentCreateEventDto.getCreatedAt())
			.userUuid(commentCreateEventDto.getUserUuid())
			.nickname(commentCreateEventDto.getNickname())
			.image(commentCreateEventDto.getImage())
			.build();
		commentRepositoryPort.createComment(commentReadDtoMapper.toCommentReadSaveDto(commentForRead));
	}

	@Override
	public void createReplyRead(ReplyCreateEventDto commentCreateEventDto) {
		CommentReadEntity commentReadEntity = commentRepositoryPort.readComment(
				commentCreateEventDto.getParentCommentCode())
			.orElseThrow(() -> new BaseException(BaseResponseStatus.NO_EXIST_DATA));

		ReplyEntity replyEntity = ReplyEntity.builder()
			.commentCode(commentCreateEventDto.getCommentCode())
			.content(commentCreateEventDto.getContent())
			.createdAt(commentCreateEventDto.getCreatedAt())
			.userUuid(commentCreateEventDto.getUserUuid())
			.nickname(commentCreateEventDto.getNickname())
			.profileImg(commentCreateEventDto.getImage())
			.build();
		commentReadEntity.getReplyList().add(replyEntity);
		//commentRepositoryPort.createComment(commentReadDtoMapper.toCommentReadSaveDto());
	}
}
