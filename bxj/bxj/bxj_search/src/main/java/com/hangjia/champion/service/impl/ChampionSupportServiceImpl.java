package com.hangjia.champion.service.impl;

import com.baobao.framework.page.Paginator;
import com.baobao.framework.support.utility.StringUtils;
import com.baobao.framework.support.utility.Validation;
import com.hangjia.bxj.dao.BxjKeywordRecordMapper;
import com.hangjia.bxj.dao.ChampionVideoMapper;
import com.hangjia.bxj.model.BxjKeywordRecord;
import com.hangjia.bxj.model.ChampionVideo;
import com.hangjia.champion.constant.SearchConstant;
import com.hangjia.champion.service.ChampionSupportService;
import com.hangjia.champion.vo.ChampionVideoVo;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.util.SimpleOrderedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保保网络科技-bxj
 * com.hangjia.champion.service.impl
 * 作者-秦岭(Tain)
 * 说明：
 * 2016/4/19 18:25
 * 2016保保网络-版权所有
 */
public class ChampionSupportServiceImpl implements ChampionSupportService {
    private HttpSolrServer videoSolrServer;

    @Autowired
    private ChampionVideoMapper championVideoMapper;
    @Autowired
    private BxjKeywordRecordMapper keywordRecordMapper;
    @Value("${bxj_path}")
    private String bxjPath;
    @Value("${qiniu_path}")
    private String qiniuPath;
    @Value("${static_path}")
    private String staticPath;

    private static final Logger logger = Logger.getLogger(ChampionSupportServiceImpl.class);
    public HttpSolrServer getVideoSolrServer() {
        return videoSolrServer;
    }

    public void setVideoSolrServer(HttpSolrServer videoSolrServer) {
        this.videoSolrServer = videoSolrServer;
    }


    public Map<String, Object> updateVideoIndexById(Long videoId) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            ChampionVideo championVideo = championVideoMapper.selectByPrimaryKey(videoId);

