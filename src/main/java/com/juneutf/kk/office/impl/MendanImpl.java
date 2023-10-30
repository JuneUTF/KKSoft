package com.juneutf.kk.office.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.office.mapper.MendanMapper;
import com.juneutf.kk.office.model.MendanModel;
import com.juneutf.kk.office.service.MendanService;
@Service
public class MendanImpl implements MendanService{
	@Autowired
	private MendanMapper mapper;

	@Override
	public List<MendanModel> getMendanByStatus() {
		return mapper.getMendanByStatus();
	}
}
