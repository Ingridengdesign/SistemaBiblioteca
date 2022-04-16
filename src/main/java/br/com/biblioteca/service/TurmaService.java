package br.com.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.biblioteca.entity.Turma;
import br.com.biblioteca.repository.TurmaRepository;

@Service
public class TurmaService {
	
	@Autowired
	TurmaRepository turmaRepo;
	
	public List<Turma> listarTodasTurmas(){
		return turmaRepo.findAll();
	}
	
	public Page<Turma> buscaPorPaginacao(int pagina, int linhasPorPaginas, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(pagina, linhasPorPaginas, Direction.valueOf(direction), orderBy);
		return new PageImpl<>(turmaRepo.findAll(), pageRequest , linhasPorPaginas);
	}
	
	public Turma buscaPorID(Integer id) throws ObjectNotFoundException{
		Optional<Turma> turma = turmaRepo.findById(id);
		return turma.orElseThrow(() -> new ObjectNotFoundException(null, "Turma não encontrada"));
	}
	
	public Turma salvar(Turma turma) {
		return turmaRepo.save(turma);
	}
	
	public Turma alterar(Turma objTurma) {
		Turma turma = buscaPorID(objTurma.getId());
		turma.setNome(objTurma.getNome());
		return salvar(turma);
	}
	
	public void excluir(Integer id) {
		turmaRepo.deleteById(id);
	}
}
