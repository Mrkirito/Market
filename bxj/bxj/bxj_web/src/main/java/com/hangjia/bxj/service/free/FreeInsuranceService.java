package com.hangjia.bxj.service.free;

import java.util.List;
import com.hangjia.bxj.BXJException;
import com.hangjia.bxj.model.FreeInsurance;

public interface FreeInsuranceService {
	
	/**
	 * 查询所有赠险产品。
	 * @return 赠险产品列表。
	 */
	List<FreeInsurance> listAllFreeInsurances();
	
	/**
	 * 赠险产品详情。
	 * @param id 产品ID。
	 * @return 赠险产品，返回值不为空，且产品可用。
	 * @throws BXJException 如果产品未找到，或产品不可用。
	 */
	FreeInsurance getFreeInsuranceDetails(String id) throws BXJException;
	
	FreeInsurance getFreeInsurance(String id);

	long receive(Integer shareId, ReceiverArgs args);

}
