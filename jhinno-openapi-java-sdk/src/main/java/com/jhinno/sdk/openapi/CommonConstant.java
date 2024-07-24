package com.jhinno.sdk.openapi;

/**
 * @author yanlongqi
 * @date 2024/1/31 10:17
 */
public class CommonConstant {

    /**
     * 请求成功时的标识
     */
    public static final String SUCCESS = "success";

    /**
     * 请求失败时的标识
     */
    public static final String FAILED = "failed";

    /**
     * AES获取Token格式
     */
    public static final String TokenUserFormat = "%s,%s";


    /**
     * 获取token时AES加密的默认key
     */
    public static final String DEFAULT_AES_KEY = "jin5no@aqec8gtw6";

    /**
     * 字符逗号
     */
    public static final String NORMAL_CHARACTER_COMMA = ",";

    /**
     * 签名key
     */

    public static final String SIGNATURE = "signature";

    /**
     * 集成方Key
     */
    public static final String ACCESS_KEY = "accessKey";

    /**
     * 用户名
     */
    public static final String USERNAME = "username";

    /**
     * 时间戳
     */
    public static final String CURRENT_TIME_MILLIS = "currentTimeMillis";

    /**
     * 签名加密格式
     * <ul>
     *     <li> accessKey </li>
     *     <li> username </li>
     *     <li> currentTimeMillis </li>
     * </ul>
     */
    public static final String SIGNATURE_FORMAT = "#%s#%s#%s#";

    /**
     * HmacSHA256 算法
     */
    public static final String HMAC_SHA_256_ALGORITHM = "HmacSHA256";

    /**
     * AES 算法
     */
    public static final String AES_ALGORITHM = "AES";

    /**
     * AES ECB Padding
     */
    public static final String AES_ECB_PADDING = "AES/ECB/PKCS5Padding";

    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    public static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


    /**
     * Rest请求JSON编码
     */
    public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";

}
