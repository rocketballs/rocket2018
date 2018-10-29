package com.rocketball.zk.book;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-12-15 0015.
 * 记录服务器配置
 */
public class ServerConfig implements Serializable {
    private String dbUrl;  //模拟数据库链接地址
    private String dbUser; //模拟数据库用户名
    private String dbPwd; //模拟数据库密码

    public String getDbUrl() {
        return dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPwd() {
        return dbPwd;
    }

    public void setDbPwd(String dbPwd) {
        this.dbPwd = dbPwd;
    }

    @Override
    public String toString() {
        return "ServerConfig{" +
                "dbUrl='" + dbUrl + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPwd='" + dbPwd + '\'' +
                '}';
    }
}
