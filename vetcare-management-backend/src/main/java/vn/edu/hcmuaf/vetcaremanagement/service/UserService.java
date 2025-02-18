package vn.edu.hcmuaf.vetcaremanagement.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.vetcaremanagement.dao.UserDao;
import vn.edu.hcmuaf.vetcaremanagement.dto.request.UserCreationRequest;
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


    public void createUser(UserCreationRequest request) {
        if (userDao.getUserByUsername(request.getUsername()).isPresent())
            throw new AppException(ErrorCode.USER_EXISTED);
        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .age(request.getAge()).build();
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