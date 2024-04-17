package fr.univtours.polytech.boutique.buisness;

import java.util.Enumeration;
import java.util.List;

import fr.univtours.polytech.boutique.DAO.ArticleDAO;
import fr.univtours.polytech.boutique.DAO.UserDAO;
import fr.univtours.polytech.boutique.model.ArticleBean;
import fr.univtours.polytech.boutique.model.CartBean;
import fr.univtours.polytech.boutique.model.UserBean;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class BoutiqueBuisnessImpl implements BoutiqueBuisness{
    @Inject
    private ArticleDAO articleDAO;
    @Inject
    private UserDAO userDAO;
    
    @Override
    public void updateArticle(ArticleBean article) {
        articleDAO.updateArticle(article);
    }

    @Override
    public UserBean getUser(String login, String password) {
        
       java.util.List<UserBean> listUser = userDAO.getUserList();
        for (UserBean userBean : listUser) {
            if (userBean.getLogin().equals(login) && userBean.getPassword().equals(password))
            {
                return userBean;
            }
        }
        return null;
    }

    @Override
    public List<ArticleBean> getArticles() {
        return articleDAO.getArticlesList();
    }

    @Override
    public CartBean removeArticle(CartBean cart, int idarticle2remove) {
        ArticleBean article2remove = articleDAO.getArticle(idarticle2remove);
        if (cart == null || article2remove == null)
        {
            System.out.println("null");
            return cart;
        }

         if (cart.getNumberArticle(article2remove)>0)
         {
            System.out.println("retire");
            cart.removeArticle(article2remove);
            article2remove.setNbRestant(article2remove.getNbRestant()+1);
            articleDAO.updateArticle(article2remove);
         }
         return cart;

    }

    @Override
    public CartBean addArticle(CartBean cart, int idarticle2add) {
        ArticleBean article2add = articleDAO.getArticle(idarticle2add);
        if (cart == null || article2add == null)
        {
            System.out.println("null");
            return cart;
        }

         if (article2add.getNbRestant()>0)
         {
            System.out.println("ajoute");
            cart.addArticle(article2add);
            article2add.setNbRestant(article2add.getNbRestant()-1);
            articleDAO.updateArticle(article2add);
         }
         else
         {
            System.out.println("vide");
         }
         return cart;
    }

    @Override
    public CartBean computeCartPrice(CartBean cart) {
        if (cart == null)
        {
            return cart;
        }
        Double price = 0.0;
       Enumeration<ArticleBean> keys = cart.getContent().keys();
        while (keys.hasMoreElements()) {
            ArticleBean article = keys.nextElement();
            Integer nbArticles = cart.getContent().get(article);
            price += article.getPrice()*nbArticles;
        }
        cart.setCartPrice(price);
        return cart;
    }
    
}
