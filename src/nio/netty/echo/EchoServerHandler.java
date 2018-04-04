package nio.netty.echo;

import java.io.UnsupportedEncodingException;

import ch.qos.logback.core.joran.spi.NoAutoStart;
import common.utils.NumericUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

@Sharable // 注解@Sharable可以让它在channels间共享
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	/**
	 * 当有收到数据的时候执行
	 * 
	 * @param ctx
	 * @param msg
	 */
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		System.out.println("server received data :[" + msg +"]");
		ByteBuf buf = (ByteBuf) msg;
		//获取接收到的字节数组
		byte[] receiveByte = null;
		if (buf.hasArray()) {
			receiveByte = buf.array();
		}else{
			receiveByte = new byte[buf.readableBytes()];
			buf.readBytes(receiveByte);
		}
		//转换成String后打印
		try {
			System.out.println("The server receive data : [" + new String(receiveByte, "UTF-8") + "]");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//处理数据后写会客户端
		if (receiveByte[2] == 0x5 && receiveByte[3] == (0xEF&0xFF)) {//23 23 05 FE
			System.out.println("The data received is platform login data");
			receiveByte[3] = 0x01;
		}
		if (receiveByte[2] == 0x6 && receiveByte[3] == -2) {//23 23 06 FE
			System.out.println("The data received is platform logout data");
			receiveByte[3] = 0x01;
		}
		if (receiveByte[2] == 0x2 && receiveByte[3] == -2) {//23 23 02 FE
			System.out.println("The data received is platform RTINFO data");
			String respStr = "23 23 02 01 4C 42 39 4B 42 38 4B 47 30 47 45 4E 4A 4C 32 35 33 01 00 06 11 08 1F 00 14 11 43";
			receiveByte = NumericUtils.hexStringToByteArray(respStr);
			
		}
//		ByteBuf resp = Unpooled.buffer();
//		resp.writeBytes(receiveByte);
		ByteBuf resp = Unpooled.copiedBuffer(receiveByte);//效果和上面两条相同, 将字节数组写入ByteBuf
		ctx.write(resp);
	}

	/**
	 * 通道激活
	 */
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		super.channelActive(ctx);
		System.out.println("channel is actived!");
	}

	/**
	 * 当channel完成的时候执行
	 * 
	 * @param ctx
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		/*ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) // flush掉所有写回的数据
				.addListener(ChannelFutureListener.CLOSE); // 当flush完成后关闭channel
		 */	
		ctx.flush();
		}

	/**
	 * 当异常出现的时候进行处理
	 * 
	 * @param ctx
	 * @param cause
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();// 捕捉异常信息
		System.out.println(">>>>>>"+cause.getMessage());
		ctx.close();// 出现异常时关闭channel
	}
}