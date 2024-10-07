package com.cucumb.service;

import com.cucumb.client.MockClient;
import com.cucumb.dto.ListProductDTO;
import com.cucumb.dto.MakerDTO;
import com.cucumb.dto.SaveProductDTO;
import com.cucumb.entity.ProductEntity;
import com.cucumb.repository.ProductRepository;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final MockClient mockClient;

    public ListProductDTO findById(Integer id) {
        MakerDTO makerDTO;
        try {
            makerDTO = mockClient.getMockResponse();
        } catch (FeignException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return productRepository.findById(id)
                .map(p -> new ListProductDTO(p.getId(), p.getName(), p.getPrice(), makerDTO))
                .orElseThrow(() -> new NoSuchElementException(("User not present")));
    }

    public ListProductDTO saveNew(SaveProductDTO product) {
        ProductEntity savedProduct = productRepository.save(new ProductEntity(product.name(), product.price()));

        MakerDTO makerDTO;

        try {
            makerDTO = mockClient.getMockResponse();
        } catch (FeignException ex) {
            throw new RuntimeException(ex.getMessage());
        }

        return Optional.of(savedProduct)
                .map(p -> new ListProductDTO(p.getId(), p.getName(), p.getPrice(), makerDTO))
                .orElseThrow(() -> new RuntimeException("Something went wrong while mapping product"));
    }
}
