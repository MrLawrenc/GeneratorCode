package github.mrLawrenc.generator.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : MrLawrenc
 */
@Data
@AllArgsConstructor
public class ResponseResult {

    private Integer code;
    private String message;
    private Object data;

    public ResponseResult(StatusEnums enums) {
        this.code = enums.getCode();
        this.message = enums.getInfo();
    }

    public ResponseResult(StatusEnums enums, Object data) {
        this.code = enums.getCode();
        this.message = enums.getInfo();
        this.data = data;
    }

    public ResponseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseResult success() {
        return new ResponseResult(StatusEnums.SUCCESS);
    }

    public static ResponseResult success(String msg) {
        return new ResponseResult(StatusEnums.SUCCESS, msg);
    }

    public static ResponseResult success(Object data) {
        return new ResponseResult(StatusEnums.SUCCESS, data);
    }

    public static ResponseResult error() {
        return new ResponseResult(StatusEnums.SERVER_ERROR);
    }

    public static ResponseResult error(StatusEnums enums) {
        return new ResponseResult(enums.getCode(), enums.getInfo());
    }

    public static ResponseResult error(StatusEnums enums, Object data) {
        return new ResponseResult(enums.getCode(), enums.getInfo(), data);
    }
}
