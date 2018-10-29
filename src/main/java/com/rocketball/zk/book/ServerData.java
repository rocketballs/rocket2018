package com.rocketball.zk.book;

import java.io.Serializable;

/**
 * Created by Administrator on 2017-12-15 0015.
 * 记录服务器信息
 */
public class ServerData implements Serializable {
    private String ipAddr; //服务器ip地址
    private Integer id; //服务器id
    private String name; //服务器名称

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ServerData{" +
                "ipAddr='" + ipAddr + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
