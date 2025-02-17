package vn.edu.hcmuaf.vetcaremanagement.dao;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import vn.edu.hcmuaf.vetcaremanagement.model.User;

import java.util.List;

@RegisterBeanMapper(User.class)
public interface UserDao {

    @SqlUpdate("INSERT INTO users (username, password, email, age, gender) VALUES (:username, :password, :email, :age, :gender)")
    void createUser(@BindBean User user);

    @SqlQuery("SELECT * FROM users")
    List<User> getAllUsers();


}