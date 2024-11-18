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

import lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo;

@EnableKafka
@Configuration
public class KafkaConfig {

	@Value("${spring.kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public ConsumerFactory<String, CommentEventVo> commentConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "comment-read-group");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		//props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");

		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
			new ErrorHandlingDeserializer<>(new JsonDeserializer<>(CommentEventVo.class, false)));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> commentEventListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, CommentEventVo> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(commentConsumerFactory());
		return factory;
	}

}
