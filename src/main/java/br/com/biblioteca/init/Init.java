package br.com.biblioteca.init;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.biblioteca.repository.AlunoRepository;
import br.com.biblioteca.service.DisciplinaService;
import br.com.biblioteca.service.TurmaService;
import br.com.biblioteca.entity.Aluno;
import br.com.biblioteca.entity.Disciplina;
import br.com.biblioteca.entity.Turma;

@Component
public class Init  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Autowired
	TurmaService turmaService;
	
	@Autowired
	DisciplinaService disciplinaService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
		//Aluno
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Ingrid");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Emanuelle");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Nicolle");
		
		alunoRepo.saveAll(Arrays.asList(aluno1, aluno2, aluno3));
		/*
		 * alunoRepo.save(aluno1); alunoRepo.save(aluno2); alunoRepo.save(aluno3);
		 */
		
		//Turmas
		Turma ads = new Turma();
		ads.setNome("ADS");
		
		Turma redes = new Turma();
		redes.setNome("Redes");
		
		turmaService.salvar(ads);
		turmaService.salvar(redes);
		
		List<Turma> listaTurmas = turmaService.listarTodasTurmas();
		
		//lista as turmas no console
		for (Turma turma : listaTurmas) {
			System.out.println("Nome da turma: " + turma.getNome());
		}
		
		Turma turma1 = turmaService.buscaPorID(1);
		
		System.out.println(turma1.getNome());
		
		
		
		Disciplina java = new Disciplina();
		java.setNome("Java");
		
		disciplinaService.salvar(java);
		
		Disciplina bd = new Disciplina();
		bd.setNome("Banco de Dados");
		
		disciplinaService.salvar(bd);
	}
	
}
