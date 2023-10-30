package com.juneutf.kk.mtg.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.mtg.mapper.SearchMapper;
import com.juneutf.kk.mtg.model.JobModel;
import com.juneutf.kk.mtg.model.SearchModel;
import com.juneutf.kk.mtg.service.SearchService;

/**
 * このクラスは、検索関連のサービスを提供する実装です。
 */
@Service
public class SearchImpl implements SearchService {
    @Autowired
    private SearchMapper mapper;

    /**
     * 指定された検索条件に一致する予約内容モデルのリストを返します。
     *
     * @param model 検索条件を含む SearchModelオブジェクト
     * @return 検索結果として得られた JobModelのリスト
     */
    @Override
    public ArrayList<JobModel> selectSearch(SearchModel model) {
        return mapper.selectSearch(model);
    }
}
