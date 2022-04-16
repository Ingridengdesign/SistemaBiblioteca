package br.com.biblioteca.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.biblioteca.constants.Messages;
import br.com.biblioteca.entity.Aluno;
import br.com.biblioteca.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Alunos")
@RestController
@RequestMapping("/aluno")
public class AlunoResource {
	
	@Autowired
	private AlunoService alunoService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Aluno>> listarAluno(){
		List<Aluno> alunos = alunoService.listaTodosAlunos();
		return ResponseEntity.ok().body(alunos);
	}
	
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Aluno> buscaPorID(@PathVariable Integer id) throws ObjectNotFoundException{
		Aluno aluno = alunoService.buscaPorID(id);
		return ResponseEntity.ok().body(aluno);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(Aluno objAluno){
		Aluno aluno = alunoService.salvar(objAluno);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		alunoService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@Operation(description = Messages.SWAGGER_UPDATE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Aluno objAluno, @PathVariable Integer id){
		objAluno.setId(id);
		alunoService.alterar(objAluno);
		return ResponseEntity.noContent().build();
	}
}
