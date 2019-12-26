package com.moyuaninfo.cloud.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
public class MyNettyServer {

    private ConfigurableApplicationContext context;

    public MyNettyServer (){}
    public MyNettyServer(ConfigurableApplicationContext context){this.context = context;}


    public void start(int port ) throws Exception {
//        EventLoopGroup bossGroup = new NioEventLoopGroup();

        EventLoopGroup workgroup = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.group(workgroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

                            ch.pipeline()
                                    .addLast(new DelimiterBasedFrameDecoder(4096,delimiter))
                                    .addLast(new StringDecoder())
                                    .addLast("readTimeOutHandler",new ReadTimeoutHandler(50))
                                    .addLast("HeartBeatHandler",new MyHttpChannelHandler());
                        }
                    });
            // 绑定端口，同步等待成功
            ChannelFuture cf = sb.bind(port).sync();
            System.out.println("****************************服务端正在监听**************************");
            cf.channel().closeFuture().sync(); // 关闭服务器通道
        } catch (Exception e) {
            log.error("服务端启动失败", e);
        } finally {
            workgroup.shutdownGracefully().sync(); // 释放线程池资源
//            bossGroup.shutdownGracefully().sync();
        }
    }
}
