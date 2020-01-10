package com.app.service;

import com.app.dto.CompanyDTO;
import com.app.dto.ProductDTO;
import com.app.exceptions.AppException;
import com.app.exceptions.ExceptionCodes;
import com.app.mappers.ProductMapper;
import com.app.model.Cart;
import com.app.repository.CartRepository;
import com.app.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    public List<ProductDTO> getProductsOfCompany(CompanyDTO companyDTO) {
        if (companyDTO == null) {
            throw new AppException(ExceptionCodes.SERVICE_PRODUCT, "getProductsOfCompany - company is null");
        }

        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCompany().getId().equals((companyDTO.getId())))
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDTO getOneProduct(Long id) {
        if (id == null) {
            throw new AppException(ExceptionCodes.SERVICE_PRODUCT, "getOneProduct - id is null");
        }
        if (id < 0) {
            throw new AppException(ExceptionCodes.SERVICE_PRODUCT, "getOneProduct - id less than zero");
        }
        return productRepository
                .findById(id)
                .map(ProductMapper::toDto)
                .orElseThrow(() -> new AppException(ExceptionCodes.SERVICE_PRODUCT, "getOneProduct - no product with ID: " + id));
    }

    public List<ProductDTO> getProductsOfCart(Long cartId) {
        if (cartId == null) {
            throw new AppException(ExceptionCodes.SERVICE_PRODUCT, "getProductsOfCart - cartId is null");
        }
        if (cartId < 0) {
            throw new AppException(ExceptionCodes.SERVICE_PRODUCT, "getProductsOfCart - cartId less than zero");
        }
        Cart cartFromDb = cartRepository.getOne(cartId);

        return cartFromDb.getProducts()
                .stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }
}