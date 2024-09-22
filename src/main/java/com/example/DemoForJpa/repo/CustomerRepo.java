package com.example.DemoForJpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DemoForJpa.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer,Long>{

}
