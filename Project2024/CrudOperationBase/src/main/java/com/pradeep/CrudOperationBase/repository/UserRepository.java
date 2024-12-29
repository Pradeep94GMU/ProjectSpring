package com.pradeep.CrudOperationBase.repository;

import com.pradeep.CrudOperationBase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContainingOrEmailContainingOrPhoneContaining(String name, String email, String phone);
}
