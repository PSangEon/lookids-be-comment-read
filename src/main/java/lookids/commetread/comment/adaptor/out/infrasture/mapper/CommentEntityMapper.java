package lookids.commetread.comment.adaptor.out.infrasture.mapper;

import java.time.Instant;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import lookids.commetread.comment.adaptor.out.infrasture.entity.CommentEntity;
import lookids.commetread.comment.application.port.dto.CommentReadCreateDto;

@Slf4j
@Component
public class CommentEntityMapper {
	private String commentCode;
	private String feedCode;
	private String userUuid;
	private String content;
	private String parentCommentCode;
	private Boolean commentStatus;
	private Instant createdAt;

	public CommentEntity toEntity(CommentReadCreateDto commentReadCreateDto) {
		return CommentEntity.builder()
			.commentCode(commentReadCreateDto.getCommentCode())
			.feedCode(commentReadCreateDto.getFeedCode())
			.userUuid(commentReadCreateDto.getUserUuid())
			.content(commentReadCreateDto.getContent())
			.parentCommentCode(commentReadCreateDto.getParentCommentCode())
			.commentStatus(commentReadCreateDto.getCommentStatus())
			.createdAt(commentReadCreateDto.getCreatedAt())
			.build();

	}
}
