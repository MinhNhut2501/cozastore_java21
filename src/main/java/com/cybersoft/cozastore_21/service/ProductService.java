package com.cybersoft.cozastore_21.service;

import com.cybersoft.cozastore_21.entity.ProductEntity;
import com.cybersoft.cozastore_21.payload.response.ProductResponse;
import com.cybersoft.cozastore_21.repository.ProductRepository;
import com.cybersoft.cozastore_21.service.imp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceImp {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getProductByCategory(int id) {
        List<ProductEntity> list = productRepository.findByCategoryId(id);
        List<ProductResponse> responses = new ArrayList<>();
        for (ProductEntity data:list){
            ProductResponse response = new ProductResponse();
            response.setId(data.getId());
            response.setName(data.getName());
            response.setPrice(data.getPrice());
            response.setImage(data.getImage());
            responses.add(response);
        }

        return responses;
    }
}
