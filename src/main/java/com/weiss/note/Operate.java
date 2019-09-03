package com.weiss.note;

@SuppressWarnings("ALL")
public class Operate {
    private Student stu[] = {new Student(1, "����", 0), new Student(2, "����", 0),
            new Student(3, "����", 0), new Student(4, "����", 0)};// ��ѡ����Ϣ
    private boolean flag = true;

    public Operate() {
        this.printInfo();    // �������ѡ����Ϣ
        while (flag) {
            this.vote();    // ѭ������ͶƱ
        }
        this.printInfo();    // ���ͶƱ֮��ĺ�ѡ����Ϣ
        this.getResult();    // �õ����
    }

    private void getResult() {    // �õ����յ�ͶƱ���
        java.util.Arrays.sort(this.stu);    // ����
        System.out.println("ͶƱ���ս����" + this.stu[0].getName() + "ͬѧ�������" + this.stu[0].getVote() + "Ʊ��ѡ�೤��");
    }

    public void vote() {    // �˷������ͶƱ����
        InputData input = new InputData();    // ��������
        int num = input.getInt("������೤��ѡ�˴��ţ�����0��������", "��ѡƱ��Ч����������ȷ�ĺ�ѡ�˴��ţ�");
        switch (num) {
            case 0: {
                this.flag = false;    // �ж�ѭ��
                break;
            }
            case 1: {
                this.stu[0].setVote(this.stu[0].getVote() + 1);
                break;
            }
            case 2: {
                this.stu[1].setVote(this.stu[1].getVote() + 1);
                break;
            }
            case 3: {
                this.stu[2].setVote(this.stu[2].getVote() + 1);
                break;
            }
            case 4: {
                this.stu[3].setVote(this.stu[3].getVote() + 1);
                break;
            }
            default: {
                System.out.println("��ѡƱ��Ч����������ȷ�ĺ�ѡ�˴��ţ�");
            }
        }
    }

    public void printInfo() {
        for (int i = 0; i < stu.length; i++) {
            System.out.println(this.stu[i].getStuNo() + "��"
                    + this.stu[i].getName() + "��" + this.stu[i].getVote() + "��");
        }
    }
}
