package cn.sf.qrcode.common.domain.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * 日期字符串转换器
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/09/03
 */
@Converter(autoApply = false)
public class StringDateConverter implements AttributeConverter<String,Date> {
  
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public Date convertToDatabaseColumn(final String time) {
        if(time == null || time.isEmpty()) {
            return null;
        }
        
        try {
            final LocalDateTime localDateTime = LocalDateTime.parse(time, this.dateTimeFormatter);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(final Date date) {
        if(date == null) {
            return null;
        }
        
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(this.dateTimeFormatter);
    }
    
    
	


}
