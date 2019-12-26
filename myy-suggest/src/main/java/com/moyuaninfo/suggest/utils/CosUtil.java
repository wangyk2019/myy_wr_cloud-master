package com.moyuaninfo.suggest.utils;

import com.moyuaninfo.suggest.common.Global;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;

/**
 * @ClassName CosUtil
 * @Description TODO
 * @Author zhaoGq
 * @Date 2019/11/1 14:51
 * @Version 1.0
 **/
public class CosUtil {
    public COSClient getTXcosClient () {
        // 1 初始化用户身份信息(secretId, secretKey)
        COSCredentials cred = new BasicCOSCredentials(Global.TXCLOUD_SECRETID, Global.TXCLOUD_SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        ClientConfig clientConfig = new ClientConfig(new Region(Global.TXCLOUD_BUCKET));
        // 3 生成cos客户端
        COSClient cosclient = new COSClient(cred, clientConfig);
        return cosclient;
    }
}
