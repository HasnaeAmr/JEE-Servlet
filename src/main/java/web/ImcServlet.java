package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet implementation class ImcServlet
 */
@WebServlet("/ImcServlet")
public class ImcServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ImcServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String p = request.getParameter("poids");  // Récupérer le poids
            String t = request.getParameter("taille"); // Récupérer la taille

            // Validate input
            if (p == null || t == null || p.isEmpty() || t.isEmpty()) {
                out.println("<h3>Erreur: Veuillez entrer un poids et une taille valides.</h3>");
                return;
            }

            try {
                // Convertir les valeurs en double
                float poids = Float.parseFloat(p);
                float taille = Float.parseFloat(t);

                // Ensure that taille and poids are positive values
                if (poids <= 0 || taille <= 0) {
                    out.println("<h3>Erreur: Le poids et la taille doivent être des valeurs positives.</h3>");
                    return;
                }

                Imc imc = new Imc(taille, poids);

                // Afficher les résultats
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<title>Résultat IMC</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Résultat IMC</h2>");
                out.println("<p>Poids: " + imc.poids + " kg</p>");
                out.println("<p>Taille: " + imc.taille + " m</p>");
                out.println("<p>IMC: " + imc.calcul() + "</p>");
                
                // Optional: Display IMC categories
                String category = imc.getCategory();
                out.println("<p>Catégorie: " + category + "</p>");
                out.println("</body>");
                out.println("</html>");
            } catch (NumberFormatException e) {
                out.println("<h3>Erreur: Veuillez entrer des nombres valides pour le poids et la taille.</h3>");
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

class Imc {
    float taille;
    float poids;

    public Imc(float taille, float poids) {
        this.taille = taille;
        this.poids = poids;
    }

    public float calcul() {
        return poids / (taille * taille);
    }

    public String getCategory() {
        float imc = calcul();
        if (imc < 18.5) {
            return "Insuffisance pondérale";
        } else if (imc < 24.9) {
            return "Poids normal";
        } else if (imc < 29.9) {
            return "Surpoids";
        } else {
            return "Obésité";
        }
    }
}
