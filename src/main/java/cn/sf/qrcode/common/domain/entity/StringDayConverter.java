package cn.sf.qrcode.common.domain.entity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter(autoApply = false)
public class StringDayConverter implements AttributeConverter<String,Date> {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    
    @Override
    public Date convertToDatabaseColumn(final String time) {
        if(time == null || time.isEmpty()) {
            return null;
        }
        
        try {
            final LocalDate localDate = LocalDate.parse(time, this.dateTimeFormatter);
            return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    @Override
    public String convertToEntityAttribute(final Date date) {
        if(date == null) {
            return null;
        }

        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().format(this.dateTimeFormatter);
    }
}
