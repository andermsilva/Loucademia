package br.com.softblue.loucademia.application.util;

import java.time.LocalDate;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("dataNascimento")
public class ValidatorDate implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object obj) throws ValidatorException {
		
		LocalDate dt = (LocalDate)obj;
		String d = dt.toString();
	
		if( dt == null || d.equals("")) {
			
			FacesMessage msg = new FacesMessage("Informe a data de Nascimento");
			System.out.println("Data => "+d);
			throw new ValidatorException(msg);
		}
	}

	
}
