package cn.sf.qrcode.common;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author sq mail:feifanhyx@foxmail.com
 * @date 2018/08/28
 */
public class CommonUtil {

    
	public static String getNow() {
		LocalDateTime now = LocalDateTime.now();
		return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
    
    public static String getDateNow() {
        LocalDate now = LocalDate.now();
        return now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
    
    
	public static LocalDateTime parserTime(String time) {
	    return LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	

    public static LocalDate parserDate(String time) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
	
	public static String formatter(LocalDateTime date ) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return date.format(formatter);
	}
	
	public static String formatter(LocalDate date ) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return date.format(formatter);
	}

}
