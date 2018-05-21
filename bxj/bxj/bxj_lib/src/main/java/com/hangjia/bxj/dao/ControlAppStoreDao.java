package com.hangjia.bxj.dao;

import java.util.List;

import com.hangjia.bxj.model.BxjResponse;

public interface ControlAppStoreDao {
	
	List<BxjResponse> selectAppStore();

	BxjResponse selectAppStoreById(String id);
}
