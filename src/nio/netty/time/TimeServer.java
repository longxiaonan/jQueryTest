package nio.netty.time;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TimeServer {  
  
  
    public void bind(int port) {  
        EventLoopGroup bossGroup = new NioEventLoopGroup();  
        EventLoopGroup workGroup = new NioEventLoopGroup();  
        ServerBootstrap b = new ServerBootstrap();  
        b.group(bossGroup, workGroup)  
            .channel(NioServerSocketChannel.class)  
            .option(ChannelOption.SO_BACKLOG, 1024)  
            .childHandler(new ChannelInitializer<SocketChannel>(){  
            	@Override
                    protected void initChannel(SocketChannel ch)  
                            throws Exception {  
                        ch.pipeline().addLast(new TimeServerHandle());  
                    }  
          
        });  
        try {  
            ChannelFuture f = b.bind(port).sync();  
            f.channel().closeFuture().sync();  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
            bossGroup.shutdownGracefully();  
            workGroup.shutdownGracefully();  
        }  
    }  
      
      
    public static void main(String[] args) {  
        int port = 28080;  
//      String host = "172.16.9.249";  
        new TimeServer().bind(port);  
    }  
}  