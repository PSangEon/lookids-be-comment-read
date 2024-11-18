package lookids.commetread.comment.adaptor.out.infrasture.mongo.restRepository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lookids.commetread.comment.adaptor.out.infrasture.mapper.CommentEntityMapper;
import lookids.commetread.comment.application.port.dto.CommentReadCreateDto;
import lookids.commetread.comment.application.port.out.CommentRepositoryPort;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryPort {

	private final MongoTemplate mongoTemplate;
	private final CommentEntityMapper commentEntityMapper;

	@Override
	public void createComment(CommentReadCreateDto commentReadCreateDto) {
		mongoTemplate.save(commentEntityMapper.toEntity(commentReadCreateDto));

	}

}
