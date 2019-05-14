package cn.sf.qrcode.code.domain;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public class CodeVO {

    private String alipay;
    
    private String wx;

    private String qq;
    
    private String name;
    
    private String openId;

    
    public CodeVO() {
        super();
    }

    public String getAlipay() {
        return alipay;
    }

    public void setAlipay(String alipay) {
        this.alipay = alipay;
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
    
    
    
}
