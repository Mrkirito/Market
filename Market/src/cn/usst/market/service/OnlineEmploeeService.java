package cn.usst.market.service;

import cn.usst.market.po.Company;
import cn.usst.market.po.OnlineEmploee;

public interface OnlineEmploeeService {

	public OnlineEmploee selectOnlineEmploeebyCompanyID(Company c);

	public void updateOnlineEmploeebyCompanyID(OnlineEmploee oe);
	
	public void insertCompanyEmploeeInfo(OnlineEmploee oe);
	
}
