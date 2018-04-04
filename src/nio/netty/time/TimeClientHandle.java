package nio.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandle extends ChannelInboundHandlerAdapter {  
  
  
    private byte[] bytes;  
  
  
    public TimeClientHandle() {  
        bytes = ("client msg" + System.getProperty("line.separator"))  
                .getBytes();  
    }  
  
  
    @Override  
    public void channelActive(ChannelHandlerContext ctx) throws Exception {  
        ByteBuf buf = null;  
        buf = Unpooled.buffer(bytes.length);  
        buf.writeBytes(bytes);  
        ctx.writeAndFlush(buf);  
    }  
  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg)  
            throws Exception {  
        String body = (String) msg;  
        System.out.println("The server reback msg is:" + body);  
    }  
  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  
            throws Exception {  
        ctx.close();  
    }  
  
  
} 