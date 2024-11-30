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
import lookids.commentread.comment.adaptor.in.web.vo.out.CommentReadResponseVo;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
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
		@RequestParam(value = "size", defaultValue = "20") int size) {  //한번에 몇개를 가지고 올지..20개를 갖고 올거.
		log.info("요청받음 : {}", feedCode);
		// 조회할 피드코드를 서비스 함수에 파라미터로 넘겨줌

		Page<CommentReadResponseDto> commentReadResponseDtoPage = commentReadUseCase.readCommentList(feedCode, page,
			size);

		return new BaseResponse<>(commentReadResponseDtoPage.map(webVoMapper::toCommentReadResponseVo));
	}

	@Operation(summary = "readCommentList API", description = "readReplyList API 입니다.")
	@GetMapping("/reply")
	public BaseResponse<List<CommentReadResponseVo>> readReplyList(
		@RequestParam(value = "commentCode") String commentCode) {
		log.info("요청받음 : {}", commentCode);
		// 조회할 부모 댓글코드를 서비스 함수에 파라미터로 넘겨줌

		List<CommentReadResponseDto> commentReadResponseDtoList = commentReadUseCase.readReplyList(commentCode);

		return new BaseResponse<>(
			commentReadResponseDtoList.stream().map(webVoMapper::toCommentReadResponseVo) // 메서드 참조 사용
				.toList());
	}
}
