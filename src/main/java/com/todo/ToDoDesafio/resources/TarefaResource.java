package com.todo.ToDoDesafio.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.ToDoDesafio.domain.Tarefa;
import com.todo.ToDoDesafio.domain.Usuario;
import com.todo.ToDoDesafio.domain.util.StatusTarefa;
import com.todo.ToDoDesafio.services.TarefaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
@Api(value = "API REST tarefa")
@CrossOrigin(origins = "*")
public class TarefaResource {

	@Autowired
	private TarefaService service;

	@ApiOperation(value = "Retorna uma tarefa por id")
	@GetMapping("/tarefas/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Tarefa tarefa = service.findById(id);
		return ResponseEntity.ok().body(tarefa);
	}

	@ApiOperation(value = "Retorna todas as tarefas")
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll() {
		List<Tarefa> tarefas = service.findAll();
		return ResponseEntity.ok().body(tarefas);
	}

	@ApiOperation(value = "Salva uma tarefa")
	@PostMapping("/tarefas")
	public ResponseEntity<?> Save(@RequestBody Tarefa tarefa) {
		tarefa = service.save(tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Atualiza uma tarefa")
	@PutMapping("/tarefas")
	public ResponseEntity<?> Update(@RequestBody Tarefa tarefa) {
		tarefa = service.update(tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Marca como concluída uma tarefa")
	@PutMapping("/tarefas/{id}")
	public ResponseEntity<?> UpdateConcluido(@PathVariable Integer id) {
		Tarefa tarefa = service.findById(id);
		tarefa.setStatusTarefa(StatusTarefa.CONCLUIDA);
		tarefa = service.update(tarefa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tarefa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta uma tarefa por id")
	@DeleteMapping("/tarefas/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value = "Retorna as tarefas por usuário")
	@GetMapping("/tarefas/usuario")
	public ResponseEntity<?> findByCleinte(@RequestBody Usuario usuario) {
		List<Tarefa> tarefas = service.findByUsuario(usuario);
		return ResponseEntity.ok().body(tarefas);
	}

	@ApiOperation(value = "Retorna as tarefas pendentes")
	@GetMapping("/tarefas/pendentes")
	public ResponseEntity<?> findByStatusPendente() {
		List<Tarefa> tarefas = service.findByStatusPendente();
		return ResponseEntity.ok().body(tarefas);

	}

	@ApiOperation(value = "Retorna as tarefas pendentes por prioridade")
	@GetMapping("/tarefas/prioridade")
	public ResponseEntity<?> findByPrioridadeTarefa(@RequestBody Tarefa tarefa) {
		if (tarefa.getPrioridadeTarefa() == null) {
			return findByStatusPendente();
		}
		List<Tarefa> tarefas = service.findByPrioridadeTarefaAndStatusTarefa(tarefa.getPrioridadeTarefa());
		return ResponseEntity.ok().body(tarefas);
	}
}
