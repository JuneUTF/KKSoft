package com.juneutf.kk.office.service;

import java.util.List;

import com.juneutf.kk.office.model.SalesModel;

public interface SalesSerive {
	List<SalesModel> selectNameidSameDay(int month);
	List<SalesModel> selectNameidNotSameDay(int month);
	int insertSales(SalesModel model);
	List<SalesModel> selectSalesToDay();
	int selectMaxId();
	List<SalesModel> selectSalesById(int id);
	List<SalesModel> selectNameIdFUll();
	int updateSalesById(SalesModel salesModel);
}
