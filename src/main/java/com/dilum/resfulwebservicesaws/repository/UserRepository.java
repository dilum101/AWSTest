package com.dilum.resfulwebservicesaws.repository;

import com.dilum.resfulwebservicesaws.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


}
