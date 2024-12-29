package com.pradeep.CrudOperationBase.repository;

import com.pradeep.CrudOperationBase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
