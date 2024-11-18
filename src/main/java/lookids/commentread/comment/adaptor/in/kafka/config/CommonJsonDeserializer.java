package lookids.commentread.comment.adaptor.in.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.deser.std.StringDeserializer;

public class CommonJsonDeserializer {

	static Map<String, Object> getStringObjectMap(String bootstrapServer, String groupId) {
		Map<String, Object> props = new HashMap<>();

		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
		props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
		props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
		props.put(JsonDeserializer.TYPE_MAPPINGS, "lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo");
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");  //메시지 오프셋 설정
		props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 5000); // 재연결 시도 간격 5초
		props.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 60000); //

		return props;
	}
}
