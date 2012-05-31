package com.weiss.j2se.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

public class RegTest {
	@Test
	public void testReg13() {
		Pattern pattern = Pattern.compile("a.*b");
		Matcher matcher = pattern.matcher("");
	}

	@Test
	public void testReg12() {
		// ／／<hadoop.version>1.0.1</hadoop.version>
		Pattern pattern = Pattern.compile(".*hadoop.*");
		Matcher matcher = pattern.matcher("<hadoop.version>1.0.1</hadoop.version>");
		if (matcher.matches()) {
			System.out.println(matcher.group());
		}

	}

	// 贪婪与懒
	@Test
	public void testReg11() {
		Pattern pattern1 = Pattern.compile("a.*b");
		Pattern pattern2 = Pattern.compile("a.*?b");
		String input = "aabab";
		Matcher matcher1 = pattern1.matcher(input);
		Matcher matcher2 = pattern2.matcher(input);
		System.out.println("---------matcher1---------");
		while (matcher1.find()) {
			System.out.println(matcher1.group());
		}
		System.out.println("--------matcher2----------");
		while (matcher2.find()) {
			System.out.println(matcher2.group());
		}
	}

	@Test
	public void testReg9() {
		// ◆截取http://地址
		// 截取url
		Pattern pattern = Pattern.compile("(http://|https://){1}[w.-/:]+");
		Matcher matcher = pattern.matcher("dsdsds<http://www.baidu.com//gfgffdfd>fdf");
		StringBuffer buffer = new StringBuffer();
		// System.out.println(matcher.group(0));
		// System.out.println(matcher.group(1));
		while (matcher.find()) {
			buffer.append(matcher.group());
			buffer.append("/r/n");
			System.out.println(buffer.toString());
		}
	}

	@Test
	public void testReg8() {
		// ◆查找html中对应条件字符串
		Pattern pattern = Pattern.compile("href=/\"(.+?)/\"");
		Matcher matcher = pattern.matcher("<a href=/\"index.html/\">主页</a>");
		if (matcher.find())
			System.out.println(matcher.group(1));
	}

	@Test
	public void testReg7() {
		// ◆去除html标记
		Pattern pattern = Pattern.compile("<.+?>", Pattern.DOTALL);
		Matcher matcher = pattern.matcher("<a href=/\"index.html/\">主页</a>");
		String string = matcher.replaceAll("");
		System.out.println(string);
	}

	@Test
	public void testReg6() {
		// ◆验证是否为邮箱地址

		String str = "ceponline@yahoo.com.cn";
		Pattern pattern = Pattern.compile("[//w//.//-]+@([//w//-]+//.)+[//w//-]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher.matches());
	}

	@Test
	public void testReg5() {
		// ◆文字替换（置换字符）
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World ");
		StringBuffer sbr = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sbr, "Java");
		}
		matcher.appendTail(sbr);
		System.out.println(sbr.toString());

	}

	@Test
	public void testReg4() {
		// ◆文字替换（全部）
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		// 替换第一个符合正则的数据
		System.out.println(matcher.replaceAll("Java"));
	}

	@Test
	public void testReg3() {
		Pattern pattern = Pattern.compile("正则表达式");
		Matcher matcher = pattern.matcher("正则表达式 Hello World,正则表达式 Hello World");
		// 替换第一个符合正则的数据
		System.out.println(matcher.replaceFirst("Java"));
	}

	@Test
	public void testReg2() {
		Pattern pattern = Pattern.compile("[, |]+");
		String[] strs = pattern.split("Java Hello World  Java,Hello,,World|Sun");
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}

	@Test
	public void testReg1() {
		// 查找以Java开头,任意结尾的字符串
		Pattern pattern = Pattern.compile("^Java.*");
		Matcher matcher = pattern.matcher("Java不是人");
		boolean b = matcher.matches();
		// 当条件满足时，将返回true，否则返回false
		System.out.println(b);
	}

	@Test
	public void testReg() {
		Pattern pattern = Pattern.compile("(http://|https://){1}[\\w.-/:]+");
		Matcher matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
		StringBuffer buffer = new StringBuffer();
		while (matcher.find()) {
			buffer.append(matcher.group());
			buffer.append("\r\n");
			System.out.println(buffer.toString());
		}
	}
}
