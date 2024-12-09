package lookids.commentread.comment.adaptor.in.web.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lookids.commentread.comment.adaptor.in.web.mapper.WebVoMapper;
import lookids.commentread.comment.adaptor.in.web.vo.out.CommentCountResponseVo;
import lookids.commentread.comment.adaptor.in.web.vo.out.CommentReadResponseVo;
import lookids.commentread.comment.adaptor.in.web.vo.out.ReplyReadResponseVo;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.comment.application.port.dto.ReplyReadResponseDto;
import lookids.commentread.comment.application.port.in.CommentReadUseCase;
import lookids.commentread.common.entity.BaseResponse;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/read/comment")
public class CommentReadController {
	private final CommentReadUseCase commentReadUseCase;
	private final WebVoMapper webVoMapper;

	@Operation(summary = "readCommentList API", description = "readCommentList API 입니다.")
	@GetMapping()
	public BaseResponse<Page<CommentReadResponseVo>> readCommentList(@RequestParam(value = "feedCode") String feedCode,
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "20") int size) {
		log.info("요청받음 : {}", feedCode);
		// 조회할 피드코드를 서비스 함수에 파라미터로 넘겨줌

		Page<CommentReadResponseDto> commentReadResponseDtoPage = commentReadUseCase.readCommentList(feedCode, page,
			size);

		return new BaseResponse<>(commentReadResponseDtoPage.map(webVoMapper::toCommentReadResponseVo));
	}

	@Operation(summary = "readCommentCount API", description = "readCommentCount API 입니다.")
	@GetMapping("/count")
	public BaseResponse<CommentCountResponseVo> readCommentCount(@RequestParam(value = "feedCode") String feedCode) {
		log.info("요청받음 : {}", feedCode);
		// 조회할 피드코드를 서비스 함수에 파라미터로 넘겨줌

		return new BaseResponse<>(webVoMapper.toCommentCountResponseVo(commentReadUseCase.readCommentCount(feedCode)));
	}

	@Operation(summary = "readCommentList API", description = "readReplyList API 입니다.")
	@GetMapping("/reply")
	public BaseResponse<List<ReplyReadResponseVo>> readReplyList(
		@RequestParam(value = "commentCode") String commentCode) {
		log.info("요청받음 : {}", commentCode);
		// 조회할 부모 댓글코드를 서비스 함수에 파라미터로 넘겨줌
		List<ReplyReadResponseDto> replyReadResponseDtoList = commentReadUseCase.readReplyList(commentCode);

		return new BaseResponse<>(replyReadResponseDtoList.stream().map(webVoMapper::toReplyReadResponseVo).toList());
	}
}
