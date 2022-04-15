package br.com.biblioteca.init;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import br.com.biblioteca.repository.AlunoRepository;
import br.com.biblioteca.entity.Aluno;

@Component
public class Init  implements ApplicationListener<ContextRefreshedEvent>{
	
	@Autowired
	AlunoRepository alunoRepo;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
	
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
		
	}
	
}
