package fr.univtours.polytech.boutique.controller;

import java.io.IOException;

import fr.univtours.polytech.boutique.buisness.BoutiqueBuisness;
import fr.univtours.polytech.boutique.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "ArticleServlet", urlPatterns = { "/articles" })
public class ArticleServlet extends HttpServlet {
   @Inject
   BoutiqueBuisness boutiqueBuisness;

   public ArticleServlet() {
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (request.getSession().getAttribute("USER") == null) {
         // Redirection vers le servlet /connexion
         response.sendRedirect(request.getContextPath() + "/connexion");
         return; // Arrêt de l'exécution du code
      }
      CartBean cartBean = new CartBean();
      request.getSession().setAttribute("USER_CART", cartBean);
      request.setAttribute("articles", boutiqueBuisness.getArticles());
      RequestDispatcher dispatcher = request.getRequestDispatcher("ListeArticle.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      if (request.getSession().getAttribute("USER") == null) {
         // Redirection vers le servlet /connexion
         response.sendRedirect(request.getContextPath() + "/connexion");
         return; // Arrêt de l'exécution du code
      }
      String article2add = request.getParameter("article2add");
      String article2remove = request.getParameter("article2remove");
      CartBean cart = (CartBean) request.getSession().getAttribute("USER_CART");
      if (article2add != null) {
         cart = boutiqueBuisness.addArticle(cart, Integer.parseInt(article2add));
      }
      if (article2remove != null) {
         cart = boutiqueBuisness.removeArticle(cart, Integer.parseInt(article2remove));
      }
      request.getSession().setAttribute("USER_CART", cart);

      request.setAttribute("articles", boutiqueBuisness.getArticles());
      RequestDispatcher dispatcher = request.getRequestDispatcher("ListeArticle.jsp");
      dispatcher.forward(request, response);
   }
}