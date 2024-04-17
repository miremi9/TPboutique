package fr.univtours.polytech.boutique.DAO;

import java.util.List;

import fr.univtours.polytech.boutique.model.UserBean;

public interface UserDAO {
    public List<UserBean> getUserList();

}
