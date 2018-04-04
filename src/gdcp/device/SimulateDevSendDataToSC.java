package gdcp.device;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import common.utils.NumericUtils;

public class SimulateDevSendDataToSC {
	public static boolean hasSend = false;

	public static void main(String[] args) {
		new SimulateDevSendDataToSC().a();
	}

	private void a() {
//			String host = "180.96.17.124";
//			int port = 2809;
			
//			String host = "183.63.187.148";
//			String host = "183.63.187.150";
//			String host = "192.168.3.51";
//			String host = "njlz.vehicledatas.com";
			String host = "127.0.0.1";
			int port = 8702;
		try (
			Socket socket = new Socket(InetAddress.getByName(host), port);
		){
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			ReadThread readThread = new ReadThread(is);
			List<byte[]> list = this.msgToSend();
			WriteThread writeThread = new WriteThread(os, list);

			readThread.start();
			writeThread.start();

			readThread.join();
			writeThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	class ReadThread extends Thread {
		private InputStream is;
		public ReadThread(InputStream is) {
			this.is = is;
		}
		@Override
		public void run() {
			try {
				while (true) {
					byte[] b = new byte[100];
					int read = is.read(b);
					byte[] tmp = new byte[read];
					System.arraycopy(b, 0, tmp, 0, read);
					System.out.println("got response:" + NumericUtils.byteArrayToHexString(tmp));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	class WriteThread extends Thread {
		private OutputStream os;
		private List<byte[]> list;
		public WriteThread(OutputStream os,List<byte[]> list) {
			this.os = os;
			this.list = list;
		}
		@Override
		public void run() 
		{
			try {
				byte[] b = list.get(0);
				while( true ){
					Thread.sleep(5000);
					os.write(b);
					os.flush();
					System.out.println("send bytes:"+NumericUtils.byteArrayToHexString(b));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<byte[]> msgToSend(){
		List<byte[]> list = new ArrayList<>();
		
//		a(list);
		b(list);
//		c(list);
		
		return list;
	}

	private void c(List<byte[]> list) {
		String s = "E7 02 02 00 66 46 11 31 70 24 00 13 D6 53 11 08 0E 08 1E 28 00 DC 01 00 00 01 01 46 7F 14 06 91 C3 CA 00 00 00 1C 0C 00 40 0B 2F 00 82 FD 05 05 1F 00 60 13 04 F0 00 00 00 01 00 00 00 00 00 B0 00 00 00 11 80 9C 00 00 50 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 80 38 00 00 7C 30 00 8B 00 00 00 00 00 00 00 00 00 00 00 00 00 00 00 04 D3 52 E7";
		byte[] bs = NumericUtils.hexStringToByteArray(s);
		list.add(bs);
	}

	private void b(List<byte[]> list) {
//		String test1 = "23 23 01 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 24 10 01 01 00 00 14 00 0C 38 39 38 36 30 37 62 31 31 39 31 37 30 30 30 30 30 38 36 37 01 06 41 42 43 44 45 46 DA";
//		byte[] test1bs = MyUtil.hexStringToByteArray(test1);
//		MyUtil.generateAndReplaceLengthByte(test1bs);
//		MyUtil.generateAndReplaceValidateCode(test1bs);
//		list.add(test1bs);
		
//		String test2 = "23 23 02 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 00 11 08 09 0B 16 19 85 11 08 09 0B 16 19 02 10 11 10 12 00";
		String test2 = "23 23 02 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 00 11 08 09 0B 16 19 "
						+ "85 11 08 09 0B 16 19 02 10 11 10 12 "
						+ "86 11 08 0A 0B 16 19 "
						//																																																																																											  3228304360
						+ "89 48 4C 46 32 30 31 5F 56 31 30 30 30 30 31 32 30 31 35 2D 30 35 2D 31 30 63 70 75 69 64 3A 31 32 33 34 35 36 30 30 46 32 30 31 37 30 31 30 33 30 30 30 31 47 53 4D 49 4D 45 49 3A 31 32 33 34 35 36 37 53 49 4D 49 4D 53 49 3A 31 32 33 34 35 36 37 53 49 4D 49 43 43 49 44 3A 31 32 33 34 35 36 37 38 39 30 31 03 E8 36 30 31 30 30 30 30 30 31 31 37 32 39 30 30 31 33 C0 6C 03 E8 C0 6C 03 E9 "
						//    				tricpmark257 
						+ "8A 11 07 0A 0B 16 19 01 01 03 04 05 06 07 31 32 33 34 35 36 37 31 31 32 33 34 35 36 37 32 31 32 33 34 35 36 37 33 31 32 33 34 35 36 37 34 31 32 33 34 35 36 37 35 31 32 33 34 35 36 37 36 31 32 33 34 35 36 37 37 "
						+ "00";
		byte[] test2bs = NumericUtils.hexStringToByteArray(test2);
		NumericUtils.generateAndReplaceLengthByte(test2bs);
		NumericUtils.generateAndReplaceValidateCode(test2bs);
		list.add(test2bs);
	}

	private void a(List<byte[]> list) {
		StringBuilder sbd = new StringBuilder();
		sbd.append("23 23 05 FE ");
		sbd.append(NumericUtils.asciiStringToHexString("IDRIVE00000000026"));
		sbd.append(" 01 ");
		sbd.append("00 29 ");// 数据单元长度:41
		sbd.append("11 07 08 0F 22 10 ");// 时间17-7-8 15:34:16
		sbd.append("00 01 ");// 登入流水号
		sbd.append(NumericUtils.asciiStringToHexString("abcd")).append(" ");
//		sbd.append(MyUtil.asciiStringToHexString("njlz")).append(" ");
		sbd.append(NumericUtils.zero(8)).append(" ");
		sbd.append(NumericUtils.asciiStringToHexString("abcdefgh")).append(" ");
//		sbd.append(MyUtil.asciiStringToHexString("weitexun")).append(" ");
		sbd.append(NumericUtils.zero(12)).append(" ");
//		sbd.append(MyUtil.zero(8));
//		sbd.append(" ").append(MyUtil.asciiStringToHexString("njlz")).append(" ");
//		sbd.append(MyUtil.zero(12));
//		sbd.append(" ").append(MyUtil.asciiStringToHexString("weitexun")).append(" ");
		sbd.append("01 ");// 加密方式
		sbd.append("01");// 校验码
		String msg = sbd.toString();
		byte[] login = NumericUtils.hexStringToByteArray(msg);
		NumericUtils.generateAndReplaceValidateCode(login);
		
		StringBuilder sbd2 = new StringBuilder();
		sbd2.append("23 23 06 FE ");
		sbd2.append(NumericUtils.asciiStringToHexString("IDRIVE00000000026"));
		sbd2.append(" 01 ");
		sbd2.append("00 08 ");// 数据单元长度:8
		sbd2.append("11 07 08 0F 23 10 ");// 时间17-7-8 15:35:16
		sbd2.append("00 01 ");// 登入流水号
		sbd2.append("01");// 校验码
		String msg2 = sbd2.toString();
		byte[] logout = NumericUtils.hexStringToByteArray(msg2);
		NumericUtils.generateAndReplaceValidateCode(logout);
		
//		String rtinfos = "23 23 02 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 32 37 32 01 00 87 11 07 07 12 1F 34 01 00 03 01 00 00 01 27 B5 AE 18 26 27 10 63 00 00 26 AB 00 00 02 01 01 02 5A 4E 20 4E 20 4C 0A 46 27 06 05 00 07 34 CA 51 01 E8 BD 08 06 FF FF FF FF FF FF FF FF FF FF FF FF FF FF 07 FF 00 01 03 00 00 00 00 00 08 01 01 18 26 27 10 FF FF 00 01 01 FF FF 09 01 01 00 28 51 51 53 52 51 51 51 51 51 51 51 51 51 51 50 51 51 51 50 51 51 51 50 51 50 51 50 50 50 50 50 50 51 51 51 51 51 53 51 53 9F";
//		String rtinfos1 = "23 23 02 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 32 37 32 01 02 65 11 07 0C 14 27 05 01 01 03 01 00 00 00 00 1E 46 18 DF 27 10 16 01 00 05 C5 00 00 02 01 01 04 4A 4E 20 4E 20 49 18 A6 27 06 05 00 07 16 A5 C7 01 E4 2A A3 06 FF 02 0C F7 FF 01 0C F4 01 01 4B 01 0C 4A 07 00 00 00 00 00 00 00 00 00 08 01 01 18 DF 27 10 01 80 00 01 C8 0C F4 0C F7 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F4 0C F5 0C F7 0C F5 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F4 0C F6 0C F6 0C F5 0C F6 0C F4 0C F5 0C F5 0C F5 0C F6 0C F5 0C F3 0C F5 0C F5 0C F6 0C F6 0C F6 0C F4 0C F6 0C F5 0C F4 0C F5 0C F5 0C F4 0C F4 0C F5 0C F4 0C F6 0C F4 0C F4 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F5 0C F6 0C F6 0C F4 0C F4 0C F5 0C F5 0C F5 0C F6 0C F5 0C F4 0C F4 0C F4 0C F4 0C F6 0C F5 0C F5 0C F5 0C F5 0C F4 0C F4 0C F5 0C F4 0C F5 0C F5 0C F4 0C F4 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F3 0C F5 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F3 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F4 0C F5 0C F4 0C F4 0C F6 0C F5 0C F5 0C F5 0C F6 0C F4 0C F4 0C F4 0C F4 0C F5 0C F6 0C F5 0C F5 0C F5 0C F4 0C F4 0C F5 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F5 0C F6 0C F5 0C F6 0C F5 0C F4 0C F5 0C F5 0C F4 0C F4 0C F6 0C F4 09 01 01 00 78 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4A 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4A 4A 4A 4B 4B 4B 8E";
//		String rtinfos2 = "23 23 02 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 32 37 32 01 01 82 11 07 0C 14 27 05 08 01 01 18 DF 27 10 01 80 00 C9 B8 0C F4 0C F7 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F4 0C F5 0C F7 0C F5 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F4 0C F6 0C F6 0C F5 0C F6 0C F4 0C F5 0C F5 0C F5 0C F6 0C F5 0C F3 0C F5 0C F5 0C F6 0C F6 0C F6 0C F4 0C F6 0C F5 0C F4 0C F5 0C F5 0C F4 0C F4 0C F5 0C F4 0C F6 0C F4 0C F4 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F6 0C F5 0C F6 0C F6 0C F4 0C F4 0C F5 0C F5 0C F5 0C F6 0C F5 0C F4 0C F4 0C F4 0C F4 0C F6 0C F5 0C F5 0C F5 0C F5 0C F4 0C F4 0C F5 0C F4 0C F5 0C F5 0C F4 0C F4 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F3 0C F5 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F3 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F5 0C F4 0C F5 0C F4 0C F4 0C F6 0C F5 0C F5 0C F5 0C F6 0C F4 0C F4 0C F4 0C F4 0C F5 0C F6 0C F5 0C F5 0C F5 0C F4 0C F4 0C F5 0C F6 0C F5 0C F6 1C";
		String rtinfos1 = "23 23 02 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 32 37 32 01 02 65 11 07 0D 11 2D 37 01 03 03 01 00 00 00 00 1E 46 18 E1 27 10 59 01 00 06 33 00 00 02 01 01 03 4C 4E 20 4E 20 4A 18 A6 27 10 05 00 07 16 A5 36 01 E4 2A 8C 06 FF 02 0C F7 FF 01 0C F5 01 0A 4C 01 01 4B 07 00 00 00 00 00 00 00 00 00 08 01 01 18 E1 27 10 01 80 00 01 C8 0C F5 0C F7 0C F7 0C F7 0C F6 0C F7 0C F7 0C F7 0C F7 0C F7 0C F6 0C F6 0C F6 0C F6 0C F7 0C F7 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F7 0C F7 0C F6 0C F5 0C F6 0C F7 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F4 0C F6 0C F6 0C F6 0C F7 0C F7 0C F5 0C F7 0C F5 0C F5 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F7 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F7 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F5 0C F7 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F5 0C F6 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F4 0C F5 0C F6 0C F5 0C F6 0C F6 0C F7 0C F6 0C F6 0C F6 0C F7 0C F6 0C F6 0C F5 0C F6 0C F6 0C F4 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F7 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F5 0C F5 0C F7 0C F5 09 01 01 00 78 4B 4B 4B 4B 4B 4B 4B 4B 4B 4C 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4C 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4C 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4C 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4C 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4A 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4B 4A 4A 4A 4A 4B 4B 4B 4B 8F";
		String rtinfos2 = "23 23 02 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 32 37 32 01 01 82 11 07 0D 11 2D 37 08 01 01 18 E1 27 10 01 80 00 C9 B8 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F5 0C F7 0C F5 0C F7 0C F7 0C F6 0C F6 0C F6 0C F4 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 FF FF FF FF FF FF FF FF 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F7 0C F6 0C F5 0C F6 0C F7 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F7 0C F7 0C F6 0C F6 0C F6 0C F6 0C F7 0C F7 0C F6 0C F7 0C F7 0C F6 0C F7 0C F7 0C F7 0C F6 0C F7 0C F7 0C F6 0C F6 0C F5 0C F6 0C F6 0C F4 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F7 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F5 0C F6 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F5 0C F5 0C F7 0C F5 0C F5 0C F5 0C F5 0C F6 0C F6 0C F5 0C F6 0C F7 0C F5 0C F7 0C F7 0C F6 0C F6 0C F6 0C F4 0C F6 0C F6 0C F6 0C F5 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F5 0C F7 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F6 0C F7 0C F6 0C F6 0C F5 0C F7 0C F6 1B";
		byte[] rtinfo1 = NumericUtils.hexStringToByteArray(rtinfos1);
		NumericUtils.generateAndReplaceValidateCode(rtinfo1);
		byte[] rtinfo2 = NumericUtils.hexStringToByteArray(rtinfos2);
		NumericUtils.generateAndReplaceValidateCode(rtinfo2);
//		list.add(login);
//		list.add(rtinfo1);
//		list.add(rtinfo2);
//		list.add(logout);
		
		String testlogin1 = "23 23 01 FE 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 39 39 39 01 00 24 11 07 0E 08 00 11 00 15 38 39 38 36 30 32 62 39 31 39 31 37 30 30 31 34 38 32 37 33 01 06 41 42 43 44 45 46 CB";
		byte[] testlogin1b = NumericUtils.hexStringToByteArray(testlogin1);
		NumericUtils.generateAndReplaceValidateCode(testlogin1b);
		String testlogin2 = "23 23 08 01 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 39 39 39 01 00 06 11 07 18 13 22 09 6E";
		byte[] testlogin2b = NumericUtils.hexStringToByteArray(testlogin2);
		NumericUtils.generateAndReplaceValidateCode(testlogin2b);
		String testlogin3 = "23 23 07 01 4C 42 39 4B 42 38 4B 47 34 47 45 4E 4A 4C 39 39 39 01 00 00 51";
		byte[] testlogin3b = NumericUtils.hexStringToByteArray(testlogin3);
		NumericUtils.generateAndReplaceValidateCode(testlogin3b);
//		list.add(testlogin1b);
//		list.add(testlogin1b);
//		list.add(testlogin2b);
//		list.add(testlogin3b);
		
		String l = "23 23 01 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 24 10 01 01 00 00 14 00 0C 38 39 38 36 30 37 62 31 31 39 31 37 30 30 30 30 30 38 36 37 01 06 41 42 43 44 45 46 DA";
		byte[] lb = NumericUtils.hexStringToByteArray(l);
		NumericUtils.generateAndReplaceValidateCode(lb);
		String r = "23 23 07 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 00 B3";
		byte[] rb = NumericUtils.hexStringToByteArray(r);
		NumericUtils.generateAndReplaceValidateCode(rb);
		
		
		String ll = "23 23 01 FE 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 24 10 01 01 00 00 14 00 0C 38 39 38 36 30 37 62 31 31 39 31 37 30 30 30 30 30 38 36 37 01 06 41 42 43 44 45 46 DA";
		byte[] llb = NumericUtils.hexStringToByteArray(ll);
		NumericUtils.generateAndReplaceValidateCode(llb);
		String lr = "23 23 02 FE 4C 42 39 4B 42 39 4B 47 37 47 45 4E 4A 4C 31 30 39 01 02 15 11 08 04 12 2B 36 01 01 03 01 00 6E 00 02 CD 44 15 AB 29 9F 62 01 02 91 77 02 00 02 01 01 01 53 50 92 4E 20 60 16 08 27 1A 05 00 07 35 58 AE 01 E7 12 B9 06 01 01 0D 05 01 03 0C E0 07 1C 51 06 16 4D 07 00 00 00 00 00 00 00 00 00 08 01 01 15 AB 29 9F 01 20 00 01 C8 0C F6 0C F7 0C F6 0D 11 0C F6 0C F7 0C F9 0C F4 0C FD 0C F7 0C F6 0C F9 0C F8 0C F7 0C F8 0C F9 0C FC 0C F6 0C FA 0C F6 0C F9 0C F4 0C F7 0C F6 0C F4 0C F9 0C FA 0C F6 0C ED 0C EA 0C EB 0C EC 0C EA 0C EC 0C E9 0C ED 0C E6 0C EC 0C E8 0C E5 0C E7 0C E7 0C E7 0C EA 0C E5 0C E9 0C E9 0C E7 0C E7 0C E9 0C EC 0C E9 0C E7 0C E9 0C EB 0C E7 0C F4 0C F9 0C F6 0C F3 0C F3 0C F7 0C F5 0C F6 0C F6 0C F4 0C FA 0C F6 0C F9 0C F8 0C F6 0C F4 0C F6 0C F6 0C F6 0C F5 0D 01 0C F8 0C F9 0C F8 0C F9 0C F7 0C F9 0C FC 0C F6 0C F6 0C F9 0C F6 0C F6 0C F4 0C FB 0C F7 0C F5 0C F5 0C F4 0C F6 0C F4 0C F6 0C F7 0C FE 0C F5 0C F6 0C F7 0C F5 0C F6 0C F6 0D 05 0C F7 0C F7 0C F9 0C F7 0C F5 0C F8 0C F6 0C F9 0C F9 0C F8 0C FF 0C F7 0C FC 0C F7 0C F6 0C F7 0C F9 0C FD 0C FA 0C F9 0C F8 0C F9 0C F6 0C F9 0C F7 0C FD 0C FA 0C FF 0C FB 0C FE 0D 00 0C FF 0C FA 0C F6 0C F9 0C FC 0C FC 0C FB 0C F9 0C F6 0C F9 0C F9 0C F6 0C F4 0C F7 0C F4 0C F7 0C F6 0C F3 0C F3 0C F5 0C F7 0C FA 0C F8 0D 02 0C F6 0C FC 0C F4 0C F6 0C F7 0C FA 0C F4 0C F7 0D 06 0C FF 0C F7 0C F9 0C FA 0C F8 0C F7 0C F9 0C F6 0C F8 0C FC 0C F7 0C F6 0C F4 0C F5 0C F4 0C F6 0C F6 0C F6 0C F6 0C F3 0C F4 0C F3 0C F4 0C F2 0C F4 0C F3 0C F4 0D 01 0C FA 09 01 01 00 28 50 4E 50 4E 50 4E 4E 4E 50 4E 4F 4E 4E 4E 50 4E 4E 4E 4E 4E 4E 4D 4E 4E 50 50 50 51 50 51 50 4E 4F 51 4E 51 4E 50 50 4E 32";
		byte[] lrb = NumericUtils.hexStringToByteArray(lr);
		NumericUtils.generateAndReplaceValidateCode(lrb);
		
		String back = "23 23 82 01 36 30 31 30 30 30 30 30 31 31 37 32 39 38 38 38 38 01 00 08 11 07 1D 0B 16 19 80 00 C1";
		byte[] backb = NumericUtils.hexStringToByteArray(back);
		NumericUtils.generateAndReplaceValidateCode(backb);
		
	}
	
	
}


