package lookids.commetread.common.exception;

import lombok.Getter;
import lookids.commetread.common.entity.BaseResponseStatus;

@Getter
public class BaseException extends RuntimeException {

	private final BaseResponseStatus status;

	public BaseException(BaseResponseStatus status) {
		this.status = status;
	}
}