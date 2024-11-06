package com.robinland.pos.RobinlandPos.functions.user.repository;

import com.robinland.pos.RobinlandPos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
