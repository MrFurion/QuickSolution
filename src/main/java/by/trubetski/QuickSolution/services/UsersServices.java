package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.models.Users;
import by.trubetski.QuickSolution.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UsersServices {
    private final UsersRepositories usersRepositories;

    @Autowired
    public UsersServices(UsersRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }
    public List<Users> findAll(){
        return usersRepositories.findAll();
    }
}
