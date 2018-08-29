package nio.netty.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeServerHandle extends ChannelInboundHandlerAdapter {  
      
    private int count;  
  
  
    @Override  
    public void channelRead(ChannelHandlerContext ctx, Object msg)  
            throws Exception {  
        ByteBuf body = (ByteBuf) msg;  
        byte[] bytes = new byte[body.readableBytes()];  
        body.readBytes(bytes);  
        String str = new String(bytes,"Utf-8");  
        System.out.println( "The heartbeat message is:" + str + ",The times is: " + count++ );  
    }  
  
  
    @Override  
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)  
            throws Exception {  
        ctx.close();  
    }  
  
  
}  