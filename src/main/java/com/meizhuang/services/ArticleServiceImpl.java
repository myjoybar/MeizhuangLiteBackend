package com.meizhuang.services;


import com.meizhuang.constant.Constant;
import com.meizhuang.dao.ArticleRepository;
import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by meizhuang on 03/02/17.
 */

@Service
public class ArticleServiceImpl implements ArticleService {


    @Autowired
    private ArticleRepository articleRepository;


    @Override
    public String test() {
        return "hello";
    }

    @Override
    public Article addArticle(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public Article addArticle(Integer creatorId, Long createTimeMillis, Long recordTimeMillis,
                              String title, String content, String author, String fromUrl, Integer type, Integer recommendStatus) {
        Article article = new Article();
        article.setCreatorId(creatorId);
        article.setCreateTimeMillis(createTimeMillis);
        article.setRecordTimeMillis(recordTimeMillis);
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(author);
        article.setFromUrl(fromUrl);
        article.setType(type);
        article.setRecommendStatus(recommendStatus);
        return articleRepository.save(article);
    }

    @Override
    public Article addArticle(Integer creatorId, String title, String subTitle, String content, String author, String fromUrl, String coverImgUrl, Integer type, Integer recommendStatus) {
        Article art = new Article();
        art.setCreatorId(creatorId);
        art.setCreateTimeMillis(System.currentTimeMillis());
        art.setRecordTimeMillis(System.currentTimeMillis());
        art.setTitle(title);
        art.setSubTitle(subTitle);
        art.setContent(content);
        art.setAuthor(author);
        art.setFromUrl(fromUrl);
        art.setCoverImgUrl(coverImgUrl);
        art.setType(type);
        art.setRecommendStatus(recommendStatus);
        return articleRepository.save(art);
    }


    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) {
        return articleRepository.findOne(id);
    }

    @Override
    public void deleteArticleById(Long id) {
        articleRepository.delete(id);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findByTitle(title);
    }


    @Override
    public List<Article> findByRecommendStatus(int recommendStatus) {
        return articleRepository.findByRecommendStatus(recommendStatus);
    }

    @Override
    public Article findActicleByTitle(String title) {
        // return articleRepository.findActicleByTitle(title);
        return null;
    }


    @Override
    public Page<Article> findArticlesNoCriteria(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        return articleRepository.findAll(pageable);
    }

    @Override
    public Page<Article> findArticlesByRecommendStatus(Integer page, Integer size, Integer recommendStatus, int sortDirection) {

        Pageable pageable = null;
        if (sortDirection == Constant.SORT_DIRECTION_DESC) {
            pageable = new PageRequest(page, size, Sort.Direction.DESC, "id");
        } else {
            pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        }
        Page<Article> bookPage = articleRepository.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                list.add(criteriaBuilder.equal(root.get("recommendStatus").as(String.class), recommendStatus));
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return bookPage;
    }

    @Override
    public Page<Article> findArticlesCriteria(Integer page, Integer size, final ArticleQuery bookQuery) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Article> bookPage = articleRepository.findAll(new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<Predicate>();
                if (null != bookQuery.getAuthor() && !"".equals(bookQuery.getAuthor())) {
                    list.add(criteriaBuilder.equal(root.get("name").as(String.class), bookQuery.getAuthor()));
                }
                if (null != bookQuery.getTitle() && !"".equals(bookQuery.getTitle())) {
                    list.add(criteriaBuilder.equal(root.get("isbn").as(String.class), bookQuery.getTitle()));
                }
                if (null != bookQuery.getContent() && !"".equals(bookQuery.getContent())) {
                    list.add(criteriaBuilder.equal(root.get("author").as(String.class), bookQuery.getContent()));
                }
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
        return bookPage;
    }


//    @Override
//    public Page<Article> findArticles(Pageable pageable) {
//        return articleRepository.findArticles(pageable);
//    }


}
