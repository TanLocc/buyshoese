package com.example.buyshoes.repository;



import com.example.buyshoes.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    User findByUsername(String userName);
}

