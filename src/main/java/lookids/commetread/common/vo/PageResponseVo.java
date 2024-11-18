package lookids.commetread.common.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
public class PageResponseVo {
	private Integer page;
	private Integer totalPage;
	private Boolean nextPage;
	private List<?> data;
}
