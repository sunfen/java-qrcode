package cn.sf.qrcode.code.domain;

import java.math.BigDecimal;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
public class CodeVO {

	private Long id;
	
    private String alipay;
    
    private String wx;

    private String qq;
    
    private String name;
    
    private String openId;
    
    private String createTime;
    
    private BigDecimal weixinTimes;

    private BigDecimal alipayTimes;

    private BigDecimal qqTimes;
    
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getWeixinTimes() {
		return weixinTimes;
	}

	public void setWeixinTimes(BigDecimal weixinTimes) {
		this.weixinTimes = weixinTimes;
	}

	public BigDecimal getAlipayTimes() {
		return alipayTimes;
	}

	public void setAlipayTimes(BigDecimal alipayTimes) {
		this.alipayTimes = alipayTimes;
	}

	public BigDecimal getQqTimes() {
		return qqTimes;
	}

	public void setQqTimes(BigDecimal qqTimes) {
		this.qqTimes = qqTimes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
    
}
