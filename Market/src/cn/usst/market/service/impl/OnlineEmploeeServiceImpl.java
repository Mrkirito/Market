package cn.usst.market.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.OnlineEmploeeMapper;
import cn.usst.market.po.Company;
import cn.usst.market.po.OnlineEmploee;
import cn.usst.market.service.OnlineEmploeeService;

@Service("OnlineEmploeeServiceImpl")
public class OnlineEmploeeServiceImpl implements OnlineEmploeeService {

	@Autowired
	private OnlineEmploeeMapper OEM;
	
	@Override
	public OnlineEmploee selectOnlineEmploeebyCompanyID(Company c) {
		// TODO Auto-generated method stub
		return OEM.selectOnlineEmploeebyCompanyID(c);
	}

	@Override
	public void updateOnlineEmploeebyCompanyID(OnlineEmploee oe) {
		// TODO Auto-generated method stub
		OEM.updateOnlineEmploeebyCompanyID(oe);
	}

	@Override
	public void insertCompanyEmploeeInfo(OnlineEmploee oe) {
		// TODO Auto-generated method stub
		OEM.insertCompanyEmploeeInfo(oe);
	}

}
