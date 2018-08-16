package org.alljet.common.biz.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import org.alljet.common.biz.netty.channel.DispatcherServletChannelInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @ClassName: NettyServer
 * @Description: netty服务端
 * @date 2015年01月27日17:10:14
 * @version V1.0
 *
 */
public class NettyServer {

    Logger logger = LoggerFactory.getLogger(NettyServer.class);

    private final String appId;
    //服务端口
    private final int port;
    //内容字符编码
    private final String charset;
    //环境类型（开发、测试、压测、生产）
    //private final String env;
    //配置文件路径
    private final String[] configLocation;

    /**
     *
     * 初始化服务端
     * @date  2015年01月27日14:13:18
     * @param port     服务端口
     * @param charset  字符编码
     * @param //env      系统环境类型
     * @param configLocation 配置文件路径*
     */
    public NettyServer(String appId,int port, String charset, /*Environment env,*/ String ... configLocation) {
        this.appId = appId;
        this.port = port;
        this.configLocation = configLocation;
        this.charset = charset;
        //this.env = env.toString();
    }

    /**
     * 启动服务
     * @date  2015年01月27日14:13:18
     * @throws Exception
     */
    public void start() throws Exception {
        ServerBootstrap bootstrap = new ServerBootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {

            bootstrap.group(new NioEventLoopGroup(), new NioEventLoopGroup());
            bootstrap.channel(NioServerSocketChannel.class);

            bootstrap.localAddress(port);
            bootstrap.childHandler(new DispatcherServletChannelInitializer(charset,/* env,*/ configLocation));
            logger.info("{} server started...[ip:{}][port:{}][env:{}]", appId, "localhost", port/*, env*/);
            bootstrap.bind().sync().channel().closeFuture().sync();
        }catch(Exception e){
            logger.info("{} server Failed...[ip:{}][port:{}][env:{}]", appId, "localhost", port/*, env*/);
        } finally {
            group.shutdownGracefully();
        }
    }
}
