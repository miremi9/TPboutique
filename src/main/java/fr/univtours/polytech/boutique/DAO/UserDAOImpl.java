package fr.univtours.polytech.boutique.DAO;

import java.util.List;

import fr.univtours.polytech.boutique.model.UserBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UserDAOImpl implements UserDAO{
    @PersistenceContext(unitName = "MySqlGestionBoutiqueJPA")
    private EntityManager em;

@Override
@SuppressWarnings("unchecked")
public List<UserBean> getUserList() {
        // Exemple de requête HQL (ou JPAQL).
        Query requete = em.createNativeQuery("select * from user", UserBean.class);
        return requete.getResultList();
    }
}