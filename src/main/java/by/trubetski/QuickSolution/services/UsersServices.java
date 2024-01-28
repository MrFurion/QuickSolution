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
    public List<Users> findAll(){
        return usersRepositories.findAll();
    }
    public Users findOne(int id){
        Optional<Users> usersId = usersRepositories.findById(id);
        return usersId.orElse(null);
    }
    public List<Orders> getOrdersOfPerson(int id){
        Optional<Users> person = usersRepositories.findById(id);

        if(person.isPresent()){
            Hibernate.initialize(person.get().getOrders());
            return person.get().getOrders();
        }
        else {
            return Collections.emptyList();
        }
    }
    @Transactional
    public void save(Users users){
        usersRepositories.save(users);
    }
}
