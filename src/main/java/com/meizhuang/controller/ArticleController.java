package com.meizhuang.controller;

import com.meizhuang.constant.Constant;
import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import com.meizhuang.services.ArticleServiceImpl;
import com.meizhuang.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by meizhuang on 03/02/17.
 */

@RestController
public class ArticleController {

    public static String TAG = "ArticleController";

    //http://localhost:8189/meizhuang/say
    @GetMapping(value = "/say")
    public String say() {
        return "Hello SpringBoot ";
    }

    //http://localhost:8189/meizhuang/test
    @GetMapping(value = "/test")
    public String test() {
        return articleService.test();
    }


    @Autowired
    private ArticleServiceImpl articleService;

    /**
     * http://localhost:8190/meizhuang/addArticle?creatorId=1&createTimeMillis=1001&recordTimeMillis=1002&title=美妆&content=教你美妆&author=匿名&fromUrl=http://xxx.com&type=1&status=2
     * 添加文章
     *
     * @param creatorId
     * @param createTimeMillis
     * @param recordTimeMillis
     * @param title
     * @param content
     * @param author
     * @param fromUrl
     * @param type
     * @param recommendStatus
     * @return
     * @throws UnsupportedEncodingException
     */
    @PostMapping(value = "/addArticleD", produces = "application/json; charset=utf-8")
    public Article articleAdd(@RequestParam("creatorId") Integer creatorId,
                              @RequestParam("createTimeMillis") Long createTimeMillis,
                              @RequestParam("recordTimeMillis") Long recordTimeMillis,
                              @RequestParam("title") String title,
                              @RequestParam("subTitle") String subTitle,
                              @RequestParam("coverImgUrl") String coverImgUrl,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author,
                              @RequestParam("fromUrl") String fromUrl,
                              @RequestParam("type") Integer type,
                              @RequestParam("recommendStatus") Integer recommendStatus) throws UnsupportedEncodingException {

        return articleService.addArticle(creatorId, createTimeMillis, recordTimeMillis, title, content, author, fromUrl, type, recommendStatus);
    }


    @PostMapping(value = "/addArticle", produces = "application/json; charset=utf-8")
    public Article articleAdd(@RequestParam("creatorId") Integer creatorId,
                              @RequestParam("title") String title,
                              @RequestParam("subTitle") String subTitle,
                              @RequestParam("coverImgUrl") String coverImgUrl,
                              @RequestParam("content") String content,
                              @RequestParam("author") String author,
                              @RequestParam("fromUrl") String fromUrl,
                              @RequestParam("type") Integer type,
                              @RequestParam("recommendStatus") Integer recommendStatus) throws UnsupportedEncodingException {

        return articleService.addArticle(creatorId,title, subTitle,content, author, fromUrl,coverImgUrl, type, recommendStatus);
    }



    /**
     * http://localhost:8190/meizhuang/articles
     * 获取所有文章列表
     *
     * @return
     */
    @GetMapping(value = "/articles")
    public List<Article> getArticleList() {
        PrintUtil.printTag(TAG, "articleList");
        return articleService.getAllArticles();
    }

    /**
     * http://localhost:8189/meizhuang/article/id/2
     * 通过文章id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/article/id/{id}")
    public Article getArticleById(@PathVariable("id") Long id) {
        PrintUtil.printTag(TAG, id);
        return articleService.getArticleById(id);
    }

    /**
     * post
     * http://localhost:8190/meizhuang/articleDeleteById?id=2
     * 根据ID删除文章
     *
     * @param id
     */
    @PostMapping(value = "/articleDeleteById")
    public void articleDeleteById(@RequestParam("id") Long id) {
        PrintUtil.printTag(TAG, id);
        articleService.deleteArticleById(id);
    }

    /**
     * http://localhost:8190/meizhuang/article/title/美妆3
     * 通过文章title查询
     *
     * @param title
     * @return
     */
    @GetMapping(value = "/article/title/{title}")
    public List<Article> articleFindByTitle(@PathVariable("title") String title) {
        return articleService.findByTitle(title);
    }


    /**
     * http://localhost:8190/meizhuang/findarticlePages?page=0&size=4
     * or
     * http://localhost:8190/meizhuang/findarticlepages
     * https://my.oschina.net/wangxincj/blog/820670
     * 分页查询
     * @RequestMapping("/findarticlepages") //不区分get和post
     * @param modelMap
     * @param page
     * @param size
     * @return
     */
   // @RequestMapping("/findarticlepages")
    @GetMapping("/findarticlepages")
    public  Page<Article> findBookNoQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                  @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<Article> datas = articleService.findArticlesNoCriteria(page, size);
        modelMap.addAttribute("datas", datas);
        return datas;
    }

    /**
     * http://localhost:8190/meizhuang/findarticlePages?page=0&size=4
     * or
     * http://localhost:8190/meizhuang/findarticlepagesquery
     * https://my.oschina.net/wangxincj/blog/820670
     * 分页查询
     * @RequestMapping("/findarticlepages") //不区分get和post
     * @param modelMap
     * @param page
     * @param size
     * @return
     */
   //  @RequestMapping(value = "/findarticlepagesquery",method = {RequestMethod.GET,RequestMethod.POST})
    @RequestMapping(value = "/findarticlepagesquery",method = {RequestMethod.GET})
    public Page<Article> findBookQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                @RequestParam(value = "size", defaultValue = "2") Integer size, ArticleQuery bookQuery){
        Page<Article> datas = articleService.findArticlesCriteria(page, size, bookQuery);
        modelMap.addAttribute("datas", datas);
        return datas;
    }


    //   @RequestMapping("/findBookNoQuery")
//    public ModelMap findBookNoQuery(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                  @RequestParam(value = "size", defaultValue = "1") Integer size){
//        Page<Article> datas = articleService.findArticleNoCriteria(page, size);
//        modelMap.addAttribute("datas", datas);
//        return modelMap;
//    }

    /**
     *
     * @param modelMap
     * @param page
     * @param size
     * @param sortDirection
     * @param recommendStatus
     * @return
     */

    @RequestMapping(value = "/findarticlepagesquery1",method = {RequestMethod.GET})
    public Page<Article> findBookQuery1(ModelMap modelMap,
                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "2") Integer size,
                                        @RequestParam(value = "recommendStatus", defaultValue = "0") Integer recommendStatus,
                                        @RequestParam(value = "sortDirection", defaultValue = "0") Integer sortDirection
                                       ){

        Page<Article> datas = articleService.findArticlesByRecommendStatus(page, size, recommendStatus,sortDirection);
        modelMap.addAttribute("datas", datas);
        return datas;
    }





}
