package com.weiss.j2se.encode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/** 
 *  Obama64 — 奥巴码...
 *  1)v1.1开始支持多字节字符，对应的编码/解码接口为:
 *  	编码：byte[] encode(byte[] content)
 *  	解码：byte[] decode(byte[] content) 
 * 	上述两个方法与v1.0中的编码思路一致，通过在每三字节前加一字节记录这三字节mod64的情况
 *  不同之处在于记录字节使用两个bit位来记录(v1.0中记录ASCII只用一个bit位)，所以相对于
 *  base64其编码长度仅多一个Bluff code所占的字节
 *  2)v1.0中所有方法都改为 xxxAsciixxx, 以表明只支持ASCII字符
 * 	3)v1.1仍然支持BLUFF功能
 * 
 *  @author Crusader
 *  @version 1.1
 *  Date: 2011-10-13
 * */
public class Obama64 {
	
	private static final int MASK_64 = 0x3F;
	private static final byte DEF_SECRET = 0x43;
	
//	static final byte[] CHUNK_SEPARATOR = { 13, 10 };
	
	public static boolean BLUFF = false;
	
//	public static boolean CHUNK = false;//76
//	public static final int CHUNK_LENGTH = 76;
	
	/** 编码基表 */
	private static final char base_encode[] = {
		// 默认序列
	    'P', 'e', 'r', 'Q', 'f', 'w', '7', 'g', 'i', 'p', 	/*  0- 9 */
	    '8', '9', 'B', 'd', 'O', 'v', '6', 'S', 'D', 'M', 	/* 10-19 */
	    'b', 's', 'R', 'C', 'N', 'c', 'm', '5', 'l', 'z', 	/* 20-29 */
	    'I', 'X', 'o', 'j', 'H', '2', 'x', 'W', '1', 'J', 	/* 30-39 */
	    'V', 'h', 'G', '0', 'Y', 'q', 'E', 'T', 'k', '3', 	/* 40-49 */
	    'a', 'L', 'y', 'n', 't', 'U', 'u', 'Z', '4', 'K', 	/* 50-59 */
	    'F', 'A', '_', '-'									/* 60-63 */ //95,45, 
	};
	
	/** 解码参照表 */
	private static int base_decode[] = {
		// 参照编码表默认序列的值序
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  			/*  0- 9 */
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  			/*  10- 19 */
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  			/*  20- 29 */
		-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,  			/*  30- 39 */
		-1, -1, -1, -1, -1, 63, -1, -1, 43, 38,  			/*  40- 49 */
		35, 49, 58, 27, 16, 6, 10, 11, -1, -1,  			/*  50- 59 */
		-1, -1, -1, -1, -1, 61, 12, 23, 18, 46,  			/*  60- 69 */
		60, 42, 34, 30, 39, 59, 51, 19, 24, 14,  			/*  70- 79 */
		0, 3, 22, 17, 47, 55, 40, 37, 31, 44,  				/*  80- 89 */
		57, -1, -1, -1, -1, 62, 0, 50, 20, 25,  			/*  90- 99 */
		13, 1, 4, 7, 41, 8, 33, 48, 28, 26,  				/*  100- 109 */
		53, 32, 9, 45, 2, 21, 54, 56, 15, 5,  				/*  110- 119 */
		36, 52, 29, -1, -1, -1, -1, -1, -1		 			/*  120- 128 */	
	};
	
	/**
	 * 重排序编码基表和对应的解码表
	 */
	public static void init(){
		
		// 重排序编码基表
		ArrayList<Character> list = new ArrayList<Character>();
		for(char c : base_encode)
			list.add(c);
		Collections.shuffle(list);
		for(int i=0; i<list.size(); i++)
			base_encode[i] = list.get(i);
		
		// 根据重排序后的编码基表初始化解码表
		initDecode();
	}
	
	private static void initDecode(){
		Arrays.fill(base_decode, -1);
		for(int i=0; i<base_encode.length; i++){
			base_decode[base_encode[i]] = i;
		}
	}
	
	public static void setDecode(int[] decode){
		base_decode = null;
		base_decode = decode;
		
		for(int i=0; i<base_decode.length; i++){
			if(base_decode[i] < 0)
				continue;
			else
				base_encode[base_decode[i]] = (char)i;
		}
	}
	
