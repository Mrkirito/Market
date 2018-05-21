package cn.usst.market.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import cn.usst.market.po.Log;
import cn.usst.market.po.LogVo;

public interface LogService {
	public void insertLog(Log log);
	public List<Log> selectLog(Log log);
	public int getCount(LogVo logVo);
	public List<LogVo> selectMemberOperateByPage(LogVo logVo);
}
