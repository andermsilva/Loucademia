package br.com.softblue.loucademia.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.softblue.loucademia.application.util.StringUtils;
import br.com.softblue.loucademia.application.util.ValidationException;
import br.com.softblue.loucademia.domain.acesso.Acesso;
import br.com.softblue.loucademia.domain.acesso.AcessoRepository;
import br.com.softblue.loucademia.domain.acesso.TipoAcesso;
import br.com.softblue.loucademia.domain.aluno.Aluno;
import br.com.softblue.loucademia.domain.aluno.AlunoRepositoy;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;
	
	@EJB
	private AlunoRepositoy alunoRepositoy;
	
	public TipoAcesso registrarAcesso(String matricula, Integer rg) {
		if(StringUtils.isEmpty(matricula) && rg == null) {
			
			throw new ValidationException("Informe o RG ou a Matricula do aluno para ter acesso");
		}
		Aluno aluno;
		if(StringUtils.isEmpty(matricula)) {
			aluno = alunoRepositoy.findByRG(rg);
		}else {
			aluno = alunoRepositoy.findByMatricula(matricula);
		}
		
		if(aluno == null) {
			throw new ValidationException("O aluno não foi encontrado");
		}
		
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);		
		
		TipoAcesso tipoAcesso;
		
		if(ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreechidas()) {
			   
			ultimoAcesso = new Acesso();
		
			ultimoAcesso.setAluno(aluno);
			tipoAcesso= ultimoAcesso.registraAcesso();
			
			acessoRepository.store(ultimoAcesso);
			
		}else {
		   tipoAcesso = ultimoAcesso.registraAcesso();
			
		}
		return tipoAcesso;
		
	}
	
}
