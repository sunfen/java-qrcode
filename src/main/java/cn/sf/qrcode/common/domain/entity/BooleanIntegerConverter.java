package cn.sf.qrcode.common.domain.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class BooleanIntegerConverter implements AttributeConverter<Boolean, Integer> {
	@Override
	public Integer convertToDatabaseColumn(Boolean value) {
		if (Boolean.TRUE.equals(value)) {
			return 1;
		} else if(Boolean.FALSE.equals(value)){
			return 0;
		}else {
			return null;
		}
	}
	
	@Override
	public Boolean convertToEntityAttribute(Integer value) {
		if(value != null) {
			
			return value != 0;
		}
		return null;
	}
}
