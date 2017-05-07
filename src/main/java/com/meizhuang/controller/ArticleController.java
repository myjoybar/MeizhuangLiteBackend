package com.meizhuang.controller;

import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import com.meizhuang.result.data.BaseResultInfo;
import com.meizhuang.result.data.SuccessResult;
import com.meizhuang.services.ArticleServiceImpl;
import com.meizhuang.utils.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by meizhuang on 03/02/17.
 */

@RestController
public class ArticleController {

    public static String TAG = "ArticleController";

    //http://localhost:8190/meizhuang/say
    @GetMapping(value = "/say")
    public BaseResultInfo say() {
        //  return new ResultInfo(ResultInfo.SUCCESS_CODE,ResultInfo.SUCCESS_MSG,"Hello");
        return new SuccessResult( "hello");
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
    public BaseResultInfo articleAdd(@RequestParam("creatorId") Integer creatorId,
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

        Article article = articleService.addArticle(creatorId, createTimeMillis, recordTimeMillis, title, content, author, fromUrl, type, recommendStatus);
        SuccessResult resultInfo = new SuccessResult(article);
        return resultInfo;
    }


    @PostMapping(value = "/addArticle", produces = "application/json; charset=utf-8")
    public BaseResultInfo articleAdd(@RequestParam("creatorId") Integer creatorId,
                                 @RequestParam("title") String title,
                                 @RequestParam("subTitle") String subTitle,
                                 @RequestParam("coverImgUrl") String coverImgUrl,
                                 @RequestParam("content") String content,
                                 @RequestParam("author") String author,
                                 @RequestParam("fromUrl") String fromUrl,
                                 @RequestParam("type") Integer type,
                                 @RequestParam("recommendStatus") Integer recommendStatus) throws UnsupportedEncodingException {
        Article article = articleService.addArticle(creatorId, title, subTitle, content, author, fromUrl, coverImgUrl, type, recommendStatus);
        SuccessResult resultInfo = new SuccessResult(article);
        return resultInfo;
    }




    @PostMapping(value = "/postceshi", produces = "application/json; charset=utf-8")
    public BaseResultInfo postCeshi(@RequestParam("creatorId") Integer creatorId,
                                     @RequestParam("title") String title
                                    ) throws UnsupportedEncodingException {
        SuccessResult resultInfo = new SuccessResult("哈哈哈哈");
        return resultInfo;
    }

    /**
     * http://localhost:8190/meizhuang/articles
     * 获取所有文章列表
     *
     * @return
     */
    @GetMapping(value = "/articles")
    public BaseResultInfo getArticleList() {
        PrintUtil.printTag(TAG, "articleList");
        List<Article> articleList = articleService.getAllArticles();
        SuccessResult resultInfo = new SuccessResult( articleList);
        return resultInfo;
    }

    /**
     * http://localhost:8189/meizhuang/article/id/2
     * 通过文章id查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/article/id/{id}")
    public BaseResultInfo getArticleById(@PathVariable("id") Long id) {
        PrintUtil.printTag(TAG, id);
        Article article = articleService.getArticleById(id);
        SuccessResult resultInfo = new SuccessResult(article);
        return resultInfo;
    }

    /**
     * post
     * http://localhost:8190/meizhuang/articleDeleteById?id=2
     * 根据ID删除文章
     *
     * @param id
     */
    @PostMapping(value = "/articleDeleteById")
    public BaseResultInfo articleDeleteById(@RequestParam("id") Long id) {
        PrintUtil.printTag(TAG, id);
        articleService.deleteArticleById(id);
        SuccessResult resultInfo = new SuccessResult("");
        return resultInfo;
    }


    /**
     * post
     * http://localhost:8190/meizhuang/article/recommendstatus?id=2&recommendStatus=1
     * 根据ID设置文章推荐状态
     *
     * @param id
     */
    @PostMapping(value = "/article/recommendstatus")
    public BaseResultInfo articleRecommendById(@RequestParam("id") Long id,
                                               @RequestParam("recommendStatus") int recommendStatus) {

        Article article = articleService.getArticleById(id);
        article.setRecommendStatus(recommendStatus);

        Article  articleNew = articleService.addArticle(article);
        SuccessResult resultInfo = new SuccessResult(articleNew);
        return resultInfo;
    }




    /**
     * http://localhost:8190/meizhuang/article/title/美妆3
     * 通过文章title查询
     *
     * @param title
     * @return
     */
    @GetMapping(value = "/article/title/{title}")
    public BaseResultInfo articleFindByTitle(@PathVariable("title") String title) {
        List<Article> articleList = articleService.findByTitle(title);
        SuccessResult resultInfo = new SuccessResult(articleList);
        return resultInfo;
    }


    /**
     * http://localhost:8190/meizhuang/findarticlePages?page=0&size=4
     * or
     * http://localhost:8190/meizhuang/findarticlepages
     * https://my.oschina.net/wangxincj/blog/820670
     * 分页查询
     *
     * @param modelMap
     * @param page
     * @param size
     * @return
     * @RequestMapping("/findarticlepages") //不区分get和post
     */
    // @RequestMapping("/findarticlepages")
    @GetMapping("/findarticlepages")
    public BaseResultInfo findBookNoQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                          @RequestParam(value = "size", defaultValue = "10") Integer size) {
        Page<Article> datas = articleService.findArticlesNoCriteria(page, size);
        modelMap.addAttribute("datas", datas);
        SuccessResult resultInfo = new SuccessResult( datas);
        return resultInfo;
    }

