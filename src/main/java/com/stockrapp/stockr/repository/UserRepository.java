package com.stockrapp.stockr.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stockrapp.stockr.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String>  {
  
}
