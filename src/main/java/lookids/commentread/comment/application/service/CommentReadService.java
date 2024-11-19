package lookids.commentread.comment.application.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.comment.application.port.in.CommentReadUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForList;
import lookids.commentread.comment.domain.model.CommentForRead;

@RequiredArgsConstructor
@Service
public class CommentReadService implements CommentReadUseCase {
	private final CommentRepositoryPort commentRepositoryPort;
	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public void createCommentRead(CommentCreateEventDto commentCreateEventDto) {
		CommentForRead commentForRead = CommentForRead.builder()
			.commentCode(commentCreateEventDto.getCommentCode())
			.feedCode(commentCreateEventDto.getFeedCode())
			.content(commentCreateEventDto.getContent())
			.createdAt(commentCreateEventDto.getCreatedAt())
			.parentCommentCode(commentCreateEventDto.getParentCommentCode())
			.nickname(commentCreateEventDto.getNickname())
			.tag(commentCreateEventDto.getTag())
			.image(commentCreateEventDto.getImage())
			.build();
		commentRepositoryPort.createComment(commentReadDtoMapper.toCommentReadSaveDto(commentForRead));
	}

	@Override
	public void updateCommentList(CommentCreateEventDto commentCreateEventDto) {
		CommentForList commentForList = CommentForList.builder()
			.commentCode(commentCreateEventDto.getCommentCode())
			.feedCode(commentCreateEventDto.getFeedCode())
			.build();
		commentRepositoryPort.updateCommentList(commentReadDtoMapper.toCommentListSaveDto(commentForList));
	}
}
