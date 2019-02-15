package com.hrabbit.admin.test;

import com.hrabbit.admin.core.common.ftp.FTPUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * FtpClient测试
 * @Auther: hrabbit
 * @Date: 2018-12-21 9:14 PM
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FtpClientTest {
    /**
     * 测试上传
     */
    @Test
    public void uploadFile(){

        for (int i=0;i<2;i++){
            boolean flag = FTPUtils.uploadFile(i+".jpg", "/Users/mrotaku/Documents/private/background/travaler_1600x1200_4x.png");
            Assert.assertEquals(true, flag);
        }
    }
}
