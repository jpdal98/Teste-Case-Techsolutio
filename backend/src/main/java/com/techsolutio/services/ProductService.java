package com.techsolutio.services;

import java.util.List;

import com.techsolutio.domain.dtos.ProductDTO;

public interface ProductService {
	ProductDTO insert(ProductDTO dto);
	
	void delete(Long id);
	
	ProductDTO update(Long id, ProductDTO dto);
	
	List<ProductDTO> findAll();
	
	ProductDTO findById(Long id);
}