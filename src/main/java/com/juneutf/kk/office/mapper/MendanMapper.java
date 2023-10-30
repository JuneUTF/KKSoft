package com.juneutf.kk.office.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.juneutf.kk.office.model.MendanModel;

@Mapper
public interface MendanMapper {
	List<MendanModel> getMendanByStatus();
}
