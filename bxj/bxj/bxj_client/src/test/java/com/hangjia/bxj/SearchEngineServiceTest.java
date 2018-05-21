package com.hangjia.bxj;

import com.caucho.hessian.client.HessianProxyFactory;
import com.hangjia.champion.service.ChampionSupportService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.net.MalformedURLException;

/**
 * SearchEngineServiceTest
 */
public class SearchEngineServiceTest {

    private ChampionSupportService championSupportService;

    @Before
    public void setup() throws MalformedURLException, ClassNotFoundException {
        HessianProxyFactory hpf = new HessianProxyFactory();
        hpf.setHessian2Request(true);
        hpf.setHessian2Reply(true);
        hpf.setDebug(true);
        championSupportService = (ChampionSupportService) hpf.create("http://112.124.126.16:4000/bxj_search/remoting/ChampionSupportService");
//        championSupportService = (ChampionSupportService) hpf.create("http://localhost:8085/bxj_search/remoting/ChampionSupportService");

    }

    @Test
    @Ignore
    public void updateItemIndexByAll() {
        championSupportService.updateVideoIndexAll();
    }





}