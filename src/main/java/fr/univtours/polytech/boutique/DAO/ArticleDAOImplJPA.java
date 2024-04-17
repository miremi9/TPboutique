package fr.univtours.polytech.boutique.DAO;

import java.util.List;

import fr.univtours.polytech.boutique.model.ArticleBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless 
public class ArticleDAOImplJPA implements ArticleDAO {

    // L'objet EntityManager qui va permettre d'effectuer les requêtes en BDD.
    @PersistenceContext(unitName = "MySqlGestionBoutiqueJPA")
    private EntityManager em;

    @SuppressWarnings("unchecked") 
    @Override
    public List<ArticleBean> getArticlesList() {
        // Exemple de requête HQL (ou JPAQL).
        Query requete = em.createNativeQuery("select * from Article", ArticleBean.class);
        return requete.getResultList();
    }

    @Override
    public void updateArticle(ArticleBean article) {
        // Pour mettre à jour un article, vous pouvez simplement utiliser la méthode merge() de l'EntityManager.
        em.merge(article);
    }
    
    @Override
    public void insertArticle(ArticleBean article) {
        // Pour insérer un nouvel article, utilisez simplement la méthode persist() de l'EntityManager.
        em.persist(article);
    }
    
    @Override
    public ArticleBean getArticle(int idarticle2remove) {
        // Utilisez la méthode find() de l'EntityManager pour obtenir l'article par son identifiant.
        return em.find(ArticleBean.class, idarticle2remove);
    }
    
}