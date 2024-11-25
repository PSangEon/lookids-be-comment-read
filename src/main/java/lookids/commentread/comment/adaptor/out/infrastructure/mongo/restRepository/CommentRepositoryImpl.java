package lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository;

import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.client.model.UpdateOptions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.adaptor.out.infrastructure.mapper.CommentEntityMapper;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadUpdateDto;
import lookids.commentread.comment.application.port.dto.UserProfileImageDto;
import lookids.commentread.comment.application.port.dto.UserProfileNicknameDto;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;
import lookids.commentread.comment.domain.model.CommentForRead;

@Slf4j
@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryPort {

	private final MongoTemplate mongoTemplate;
	private final CommentEntityMapper commentEntityMapper;
	private final CommentReadMongoRepository commentReadMongoRepository;

	@Override
	public void createComment(CommentReadSaveDto commentReadSaveDto) {
		mongoTemplate.save(commentEntityMapper.toEntity(commentReadSaveDto));
	}

	@Override
	public void updateComment(CommentReadUpdateDto commentReadUpdateDto) {
		mongoTemplate.save(commentEntityMapper.toUpdateEntity(commentReadUpdateDto));
	}

	@Override
	public Page<CommentForRead> readCommentList(String feedCode, Pageable pageable) {
		// Query query = new Query();
		// query.addCriteria(Criteria.where("feedCode").is(feedCode));
		//
		// // 페이지네이션 적용
		// long total = mongoTemplate.count(query, CommentReadEntity.class, "comment_entity");
		// query.with(pageable);
		//
		// List<CommentReadEntity> commentList = mongoTemplate.find(query, CommentReadEntity.class, "comment_entity");
		//
		// return new PageImpl<>(commentList, pageable, total);
		return commentEntityMapper.toDomainPage(commentReadMongoRepository.findByFeedCode(feedCode, pageable));
	}

	@Override
	public Page<CommentForRead> readRelyList(String parentCommentCode, Pageable pageable) {
		return commentEntityMapper.toDomainPage(commentReadMongoRepository.findByFeedCode(parentCommentCode, pageable));
	}

	@Override
	public CommentForRead readComment(String commentCode) {

		// Optional에서 값을 꺼내고, 값이 없을 경우 예외 처리
		CommentReadEntity commentReadEntity;
		try {
			commentReadEntity = commentReadMongoRepository.findByCommentCode(commentCode)
				.orElseThrow(() -> new IllegalArgumentException("Comment not found for code: " + commentCode));
		} catch (Exception e) {
			throw e;  // 예외를 다시 던지거나 적절히 처리
		}

		// commentReadEntity를 사용하여 도메인 객체로 변환
		return commentEntityMapper.toDomain(commentReadEntity);
	}

	@Override
	public void updateUserNickname(UserProfileNicknameDto userProfileNicknameDto) {
		Update update = new Update().set("nickname", userProfileNicknameDto.getNickname())
			.set("tag", userProfileNicknameDto.getTag());
		updateFieldsInCommentsAndReplies(userProfileNicknameDto.getUserUuid(), update);
	}

	@Override
	public void updateUserImage(UserProfileImageDto userProfileImageDto) {
		Update update = new Update().set("profileImg", userProfileImageDto.getImage());
		updateFieldsInCommentsAndReplies(userProfileImageDto.getUserUuid(), update);
	}

	private void updateFieldsInCommentsAndReplies(String userUuid, Update update) {
		// 댓글 갱신
		Query commentQuery = new Query(Criteria.where("userUuid").is(userUuid));
		mongoTemplate.updateMulti(commentQuery, update, "comment_entity");

		// 대댓글 갱신
		Query replyQuery = new Query(Criteria.where("replyList.userUuid").is(userUuid));

		// 배열 필터 설정
		UpdateOptions options = new UpdateOptions().arrayFilters(List.of(new Document("elem.userUuid", userUuid)));

		mongoTemplate.getCollection("comment_entity")
			.updateMany(replyQuery.getQueryObject(), update.getUpdateObject(), options);
	}
}
