package com.juneutf.kk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.kk.model.ChangePasswordModel;
@Mapper
public interface ChangePasswordMapper {
	List<ChangePasswordModel> selectPasswordByUsername (ChangePasswordModel model);
	int setNewPassword (ChangePasswordModel model);
}
