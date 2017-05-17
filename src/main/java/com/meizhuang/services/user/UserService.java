package com.meizhuang.services.user;

import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import com.meizhuang.entity.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by joybar on 2017/5/15.
 */
public interface UserService {

    Article addUser(UserInfo userInfo);

    Article addUser(
                    String userName,
                    String password,
                    int sex,
                    int role,
                    long birthDate,

                    String nickName,
                    String avatarUrl,
                    String email,
                    String mobilePhoneNumber,
                    String homeTown,

                    String currentCity,
                    String currentDetailAddress);


    List<UserInfo> getAllUsers();

    UserInfo getUserById(Long id);

    void deleteUsereById(Long id);

    List<UserInfo> findByUserName(String userName);
    List<UserInfo> findByRole(int role);


    Page<UserInfo> findUsersCriteria(Integer page, Integer size, ArticleQuery articleQuery);

    Page<UserInfo> findUsersNoCriteria(Integer page, Integer size);


}
