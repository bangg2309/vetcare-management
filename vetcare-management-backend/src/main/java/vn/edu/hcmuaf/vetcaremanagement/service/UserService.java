package vn.edu.hcmuaf.vetcaremanagement.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.vetcaremanagement.dao.UserDao;
import vn.edu.hcmuaf.vetcaremanagement.exeption.AppException;
import vn.edu.hcmuaf.vetcaremanagement.exeption.ErrorCode;
import vn.edu.hcmuaf.vetcaremanagement.model.User;

import java.util.List;

@ApplicationScoped
public class UserService {
    private UserDao userDao;

    public UserService() {
    }

    @Inject
    public UserService(Jdbi jdbi) {
        this.userDao = jdbi.onDemand(UserDao.class);
    }


    public void createUser(User user) {
        userDao.createUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getUserByUsername(String username) {
        return userDao.getUserByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
    }
}