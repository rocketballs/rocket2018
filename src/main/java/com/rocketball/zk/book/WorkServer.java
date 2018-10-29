package com.rocketball.zk.book;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;

/**
 * Created by Administrator on 2017-12-15 0015.
 * 工作服务器类
 */
public class WorkServer {
    private ZkClient zkClient;
    private String configPath;  //zookeeper中config节点路径
    private String serversPath;  //zookeeper中server节点路径
    private ServerData serverData; //当前服务器数据
    private ServerConfig serverConfig;  //当前服务器配置
    private IZkDataListener dataListener;  //数据监听类
    /**
     * 构造函数
     */
    public WorkServer(String configPath, String serversPath,ServerData serverData, ZkClient zkClient, ServerConfig initConfig){
           this.configPath = configPath;
           this.serversPath = serversPath;
           this.serverData = serverData;
           this.zkClient = zkClient;
           this.serverConfig = initConfig;
        this.dataListener = new IZkDataListener() {

            public void handleDataDeleted(String dataPath) throws Exception {
                // TODO Auto-generated method stub

            }

            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
                // TODO Auto-generated method stub
                String retJson = new String((byte[])data);
                ServerConfig serverConfigLocal = (ServerConfig)JSON.parseObject(retJson,ServerConfig.class);
                updateConfig(serverConfigLocal);
                System.out.println("new Work server config is:"+serverConfig.toString());

            }
        };
    }

    /**
     * 服务器启动方法
     */
    public void start(){
        initRunning();
        System.out.println(serverData.getIpAddr()+" work server start...");
    }

    /**
     * 服务器停止方法
     */
    public void stop(){
   //取消事件监听
        zkClient.unsubscribeDataChanges(configPath,dataListener);
        System.out.println(serverData.getIpAddr()+" work server stop ...");

    }

    /**
     * 服务器初始化方法
     */
    private void initRunning(){
        //注册
        registMe();
        //订阅config节点的改变
        zkClient.subscribeDataChanges(configPath,dataListener);

    }

    /**
     * 向zookeeper注册自己
     */
    private void registMe(){
       //创建临时节点在server路径下
       String mePath = serversPath.concat("/").concat(serverData.getIpAddr());
        try {
            zkClient.createEphemeral(mePath, JSON.toJSONString(serverData).getBytes());
        }catch (ZkNoNodeException e){  //serverPath节点没有找到异常,要先创建这个节点，再注册
            zkClient.createPersistent(serversPath,true);
            registMe();

        }


    }

    private void updateConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

}
