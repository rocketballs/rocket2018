package com.rocketball.zk.book;

import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;

import java.util.List;

/**
 * Created by Administrator on 2017-12-15 0015.
 * 管理服务器类
 */
public class ManagerServer {
    private String serversPath; //zookeeper servers节点路径
    private String commandPath;  //command节点路径
    private String configPath;  //config节点路径
    private ZkClient zkClient;
    private ServerConfig config;  //当前服务器配置信息
    private IZkChildListener childListener;  //子节点监听类
    private IZkDataListener dataListener;  //数据改变监听类,监听command节点数据变化
    private List<String> workServerList;  //服务器列表
    public ManagerServer(String serversPath, String commandPath,String configPath, ZkClient zkClient, ServerConfig config) {
        this.serversPath = serversPath;
        this.commandPath = commandPath;
        this.zkClient = zkClient;
        this.config = config;
        this.configPath = configPath;
        this.childListener = new IZkChildListener() {

            public void handleChildChange(String parentPath,List<String> currentChilds) throws Exception {
                // TODO Auto-generated method stub
                workServerList = currentChilds;

                System.out.println("work server list changed, new list is ");
                execList();

            }
        };
        this.dataListener = new IZkDataListener() {

            public void handleDataDeleted(String dataPath) throws Exception {
                // TODO Auto-generated method stub
                // ignore;
            }

            public void handleDataChange(String dataPath, Object data)
                    throws Exception {
                // TODO Auto-generated method stub
                String cmd = new String((byte[]) data);
                System.out.println("cmd:"+cmd);
                exeCmd(cmd);

            }
        };

    }

    /**
     * 初始化运行方法
     */
    private void initRunning(){
     //执行两个事件订阅
     zkClient.subscribeDataChanges(commandPath,dataListener); //检测command节点命令
     zkClient.subscribeChildChanges(serversPath,childListener);  //检测子节点变化
    }



    /**
     * 开启服务
     */
    public void start(){
        initRunning();
    }

    /**
     * 停止服务
     */
    public void stop(){
     //取消事件订阅
        zkClient.unsubscribeDataChanges(commandPath,dataListener);
        zkClient.unsubscribeChildChanges(serversPath,childListener);
    }
    /*
	 * 1: getList 2: createConfig 3: modifyUser
	 */
    private void exeCmd(String cmdType) {
        if ("getList".equals(cmdType)) {
            execList();

        } else if ("createConfig".equals(cmdType)) {
            execCreate();
        } else if ("modifyUser".equals(cmdType)) {
            execModify();
        } else {
            System.out.println("error command!" + cmdType);
        }

    }

    private void execList() {
        System.out.println(workServerList.toString());
    }

    private void execCreate() {
        if (!zkClient.exists(configPath)) {
            try {
                zkClient.createPersistent(configPath, JSON.toJSONString(config)
                        .getBytes());
            } catch (ZkNodeExistsException e) {
                zkClient.writeData(configPath, JSON.toJSONString(config)
                        .getBytes());
            } catch (ZkNoNodeException e) {
                String parentDir = configPath.substring(0,
                        configPath.lastIndexOf('/'));
                zkClient.createPersistent(parentDir, true);
                execCreate();
            }
        }
    }

    private void execModify() {
        config.setDbUser(config.getDbUser() + "_modify");

        try {
            zkClient.writeData(configPath, JSON.toJSONString(config).getBytes());
        } catch (ZkNoNodeException e) {
            execCreate();
        }
    }
}
