package lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import lookids.commentread.comment.adaptor.out.infrastructure.entity.CommentReadEntity;

@Repository
public interface CommentReadMongoRepository extends MongoRepository<CommentReadEntity, String> {
}
