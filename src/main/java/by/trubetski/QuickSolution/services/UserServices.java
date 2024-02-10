package by.trubetski.QuickSolution.services;

import by.trubetski.QuickSolution.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserServices {
    private final UserRepositories userRepositories;

    @Autowired
    public UserServices(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

}
