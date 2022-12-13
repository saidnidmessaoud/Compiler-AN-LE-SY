package net.mips.compiler;

public class Symboles {
	private Tokens token;
	private String nom;
	private ClasseIdf classe;
	
	public ClasseIdf getClasse() {
		return classe;
	}
	public void setClasse(ClasseIdf classe) {
		this.classe = classe;
	}

	public Tokens getToken() {
		return token;
	}
	public void setToken(Tokens token) {
		this.token = token;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Symboles(Tokens token, String nom) {
		this.token = token;
		this.nom = nom;
	}
	public Symboles(Tokens token, String nom,ClasseIdf cidf) {
		this.token = token;
		this.nom = nom;
		this.classe=cidf;
	}
	public Symboles() {
		this.nom="";
	}
	
	
	
}