	public static byte[] encode(byte[] content){
		if(content==null || content.length==0)
			return content;
		
		int len = content.length;
		
		byte[] cArray = new byte[len + (int)Math.ceil(len/3.0d) + 1];
		
		if(BLUFF){
			int s = 0;
			int r = new Random().nextInt(26);
			boolean u = ((r ^ len) & 1) == 1;
			s = u ? 65 : 97;
			cArray[0] = (byte)(r + s);
		}else{
			cArray[0] = DEF_SECRET;
		}
		
		byte c = 0;
		int n = 0;
		int mark = 0;
		int pos = 0;
		int segs = 0;
		for(int i=0; i<len; i+=3){
			mark = 1+i+segs;
			for(int k=0; (k<3) && (pos<len); k++){
				c = content[pos];
				
				if(c<0){
					c = (byte)~c;
					cArray[mark] |= (2<<(k<<1));
				}
				
				n = c ^ cArray[0]; 
				n ^= k;
				
				cArray[mark] |= ((n>>>6)<<(k<<1)); 
				
				cArray[mark+k+1] = (byte)base_encode[n & MASK_64];
				pos++;
			}
			segs++;
			cArray[mark] = (byte)base_encode[cArray[mark]];
		}
		
		return cArray;
	}
	
	public static byte[] decode(byte[] content){
		
		if(content==null || content.length==0)
			return content;
		
		int len = content.length;
		
		byte[] cArray = new byte[len - 1 - (int)Math.ceil((len-1)/4.0)];
		
		byte secret = content[0];
		byte c = 0;
		int mark = 0;
		int index = 0;
		int tmp = 0;
		for(int i=1; i<len; i+=4){
			mark = base_decode[content[i]];
			for(int k=0, pos=i + k + 1; k<3 && pos<len; k++, pos++){
				c = content[pos];
				tmp = mark>>>(k<<1); 
				cArray[index] = (byte)((base_decode[c] + ((tmp&1)<<6))^ k ^secret);
				if((tmp & 2) > 0)
					cArray[index] = (byte)~cArray[index];
				index++;
			}
		}
		
		return cArray;
	}
	
	
	/** 
	 * 	编码
	 * */
	public static byte[] encodeAsciiBytes(byte[] content){
		
		if(content==null || content.length==0)
			return null;
		
		int len = content.length;
		
		byte[] cArray = new byte[len + (int)Math.ceil(len/6.0d) + 1];
		
		if(BLUFF){
			int s = 0;
			int r = new Random().nextInt(26);
			boolean u = ((r ^ len) & 1) == 1;
			s = u ? 65 : 97;
			cArray[0] = (byte)(r + s);
		}else{
			cArray[0] = DEF_SECRET;
		}
		
		
		byte c = 0;
		int n = 0;
		int mark = 0;
		int pos = 0;
		int segs = 0;
		for(int i=0; i<len; i+=6){
			mark = 1+i+segs;
			for(int k=0; (k<6) && (pos<len); k++){
				c = content[pos];
				n = c ^ cArray[0]; 
				n ^= k;
				cArray[mark] |= ((n>>>6)<<k); 
				
				cArray[mark+k+1] = (byte)base_encode[n & MASK_64];
				pos++;
			}
			segs++;
			cArray[mark] = (byte)base_encode[cArray[mark]];
		}
		
		return cArray;
		
	}
	
	public static byte[] encodeAsciiBytes(String content){
		
		if(content==null || content.length()==0)
			return null;
		
		int len = content.length();
		
		byte[] cArray = new byte[len + (int)Math.ceil(len/6.0d) + 1];
		
		if(BLUFF){
			int s = 0;
			int r = new Random().nextInt(26);
			boolean u = ((r ^ len) & 1) == 1;
			s = u ? 65 : 97;
			cArray[0] = (byte)(r + s);
		}else{
			cArray[0] = DEF_SECRET;
		}
		
		char c = '0';
		int n = 0;
		int mark = 0;
		int pos = 0;
		int segs = 0;
		
		for(int i=0; i<len; i+=6){
			mark = 1+i+segs;
			for(int k=0; (k<6) && (pos<len); k++){
				c = content.charAt(pos);
				n = c ^ cArray[0]; 
				n ^= k;
				cArray[mark] |= ((n>>>6)<<k); 
				
				cArray[mark+k+1] = (byte)base_encode[n & MASK_64];
				pos++;
			}
			segs++;
			cArray[mark] = (byte)base_encode[cArray[mark]];
		}
		
		return cArray;
		
	}
	
	public static String encodeAsciiString(String content){
		
		if(content==null || content.length()==0)
			return null;
		
		int len = content.length();
		
		char[] cArray = new char[len + (int)Math.ceil(len/6.0d) + 1];
		
		if(BLUFF){
			int s = 0;
			int r = new Random().nextInt(26);
			boolean u = ((r ^ len) & 1) == 1;
			s = u ? 65 : 97;
			cArray[0] = (char)(r + s);
		}else{
			cArray[0] = (char)DEF_SECRET;
		}
		
		char c = '0';
		int n = 0;
		int mark = 0;
		int pos = 0;
		int segs = 0;
		for(int i=0; i<len; i+=6){
			mark = 1+i+segs;
			for(int k=0; (k<6) && (pos<len); k++){
				c = content.charAt(pos);
				n = c ^ cArray[0]; 
				n ^= k;
				cArray[mark] |= ((n>>>6)<<k); 
				
				cArray[mark+k+1] = base_encode[n & MASK_64];
				pos++;
			}
			segs++;
			cArray[mark] = base_encode[cArray[mark]];
		}
		
		return new String(cArray);
		
	}
	
