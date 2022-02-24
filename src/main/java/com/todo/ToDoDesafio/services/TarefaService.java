package com.todo.ToDoDesafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.todo.ToDoDesafio.domain.Tarefa;
import com.todo.ToDoDesafio.domain.Usuario;
import com.todo.ToDoDesafio.domain.util.Prioridade;
import com.todo.ToDoDesafio.domain.util.StatusTarefa;
import com.todo.ToDoDesafio.repositories.TarefaRepository;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository repository;

	public Tarefa findById(Integer id) {
		Optional<Tarefa> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Tarefa> findAll() {
		return repository.findAll();
	}

	public Tarefa save(Tarefa tarefa) {
		tarefa.setId(null);
		return repository.save(tarefa);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Tarefa update(Tarefa tarefa) {
		findById(tarefa.getId());
		return repository.saveAndFlush(tarefa);
	}

	public Page<Tarefa> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}

	public List<Tarefa> findByUsuario(Usuario usuario) {
		return repository.findByUsuario(usuario);
	}

	public List<Tarefa> findByStatusPendente() {
		return repository.findByStatusTarefa(StatusTarefa.PENDENTE);
	}
	
	public List<Tarefa> findByPrioridadeTarefaAndStatusTarefa(Prioridade prioridade) {
		return repository.findByPrioridadeTarefaAndStatusTarefa(prioridade, StatusTarefa.PENDENTE);
	}
}