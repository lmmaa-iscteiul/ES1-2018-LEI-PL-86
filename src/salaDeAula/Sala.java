package salaDeAula;

import java.util.ArrayList;

public class Sala {
	private String bloco;
	private int numero;
	private int capacidade;
	private ArrayList<Aluno> alunos;

	public Sala(String bloco, int numero, int capacidade) {
		this.bloco = bloco;
		this.numero = numero;
		this.capacidade = capacidade;
		alunos = new ArrayList<Aluno>();
	}

	public void adicionaAlunos(Aluno aluno) {
		if (alunos.size() != capacidade) {
			alunos.add(aluno);
		}

	}

	// procurar o aluno com o parâmetro dado na lista de de alunos
	public Aluno procuraAluno(String id) {
		for (Aluno a : alunos) {
			if (a.getNumeroDeAluno().equals(id)) {
				return a;
			}
		}
		return null;

	}

	// remover o aluno com o id dado ao método da lista de alunos.
	public void removeAluno(String id) {
		Aluno a = procuraAluno(id);
		if (a != null) {
			alunos.remove(a);
		} else
			System.out.println("Não foi encontrado o aluno " + "id");

	}

	// mostrar os alunos presentes na sala de aula.
	public void listaDeAlunos() {
		for (Aluno aluno : alunos) {
			aluno.toString();
			System.out.println(aluno);
		}
	}

	public String getBloco() {
		return bloco;
	}

	public void setBloco(String bloco) {
		this.bloco = bloco;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
