package br.com.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.biblioteca.entity.AlunoDisciplina;
import br.com.biblioteca.entity.Avaliacao;
import br.com.biblioteca.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {
	
	@Autowired
	AvaliacaoRepository avaliacaoRepo;
	
	public Avaliacao save(Avaliacao avaliacao) {
		return avaliacaoRepo.save(avaliacao);
	}
	
	public List<Avaliacao> findAll(){
		return avaliacaoRepo.findAll();
	}
	
	public Avaliacao buscarNotaAlunoDisciplina(AlunoDisciplina alunoDisciplina) {
		return avaliacaoRepo.findByAlunoDisciplina(alunoDisciplina);
	}
	
}
