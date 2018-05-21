package cn.usst.market.mapper;

import cn.usst.market.po.Company;
import cn.usst.market.po.OnlineEmploee;

public interface OnlineEmploeeMapper {
	
	public OnlineEmploee selectOnlineEmploeebyCompanyID(Company c);

	public void updateOnlineEmploeebyCompanyID(OnlineEmploee oe);
	
	public void insertCompanyEmploeeInfo(OnlineEmploee oe);
	
}
