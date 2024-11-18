package lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lookids.commentread.comment.adaptor.out.infrastructure.mapper.CommentEntityMapper;
import lookids.commentread.comment.application.port.dto.CommentListSaveDto;
import lookids.commentread.comment.application.port.dto.CommentReadSaveDto;
import lookids.commentread.comment.application.port.out.CommentRepositoryPort;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryPort {

	private final MongoTemplate mongoTemplate;
	private final CommentEntityMapper commentEntityMapper;

	@Override
	public void createComment(CommentReadSaveDto commentReadSaveDto) {
		mongoTemplate.save(commentEntityMapper.toEntity(commentReadSaveDto));
	}

	@Override
	public void updateCommentList(CommentListSaveDto commentListSaveDto) {
		//mongoTemplate.save(commentEntityMapper.toEntity(commentListSaveDto));
	}

}
