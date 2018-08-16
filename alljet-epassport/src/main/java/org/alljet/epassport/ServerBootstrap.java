package org.alljet.epassport;


import java.io.IOException;
import java.util.Properties;
import org.alljet.common.biz.netty.server.NettyServer;
import org.alljet.common.biz.netty.server.Environment;


public class ServerBootstrap {


    public static void main(String[] args) throws IOException {
        //读取服务配置文件
        Properties properties = new Properties();
        properties.load(ServerBootstrap.class.getClass().getResourceAsStream("/application.properties"));
        String appId = properties.get("sys.appId").toString();
        Integer port = Integer.parseInt(properties.get("sys.port").toString());
        String charset = properties.get("sys.charset").toString();

        String configLocation = properties.get("sys.contextConfigLocation").toString();

        NettyServer server = new NettyServer(appId, port, charset, Environment.valueOf(System.getProperty("environment")), configLocation.split(","));
        try {
            server.start();
            System.out.print("epassport-service done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
