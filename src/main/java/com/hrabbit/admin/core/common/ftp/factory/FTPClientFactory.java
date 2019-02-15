package com.hrabbit.admin.core.common.ftp.factory;

import com.hrabbit.admin.config.properties.FTPProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.pool.PoolableObjectFactory;

/**
 * FTPClient 工厂
 *
 * @Auther: hrabbit
 * @Date: 2018-12-03 3:41 PM
 * @Description:
 */
@Slf4j
@SuppressWarnings("all")
public class FTPClientFactory implements PoolableObjectFactory<FTPClient> {

    private FTPProperties ftpProperties;

    public FTPClientFactory(FTPProperties ftpProperties) {
        this.ftpProperties = ftpProperties;
    }

    @Override
    public FTPClient makeObject() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setControlEncoding(ftpProperties.getEncoding());
        ftpClient.setConnectTimeout(ftpProperties.getClientTimeout());
        try {
            ftpClient.connect(ftpProperties.getHost(), ftpProperties.getPort());
            int reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                log.warn("FTPServer refused connection");
                return null;
            }
            boolean result = ftpClient.login(ftpProperties.getUsername(), ftpProperties.getPassword());
            ftpClient.setFileType(ftpProperties.getTransferFileType());
            if (!result) {
                log.warn("ftpClient login failed... username is {}", ftpProperties.getUsername());
            }
        } catch (Exception e) {
            log.error("create ftp connection failed...{}", e);
            throw e;
        }
        return ftpClient;
    }

    @Override
    public void destroyObject(FTPClient ftpClient) throws Exception {
        try {
            if (ftpClient != null && ftpClient.isConnected()) {
                ftpClient.logout();
            }
        } catch (Exception e) {
            log.error("ftp client logout failed...{}", e);
            throw e;
        } finally {
            if (ftpClient != null) {
                ftpClient.disconnect();
            }
        }

    }

    @Override
    public boolean validateObject(FTPClient ftpClient) {
        try {
            return ftpClient.sendNoOp();
        } catch (Exception e) {
            log.error("Failed to validate client: {}");
        }
        return false;
    }

    @Override
    public void activateObject(FTPClient obj) throws Exception {
        //Do nothing

    }

    @Override
    public void passivateObject(FTPClient obj) throws Exception {
        //Do nothing

    }

}

