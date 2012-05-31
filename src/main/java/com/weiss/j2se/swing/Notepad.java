package com.weiss.j2se.swing;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Notepad {
	public static void main(String args[]) {
		MyMenuFrame mf = new MyMenuFrame();// 定义主类
		mf.setSize(new Dimension(300, 200));// 用setSize()方法指定窗口的初始大小
		mf.setVisible(true);
	}
}

class MyMenuFrame extends Frame implements ActionListener// 定义窗口
{
	Clipboard clipboard;// 定义剪切板对象
	FileDialog filedialog_save, filedialog_load;// 定义文件对话框
	MenuBar m_MenuBar;// 定义菜单条
	Menu menuFile, menuEdit, menuStyle, menuSearch, menuHelp;// 定义菜单项
	MenuItem mi_File_New, mi_File_Open, mi_File_Save, mi_file_Savaas, mi_File_Close, mi_File_Exit, mi_Edit_Ce, mi_Edit_Copy, mi_Edit_Cut,
			mi_Edit_Paste, mi_Edit_Style, mi_Style_Font, mi_Style_Auto, mi_Search_sphere, mi_Help_Helptopic, mi_Help_About;
	TextArea text;

	MyMenuFrame()// 构造方法
	{
		super("记事本");// 指定窗口标题
		text = new TextArea(20, 20);
		add(text);
		clipboard = null;
		clipboard = getToolkit().getSystemClipboard();// 获取系统剪切板

		filedialog_save = new FileDialog(this, "保存文件对话框", FileDialog.SAVE);
		filedialog_save.setVisible(false);
		filedialog_load = new FileDialog(this, "打开文件对话框", FileDialog.LOAD);
		filedialog_load.setVisible(false);
		m_MenuBar = new MenuBar();
		menuFile = new Menu("file");// 创建菜单项，创建菜单子项并初始化
		MenuItem mi_File_New = new MenuItem("new");
		MenuItem mi_File_Open = new MenuItem("open");
		MenuItem mi_File_Save = new MenuItem("save");
		MenuItem mi_File_Saveas = new MenuItem("save as");
		MenuItem mi_File_Close = new MenuItem("shutdown");
		MenuItem mi_File_Exit = new MenuItem(" exit");
		mi_File_Exit.setShortcut(new MenuShortcut('x'));// 设置快捷键
		mi_File_Open.setActionCommand("open");// 简化
		mi_File_New.setActionCommand("new");

		mi_File_Exit.setActionCommand("exit");

		mi_File_New.addActionListener(this);// 使菜单子项响应动作事件
		mi_File_Open.addActionListener(this);
		mi_File_Save.addActionListener(this);
		mi_File_Saveas.addActionListener(this);
		mi_File_Close.addActionListener(this);
		mi_File_Exit.addActionListener(this);
		menuFile.add(mi_File_New);// 把菜单子项加入菜单项
		menuFile.add(mi_File_Open);
		menuFile.add(mi_File_Save);
		menuFile.add(mi_File_Saveas);
		menuFile.add(mi_File_Close);
		menuFile.addSeparator();// 加一条横向分割线
		menuFile.add(mi_File_Exit);
		m_MenuBar.add(menuFile);// 把菜单项加入菜单条
		menuEdit = new Menu("edit");
		mi_Edit_Ce = new MenuItem("back");
		mi_Edit_Copy = new MenuItem("copy");
		mi_Edit_Cut = new MenuItem("cut");
		mi_Edit_Paste = new MenuItem("paste");
		mi_Edit_Copy.setActionCommand("copy");
		mi_Edit_Cut.setActionCommand("cut");
		mi_Edit_Paste.setActionCommand("paste");

		mi_Edit_Ce.addActionListener(this);
		mi_Edit_Copy.addActionListener(this);
		mi_Edit_Cut.addActionListener(this);
		mi_Edit_Paste.addActionListener(this);
		menuEdit.add(mi_Edit_Ce);
		menuEdit.add(mi_Edit_Copy);
		menuEdit.add(mi_Edit_Cut);
		menuEdit.add(mi_Edit_Paste);
		m_MenuBar.add(menuEdit);

		menuStyle = new Menu("format");
		mi_Style_Auto = new MenuItem("new line");
		mi_Style_Font = new MenuItem("font");
		mi_Style_Auto.addActionListener(this);
		mi_Style_Font.addActionListener(this);
		menuStyle.add(mi_Style_Auto);
		menuStyle.add(mi_Style_Font);
		m_MenuBar.add(menuStyle);
		menuSearch = new Menu("look");
		mi_Search_sphere = new MenuItem("state");
		mi_Search_sphere.addActionListener(this);
		menuSearch.add(mi_Search_sphere);
		m_MenuBar.add(menuSearch);
		menuHelp = new Menu("help");
		mi_Help_Helptopic = new MenuItem("help main");
		mi_Help_About = new MenuItem("about");
		mi_Help_Helptopic.addActionListener(this);
		mi_Help_About.addActionListener(this);
		menuHelp.add(mi_Help_Helptopic);
		menuHelp.add(mi_Help_About);
		m_MenuBar.add(menuHelp);
		setMenuBar(m_MenuBar);// 把菜单条加入到菜单
		addWindowListener(new WindowAdapter()// 监听窗口（关闭窗口）
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		setVisible(true);
		validate(); // 如果加载了其他东西，就可用它来显示组件

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "new") {
			text.setText(null);// 清空
		}
		if (e.getActionCommand() == "open") {
			filedialog_load.setVisible(true);// 使文件对话框可见
		}
		if (e.getActionCommand() == "save") {
			filedialog_save.setVisible(true);// 使文件对话框可见
		}
		if (e.getActionCommand() == "copy") {
			String s = text.getSelectedText();// 复制到剪切板
			StringSelection text1 = new StringSelection(s);// 拖动鼠标选取文本
			clipboard.setContents(text1, null);
		}
		if (e.getActionCommand() == "cut") {
			String s1 = text.getSelectedText();// 剪切到剪切板
			StringSelection text1 = new StringSelection(s1);// 拖动鼠标选取文本
			clipboard.setContents(text1, null);
			int j = text.getSelectionStart();
			int k = text.getSelectionEnd();
			text.replaceRange("", j, k);// 从Text中删除被选取文本
		}
		if (e.getActionCommand() == "paste")// 从剪切板粘贴数据
		{
			Transferable transferable = clipboard.getContents(this);
			DataFlavor dataflavor = DataFlavor.stringFlavor;
			if (transferable.isDataFlavorSupported(dataflavor)) {
				try// 处理异常事件的发生（类型不符合）
				{
					String s3 = (String) transferable.getTransferData(dataflavor);
					text.insert(s3, text.getCaretPosition());
				} catch (Exception e1) {
				}
			}
		}
		if (e.getActionCommand() == "exit") {
			dispose();
			System.exit(0);
		}

	}
}