package com.weiss.json;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

public class GsonTest {

	// private static Gson gson = new
	// GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").disableHtmlEscaping().create();

	@Test
	public void test4() {
		Gson gson = new Gson();
		Type listType = new TypeToken<List<String>>() {
		}.getType();

		List<String> strs = new ArrayList<String>();
		strs.add("hello");
		strs.add("world");
		strs.add("welcome");
		String jsonStr = gson.toJson(strs, listType);
		System.out.println(jsonStr);
		List<String> fromJson = gson.fromJson(jsonStr, listType);
		System.out.println(fromJson);

	}

	@Test
	public void test3() {
		// (Serialization)
		Gson gson = new Gson();
		assertEquals("1", gson.toJson(1));
		System.out.println(gson.toJson("abcd"));
		// assertEquals("abcd", gson.toJson("abcd"));
		assertEquals("10", gson.toJson(new Long(10)));
		int[] values = { 1 };
		assertEquals("[1]", gson.toJson(values));

		// (Deserialization)
		int one = gson.fromJson("1", int.class);
		assertEquals(1, one);
		Boolean fals = gson.fromJson("false", Boolean.class);
		assertEquals(false, fals);
		String str = gson.fromJson("\"abc\"", String.class);
		assertEquals("abc", str);
		String[] strArray = gson.fromJson("[\"abc\"]", String[].class);
		// assertEquals("[\"abc\"]", strArray);
		System.out.println(strArray);
	}

	@Test
	public void testTrasint2() {
		Gson gson = new Gson();
		System.out.println(gson.fromJson("{'id':10,'name':'weiss','gender':'male'}", Person.class));
	}

	@Test
	public void testTrasint1() {
		Gson gson = new Gson();
		System.out.println(gson.toJson(new Person(10, "weiss", "male")));
	}

	@Test
	public void testExpose2() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println(gson.toJson(new Person(10, "weiss", "male")));
	}

	@Test
	public void testExpose1() {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.out.println(gson.fromJson("{'id':10,'name':'weiss','gender':'male'}", Person.class));
	}

	public static class Person {
		private int id;
		@Expose
		private String name;
		@Expose
		private String gender;
		@Expose
		private int age;

		public Person(int id, String name, String gender) {
			super();
			this.id = id;
			this.name = name;
			this.gender = gender;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public Person(int id, String name) {
			this.id = id;
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", name=" + name + ", gender=" + gender + ", age=" + age + "]";
		}

	}
}
