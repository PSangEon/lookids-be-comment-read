package lookids.commentread.comment.adaptor.out.infrastructure.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadUpdateDto;
import lookids.commentread.comment.domain.model.CommentForRead;

@Slf4j
@Component
public class CommentEntityMapper {

	public CommentReadEntity toEntity(CommentReadSaveDto commentReadSaveDto) {
		return CommentReadEntity.builder()
			.commentCode(commentReadSaveDto.getCommentCode())
			.feedCode(commentReadSaveDto.getFeedCode())
			.userUuid(commentReadSaveDto.getUserUuid())
			.content(commentReadSaveDto.getContent())
			.createdAt(commentReadSaveDto.getCreatedAt())
			.nickname(commentReadSaveDto.getNickname())
			.tag(commentReadSaveDto.getTag())
			.profileImg(commentReadSaveDto.getImage())
			.replyList(commentReadSaveDto.getReplyForReadList())
			.build();
	}

	public CommentReadEntity toUpdateEntity(CommentReadUpdateDto commentReadUpdateDto) {
		return CommentReadEntity.builder()
			.id(commentReadUpdateDto.getId())
			.commentCode(commentReadUpdateDto.getCommentCode())
			.feedCode(commentReadUpdateDto.getFeedCode())
			.userUuid(commentReadUpdateDto.getUserUuid())
			.content(commentReadUpdateDto.getContent())
			.createdAt(commentReadUpdateDto.getCreatedAt())
			.nickname(commentReadUpdateDto.getNickname())
			.tag(commentReadUpdateDto.getTag())
			.profileImg(commentReadUpdateDto.getImage())
			.replyList(commentReadUpdateDto.getReplyForReadList())
			.build();
	}

	public CommentForRead toDomain(CommentReadEntity commentReadEntity) {
		return CommentForRead.builder()
			.id(commentReadEntity.getId())
			.commentCode(commentReadEntity.getCommentCode())
			.feedCode(commentReadEntity.getFeedCode())
			.userUuid(commentReadEntity.getUserUuid())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.nickname(commentReadEntity.getNickname())
			.tag(commentReadEntity.getTag())
			.image(commentReadEntity.getProfileImg())
			.replyForReadList(commentReadEntity.getReplyList())
			.build();
	}

	public Page<CommentForRead> toDomainPage(Page<CommentReadEntity> commentReadEntities) {
		// Page의 내용을 변환
		return commentReadEntities.map(commentReadEntity -> CommentForRead.builder()
			.id(commentReadEntity.getId())
			.commentCode(commentReadEntity.getCommentCode())
			.feedCode(commentReadEntity.getFeedCode())
			.userUuid(commentReadEntity.getUserUuid())
			.content(commentReadEntity.getContent())
			.createdAt(commentReadEntity.getCreatedAt())
			.nickname(commentReadEntity.getNickname())
			.tag(commentReadEntity.getTag())
			.image(commentReadEntity.getProfileImg())
			.build());
	}
}
