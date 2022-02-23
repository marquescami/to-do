package com.todo.ToDoDesafio.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.todo.ToDoDesafio.domain.util.Prioridade;
import com.todo.ToDoDesafio.domain.util.StatusTarefa;

@Entity
public class Tarefa implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.ORDINAL)
	private Prioridade prioridadeTarefa;
	
	@Enumerated(EnumType.ORDINAL)
	private StatusTarefa statusTarefa;
	
	@Column(nullable = false)
	private String descricaoTarefa;
	
	@OneToOne
	@JoinColumn(name = "usuario_id_fk")
	private Usuario usuario;
	
	
	public Tarefa() {
		super();
	}

	public Tarefa(Prioridade prioridadeTarefa, StatusTarefa statusTarefa, String descricaoTarefa) {
		super();
		this.prioridadeTarefa = prioridadeTarefa;
		this.statusTarefa = statusTarefa;
		this.descricaoTarefa = descricaoTarefa;
	}

	public void excluiTarefa (String id) {
		
	}
	
	public void alteraTarefa(String idTarefa, String descricaoTarefa, Prioridade prioridadeTarefa) {
		this.descricaoTarefa = descricaoTarefa;
		this.prioridadeTarefa =prioridadeTarefa;
	}
	
	@Override
	public String toString() {
		return "Tarefa [id=" + id + ", prioridadeTarefa=" + prioridadeTarefa + ", statusTarefa=" + statusTarefa
				+ ", descricaoTarefa=" + descricaoTarefa + ", usuario=" + usuario + "]";
	}

	public void concluiTarefa (Integer id) {
		this.id = id;
		this.statusTarefa = StatusTarefa.CONCLUIDA;
	}
	
	public void verificaUsuario() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descricaoTarefa, id, prioridadeTarefa, statusTarefa, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarefa other = (Tarefa) obj;
		return Objects.equals(descricaoTarefa, other.descricaoTarefa) && Objects.equals(id, other.id)
				&& prioridadeTarefa == other.prioridadeTarefa && statusTarefa == other.statusTarefa
				&& Objects.equals(usuario, other.usuario);
	}

	public Prioridade getPrioridadeTarefa() {
		return prioridadeTarefa;
	}

	public void setPrioridadeTarefa(Prioridade prioridadeTarefa) {
		this.prioridadeTarefa = prioridadeTarefa;
	}

	public StatusTarefa getStatusTarefa() {
		return statusTarefa;
	}

	public void setStatusTarefa(StatusTarefa statusTarefa) {
		this.statusTarefa = statusTarefa;
	}

	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}

	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}
