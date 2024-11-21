package lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;
import lookids.commentread.comment.adaptor.out.infrastructure.mapper.CommentEntityMapper;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;

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
	public Page<CommentReadEntity> readCommentList(String feedCode, Pageable pageable) {
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
		return commentReadMongoRepository.findByFeedCode(feedCode, pageable);
	}

	@Override
	public Page<CommentReadEntity> readRelyList(String parentCommentCode, Pageable pageable) {
		return commentReadMongoRepository.findByParentCommentCode(parentCommentCode, pageable);
	}

	public Optional<CommentReadEntity> readComment(String commentCode) {
		return commentReadMongoRepository.findByCommentCode(commentCode);
	}
}
