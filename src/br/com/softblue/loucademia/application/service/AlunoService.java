package br.com.softblue.loucademia.application.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.softblue.loucademia.application.util.StringUtils;
import br.com.softblue.loucademia.application.util.Validation;
import br.com.softblue.loucademia.application.util.ValidationException;
import br.com.softblue.loucademia.domain.acesso.Acesso;
import br.com.softblue.loucademia.domain.aluno.Aluno;
import br.com.softblue.loucademia.domain.aluno.Aluno.Situacao;
import br.com.softblue.loucademia.domain.aluno.AlunoRepositoy;

@Stateless
public class AlunoService {
	
	@EJB
	private AlunoRepositoy alunoRepositoy;
	
	public void creatOrUpdate(Aluno aluno){
		if(StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		}else {
			update(aluno);
		}
		
		
	}
	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		String maxMatricula =alunoRepositoy.getMaxMatriculaAluno();
		aluno.gerarMartricula(maxMatricula);
		alunoRepositoy.store(aluno);
	}

	private void update(Aluno aluno) {
		
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());
		
		alunoRepositoy.update(aluno);
		
	}
	
	public Aluno findByMatricula(String matricula) {
		return alunoRepositoy.findByMatricula(matricula);
	}
	
	public List<Aluno> listAlunos(String matricula, String nome, Integer rg, Integer telefone){
		
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Informe pelo menos um campo para pesquisar Alunos!");
		}
			
	   return alunoRepositoy.listAlunos(matricula, nome, rg, telefone);
	}
	  
	public void delete(String matricula){
		
		alunoRepositoy.delete(matricula);
		
	}
	
	public List<Aluno> listSituacoesAlunos(Situacao situacao){
		
		Validation.assertNotEmpty(situacao);
		return alunoRepositoy.listSituacoesAlunos(situacao);
	}
	

	public List<Acesso> listAcessosAlunos(String matricula, LocalDate dataInicial, LocalDate dataFinal){
		
		if(StringUtils.isEmpty(matricula) && dataFinal==null && dataInicial ==null) {
		 throw new ValidationException("Informe pelo menos um campo para pesquisar Alunos!");
			
		}
		
		return alunoRepositoy.listAcessosAlunos(matricula, dataInicial, dataFinal);
	}
	
}
