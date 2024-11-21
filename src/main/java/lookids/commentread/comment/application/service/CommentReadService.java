package lookids.commentread.comment.application.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.mapper.CommentReadDtoMapper;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.comment.application.port.in.CommentReadUseCase;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.common.dto.PageResponseDto;

@RequiredArgsConstructor
@Service
public class CommentReadService implements CommentReadUseCase {
	private final CommentRepositoryPort commentRepositoryPort;
	private final CommentReadDtoMapper commentReadDtoMapper;

	@Override
	public PageResponseDto readCommentList(String feedCode, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<CommentReadEntity> commentList = commentRepositoryPort.readCommentList(feedCode, pageable);

		List<CommentReadResponseDto> responseDtoList = commentList.stream()
			.map(commentReadDtoMapper::toCommentReadResponseDto) // 메서드 참조 사용
			.toList();

		return PageResponseDto.toDto(page, commentList.getTotalPages(), commentList.hasNext(), responseDtoList);
	}

	@Override
	public PageResponseDto readReplyList(String parentCommentCode, int page, int size) {
		Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
		Page<CommentReadEntity> commentList = commentRepositoryPort.readRelyList(parentCommentCode, pageable);

		List<CommentReadResponseDto> responseDtoList = commentList.stream()
			.map(commentReadDtoMapper::toCommentReadResponseDto) // 메서드 참조 사용
			.toList();

		return PageResponseDto.toDto(page, commentList.getTotalPages(), commentList.hasNext(), responseDtoList);
	}
}
