package cn.usst.market.mapper;

import java.util.List;

import cn.usst.market.po.HelpDocument;

public interface HelpDocumentMapper {
	public int getTitleLevelOneCount();
	public List<HelpDocument> selectAllHelpDocument();
	public HelpDocument selectHelpDocument(HelpDocument helpDocument);
}
