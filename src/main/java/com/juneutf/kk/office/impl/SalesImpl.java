package com.juneutf.kk.office.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.office.mapper.SalesMapper;
import com.juneutf.kk.office.model.SalesModel;
import com.juneutf.kk.office.service.SalesSerive;
@Service
public class SalesImpl implements SalesSerive{
	@Autowired
	private SalesMapper mapper;
	@Override
	public List<SalesModel> selectNameidSameDay(int month) {
		return mapper.selectNameidSameDay(month);
	}
	@Override
	public List<SalesModel> selectNameidNotSameDay(int month) {
		return mapper.selectNameidNotSameDay(month);
	}
	@Override
	public int insertSales(SalesModel model) {
		return mapper.insertSales(model);
	}
	@Override
	public List<SalesModel> selectSalesToDay() {
		return mapper.selectSalesToDay();
	}
	@Override
	public int selectMaxId() {
		return mapper.selectMaxId();
	}
	@Override
	public List<SalesModel> selectSalesById(int id) {
		return mapper.selectSalesById(id);
	}
	@Override
	public List<SalesModel> selectNameIdFUll() {
		return mapper.selectNameIdFUll();
	}
	@Override
	public int updateSalesById(SalesModel salesModel) {
		return mapper.updateSalesById(salesModel);
	}

}
