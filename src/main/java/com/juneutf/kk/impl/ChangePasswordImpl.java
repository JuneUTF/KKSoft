package com.juneutf.kk.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juneutf.kk.mapper.ChangePasswordMapper;
import com.juneutf.kk.model.ChangePasswordModel;
import com.juneutf.kk.service.ChangePasswordService;

@Service
public class ChangePasswordImpl implements ChangePasswordService{
	@Autowired 
	private ChangePasswordMapper mapper;
	@Override
	public List<ChangePasswordModel> selectPasswordByUsername(ChangePasswordModel model) {
		return mapper.selectPasswordByUsername(model);
	}

	@Override
	public int setNewPassword(ChangePasswordModel model) {
		return mapper.setNewPassword(model);
	}

}