	public static String encodeAsciiString(byte[] content){
		
		if(content==null || content.length==0)
			return null;
		
		int len = content.length;
		
		char[] cArray = new char[len + (int)Math.ceil(len/6.0d) + 1];
		
		if(BLUFF){
			int s = 0;
			int r = new Random().nextInt(26);
			boolean u = ((r ^ len) & 1) == 1;
			s = u ? 65 : 97;
			cArray[0] = (char)(r + s);
		}else{
			cArray[0] = (char)DEF_SECRET;
		}
		
		byte c = 0;
		int n = 0;
		int mark = 0;
		int pos = 0;
		int segs = 0;
		for(int i=0; i<len; i+=6){
			mark = 1+i+segs;
			for(int k=0; (k<6) && (pos<len); k++){
				c = content[pos];
				n = c ^ cArray[0]; 
				n ^= k;
				cArray[mark] |= ((n>>>6)<<k); 
				
				cArray[mark+k+1] = base_encode[n & MASK_64];
				pos++;
			}
			segs++;
			cArray[mark] = base_encode[cArray[mark]];
		}
		
		return new String(cArray);
		
	}
	
	/** 解码 */
	public static String decodeAsciiString(String content){
		
		if(content==null || content.length()==0)
			return null;
		
		int len = content.length();
		
		char[] cArray = new char[len - 1 - (int)Math.ceil((len-1)/7.0)];
		
		char secret = content.charAt(0);
		char c = '0';
		int mark = 0;
		int index = 0;
		for(int i=1; i<len; i+=7){
			mark = base_decode[content.charAt(i)];
			for(int k=0, pos=i + k + 1; k<6 && pos<len; k++, pos++){
				c = content.charAt(pos);
//				cArray[index] = (char)((base_decode[c] + 64*((mark>>>k)&1))^ k ^secret);
				cArray[index] = (char)((base_decode[c] + (((mark>>>k)&1)<<6))^ k ^secret);
				index++;
			}
		}
		
		return new String(cArray);
	}
	
	public static String decodeAsciiString(byte[] content){
		
		if(content==null || content.length==0)
			return null;
		
		int len = content.length;
		
		char[] cArray = new char[len - 1 - (int)Math.ceil((len-1)/7.0)];
		
		byte secret = content[0];
		byte c = 0;
		int mark = 0;
		int index = 0;
		for(int i=1; i<len; i+=7){
			mark = base_decode[content[i]];
			for(int k=0, pos=i + k + 1; k<6 && pos<len; k++, pos++){
				c = content[pos];
				cArray[index] = (char)((base_decode[c] + (((mark>>>k)&1)<<6))^ k ^secret);
				index++;
			}
		}
		
		return new String(cArray);
	}
	
	public static byte[] decodeAsciiBytes(byte[] content){
		
		if(content==null || content.length==0)
			return null;
		
		int len = content.length;
		
		byte[] cArray = new byte[len - 1 - (int)Math.ceil((len-1)/7.0)];
		
		byte secret = content[0];
		byte c = 0;
		int mark = 0;
		int index = 0;
		for(int i=1; i<len; i+=7){
			mark = base_decode[content[i]];
			for(int k=0, pos=i + k + 1; k<6 && pos<len; k++, pos++){
				c = content[pos];
				cArray[index] = (byte)((base_decode[c] + (((mark>>>k)&1)<<6))^ k ^secret);
				index++;
			}
		}
		
		return cArray;
	}
	
	public static byte[] decodeAsciiBytes(String content){
		
		if(content==null || content.length()==0)
			return null;
		
		int len = content.length();
		
		byte[] cArray = new byte[len - 1 - (int)Math.ceil((len-1)/7.0)];
		
		byte secret = (byte)content.charAt(0);
		byte c = 0;
		int mark = 0;
		int index = 0;
		for(int i=1; i<len; i+=7){
			mark = base_decode[content.charAt(i)];
			for(int k=0, pos=i + k + 1; k<6 && pos<len; k++, pos++){
				c = (byte)content.charAt(pos);
				cArray[index] = (byte)((base_decode[c] + (((mark>>>k)&1)<<6))^ k ^secret);
				index++;
			}
		}
		
		return cArray;
	}
	
	public static void printBaseEncode(){
		int loop =0;
		for(char n : base_encode){
			System.out.print("'"+n+"', ");
			loop++;
			if(loop%10==0)
				System.out.println("\t/* " + (loop-10) + " - " + (loop-1) + " */" );
		}
	}
	
	public static void printBaseDecode(){
		int loop =0;
		for(int n : base_decode){
			System.out.print((n<10&&n>-1?" ":"") + n + ", ");
			loop++;
			if(loop%10==0)
				System.out.println("\t/* " + (loop-10) + " - " + (loop-1) + " */");
		}
	}

}