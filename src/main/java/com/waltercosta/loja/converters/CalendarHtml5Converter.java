package com.waltercosta.loja.converters;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass=Calendar.class)
public class CalendarHtml5Converter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	private static DateTimeConverter originalConverter = new DateTimeConverter();

	static {
		originalConverter.setPattern("yyyy-MM-dd");
	}

	@Override
	public Object getAsObject(FacesContext context,UIComponent component,String value) {
		Date date = (Date) originalConverter.getAsObject(context,component, value);
		if(date == null) {
			return null;
		}
		Calendar newCalendar = Calendar.getInstance();
		newCalendar.setTime(date);
		return newCalendar;
	}

	@Override
	public String getAsString(FacesContext context,UIComponent component,Object value) {
		if(value == null){
			return null;
		}
		Calendar calendar = (Calendar) value;
		return originalConverter.getAsString(context, component,calendar.getTime());
	}
}