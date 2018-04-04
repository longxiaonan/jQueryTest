package nio.netty.time;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

public class TimeClient {  
  
  
    public void connect(String host, int port) {  
        EventLoopGroup group = new NioEventLoopGroup();  
        Bootstrap b = new Bootstrap();  
        b.group(group)  
            .channel(NioSocketChannel.class)  
            .option(ChannelOption.TCP_NODELAY, true)  
            .handler(new ChannelInitializer<SocketChannel>(){  
  
                @Override  
                protected void initChannel(SocketChannel ch)  
                        throws Exception {  
                    ChannelPipeline pipeline = ch.pipeline();  
                    pipeline.addLast(new IdleStateHandler(0, 0, 10,TimeUnit.SECONDS));  
                    pipeline.addLast(new TimeClientHandle());  
                }  
          
            });  
    try {  
        ChannelFuture f;  
        f = b.connect(host, port).sync();  
        f.channel().closeFuture().sync();  
    } catch (InterruptedException e) {  
        group.shutdownGracefully();  
    }  
}  
  
  
    public static void main(String[] args) {  
       new TimeClient().connect("127.0.0.1", 28080);  
    }  
  
  
}  