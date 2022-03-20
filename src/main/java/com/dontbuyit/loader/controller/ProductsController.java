package com.dontbuyit.loader.controller;

import com.dontbuyit.loader.model.ProductModel;
import com.dontbuyit.loader.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/products")
public class ProductsController {

  @Autowired
  private ProductService productService;

  @GetMapping
  public List<ProductModel> getProducts() {
    return productService.getProducts();
  }
}
