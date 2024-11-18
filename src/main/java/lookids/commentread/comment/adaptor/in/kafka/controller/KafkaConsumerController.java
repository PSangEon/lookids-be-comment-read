package lookids.commentread.comment.adaptor.in.kafka.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.in.kafka.event.CommentEvent;
import lookids.commentread.comment.adaptor.in.kafka.mapper.CommentKafkaVoMapper;
import lookids.commentread.comment.adaptor.in.kafka.vo.CommentEventVo;
import lookids.commentread.comment.application.port.in.CommentReadUseCase;

@Slf4j
@RequiredArgsConstructor
@Component
public class KafkaConsumerController {

	private final CommentReadUseCase commentReadUseCase;

	private final CommentKafkaVoMapper commentKafkaVoMapper;

	private final ConcurrentHashMap<String, CompletableFuture<CommentEvent>> commentEventFutureMap = new ConcurrentHashMap<>();

	@KafkaListener(topics = "comment-create", groupId = "comment-read-group", containerFactory = "commentEventListenerContainerFactory")
	public void consumeCommentEvent(CommentEventVo commentEventVo) {
		commentReadUseCase.createCommentRead(commentKafkaVoMapper.toCommentCreateEventDto(commentEventVo));
		
		log.info("consumeFeedEvent: {}", commentEventVo);
	}
}