package br.com.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.biblioteca.entity.AlunoDisciplina;
import br.com.biblioteca.entity.Avaliacao;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, AlunoDisciplina> {
	
	Avaliacao findByAlunoDisciplina(AlunoDisciplina alunoDisciplina);
	
}
