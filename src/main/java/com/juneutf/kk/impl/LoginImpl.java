package com.juneutf.kk.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.mapper.LoginMapper;
import com.juneutf.kk.model.LoginModel;
import com.juneutf.kk.service.LoginService;

/**
 * このクラスは、ログイン関連のサービスを提供します。
 */
@Service
public class LoginImpl implements LoginService {

    @Autowired
    LoginMapper mapper;
    
    /**
     * ユーザー名に基づいてログインモデルを選択します。
     *
     * @param ログイン情報を検索するためのユーザー名またはメールアドレス
     * @return ユーザー名に一致するログインモデル、存在しない場合はnull
     */
    @Override
    public LoginModel selectLoginByUsername(String username){
        return mapper.selectLoginByUsername(username);
    }
}
