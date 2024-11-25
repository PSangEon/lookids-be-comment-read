package lookids.commentread.comment.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.adaptor.in.web.vo.out.CommentReadResponseVo;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;

@Component
public class WebVoMapper {

	public CommentReadResponseVo toCommentReadResponseVo(CommentReadResponseDto commentReadResponseDto) {
		return CommentReadResponseVo.builder()
			.commentCode(commentReadResponseDto.getCommentCode())
			.content(commentReadResponseDto.getContent())
			.createdAt(commentReadResponseDto.getCreatedAt())
			.userUuid(commentReadResponseDto.getUserUuid())
			.nickname(commentReadResponseDto.getNickname())
			.tag(commentReadResponseDto.getTag())
			.image(commentReadResponseDto.getImage())
			.build();
	}
}
