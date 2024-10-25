package web;

public class Imc {
	float taille;
	float poids;
	
	public Imc(float taille, float poids) {
		this.taille=taille;
		this.poids=poids;
	}
	
	float calcul() {
		return poids / (taille * taille);
	}

}


