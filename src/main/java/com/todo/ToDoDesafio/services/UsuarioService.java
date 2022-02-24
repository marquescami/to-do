package com.todo.ToDoDesafio.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.todo.ToDoDesafio.domain.Usuario;
import com.todo.ToDoDesafio.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository repository;

	public Usuario findById(Integer id) {
		Optional<Usuario> obj = repository.findById(id);
		return obj.orElse(null);
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}

	public Usuario save(Usuario usuario) {
		usuario.setId(null);
		return repository.save(usuario);
	}

	public void delete(Integer id) {
		findById(id);
		repository.deleteById(id);
	}

	public Usuario update(Usuario usuario) {
		findById(usuario.getId());
		return repository.saveAndFlush(usuario);
	}

	public Page<Usuario> findAllPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);

	}
}
