package com.techsolutio.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.techsolutio.domain.Product;

public class ProductDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	@Size(min = 5, max = 60, message = "Nome deve ter entre 5 e 60 caracteres!")
	@NotBlank(message = "Campo obrigatório!!")
	private String name;
	
	@Size(min = 4, max = 60, message = "Nome do fornecedor deve ter entre 5 e 60 caracteres!")
	@NotBlank(message = "Campo obrigatório!!")
	private String supplier;

	@Positive(message = "O preço deve ser um valor positivo!")
	private Double price;

	public ProductDTO() {

	}

	public ProductDTO(Long id, String name, String supplier, Double price) {
		this.id = id;
		this.name = name;
		this.supplier = supplier;
		this.price = price;
	}

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.supplier = product.getSupplier();
		this.price = product.getPrice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
}
