package br.com.softblue.loucademia.application.util;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

@FacesConverter(forClass = LocalDate.class ,value = "convData")
public class ConverterData implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if(value == null || value.equals("")) {
        	 return null;
         }
          String tokens[] = value.split("-");
         
         int ano = Integer.parseInt(tokens[0]);
         int mes = Integer.parseInt(tokens[1]);
         int dia = Integer.parseInt(tokens[2]);         
         LocalDate data = LocalDate.of(ano, mes, dia); 
         return data;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component , Object obj) {
		
		 if(obj instanceof LocalDateTime) {
			 LocalDateTime ldt = (LocalDateTime)obj;
			 DateTimeFormatter dft = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			 String text = ldt.format(dft);
			 return text;
			 
		 }
		LocalDate dt = (LocalDate)obj; 
		
		DateTimeFormatter dft =   DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String text = dt.format(dft);
		
		return text;
	}

}
