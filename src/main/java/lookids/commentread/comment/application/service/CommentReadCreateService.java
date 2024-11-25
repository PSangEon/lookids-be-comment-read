package lookids.commentread.comment.application.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.dto.CommentCreateEventDto;
import lookids.commentread.comment.application.port.dto.ReplyCreateEventDto;
import lookids.commentread.comment.application.port.in.CommentReadCreateUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForRead;
import lookids.commentread.comment.domain.model.ReplyForRead;

@Slf4j
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
			.replyForReadList(new ArrayList<>())
			.build();
		commentRepositoryPort.createComment(commentReadDtoMapper.toCommentReadSaveDto(commentForRead));
	}

	@Override
	public void createReplyRead(ReplyCreateEventDto commentCreateEventDto) {
		log.info("commentCreateEventDto:{}", commentCreateEventDto);
		CommentForRead commentForRead = commentRepositoryPort.readComment(commentCreateEventDto.getParentCommentCode());
		log.info("commentForRead:{}", commentForRead);

		ReplyForRead replyForRead = ReplyForRead.builder()
			.commentCode(commentCreateEventDto.getCommentCode())
			.content(commentCreateEventDto.getContent())
			.createdAt(commentCreateEventDto.getCreatedAt())
			.userUuid(commentCreateEventDto.getUserUuid())
			.nickname(commentCreateEventDto.getNickname())
			.profileImg(commentCreateEventDto.getImage())
			.build();
		commentForRead.getReplyForReadList().add(replyForRead);
		log.info("reply:{}", commentForRead.getReplyForReadList());
		commentRepositoryPort.updateComment(commentReadDtoMapper.toCommentReadUpdateDto(commentForRead));
	}
}
