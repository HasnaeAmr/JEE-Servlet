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

            // Convertir les valeurs en double
            float poids = Float.parseFloat(p);
            float taille = Float.parseFloat(t);
            
            Imc imc = new Imc(taille,poids);

            // Afficher les résultats
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<title>Résultat IMC</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("Poids: " + imc.poids + " kg<br>");
            out.println("Taille: " + imc.taille + " m<br>");
            out.println("IMC: " + imc.calcul() + "<br>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
