package com.meizhuang.services;


import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by meizhuang on 03/02/17.
 */
public interface ArticleService {

    String test();

    Article addArticle(Article article);

    Article addArticle(Integer creatorId, Long createTimeMillis,
                       Long recordTimeMillis, String title,
                       String content,
                       String author, String fromUrl,
                       Integer type, Integer status);


    List<Article> getAllArticles();

    Article getArticleById(Long id);

    void deleteArticleById(Long id);

    List<Article> findByTitle(String title);

    Article findActicleByTitle(String title);

    Page<Article> findArticlesCriteria(Integer page, Integer size, ArticleQuery articleQuery);

    Page<Article> findArticlesNoCriteria(Integer page, Integer size);

    // public Page<Article> findArticles(Pageable pageable);
}
