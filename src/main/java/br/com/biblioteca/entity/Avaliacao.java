package br.com.biblioteca.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Avaliação")
public class Avaliacao implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7707404374973418313L;

	@EmbeddedId
	private AlunoDisciplina alunoDisciplina;
	
	private String conceito;

	public AlunoDisciplina getAlunoDisciplina() {
		return alunoDisciplina;
	}

	public void setAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		this.alunoDisciplina = alunoDisciplina;
	}

	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
}
