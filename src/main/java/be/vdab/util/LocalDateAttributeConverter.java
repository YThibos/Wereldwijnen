package be.vdab.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply=true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, Date> {

	@Override
	public Date convertToDatabaseColumn(LocalDate localdate) {
		return (localdate == null ? null : Date.valueOf(localdate));
	}

	@Override
	public LocalDate convertToEntityAttribute(Date date) {
		return (date == null ? null : date.toLocalDate());
	}

	
}
