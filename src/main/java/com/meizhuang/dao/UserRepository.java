package com.meizhuang.dao;

import com.meizhuang.entity.Article;
import com.meizhuang.entity.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

/**
 * Created by joybar on 2017/5/15.
 */
public interface UserRepository extends JpaRepository<UserInfo,Long>,JpaSpecificationExecutor<UserInfo> {

    //通过userName查询
    List<UserInfo> findByUserName(String userName);
    //通过角色查询
    List<UserInfo> findByRole(Integer role);


}