    /**
     * http://localhost:8190/meizhuang/findarticlePages?page=0&size=4
     * or
     * http://localhost:8190/meizhuang/findarticlepagesquery
     * https://my.oschina.net/wangxincj/blog/820670
     * 分页查询
     *
     * @param modelMap
     * @param page
     * @param size
     * @return
     * @RequestMapping("/findarticlepages") //不区分get和post
     */
    //  @RequestMapping(value = "/findarticlepagesquery",method = {RequestMethod.GET,RequestMethod.POST})
    @RequestMapping(value = "/findarticlepagesquery", method = {RequestMethod.GET})
    public BaseResultInfo findBookQuery(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                                       @RequestParam(value = "size", defaultValue = "2") Integer size, ArticleQuery bookQuery) {
        Page<Article> datas = articleService.findArticlesCriteria(page, size, bookQuery);
        modelMap.addAttribute("datas", datas);
        SuccessResult resultInfo = new SuccessResult(datas);
        return resultInfo;
    }


    //   @RequestMapping("/findBookNoQuery")
//    public ModelMap findBookNoQuery(ModelMap modelMap,@RequestParam(value = "page", defaultValue = "0") Integer page,
//                                  @RequestParam(value = "size", defaultValue = "1") Integer size){
//        Page<Article> datas = articleService.findArticleNoCriteria(page, size);
//        modelMap.addAttribute("datas", datas);
//        return modelMap;
//    }

    /**
     * @param modelMap
     * @param page
     * @param size
     * @param sortDirection
     * @param recommendStatus
     * @return
     */

    @RequestMapping(value = "/findarticlepagesquery1", method = {RequestMethod.GET})
    public BaseResultInfo findBookQuery1(ModelMap modelMap,
                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                        @RequestParam(value = "size", defaultValue = "2") Integer size,
                                        @RequestParam(value = "recommendStatus", defaultValue = "0") Integer recommendStatus,
                                        @RequestParam(value = "sortDirection", defaultValue = "0") Integer sortDirection
    ) {

        Page<Article> datas = articleService.findArticlesByRecommendStatus(page, size, recommendStatus, sortDirection);
        modelMap.addAttribute("datas", datas);
        SuccessResult resultInfo = new SuccessResult( datas);
        return resultInfo;
    }


}
