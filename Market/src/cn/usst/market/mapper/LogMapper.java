package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.Log;
import cn.usst.market.po.LogVo;

public interface LogMapper {
	public void insertLog(Log log);
	public List<Log> selectLog(Log log);
	public int getCount(LogVo logVo);
	public List<LogVo> selectMemberOperateByPage(LogVo logVo);
}
