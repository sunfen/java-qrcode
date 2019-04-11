package cn.sf.qrcode.common;

/**
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/08/28
 */
public class AlipayConfig {
	
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "https://www.art-sculpture.cn/qrcode/notify_url.jsp";
    //public static String notify_url = "http://localhost:8082/qrcode/notify_url.jsp";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:8082/qrcode/return_url.jsp";
    //public static String return_url = "https://www.art-sculpture.cn/qrcode/return_url.jsp";
    // 请求网关地址
    public static String URL = "https://openapi.alipay.com/gateway.do";

    // 商户appid
	public static String APPID = "2019040963865222";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = 
	    "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC8ZfoinhNsuxYj" + 
	    "QhQMo837+KT8CtMVHt/FjXAPTt3WFG0Zxeshkc53P9iyanzD1zH/D5Rp/BaqWNtW" + 
	    "dIENM77tv/aQoggrcuQWH38aOhDTcvqqVveq/mpcNWZlhZO+l/aPN8PKA9zvxF+F" + 
	    "GeANB4MH2GHn7wUDz31RcWy4o5+G+5HqbbTazPI13UHCE7hMpEVSrRymusc2Mz9h" + 
	    "JicOb4PyL0QEaZdSss6PN0WOvQGuSt3qxiqMdZWFRa9gqAGNUQm67x9pcIIKhrtT" + 
	    "hZ+wLiN8sdTtm41su76jc2UW4QdjZ/A/7NYxh5LWjDAi80fxc0eBNX3J/M3FYa+/" + 
	    "/vSSTxAPAgMBAAECggEAJzR5dxa6KBhOpQutLGwyA0tc2e5Oushv/KZQOX9J/rDF" + 
	    "e2WKZrP26S3qJEawq/huOQu+8Mc62DwheL5J1Lt98e/OmY5+cLw55rirNIP82GPG" + 
	    "8gejBnxnGcUrB9liQ3lQH8GVBscDBQoybnpR+6QiilFnHmLJAl/9TiNyYoH6QpZf" + 
	    "xvzZ6OAMdOOZcNsCi2RoN+e5Ae4zGCzd18BtLZ5pATHrFyRpQPabXK2XHVVAsUAp" + 
	    "KHtrFVf8WQHKk3AP51Bh8xEhQBnt22cYh9eiB+yfLjd5yZ9KUf4+k8WdRYnLM1LO" + 
	    "l3jVVdxBNG6yVWE0bGzo+buGvLBelvzjPYd9yACmwQKBgQDrxH12yjJDZsJqyiVx" + 
	    "35dkKWVhFrLrqtQX5STVgMybg9nC5Aw1hSCgcXub1Tw8Ni4pdCvnFu9aHEcX/7Q2" + 
	    "Dt/+nxCcqdlCjwDerb2zqXSVFJo1pA3iZrEW5WT1SBIvBddAPJBvXTpA5VZzJjTr" + 
	    "1xUeqQ37EQRcHW390N52vxBBfwKBgQDMkNjDAb0CSQDRGnYJ8CEyeMIQwJZsaZ89" + 
	    "BaExdDDEn4jRtaRKsFQKsJSCqddOOdBMiNZW0Qg1dbf7ewg67kY4u6DcTQcMoBvO" + 
	    "egDzbi3EHRu219sBiMC5WXCXBIxhthbnAdUfQsvaztxWAfh6jn+Lt+VteDoeZkzt" + 
	    "sYGp3yNZcQKBgDgjeFBxwtgjvsVYzuO3fr/dz4L3ubC943SxHj0sRwv8sDxkurd/" + 
	    "tI4fl3Q6qPYZXJKIU+/ZmTKWqyL+wvVgQ1vImlcDZnUfwfBD5HjEL7tTxYhiQOAY" + 
	    "3a/6/Tgeah4CQQfNpAKway+KrDQM8+r4MtIzJN4UZjpc05TtSBt0UTWXAoGAYn+M" + 
	    "H7mCjMsh/Y1EMNzS3z6OEwroYyMobXV8fBd6M/KtKmzPwrbMgKktmQy2gxALYA6z" + 
	    "RfI4haU9BXXkLJv2a/LMLcdZEZc2TGEqfAwdNUTex1eAOGjkg1qxf8XM4m+O74RO" + 
	    "XlMHXUAlj7Q7NKfQejH94ov1UBXqDLlfua+bkcECgYB4cQUJL03uz7dMkdYxmcUr" + 
	    "LNruj8uqYhvJjNZl/bYOhhA8UqsE8o64zV5cRwnyFOeUd7HcC79yJz3vJGXiXT5b" + 
	    "aQK4YKeqXNGMEzuSCYXz5zkFMH+l5EaH5lWmqnrQ4SGRsyhJSnmXQbF2B86AMmU2" + 
	    "K2E4+A1YwZY/r5FJaLGocw==";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvGX6Ip4TbLsWI0IUDKPN+/ik/ArTFR7fxY1wD07d1hRtGcXrIZHOdz/Ysmp8w9cx/w+UafwWqljbVnSBDTO+7b/2kKIIK3LkFh9/GjoQ03L6qlb3qv5qXDVmZYWTvpf2jzfDygPc78RfhRngDQeDB9hh5+8FA899UXFsuKOfhvuR6m202szyNd1BwhO4TKRFUq0cprrHNjM/YSYnDm+D8i9EBGmXUrLOjzdFjr0Brkrd6sYqjHWVhUWvYKgBjVEJuu8faXCCCoa7U4WfsC4jfLHU7ZuNbLu+o3NlFuEHY2fwP+zWMYeS1owwIvNH8XNHgTV9yfzNxWGvv/70kk8QDwIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";

}
