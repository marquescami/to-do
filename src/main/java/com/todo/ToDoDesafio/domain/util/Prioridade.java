package com.todo.ToDoDesafio.domain.util;

public enum Prioridade {
	ALTA(0), MEDIA(1), BAIXA(2);

	private final int value;

	private Prioridade(int value) {
		this.value = value;
	}

	public String toString() {
		return Integer.toString(value);
	}

	public int getValue() {
		return value;
	}
	
}
