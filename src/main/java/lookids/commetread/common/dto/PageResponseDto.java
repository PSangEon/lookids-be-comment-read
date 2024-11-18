package lookids.commetread.common.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lookids.commetread.common.vo.PageResponseVo;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseDto {

	private Integer page;
	private Integer totalPage;
	private Boolean nextPage;
	private List<?> data;

	public static PageResponseDto toDto(Integer page, Integer totalPage, Boolean nextPage, List<?> data) {
		return PageResponseDto.builder().page(page).totalPage(totalPage).nextPage(nextPage).data(data).build();
	}

	public PageResponseVo toVo() {
		return PageResponseVo.builder()
			.page(this.page)
			.totalPage(this.totalPage)
			.nextPage(this.nextPage)
			.data(this.data)
			.build();
	}
}
