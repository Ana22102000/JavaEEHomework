package com.example.demo.Database.Repository;

import com.example.demo.Database.Entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer> {


}