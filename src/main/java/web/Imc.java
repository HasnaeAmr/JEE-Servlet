package web;

public class Imc {
    float taille;
    float poids;

    public Imc(float taille, float poids) {
        this.taille = taille;
        this.poids = poids;
    }

    // Calculate the BMI
    float calcul() {
        return poids / (taille * taille);
    }

    // Determine the category based on the BMI value
    public String getCategory() {
        float imc = calcul();
        if (imc < 18.5) {
            return "Insuffisance pondérale";  // Underweight
        } else if (imc < 24.9) {
            return "Poids normal";  // Normal weight
        } else if (imc < 29.9) {
            return "Surpoids";  // Overweight
        } else {
            return "Obésité";  // Obesity
        }
    }
}
