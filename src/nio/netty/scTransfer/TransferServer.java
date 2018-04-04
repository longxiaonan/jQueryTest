package nio.netty.scTransfer;

import java.net.InetSocketAddress;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TransferServer {  
    public void start(int port) throws InterruptedException {  
        ServerBootstrap b = new ServerBootstrap();// ������������ 
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();// ͨ��nio��ʽ���������Ӻʹ�������
        try {  
            b.group(bossGroup, workerGroup);  
            b.channel(NioServerSocketChannel.class);// ����nio���͵�channel  
            b.localAddress(new InetSocketAddress(port));// ���ü����˿�  
            b.childHandler(new ChannelInitializer<SocketChannel>() {//�����ӵ���ʱ�ᴴ��һ��channel
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // pipeline����channel�е�Handler����channel���������һ��handler������ҵ��
                            ch.pipeline().addLast("myHandler", new TransferServerHandler());  
                        }  
                    });  
            ChannelFuture f = b.bind().sync();// ������ɣ���ʼ��server��ͨ������syncͬ����������ֱ���󶨳ɹ�  
            System.out.println(TransferServer.class.getName() + " started and listen on " + f.channel().localAddress());  
            f.channel().closeFuture().sync();// Ӧ�ó����һֱ�ȴ���ֱ��channel�ر�, main�������˳�.
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            bossGroup.shutdownGracefully().sync();//�ر�EventLoopGroup���ͷŵ�������Դ�����������߳�  
            workerGroup.shutdownGracefully().sync();
        }  
    }  
    public static void main(String[] args) {  
        try {  
            new TransferServer().start(8899);
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
    }  
}  