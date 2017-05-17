package com.meizhuang.services.user;

import com.meizhuang.entity.Article;
import com.meizhuang.entity.ArticleQuery;
import com.meizhuang.entity.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by joybar on 2017/5/15.
 */
public class UserServiceImpl implements UserService {
    @Override
    public Article addUser(UserInfo userInfo) {
        return null;
    }

    @Override
    public Article addUser(String userName, String password, int sex, int role, long birthDate, String nickName, String avatarUrl, String email, String mobilePhoneNumber, String homeTown, String currentCity, String currentDetailAddress) {
        return null;
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return null;
    }

    @Override
    public UserInfo getUserById(Long id) {
        return null;
    }

    @Override
    public void deleteUsereById(Long id) {

    }

    @Override
    public List<UserInfo> findByUserName(String userName) {
        return null;
    }

    @Override
    public List<UserInfo> findByRole(int role) {
        return null;
    }

    @Override
    public Page<UserInfo> findUsersCriteria(Integer page, Integer size, ArticleQuery articleQuery) {
        return null;
    }

    @Override
    public Page<UserInfo> findUsersNoCriteria(Integer page, Integer size) {
        return null;
    }
}
