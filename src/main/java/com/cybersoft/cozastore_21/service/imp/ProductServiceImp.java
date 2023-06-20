package com.cybersoft.cozastore_21.service.imp;

import com.cybersoft.cozastore_21.payload.response.ProductResponse;

import java.util.List;

public interface ProductServiceImp {
    List<ProductResponse> getProductByCategory(int id);
}
