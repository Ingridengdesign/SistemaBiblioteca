package br.com.biblioteca.init;

import java.util.Arrays;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.biblioteca.repository.AlunoRepository;
import br.com.biblioteca.service.AvaliacaoService;
import br.com.biblioteca.service.DisciplinaService;
import br.com.biblioteca.service.TurmaService;
import br.com.biblioteca.entity.Aluno;
import br.com.biblioteca.entity.AlunoDisciplina;
import br.com.biblioteca.entity.Avaliacao;
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
	
	@Autowired
	AvaliacaoService avaliacaoService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
		//Aluno
		Aluno aluno1 = new Aluno();
		aluno1.setNome("Ingrid");
		
		Aluno aluno2 = new Aluno();
		aluno2.setNome("Emanuelle");
		
		Aluno aluno3 = new Aluno();
		aluno3.setNome("Nicolle");
		
		Aluno aluno4 = new Aluno();
		aluno4.setNome("Prince");
		
		//Turmas
		Turma ads = new Turma();
		ads.setNome("ADS");
		
		turmaService.salvar(ads);
		
		Turma redes = new Turma();
		redes.setNome("Redes");
		
		turmaService.salvar(redes);
		
		/*
		 * List<Turma> listaTurmas = turmaService.listarTodasTurmas();
		 * 
		 * //lista as turmas no console for (Turma turma : listaTurmas) {
		 * System.out.println("Nome da turma: " + turma.getNome()); }
		 * 
		 * Turma turma1 = turmaService.buscaPorID(1);
		 * 
		 * System.out.println(turma1.getNome());
		 */
		
		//Disciplinas
		Disciplina java = new Disciplina();
		java.setNome("Java");
		
		disciplinaService.salvar(java);
		
		Disciplina bd = new Disciplina();
		bd.setNome("Banco de Dados");
		
		disciplinaService.salvar(bd);
		
		Disciplina algoritmos = new Disciplina();
		algoritmos.setNome("Algoritmos");
		
		disciplinaService.salvar(algoritmos);
		
		Disciplina oo = new Disciplina();
		oo.setNome("Orienta????o a Objetos");
		
		disciplinaService.salvar(oo);
		
		aluno1.setTurma(ads);
		aluno2.setTurma(redes);
		aluno3.setTurma(ads);
		aluno4.setTurma(redes);
		
		aluno1.setDisciplinas(Arrays.asList(java, bd, oo));
		aluno2.setDisciplinas(Arrays.asList(java));
		aluno3.setDisciplinas(Arrays.asList(algoritmos, java));
		aluno4.setDisciplinas(Arrays.asList(bd, java, oo));
		
		//alunoRepo.saveAll(Arrays.asList(aluno1, aluno2, aluno3, aluno4));
		
		alunoRepo.save(aluno1);
		alunoRepo.save(aluno2);
		alunoRepo.save(aluno3);
		alunoRepo.save(aluno4);
		
		Avaliacao avaliacaoAluno1 = new Avaliacao();
		
		AlunoDisciplina alunoDisciplina1 = new AlunoDisciplina();
		alunoDisciplina1.setAluno(aluno1);
		alunoDisciplina1.setDisciplina(java);
		
		avaliacaoAluno1.setAlunoDisciplina(alunoDisciplina1);
		avaliacaoAluno1.setConceito("A");
		avaliacaoService.save(avaliacaoAluno1);
		
		Avaliacao avaliacaoAluno2 = new Avaliacao();
		
		AlunoDisciplina alunoDisciplina2 = new AlunoDisciplina();
		alunoDisciplina2.setAluno(aluno2);
		alunoDisciplina2.setDisciplina(bd);
		
		avaliacaoAluno2.setAlunoDisciplina(alunoDisciplina2);
		avaliacaoAluno2.setConceito("B");
		avaliacaoService.save(avaliacaoAluno2);
	}
	
}
