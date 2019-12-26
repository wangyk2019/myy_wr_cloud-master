package com.moyuaninfo.cloud.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.util.CharsetUtil;

public class MyHttpChannelHandler extends ChannelInitializer<NioSocketChannel> {

//    private PrelimWarnService prelimWarnService;
    public MyHttpChannelHandler(){}
//    public MyHttpChannelHandler(PrelimWarnService prelimWarnService){this.prelimWarnService = prelimWarnService;}

    @Override
    protected void initChannel(NioSocketChannel ch) throws Exception {


        //创建分隔符缓冲对象，使用"$_"作为分隔符
        ByteBuf delimiter = Unpooled.copiedBuffer("$_", CharsetUtil.UTF_8);
        //创建DelimiterBasedFrameDecoder对象，将其加入到ChannelPipeline
        //参数1024表示单条消息的最大长度，当达到该长度仍然没有找到分隔符就抛出TooLongFrame异常，第二个参数就是分隔符
        //由于DelimiterBasedFrameDecoder自动对请求消息进行了解码，下面的ChannelHandler接收到的msg对象就是完整的消息包
        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter));
        //StringDecoder解码器将ByteBuf解码成字符串对象，这样在ChannelHandlerAdapter中读取消息时就不需要通过ByteBuf获取了
        ch.pipeline().addLast(new StringDecoder());
        ch.pipeline().addLast(new ReadTimeoutHandler(50));
        //        主要用于支持浏览器和服务端进行WebSocket通信
        ch.pipeline().addLast(new ChunkedWriteHandler());


        //添加自定义处理器
        ch.pipeline().addLast(new MyHttpHandler());
    }
}
