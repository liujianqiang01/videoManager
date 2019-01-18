package com.video.manager.common;


import javax.net.ssl.SSLContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

/**
 * @Author: liujianqiang
 * @Date: 2019-01-18
 * @Description:
 */
public class CertUtil {
    /**
     * 加载证书
     */
    public static SSLConnectionSocketFactory initCert() throws Exception {
        FileInputStream instream = null ;
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        instream = new FileInputStream(new File("/Users/liujianqiang/Desktop/project/videoManager/src/main/resources/cert/apiclient_cert.p12"));
        keyStore.load(instream, Configure.getMch_id().toCharArray());

        if (null != instream) {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore,Configure.getMch_id().toCharArray()).build();
       // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.STRICT_HOSTNAME_VERIFIER);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext);
        return sslsf;
    }

    public static void main(String[] args) {
        try {
            SSLConnectionSocketFactory sslConnectionSocketFactory = initCert();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}