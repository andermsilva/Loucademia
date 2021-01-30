package br.com.softblue.loucademia.interfaces.aluno.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.RollbackException;

import br.com.softblue.loucademia.application.service.AlunoService;
import br.com.softblue.loucademia.application.util.StringUtils;
import br.com.softblue.loucademia.application.util.ValidationException;
import br.com.softblue.loucademia.domain.aluno.Aluno;

@Named
@RequestScoped
public class AlunoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	FacesContext facesContext;
	
	private Aluno aluno = new Aluno();
	
	private String matricula;
	
	private String titulo ="Novo Aluno";
	
	
	@EJB
	private AlunoService alunoService;
	
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	
	
	public Aluno getAluno() {
		return aluno;
	}
	
	public void carregar() {
		if(!StringUtils.isEmpty(matricula)) {
			aluno = alunoService.findByMatricula(matricula);
			titulo = "Alterar aluno";
		}
		
		
	}
   
	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String gravar() {
		try {
			alunoService.creatOrUpdate(aluno);
			
		}catch (Exception e) {
			facesContext.addMessage(null, new FacesMessage("O Rg já cadastrado no bando de dados!"));
			
			return null;
		}
		
		
		facesContext.addMessage(null, new FacesMessage("Dados Gravados com Sucesso!"));
		return null;
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	
	
	

}
