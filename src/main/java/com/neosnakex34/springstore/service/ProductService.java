package com.neosnakex34.springstore.service;

import com.neosnakex34.springstore.dto.ProductDTO;
import com.neosnakex34.springstore.entity.Product;
import com.neosnakex34.springstore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    private ProductDTO maptToDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getDescription(), product.getPrice());
    }

    private Product mapToEntity(ProductDTO productDTO) {
        return new Product(productDTO.getName(), productDTO.getDescription(), productDTO.getPrice());
    }

    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::maptToDTO)
                .collect(Collectors.toList());
    }

    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("product not found"));
        return maptToDTO(product);
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Product product = mapToEntity(productDTO);
        Product savedProduct = productRepository.save(product);
        return maptToDTO(savedProduct);
    }

    public ProductDTO updatedProduct(Long id, ProductDTO productDTO) {
        Product existingProduct = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found")
        );

        existingProduct.setName(productDTO.getName());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setPrice(productDTO.getPrice());

        Product updatedProduct = productRepository.save(existingProduct);
        return maptToDTO(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("product not found, cannot delete"));

        productRepository.delete(product);

    }


}
