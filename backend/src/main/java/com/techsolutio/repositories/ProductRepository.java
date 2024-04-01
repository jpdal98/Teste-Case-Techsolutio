package com.techsolutio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techsolutio.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
