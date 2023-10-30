package com.juneutf.kk.mtg.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.mtg.mapper.APIMapper;
import com.juneutf.kk.mtg.model.APIChargeModel;
import com.juneutf.kk.mtg.model.APIPurposeModel;
import com.juneutf.kk.mtg.service.APIService;

/**
 * このクラスは、APIServiceを実装しており、APIに関連するデータを提供します。
 */
@Service
public class APIImpl implements APIService {
    @Autowired
    private APIMapper mapper;

    /**
     * APIの担当者モデルを選択して返します。
     *
     * @return APIの担当者モデルのリスト
     */
    @Override
    public ArrayList<APIChargeModel> selectAPICharge(int id) {
        return mapper.selectAPICharge(id);
    }

    /**
     * APIの内容モデルを選択して返します。
     *
     * @return APIの内容モデルのリスト
     */
    @Override
    public ArrayList<APIPurposeModel> selectAPIPurpose(int id) {
        return mapper.selectAPIPurpose(id);
    }
}
