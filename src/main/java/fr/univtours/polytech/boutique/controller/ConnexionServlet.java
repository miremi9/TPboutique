package fr.univtours.polytech.boutique.controller;

import java.io.IOException;

import fr.univtours.polytech.boutique.buisness.BoutiqueBuisness;
import fr.univtours.polytech.boutique.model.UserBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet(
   name = "ConnexionServlet",
   urlPatterns = {"/connexion"}
)
public class ConnexionServlet extends HttpServlet {

  @Inject
  BoutiqueBuisness boutiqueBuisness;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println ( "dogetCOnnexiojn: ");

    RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
    dispatcher.forward(request, response);
}

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println ( "dopostCOnnexiojn: ");
    String login = request.getParameter("login");
      String password = request.getParameter("password");
      UserBean user = boutiqueBuisness.getUser(login,password);
      System.out.println ( "user: "+user);
      if (user == null)
      {
        System.out.println ( "recommence ");
        RequestDispatcher dispatcher = request.getRequestDispatcher("connexion.jsp");
        dispatcher.forward(request, response);
      }
      else
      {
        request.getSession().setAttribute("USER", user);

        response.sendRedirect(request.getContextPath() + "/articles");
         return; // Arrêt de l'exécution du code
      }

   }
}