            if(championVideo != null) {
                ChampionVideoVo championVideoVo = new ChampionVideoVo();
                BeanUtils.copyProperties(championVideo, championVideoVo);
                updateVideoIndexByDocument(championVideoVo);
            }
            rMap.put("status","0");
            rMap.put("msg","ok");
        } catch (Exception e) {
            rMap.put("status","-1");
            rMap.put("msg","error");
        }
        return rMap;
    }

    public Paginator paginateObject(Paginator paginator) {
        Integer count = Integer.valueOf(championVideoMapper.selectByPaginatorCount(paginator));
        List result = this.championVideoMapper.selectPaginateVideo(paginator);
        paginator.setItems(count.intValue());
        paginator.setResults(result);
        return paginator;
    }

    public Map<String, Object> updateVideoIndexAll() {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            int pageSize = 100;
            Paginator paginator = new Paginator();
            paginator.setItemsPerPage(pageSize);
            paginator.setPage(1);
            paginator = paginateObject(paginator);

            int pageCount = paginator.getPages();
            List<ChampionVideo> results = null;
            for (int i = 0; i < pageCount; i++) {
                if (i == 0) {
                    results = (List<ChampionVideo>) paginator.getResults();
                } else {
                    paginator.setPage(i + 1);
                    paginator = paginateObject(paginator);
                    results = (List<ChampionVideo>) paginator.getResults();
                }

                for (ChampionVideo document : results) {
                    ChampionVideoVo vo = new ChampionVideoVo();
                    BeanUtils.copyProperties(document, vo);
                    updateVideoIndexByDocument(vo);
                }
            }

            rMap.put("status","0");
            rMap.put("msg","ok");
        } catch (Exception e) {
            e.printStackTrace();
            rMap.put("status","-1");
            rMap.put("msg","error");
        }
        return rMap;

    }


    // 添加单条产品索引
    public boolean updateVideoIndexByDocument(ChampionVideoVo document) {
        Assert.isTrue(Validation.isNotEmpty(document.getFid()), "id\t产品编号,字段不能为空");
        Assert.isTrue(Validation.isNotEmpty(document.getTitle()), "product_name\t产品名称,字段不能为空");
        try {
            SolrInputDocument doc = new SolrInputDocument();
            doc.addField(SearchConstant.ID, document.getFid());
            doc.addField(SearchConstant.FID, document.getFid());
            doc.addField(SearchConstant.TITLE, document.getTitle());
            doc.addField(SearchConstant.CHANNEL_ID, document.getChannelId());
            doc.addField(SearchConstant.CHANNEL_TITLE, document.getChannelTitle());
            doc.addField(SearchConstant.LECTURER_ID, document.getLecturerId());
            doc.addField(SearchConstant.LECTURER_NAME, document.getLecturerName());
            doc.addField(SearchConstant.PAGE_URL, document.getPageUrl());
            doc.addField(SearchConstant.VIDEO_SIZE, document.getVideoSize());
            doc.addField(SearchConstant.VIDEO_URL, document.getVideoUrl());
            doc.addField(SearchConstant.VIDEO_TYPE, document.getVideoType());
            doc.addField(SearchConstant.COVER_IMAGE_URL, document.getCoverImageUrl());
            doc.addField(SearchConstant.AUDIT_STATUS, document.getAuditStatus());
            doc.addField(SearchConstant.IS_RECOMMEND, document.getIsRecommend());
            doc.addField(SearchConstant.IS_UNICAST, document.getIsUnicast());
            doc.addField(SearchConstant.PLAY_TYPE, document.getPlayType());
            doc.addField(SearchConstant.DURATION_TIME, document.getDurationTime());
            doc.addField(SearchConstant.ONLINE_TIME, document.getOnlineTime());
            doc.addField(SearchConstant.OFFLINE_TIME, document.getOfflineTime());
            doc.addField(SearchConstant.PLAY_COUNT, document.getPlayCount());
//            doc.addField(SearchConstant.IS_FALSE_COUNT, document.getis());
//            doc.addField(SearchConstant.FALSE_COUNT, document.getFid());
            doc.addField(SearchConstant.SHARE_COUNT, document.getShareCount());
            doc.addField(SearchConstant.COLLECTION_COUNT, document.getCollectionCount());
            doc.addField(SearchConstant.VOUCHER_ID, document.getVoucherId());
            doc.addField(SearchConstant.VOUCHER_COUNT, document.getVoucherCount());
            doc.addField(SearchConstant.MONEY, document.getMoney());
            doc.addField(SearchConstant.DESCRIPTION, document.getDescription());
            doc.addField(SearchConstant.QINIU_ID, document.getQiniuId());
            doc.addField(SearchConstant.STATUS, document.getStatus());
            doc.addField(SearchConstant.SORT, document.getSort());
            doc.addField(SearchConstant.CREATE_USER, document.getCreateUser());
            doc.addField(SearchConstant.CREATE_AT, document.getCreateAt());
            doc.addField(SearchConstant.MODIFY_USER, document.getModifyUser());
            doc.addField(SearchConstant.MODIFY_AT, document.getModifyAt());
            doc.addField(SearchConstant.AUDIT_USER, document.getAuditUser());
            doc.addField(SearchConstant.AUDIT_AT, document.getAuditAt());

//            delVideoIndexById(document.getFid());
            UpdateResponse response = videoSolrServer.add(doc);
            if (response.getStatus() != 0) {
                logger.error("创建视频索引失败:response.status=" + response.getStatus());
            } else {
                logger.info("创建视频索引成功:id=" + document.getFid() + "," + document.getTitle());
            }
            videoSolrServer.commit();
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error("创建视频索引失败:" + ex.getMessage());
        }
        return false;
    }

    public Map<String, Object> delVideoIndexById(Long fid) {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            UpdateResponse response = videoSolrServer.deleteById(String.valueOf(fid));
            if (response.getStatus() != 0) {
                logger.error("删除视频索引失败:response.status=" + response.getStatus());
            } else {
                logger.info("删除视频索引成功:id=" + fid);
            }
            videoSolrServer.commit();
            rMap.put("status","0");
            rMap.put("msg","ok");
        } catch (Exception e) {
            logger.error("删除视频索引失败:" + e.getMessage());
            rMap.put("status","-1");
            rMap.put("msg","error");
        }
        return rMap;
    }

    public Map<String, Object> delAllIndex() {
        Map<String, Object> rMap = new HashMap<String, Object>();
        try {
            UpdateResponse response = videoSolrServer.deleteByQuery("*:*");
            if (response.getStatus() != 0) {
                logger.error("删除所有视频索引失败:response.status=" + response.getStatus());
            } else {
                logger.info("删除所有视频索引成功:" );
            }
            videoSolrServer.commit();
            rMap.put("status","0");
            rMap.put("msg","ok");
        } catch (Exception e) {
            logger.error("删除视频索引失败:" + e.getMessage());
            rMap.put("status","-1");
            rMap.put("msg","error");
        }
        return rMap;
    }

    public Map<String, Object> searchVideoList(Paginator page, ChampionVideoVo ChampionVideoVo, String key) {
        Map<String, Object> param = null;
        String bxjNewPath = bxjPath;
        try {
            param = (Map<String, Object>) page.getParams();
            if(param != null) {
                logger.info("paraUrl:"+param.get("championUrl"));
                Object pathUrl = param.get("championUrl");
                if(pathUrl != null) {
                    bxjNewPath = pathUrl.toString();
                }
            } else {
                logger.info("parame is null");
            }
            BxjKeywordRecord keywordRecord = new BxjKeywordRecord();
            keywordRecord.setKeyword(key);
            keywordRecord.setType(1);
            keywordRecordMapper.insert(keywordRecord);
        } catch (Exception ex) {
            logger.error("关键字日志插入异常，异常原因：" + ex.getMessage());
            ex.printStackTrace();
        }


        Map<String, Object> map = new HashMap<String, Object>();
        SolrQuery paramsAll = null; // 所有过滤条件
        Integer maxSize = null; // 所有搜索出来的商品数量

        int itemsPerPage = page.getItemsPerPage(); // 每一页需要显示的商品数量
        int startIndex = (page.getPage() - 1) * itemsPerPage; // 从第几条开始
        try {
            // 获取所有满足过滤条件的商品
            paramsAll = getItems(ChampionVideoVo, startIndex, itemsPerPage, page, key);
            QueryResponse responseAll = videoSolrServer.query(paramsAll);// 所有
            SolrDocumentList solrList = responseAll.getGroupResponse().getValues().get(0).getValues().get(0).getResult();

            Map<String, Map<String, List<String>>> highLiss = responseAll.getHighlighting();//高亮
            SolrDocumentList newSolrList =new SolrDocumentList();
            for(int i=0;i<solrList.size();i++) {
                SolrDocument sd = solrList.get(i);
                if (highLiss.get(sd.get(SearchConstant.FID).toString()) != null) {
                    List<String> hmap = highLiss.get(sd.get(SearchConstant.FID).toString()).get(SearchConstant.TITLE);
                    if (hmap != null) {
                        sd.setField(SearchConstant.TITLE, hmap.get(0));
                    }
                    List<String> hmap1 = highLiss.get(sd.get(SearchConstant.FID).toString()).get(SearchConstant.LECTURER_NAME);
                    if (hmap1 != null) {
                        sd.setField(SearchConstant.LECTURER_NAME, hmap1.get(0));
                    }
                    //add 20160503 路径处理
                    sd.setField(SearchConstant.COVER_IMAGE_URL, staticPath + "/" +sd.getFieldValue(SearchConstant.COVER_IMAGE_URL));
                    String pageUrl = bxjNewPath + "/" +sd.getFieldValue(SearchConstant.PAGE_URL);
                    sd.setField(SearchConstant.PAGE_URL, pageUrl);
                    logger.info("pageUrl:"+pageUrl);
                    String path = qiniuPath;
                    if("2".equals(sd.getFieldValue(SearchConstant.VIDEO_TYPE)+"")) {
                        path = staticPath;
                    }
                    sd.setField(SearchConstant.VIDEO_URL, path + "/" + sd.getFieldValue(SearchConstant.VIDEO_URL));
                }
                newSolrList.add(sd);
            }

            SimpleOrderedMap value = (SimpleOrderedMap)(((SimpleOrderedMap)responseAll.getResponse().getVal(1)).getVal(0));
            maxSize =(Integer) value.get("ngroups");

            if(maxSize != null) {
                page.setItems(maxSize);
            }

            map.put("resultList", newSolrList);
            map.put("size", maxSize);
        } catch (Exception e) {
            page.setItemsPerPage(10);
            page.setItems(0);
        }
        return map;
    }


    public SolrQuery getItems(ChampionVideoVo championVideoVo, int startIndex, int maxSize, Paginator page, String key) {
        // solr搜索参数
        SolrQuery params = new SolrQuery();

        if(StringUtils.isNotBlank(key)) { //关键字搜索，字段
            //设置结果排序权重值越大权重越高,edismax,dismax
            params.set("defType","dismax");
            params.setQuery("*"+key+"*");
            StringBuilder defQuery = new StringBuilder();
            defQuery.append(SearchConstant.TITLE+"^10.0 ");
            defQuery.append(SearchConstant.LECTURER_NAME+"^5.0 ");
            defQuery.append(SearchConstant.CHANNEL_TITLE+"^1.0 ");
            params.set("qf",defQuery.toString());
        } else {
            params.setQuery(SearchConstant.DEFAULT_KEYWORD_ALL);//无关键词匹配所有
        }

        // 排序方式
        String orderSort = page.getSortField();

        if (StringUtils.isNotBlank(orderSort)) {
            String[] orderField = orderSort.split(",");
            String[] orderType = page.getSortType().split(",");
            for(int i=0;i<orderField.length;i++) {
                // 将排序方式添加到solr条件中
                params.addSort(orderField[i], SolrQuery.ORDER.valueOf(orderType[i]));
            }
        }

        if(StringUtils.isBlank(orderSort) && StringUtils.isBlank(key)) {
            // 如果没有设置排序方式，且没有设置关键字，则按照
            params.addSort(SearchConstant.PLAY_COUNT, SolrQuery.ORDER.desc);
        }

        //视频标题
        if (StringUtils.isNotBlank(championVideoVo.getTitle())) {
            params.addFilterQuery(SearchConstant.TITLE + SearchConstant.COLON_SIGN+ "\""+ championVideoVo.getTitle()+"\"");
        }

        //讲师名称
        if (StringUtils.isNotBlank(championVideoVo.getLecturerName())) {
            params.addFilterQuery(SearchConstant.LECTURER_NAME + SearchConstant.COLON_SIGN+ "\""+ championVideoVo.getLecturerName()+"\"");
        }

        //频道名称
        if (StringUtils.isNotBlank(championVideoVo.getChannelTitle())) {
            params.addFilterQuery(SearchConstant.CHANNEL_TITLE + SearchConstant.COLON_SIGN+ "\""+ championVideoVo.getChannelTitle()+"\"");
        }
        //频道ID
        if (StringUtils.isNotBlank(championVideoVo.getChannelId())) {
            params.addFilterQuery(SearchConstant.CHANNEL_ID + SearchConstant.COLON_SIGN+ "\""+ championVideoVo.getChannelId()+"\"");
        }

        params.addFilterQuery(SearchConstant.AUDIT_STATUS + SearchConstant.COLON_SIGN + "2"); //audit_status=2只搜索出审核通过的数据

        // 把商品名称设置高亮字段
        params.setHighlight(true);
        params.addHighlightField(SearchConstant.TITLE);
        params.addHighlightField(SearchConstant.LECTURER_NAME);
        params.setHighlightSimplePre("<i>");
        params.setHighlightSimplePost("</i>");
        params.setHighlightSnippets(1);
        params.setHighlightFragsize(1000);
        // 分页
        params.setStart(startIndex);
        params.setRows(maxSize);

        //按照商品集搜索
        params.add("group","true");
        params.add("group.field", SearchConstant.FID);
        params.add("group.ngroups","true");
        params.add("group.format","simple");
        params.add("group.facet","true");

        return params;
    }
}
