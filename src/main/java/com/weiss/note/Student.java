package com.weiss.note;

	public class Student implements Comparable<Student>{
		private int stuNo ;	// 学生编号
		private String name ;	// 学生姓名
		private int vote ;	// 学生票数
		public Student(int stuNo,String name,int vote){
			this.setStuNo(stuNo) ;
			this.setName(name) ;
			this.setVote(vote) ;
		}
		public int compareTo(Student o){
			if(this.vote<o.vote){
				return 1 ;
			}else if(this.vote>o.vote){
				return -1 ;
			}else{
				return 0 ;
			}
		}
		public void setStuNo(int stuNo){
			this.stuNo = stuNo ;
		}
		public void setName(String name){
			this.name = name ;
		}
		public void setVote(int vote){
			this.vote = vote ;
		}
		public int getStuNo(){
			return this.stuNo ;
		}
		public String getName(){
			return this.name ;
		}
		public int getVote(){
			return this.vote ;
		}
	 };

