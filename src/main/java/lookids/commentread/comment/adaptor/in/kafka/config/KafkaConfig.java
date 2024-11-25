package lookids.commentread.comment.adaptor.in.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.NicknameEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.ProfileImageEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.ReplyEvent;
import lookids.commentread.comment.adaptor.in.kafka.event.UserProfileEvent;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	private Map<String, Object> commonConsumerProps(String groupId) {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "300000");
		return props;
	}

	private <T> ConsumerFactory<String, T> createConsumerFactory(Class<T> targetType, String groupId) {
		return new DefaultKafkaConsumerFactory<>(commonConsumerProps(groupId), new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(targetType, false)));
	}

	private <T> ConcurrentKafkaListenerContainerFactory<String, T> createListenerContainerFactory(
		ConsumerFactory<String, T> consumerFactory) {
		ConcurrentKafkaListenerContainerFactory<String, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConsumerFactory<String, CommentEvent> commentConsumerFactory() {
		return createConsumerFactory(CommentEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommentEvent> commentEventListenerContainerFactory() {
		return createListenerContainerFactory(commentConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, ReplyEvent> replyConsumerFactory() {
		return createConsumerFactory(ReplyEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ReplyEvent> replyEventListenerContainerFactory() {
		return createListenerContainerFactory(replyConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, UserProfileEvent> userProfileConsumerFactory() {
		return createConsumerFactory(UserProfileEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, UserProfileEvent> userProfileEventListenerContainerFactory() {
		return createListenerContainerFactory(userProfileConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, NicknameEvent> nicknameConsumerFactory() {
		return createConsumerFactory(NicknameEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, NicknameEvent> nicknameEventListenerContainerFactory() {
		return createListenerContainerFactory(nicknameConsumerFactory());
	}

	@Bean
	public ConsumerFactory<String, ProfileImageEvent> profileImageConsumerFactory() {
		return createConsumerFactory(ProfileImageEvent.class, "comment-read-group");
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, ProfileImageEvent> profileImageEventListenerContainerFactory() {
		return createListenerContainerFactory(profileImageConsumerFactory());
	}

}
