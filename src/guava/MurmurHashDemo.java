package guava;

import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import encrypt.MD5;

public class MurmurHashDemo {
	public static void main(String[] args) {
		HashFunction hf = Hashing.murmur3_128(); // 32bit version available as well
		HashCode hc = hf.newHasher()
		   .putLong(123)
		   .putString("longxiaonan", Charsets.UTF_8)
		   .hash();
		System.out.println(hc);
		
		System.out.println(MD5.encryptToHex("123"));
	}
}
