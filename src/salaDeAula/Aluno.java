package salaDeAula;

public class Aluno {
	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", numeroDeAluno=" + numeroDeAluno + ", curso=" + curso + "]";
	}

	private String nome;
	private String numeroDeAluno;
	private String curso;

	public Aluno(String nome, String numeroDeAluno, String curso) {
		this.curso = curso;
		this.nome = nome;
		this.numeroDeAluno = numeroDeAluno;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroDeAluno() {
		return numeroDeAluno;
	}

	public void setNumeroDeAluno(String numero) {
		numeroDeAluno = numero;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

}
