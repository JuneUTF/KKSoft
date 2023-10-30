package com.juneutf.kk.service;

import java.util.List;

import com.juneutf.kk.model.ChangePasswordModel;

public interface ChangePasswordService {
	List<ChangePasswordModel> selectPasswordByUsername (ChangePasswordModel model);
	int setNewPassword (ChangePasswordModel model);
}
