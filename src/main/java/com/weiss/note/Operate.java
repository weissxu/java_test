package com.weiss.note;

public class Operate {
	private Student stu[] = {new Student(1,"张三",0),new Student(2,"李四",0),
			new Student(3,"王五",0),new Student(4,"赵六",0)} ;// 侯选人信息
		private boolean flag = true ;
		public Operate(){
			this.printInfo() ;	// 先输出候选人信息
			while(flag){
				this.vote() ;	// 循环调用投票
			}
			this.printInfo() ;	// 输出投票之后的侯选人信息
			this.getResult() ;	// 得到结果
		}
		private void getResult(){	// 得到最终的投票结果
			java.util.Arrays.sort(this.stu) ;	// 排序
			System.out.println("投票最终结果：" + this.stu[0].getName()+"同学，最后以"+this.stu[0].getVote()+"票当选班长！") ;
		}
		public void vote(){	// 此方法完成投票功能
			InputData input = new InputData() ;	// 输入数据
			int num = input.getInt("请输入班长侯选人代号（数字0结束）：","此选票无效，请输入正确的侯选人代号！") ;
			switch(num){
				case 0:{
					this.flag = false ;	// 中断循环
					break ;
				}
				case 1:{
					this.stu[0].setVote(this.stu[0].getVote() + 1) ;
					break ;
				}
				case 2:{
					this.stu[1].setVote(this.stu[1].getVote() + 1) ;
					break ;
				}
				case 3:{
					this.stu[2].setVote(this.stu[2].getVote() + 1) ;
					break ;
				}
				case 4:{
					this.stu[3].setVote(this.stu[3].getVote() + 1) ;
					break ;
				}
				default:{
					System.out.println("此选票无效，请输入正确的候选人代号！") ;
				}
			}
		}
		public void printInfo(){	
			for(int i=0;i<stu.length;i++){
				System.out.println(this.stu[i].getStuNo() + "："
					+ this.stu[i].getName() + "【"+this.stu[i].getVote()+"】") ;	
			}
		}
}
