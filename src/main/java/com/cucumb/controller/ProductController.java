package com.cucumb.controller;

import com.cucumb.dto.ListProductDTO;
import com.cucumb.dto.SaveProductDTO;
import com.cucumb.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ListProductDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ListProductDTO> saveNew(@RequestBody SaveProductDTO product) {
        return ResponseEntity.ok(productService.saveNew(product));
    }
}
