package com.hangjia.bxj;

import com.baobao.framework.page.Paginator;
import com.caucho.hessian.client.HessianProxyFactory;
import com.hangjia.bxj.service.BxjSupportService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


import java.net.MalformedURLException;


/**
 * SearchEngineServiceTest
 */
public class BxjServiceTest {

    private BxjSupportService bxjSupportService;

    @Before

    public void setup() throws MalformedURLException, ClassNotFoundException {
        HessianProxyFactory hpf = new HessianProxyFactory();
        hpf.setHessian2Request(true);
        hpf.setHessian2Reply(true);
        hpf.setDebug(true);
        bxjSupportService = (BxjSupportService) hpf.create("http://localhost:8085/bxj_service/remoting/BxjSupportService");
    }

    @Test
    @Ignore
    public void run() throws MalformedURLException, ClassNotFoundException {
//        System.out.print(bxjSupportService.queryInviteUser(179989l));

        System.out.print(bxjSupportService.queryInviteSortList(new Paginator()));
    }



}