package com.weiss.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("ALL")
public class InputData {
    private BufferedReader buf = null;        // ��������

    public InputData() {
        this.buf = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getString(String info) {    // �õ��ַ���
        String temp = null;    // ������������
        System.out.print(info);
        try {
            temp = this.buf.readLine();    // ��������
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public int getInt(String info, String err) {    // �õ���������
        int temp = 0;
        String str = null;
        boolean flag = true;    // ����һ��ѭ�����
        while (flag) {
            str = this.getString(info);
            if (str.matches("\\d+")) {
                temp = Integer.parseInt(str);
                flag = false;    // ���ı�־λ�����˳�ѭ��
            } else {
                System.out.println(err);
            }
        }
        return temp;
    }
}
