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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.todo.ToDoDesafio.domain.Usuario;
import com.todo.ToDoDesafio.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/")
@Api(value = "API REST usuário")
@CrossOrigin(origins = "*")

public class UsuarioResource {
	@Autowired
	private UsuarioService service;

	@ApiOperation(value = "Retorna um usuario por id")
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {
		Usuario usuario = service.findById(id);
		return ResponseEntity.ok().body(usuario);
	}

	@ApiOperation(value = "Retorna todos os usuarios")
	@GetMapping("/usuarios")
	public ResponseEntity<?> findAll() {
		List<Usuario> usuarios = service.findAll();
		return ResponseEntity.ok().body(usuarios);
	}

	@ApiOperation(value = "Salva um usuario")
	@PostMapping("/usuarios")
	public ResponseEntity<?> Save(@RequestBody Usuario usuario) {
		usuario = service.save(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@ApiOperation(value = "Deleta um usuario por id")
	@DeleteMapping("/usuarios/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}