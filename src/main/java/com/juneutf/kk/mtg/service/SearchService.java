package com.juneutf.kk.mtg.service;

import java.util.ArrayList;

import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.model.SearchModel;

/**
 * SearchService インターフェースは、検索に関連するサービスメソッドを提供します。
 */
public interface SearchService {
    
    /**
     * 指定された検索条件に基づいて内容を検索します。
     *
     * @param model 検索条件を指定するモデル
     * @return 検索結果の内容のリスト
     */
    ArrayList<JobModel> selectSearch(SearchModel model);
}
