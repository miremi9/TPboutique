package fr.univtours.polytech.boutique.buisness;

import java.util.List;

import fr.univtours.polytech.boutique.model.ArticleBean;
import fr.univtours.polytech.boutique.model.CartBean;
import fr.univtours.polytech.boutique.model.UserBean;


public interface BoutiqueBuisness {

    public void updateArticle(ArticleBean article);
    public List<ArticleBean> getArticles();
    public UserBean getUser(String login,String password);
    public CartBean removeArticle(CartBean cart, int idarticle2remove);
    public CartBean addArticle(CartBean cart, int idarticle2add);
    public CartBean computeCartPrice(CartBean cart);
} 