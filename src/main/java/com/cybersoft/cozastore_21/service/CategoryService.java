package com.cybersoft.cozastore_21.service;

import com.cybersoft.cozastore_21.entity.CategoryEntity;
import com.cybersoft.cozastore_21.payload.response.CategoryResponse;
import com.cybersoft.cozastore_21.repository.CategoryRepository;
import com.cybersoft.cozastore_21.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryService implements CategoryServiceImp {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategory() {
        List<CategoryEntity> list = categoryRepository.findAll();
        List<CategoryResponse> responseList = new ArrayList<>();
        for(CategoryEntity data:list){
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setId(data.getId());
            categoryResponse.setName(data.getName());
            responseList.add(categoryResponse);
        }
        return responseList;
    }
}
