package by.trubetski.quick.solution.repositories;

import by.trubetski.quick.solution.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositories extends JpaRepository<Item, Integer> {
}