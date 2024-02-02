package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.Orders;
import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.repositories.UsersRepositories;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UsersServices {
    private final UsersRepositories usersRepositories;

    @Autowired
    public UsersServices(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }
    public Users login(String username, String password) {
        return usersRepositories.findByUsernameAndPassword(username, password);
    }
    @Transactional
    public void createUser(Users users) {
        if (usersRepositories.findByUsername(users.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        if (usersRepositories.findByEmail(users.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        usersRepositories.save(users);
    }
    public Users findOne(int id){
        Optional<Users> usersId = usersRepositories.findById(id);
        return usersId.orElse(null);
    }


}
