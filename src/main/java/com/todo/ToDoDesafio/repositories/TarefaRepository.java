package com.todo.ToDoDesafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.todo.ToDoDesafio.domain.Tarefa;
import com.todo.ToDoDesafio.domain.Usuario;
import com.todo.ToDoDesafio.domain.util.Prioridade;
import com.todo.ToDoDesafio.domain.util.StatusTarefa;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

//	@Query("select a from Tarefa t where t.prioridadeTarefa>=?1 and a.dataFim<=?2 and a.cliente=?3 order by a.dataInicio")
	public List<Tarefa> findByStatusTarefa(StatusTarefa statusTarefa);

	public List<Tarefa> findByPrioridadeTarefaAndStatusTarefa(Prioridade prioridade, StatusTarefa statusTarefa);

	public List<Tarefa> findByUsuario(Usuario usuario);

}
