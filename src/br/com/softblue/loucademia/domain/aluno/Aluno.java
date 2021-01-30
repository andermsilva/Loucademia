package br.com.softblue.loucademia.domain.aluno;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.softblue.loucademia.application.util.StringUtils;

@Entity
@Table(name = "ALUNO")
public class Aluno implements Serializable {
	private static final long serialVersionUID = 1L;

	public enum Sexo{
		MASCULINO, FEMINNO;
	}

	public enum Situacao{
		
		ATIVO,INATIVO,PENDENTE;
	}
	
	@Id
	@Column(name = "id", nullable = false, length = 8) 
	private String matricula;
	
	@Column(name = "nome", nullable = false, length = 64)
	private String nome;
	
	@Enumerated
	@Column(name = "sexo", nullable=false, length = 1 )
	private Sexo sexo;
	
	@Column(name = "rg", nullable =  false, length = 10)
	private Integer rg;
	
	@Column(name = "nascimento", nullable=false)
	private LocalDate dataNascimento;
	
	@Enumerated
	@Column(name = "situacao",nullable = false,length = 1)
	private Situacao situacao;
	
	@Column(name = "email", nullable =false, length = 64)
	private String email;
	
	@Embedded
	@Column(name = "telefone", nullable = false)
	private Telefone telefone = new Telefone();
	
	@Embedded
	@Column(name = "endereco" ,nullable = false)
	private Endereco endereco = new Endereco();

	
	public void gerarMartricula(String maxMatricula) {
		Year year = Year.now();
		
		if(maxMatricula == null) {
			maxMatricula = year + StringUtils.leftZeros(0, 4);
		}
		
		int sequencial = Integer.parseInt(maxMatricula.substring(4));
		sequencial ++;
		
		this.matricula = year + StringUtils.leftZeros(sequencial,4);			
	}
	
	
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco enderco) {
		this.endereco = enderco;
	}

	@Override
	public String toString() {
		return "Aluno [matricula=" + matricula + ", nome=" + nome + ", sexo=" + sexo + ", dataNascimento="
				+ dataNascimento + ", situacao=" + situacao +", E-mail "+email+",  telefone=" + telefone + ", enderco=" + endereco + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}
	
	
	
	
	
	
}
