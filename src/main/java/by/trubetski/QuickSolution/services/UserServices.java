package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServices {
    private final UserRepositories usersRepositories;

    @Autowired
    public UserServices(UserRepositories usersRepositories) {
        this.usersRepositories = usersRepositories;
    }

}
