package com.opencart.product.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.opencart.product.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

}
