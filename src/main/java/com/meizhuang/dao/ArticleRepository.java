package com.meizhuang.dao;

import com.meizhuang.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by meizhuang on 03/02/17.
 */
public interface ArticleRepository extends JpaRepository<Article,Long>,JpaSpecificationExecutor<Article> {

    //JpaSpecificationExecutor<Article>  --分页查询


    //通过title查询
    List<Article> findByTitle(String title);
    //通过推荐状态查询
    List<Article> findByRecommendStatus(Integer recommendStatus);

    //Page<Article> findAll(Specification<Article> specification, Pageable pageable);

    // public Page<Article> findArticles(Pageable pageable);

//    @Query("from Article b where b.title=:title")
//    public Article findActicleByTitle(@Param("name") String title);
}
