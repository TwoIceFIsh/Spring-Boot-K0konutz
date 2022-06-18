package com.kokonut.Repository;

import com.kokonut.Entity.articleEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ArticleRepository extends CrudRepository<articleEntity, Long> {
    @Override
    ArrayList<articleEntity> findAll();

}
