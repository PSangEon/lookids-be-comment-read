package lookids.commentread.comment.adaptor.in.web.mapper;

import org.springframework.stereotype.Component;

import lookids.commentread.comment.adaptor.in.web.vo.out.CommentCountResponseVo;
import lookids.commentread.comment.adaptor.in.web.vo.out.CommentReadResponseVo;
import lookids.commentread.comment.adaptor.in.web.vo.out.ReplyReadResponseVo;
import lookids.commentread.comment.application.port.dto.CommentCountResponseDto;
import lookids.commentread.comment.application.port.dto.CommentReadResponseDto;
import lookids.commentread.comment.application.port.dto.ReplyReadResponseDto;

@Component
public class WebVoMapper {

	public CommentReadResponseVo toCommentReadResponseVo(CommentReadResponseDto commentReadResponseDto) {
		return CommentReadResponseVo.builder()
			.commentCode(commentReadResponseDto.getCommentCode())
			.content(commentReadResponseDto.getContent())
			.createdAt(commentReadResponseDto.getCreatedAt())
			.uuid(commentReadResponseDto.getUserUuid())
			.nickname(commentReadResponseDto.getNickname())
			.tag(commentReadResponseDto.getTag())
			.image(commentReadResponseDto.getImage())
			.replyCount(commentReadResponseDto.getReplyCount())
			.build();
	}

	public ReplyReadResponseVo toReplyReadResponseVo(ReplyReadResponseDto replyReadResponseDto) {
		return ReplyReadResponseVo.builder()
			.commentCode(replyReadResponseDto.getCommentCode())
			.content(replyReadResponseDto.getContent())
			.createdAt(replyReadResponseDto.getCreatedAt())
			.uuid(replyReadResponseDto.getUserUuid())
			.nickname(replyReadResponseDto.getNickname())
			.tag(replyReadResponseDto.getTag())
			.image(replyReadResponseDto.getImage())
			.build();
	}

	public CommentCountResponseVo toCommentCountResponseVo(CommentCountResponseDto commentCountResponseDto) {
		return CommentCountResponseVo.builder().commentCount(commentCountResponseDto.getCommentCount()).build();
	}
}
