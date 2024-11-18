package lookids.commentread.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "lookids.commentread.comment.adaptor.out.infrastructure.mongo.restRepository")
@EnableMongoAuditing
public class MongoConfig {
}
