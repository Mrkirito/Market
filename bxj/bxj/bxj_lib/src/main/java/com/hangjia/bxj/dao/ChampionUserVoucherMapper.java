package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.ChampionUserVoucher;

public interface ChampionUserVoucherMapper {
	int deleteByPrimaryKey(Long fid);

	int insert(ChampionUserVoucher record);

	int insertSelective(ChampionUserVoucher record);

	ChampionUserVoucher selectByPrimaryKey(Long fid);

	int updateByPrimaryKeySelective(ChampionUserVoucher record);

	int updateByPrimaryKey(ChampionUserVoucher record);

	List<ChampionUserVoucher> selectBySelective(ChampionUserVoucher record);
}