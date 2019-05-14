package cn.sf.qrcode.code.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

import cn.sf.qrcode.common.CommonUtil;
import cn.sf.qrcode.common.domain.entity.AbstractSecureObject;
import cn.sf.qrcode.common.domain.entity.StringDateConverter;
import cn.sf.qrcode.user.domain.entity.User;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2018/12/15
 */
@Table(name="codes")
@Entity
public class Code extends AbstractSecureObject{

    @Column(length = 256, nullable = false)
    private String alipay;
    
    @Column(length = 256, nullable = false)
    private String wx;

    @Column(length = 512)
    private String qq;
    
    @Column(length = 256)
    private String name;
    
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Convert(converter = StringDateConverter.class)
    private String createTime;
    
    @Column(precision = 8, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal weixinTimes;

    @Column(precision = 8, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal alipayTimes;

    @Column(precision = 8, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal qqTimes;
    
    
    public Code() {
        super();
        this.createTime = CommonUtil.formatter(LocalDateTime.now());
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
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


	public String getQq() {
		return qq;
	}


	public void setQq(String qq) {
		this.qq = qq;
	}


	public BigDecimal getQqTimes() {
		return qqTimes;
	}


	public void setQqTimes(BigDecimal qqTimes) {
		this.qqTimes = qqTimes;
	}

    
    
}
