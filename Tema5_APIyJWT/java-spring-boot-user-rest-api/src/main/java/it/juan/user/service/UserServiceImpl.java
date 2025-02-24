package it.juan.user.service;

import it.juan.user.entity.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.juan.user.dao.UserDAO;


@Service
public class UserServiceImpl implements UserService {
    // @Autowired para inyectar nuestro DAO y hacer uso de él:
    @Autowired
    private UserDAO userDAO;

    @Override
    public List<User> findAll() {
        List<User> listUsers = userDAO.findAll();
        return listUsers;
    }

    @Override
    public User findById(int id) {
        User user = userDAO.findById(id);
        return user;
    }

    @Override
    public void save(User user) {
        userDAO.save(user);

    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }


}
