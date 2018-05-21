package cn.usst.market.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.usst.market.mapper.LogMapper;
import cn.usst.market.po.Log;
import cn.usst.market.po.LogVo;
import cn.usst.market.service.LogService;
@Service("logService")
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogMapper logMapper;
	
	@Override
	public void insertLog(Log log) {
		logMapper.insertLog(log);
	}

	@Override
	public List<Log> selectLog(Log log) {
		// TODO Auto-generated method stub
		return logMapper.selectLog(log);
	}

	@Override
	public List<LogVo> selectMemberOperateByPage(LogVo logVo) {
		// TODO Auto-generated method stub
		return logMapper.selectMemberOperateByPage(logVo);
	}

	@Override
	public int getCount(LogVo logVo) {
		// TODO Auto-generated method stub
		return logMapper.getCount(logVo);
	}
	

}
