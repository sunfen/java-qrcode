package cn.sf.qrcode.codes.domain.entity;

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
@Table(name="multiple_codes")
@Entity
public class MultipleCode extends AbstractSecureObject{


    @Column(length = 512)
    private String url;
    
    @ManyToOne(targetEntity = User.class)
    private User user;

    @Convert(converter = StringDateConverter.class)
    private String createTime;
    
    @Column(precision = 8, scale = 2)
    @ColumnDefault("0.00")
    private BigDecimal times;

    
    public MultipleCode() {
        super();
        this.createTime = CommonUtil.formatter(LocalDateTime.now());
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigDecimal getTimes() {
		return times;
	}

	public void setTimes(BigDecimal times) {
		this.times = times;
	}

    
}
