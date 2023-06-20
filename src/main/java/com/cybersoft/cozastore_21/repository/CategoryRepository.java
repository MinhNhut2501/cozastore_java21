package com.cybersoft.cozastore_21.repository;

import com.cybersoft.cozastore_21.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Integer> {


}
