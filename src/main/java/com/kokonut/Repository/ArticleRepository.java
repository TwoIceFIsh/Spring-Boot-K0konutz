package com.kokonut.Repository;

import com.kokonut.Entity.articleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<articleEntity, Long> {

}
