package br.com.biblioteca.resource;

import java.net.URI;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.biblioteca.constants.Messages;
import br.com.biblioteca.entity.Turma;
import br.com.biblioteca.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Turmas")
@RestController
@RequestMapping("/turma")
public class TurmaResource {
	
	@Autowired
	private TurmaService turmaService;
	
	@Operation(description = Messages.SWAGGER_GET_ALL)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Turma>> listarTurmas(){
		List<Turma> turmas = turmaService.listarTodasTurmas();
		return ResponseEntity.ok().body(turmas);
	}
	
	@GetMapping(value ="/v1/page")
	public ResponseEntity<Page<Turma>> listarTurmasPorPaginacaov1(
		@RequestParam(value = "pagina", defaultValue = "0") int pagina,
		@RequestParam(value = "linhasPorPagina", defaultValue = "24") int linhasPorPagina,
		@RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
		@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
	){
		Page<Turma> turmas = turmaService.buscaPorPaginacao(pagina, linhasPorPagina, direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	
	@GetMapping(value ="/v2/page")
	public ResponseEntity<Page<Turma>> listarTurmasPorPaginacaov2(
		@RequestParam(value = "pagina", defaultValue = "0") int pagina,
		@RequestParam(value = "direcao", defaultValue = "ASC") String direcao,
		@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy
	){
		Page<Turma> turmas = turmaService.buscaPorPaginacao(pagina, 10, direcao, orderBy);
		return ResponseEntity.ok().body(turmas);
	}
	
	@Operation(description = Messages.SWAGGER_GET)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Turma> buscaPorID(Integer id) throws ObjectNotFoundException{
		Turma turma = turmaService.buscaPorID(id);
		return ResponseEntity.ok().body(turma);
	}
	
	@Operation(description = Messages.SWAGGER_INSERT)
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody Turma turma){
		Turma objTurma = turmaService.salvar(turma);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objTurma.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@Operation(description = Messages.SWAGGER_DELETE)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable Integer id){
		turmaService.excluir(id);
		return ResponseEntity.noContent().build();
	}
	@Operation(description = Messages.SWAGGER_UPDATE)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> alterar(@RequestBody Turma objTurma, @PathVariable Integer id){
		objTurma.setId(id);
		turmaService.alterar(objTurma);
		return ResponseEntity.noContent().build();
	}
}
