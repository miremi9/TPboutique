package fr.univtours.polytech.boutique.DAO;

import java.util.List;

import fr.univtours.polytech.boutique.model.ArticleBean;



public interface ArticleDAO {

    public List<ArticleBean> getArticlesList();

    public void insertArticle(ArticleBean Article);

    public void updateArticle(ArticleBean Article);

    public ArticleBean getArticle(int idarticle2remove);
}   
