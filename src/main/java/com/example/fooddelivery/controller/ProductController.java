package com.example.fooddelivery.controller;

import com.example.fooddelivery.model.Order;
import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.model.dto.AddOrderProductRequest;
import com.example.fooddelivery.model.dto.OrderDto;
import com.example.fooddelivery.model.dto.ProductDto;
import com.example.fooddelivery.service.OrderService;
import com.example.fooddelivery.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductDto> saveProduct(@RequestBody ProductDto productDto){
        return ResponseEntity.of(Optional.of(productService.saveProduct(productDto)));
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable("id") Long id){
        Optional<Product> optionalProduct = productService.findProductById(id);
        if(optionalProduct.isPresent()){
            ProductDto productDto = ProductDto.entityToDto(optionalProduct.get());
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete-by-id/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable("id") Long id){
        boolean result = productService.deleteProduct(id);
        if(result){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto dto){
        ProductDto result = productService.updateProduct(id, dto);
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/add-orders")
    public ResponseEntity<ProductDto> addOrderProduct(@RequestBody AddOrderProductRequest addOrderProductRequest){
        ProductDto result = productService.addOrderProduct(addOrderProductRequest.getOrderId(),
                addOrderProductRequest.getOrderProductId(), addOrderProductRequest.getQuantity());
        if(result == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

}
