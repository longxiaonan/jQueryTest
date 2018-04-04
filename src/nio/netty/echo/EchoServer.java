package nio.netty.echo;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import nio.netty.time.TimeServerHandle;

public class EchoServer {  
    public void start(int port) throws InterruptedException {  
        ServerBootstrap b = new ServerBootstrap();// 引导辅助程序 
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();// 通过nio方式来接收连接和处理连接  
        try {  
            b.group(bossGroup, workerGroup);  
            b.channel(NioServerSocketChannel.class);// 设置nio类型的channel  
            b.localAddress(new InetSocketAddress(port));// 设置监听端口  
            b.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // pipeline管理channel中的Handler，在channel队列中添加一个handler来处理业务
                        	//添加Handler处理器
                            ch.pipeline().addLast("myHandler", new EchoServerHandler());
                            
                            //添加编码解码器, 解决粘包问题(client也需要配置)
                            //遍历ByteBuf中的字节, 是否有\n或者\r\n, 如果有, 就说明说明结束, 读取从开始字节到\n结尾的字节.
                            //可以指定读取的长度
                            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
                            ch.pipeline().addLast(new StringDecoder());//将channelRead里面的msg转换成了String
                        }  
                    });  
            ChannelFuture f = b.bind().sync();// 配置完成，开始绑定server，通过调用sync同步方法阻塞直到绑定成功  
            System.out.println(EchoServer.class.getName() + " started and listen on " + f.channel().localAddress());  
            f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭, main函数成退出.
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            bossGroup.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉所有资源包括创建的线程  
            workerGroup.shutdownGracefully().sync();
        }  
    }  
    public static void main(String[] args) {  
        try {  
            new EchoServer().start(9111);
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  