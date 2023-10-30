package com.juneutf.kk.mtg.service;

import java.util.ArrayList;

import com.juneutf.kk.mtg.model.APIChargeModel;
import com.juneutf.kk.mtg.model.APIPurposeModel;

/**
 * APIService インターフェースは、API関連のサービスメソッドを提供します。
 */
public interface APIService {
    
    /**
     * API料金モデルのリストを取得します。
     *
     * @return API担当者モデルのリスト
     */
    ArrayList<APIChargeModel> selectAPICharge(int id);
    
    /**
     * API内容モデルのリストを取得します。
     *
     * @return API内容モデルのリスト
     */
    ArrayList<APIPurposeModel> selectAPIPurpose(int id);
}
