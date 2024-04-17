package fr.univtours.polytech.boutique.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.univtours.polytech.boutique.buisness.BoutiqueBuisness;
import fr.univtours.polytech.boutique.model.ArticleBean;
import fr.univtours.polytech.boutique.model.CartBean;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "PanierServlet", urlPatterns = { "/panier" })
public class PanierServlet extends HttpServlet {

   @Inject
   BoutiqueBuisness boutiqueBuisness;

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      if (request.getSession().getAttribute("USER") == null) {
         // Redirection vers le servlet /connexion
         response.sendRedirect(request.getContextPath() + "/connexion");
         return; // Arrêt de l'exécution du code
      }
      CartBean cart = (CartBean) request.getSession().getAttribute("USER_CART");
      cart = boutiqueBuisness.computeCartPrice(cart);
      request.getSession().setAttribute("USER_CART", cart);
      request.setAttribute("articles", boutiqueBuisness.getArticles());
      RequestDispatcher dispatcher = request.getRequestDispatcher("afficherPanier.jsp");
      dispatcher.forward(request, response);
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      if (request.getSession().getAttribute("USER") == null) {
         // Redirection vers le servlet /connexion
         response.sendRedirect(request.getContextPath() + "/connexion");
         return; // Arrêt de l'exécution du code
      }
      CartBean cart = (CartBean) request.getSession().getAttribute("USER_CART");
      System.out.println(cart);
      cart = boutiqueBuisness.computeCartPrice(cart);
      System.out.println(cart.getCartPrice());
      Set<Map.Entry<ArticleBean, Integer>> cartEntries = cart.getContent().entrySet();
      List<ArticleBean> articles = new ArrayList<>();

      for (Map.Entry<ArticleBean, Integer> entry : cartEntries) {
         articles.add(entry.getKey());
      }

      request.setAttribute("articles", articles);

      request.getSession().setAttribute("USER_CART", cart);
      request.setAttribute("articles", cartEntries);
      System.out.println(cart.getContent().entrySet());
      System.out.println(cart.getContent().size());
      RequestDispatcher dispatcher = request.getRequestDispatcher("afficherPanier.jsp");
      dispatcher.forward(request, response);
   }

}
