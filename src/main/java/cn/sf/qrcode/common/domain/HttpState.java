package cn.sf.qrcode.common.domain;

/**
 * 
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/17
 */
public class HttpState<T> {

    /**
     * 请求状态
     */
    private final Integer code;

    /**
     * 请求返回值
     */
    private final T t;

    /**
     * 请求成功，无返回值
     * 
     * @return 请求状态
     */
    public static HttpState<String> success() {
        return new HttpState<String>(200, null);
    }

    /**
     * 请求成功，有返回值
     * 
     * @param t 返回值
     * @return 请求状态
     */
    public static <T> HttpState<T> success(T t) {
        return new HttpState<T>(200, t);
    }

    /**
     * 请求失败，服务器内部错误
     * 
     * @return 请求状态
     */
    public static HttpState<String> error() {
        return new HttpState<String>(500, "服务器内部错误！");
    }

    /**
     * 请求失败，服务器内部错误
     * 
     * @param t 返回值
     * @return 请求状态
     */
    public static <T> HttpState<T> error(T t) {
        return new HttpState<T>(500, t);
    }

    /**
     * 请求失败
     * 
     * @param code 状态码
     * @param t 返回值
     * @return 请求状态
     */
    public static <T> HttpState<T> error(Integer code, T t) {
        return new HttpState<T>(code, t);
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @return the t
     */
    public T getT() {
        return t;
    }

    /**
     * @param code
     * @param t
     */
    public HttpState(Integer code, T t) {
        super();
        this.code = code;
        this.t = t;
    }

}

