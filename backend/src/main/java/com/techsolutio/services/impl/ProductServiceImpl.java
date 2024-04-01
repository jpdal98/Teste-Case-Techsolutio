package com.techsolutio.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techsolutio.domain.Product;
import com.techsolutio.domain.dtos.ProductDTO;
import com.techsolutio.repositories.ProductRepository;
import com.techsolutio.services.ProductService;
import com.techsolutio.services.exceptions.DataBaseException;
import com.techsolutio.services.exceptions.ObjectNotFoundException;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		copyDtoToEntity(dto, entity);
		entity = productRepository.save(entity);
		return new ProductDTO(entity);
		
	}

	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Id not found " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}
	
	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = productRepository.getById(id);
			copyDtoToEntity(dto, entity);
			entity = productRepository.save(entity);
			return new ProductDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ObjectNotFoundException("Id not found " + id);
		}
	}

	@Transactional(readOnly = true)
	public List<ProductDTO> findAll() {
		List<Product> list = productRepository.findAll();
		List<ProductDTO> listDTO = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());
		return listDTO;
	}

	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product entity = obj.orElseThrow(()-> new ObjectNotFoundException("Object not found"));
		return new ProductDTO(entity);
	}
	
	private void copyDtoToEntity(ProductDTO dto, Product entity) {
		entity.setName(dto.getName());
		entity.setSupplier(dto.getSupplier());
		entity.setPrice(dto.getPrice());
	}
}
