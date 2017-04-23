package com.meizhuang.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by meizhuang on 03/02/17.
 */

@Entity
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private Integer creatorId;
    private Long createTimeMillis;
    private Long recordTimeMillis;

    private String title = "";
    private String subTitle = "";
    private String coverImgUrl = "";

    private String content = "";
    private String author= "";
    private String fromUrl= "";

    private Integer type = 0;
    private Integer status =0;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreateTimeMillis() {
        return createTimeMillis;
    }

    public void setCreateTimeMillis(Long createTimeMillis) {
        this.createTimeMillis = createTimeMillis;
    }

    public Long getRecordTimeMillis() {
        return recordTimeMillis;
    }

    public void setRecordTimeMillis(Long recordTimeMillis) {
        this.recordTimeMillis = recordTimeMillis;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getCoverImgUrl() {
        return coverImgUrl;
    }

    public void setCoverImgUrl(String coverImgUrl) {
        this.coverImgUrl = coverImgUrl;
    }
}
