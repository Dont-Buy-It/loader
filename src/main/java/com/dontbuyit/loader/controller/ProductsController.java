package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductsController {

    private final ProductService productService;

    @GetMapping
    public List<ProductModel> getProducts() {
        return productService.getProducts();
    }
}
