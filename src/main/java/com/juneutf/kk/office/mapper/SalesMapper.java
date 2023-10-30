package com.juneutf.kk.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.kk.office.model.SalesModel;
@Mapper
public interface SalesMapper {
	List<SalesModel> selectNameidSameDay(int month);
	List<SalesModel> selectNameidNotSameDay(int month);
	int insertSales(SalesModel model);
	List<SalesModel> selectSalesToDay();
	int selectMaxId();
	List<SalesModel> selectSalesById(int id);
	List<SalesModel> selectNameIdFUll();
	int updateSalesById(SalesModel salesModel);
}
