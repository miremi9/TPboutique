package fr.univtours.polytech.boutique.model;

import java.util.Hashtable;
import java.util.Map;

public class CartBean implements java.io.Serializable {
    Hashtable<ArticleBean,Integer> Content ;
    public Double getCartPrice() {
        return cartPrice;
    }
    public void setCartPrice(Double cartPrice) {
        this.cartPrice = cartPrice;
    }
    private Double cartPrice;
    public CartBean()
    {
        cartPrice = 0.0;
        Content = new Hashtable<ArticleBean,Integer>();

    }
    public Hashtable<ArticleBean, Integer> getContent() {
        return Content;
    }


    public void addArticle(ArticleBean article) {
        for (Map.Entry<ArticleBean, Integer> entry : Content.entrySet()) {
            ArticleBean tempArticle = entry.getKey();
            Integer number = entry.getValue();
            if (tempArticle.getId() == article.getId())
            {
                Content.put(tempArticle, number + 1);
                return;
            }
        }
        Content.put(article,1);
    }

         
    public Integer getNumberArticle(ArticleBean article)
    {
        if (article == null)
        {
            return 0;
        }
        for (Map.Entry<ArticleBean, Integer> entry : Content.entrySet()) {
            
            ArticleBean tempArticle = entry.getKey();
            Integer number = entry.getValue();
            System.out.println(tempArticle.getName());
            if (tempArticle.getId() == article.getId())
            {
                
                return number;
            }
        }
        return 0;
    }
    public void removeArticle(ArticleBean article2remove) {
        for (Map.Entry<ArticleBean, Integer> entry : Content.entrySet()) {
            ArticleBean tempArticle = entry.getKey();
            Integer number = entry.getValue();
            if (tempArticle.getId() == article2remove.getId()) {
                if (number > 1) {
                    Content.put(tempArticle, number - 1); // Décrémentez la quantité si elle est supérieure à 1
                } else {
                    Content.remove(tempArticle); // Supprimez l'article si la quantité est égale à 1
                }
                return;
            }
        }
    }
    
    
    

}
