package com.pccu.tsai.backtoschool;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 
import javax.swing.*;

//BackToSchoolHomeWork   
//**testing playground port3 測試服
//19/11/15 更改為 1.0.0 正式版
public class GameStart  extends JFrame{
	//java如何偵測鍵入 按件         (完成)
	//做一個輸出圖作文地圖 操做一次進行更新    (完成)
	//設計按下一個按鍵進行對話 (必須要在腳色面前)  (完成)
	//玩家如果想撞牆進行限制    (完成)
	//教官說完話之後就用罐頭話  (完成)
	
	//更改ctrltalk 修正沒偵測到時 從true改成false  (完成) 
	//將版本改為 someone screen 版本 (完成)
	
	//修正玩家可以位移吃掉人物   (完成)

	
	static Boolean controlLeft=false;
	static Boolean controlRight=false;
	static Boolean controlUp=false;
	static Boolean controlDown=false;
	static Boolean controlED=false;
	static Boolean controlTalk=false;
	static Boolean TheKey=false;
	static Boolean Thedead=false;

	static void mapPrint(String a[][]) {  //地圖輸出 目前無用可刪除
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
	}
	
	public GameStart() {           //控制視窗  {網路上抓取進行 控制執行修改}
		setTitle("Hern");
		setBounds(400, 400, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JLabel label = new JLabel();
		label.setText("請輸入");
		add(label, BorderLayout.WEST);
		
		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) { //按鍵被按下時觸發
				
				String keyText = KeyEvent.getKeyText(e.getKeyCode());//獲得描述keyCode的標籤
				if (e.isActionKey()) {  //判斷按下的是否為動作鍵
		
//					System.out.println("你按下的是動作鍵“" + keyText + "”");
					int keyCode = e.getKeyCode();
					switch (keyCode) {             //修改部分********
						case KeyEvent.VK_LEFT:
							controlLeft=true;
							break;
						case KeyEvent.VK_RIGHT:
							controlRight=true;
							break;
						case KeyEvent.VK_UP:
							controlUp=true;
							break;
						case KeyEvent.VK_DOWN:
							controlDown=true;
							break;                 //***************
					}
				} 
				else {
					
//					System.out.print("你按下的是非動作鍵“" + keyText + "”");
					int keyCode = e.getKeyCode();  //獲得與此事件中鍵相關聯的字符
					
					switch (keyCode) {
						case KeyEvent.VK_CONTROL:   //判斷按下的是否為ctrl鍵
//							System.out.print("，Ctrl鍵被按下");
							controlTalk=true;                            //控制說話
							break;
						case KeyEvent.VK_ALT:  //判斷按下的是否為alt鍵
//							System.out.print("，Alt鍵被按下");
							break;
						case KeyEvent.VK_SHIFT:  //判斷按下的是否為shift鍵
//							System.out.print("，Shift鍵被按下");
							break;
					}
					System.out.println();
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
//			public void keyTyped(KeyEvent e) { //發生擊鍵事件時被觸發
//				System.out.println("這次輸入的是“" + e.getKeyChar() + "”");//獲得輸入的字符
//			}
//			
//			public void keyReleased(KeyEvent e) { //按鍵被釋放時被觸發
//				String keyText = KeyEvent.getKeyText(e.getKeyCode());//獲得描述keycode的標籤
//				System.out.println("你釋放的是“" + keyText + "”鍵");
//				System.out.println();
//			}
		});
		
		textArea.setLineWrap(true);  //設置文本域自動換行
		textArea.setRows(3);        //設置文本域行數
		textArea.setColumns(5);     //設置文本域的列數
		scrollPane.setViewportView(textArea);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) throws InterruptedException { 
		// TODO Auto-generated method stub
		
		GameStart test = new GameStart();
		
		SomeOne StudentName =new SomeOne();
		StudentName.name=" [學生]";
		Screen Student = new Screen() {  //人物說話腳本
			int studentTalk=0;
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				System.out.println(so.name);
			}

			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				
				if(studentTalk==0) {                      //studentTalk標記是否有跟玩家交談過，說過的話不再重複
					System.out.println("我這有一把鑰匙給你");
					System.out.println("++獲得一把鑰匙++");
					TheKey=true;
				}else if(studentTalk==1){
					System.out.println("還有不要告訴教官我在這喔");
				}else {
					System.out.println("萬事拜託你了");
				}
				studentTalk++;
			}
	
		};
		
		SomeOne InstructorName =new SomeOne();
		InstructorName.name=" [教官]";
		Screen Instructor = new Screen() {
			int InstructorTalk=0;
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				System.out.println(so.name);
			}

			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				if(InstructorTalk==0) {
					System.out.println("幹什麼!! 上課時間還在外面鬼混");
				}else {
					System.out.println("別逼我抓你!!!");
				}
				InstructorTalk++;
			}
		
		};
		
		SomeOne TeacherName =new SomeOne();
		TeacherName.name=" [老師]";
		Screen Teacher = new Screen() {
			int teacherTalk=0;
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				System.out.println(so.name);
			}

			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				
				if(teacherTalk==0)
					System.out.println("已經遲到了~ 快回到座位上坐好"); //你怎麼還在這裡
				else
					System.out.println("你怎麼還在這裡");
				teacherTalk++;
			}
	
		};
		
		
		SomeOne UnknowName =new SomeOne();
		UnknowName.name=" [???]";
		Screen unknow = new Screen() {
			int unknowTalk=0;
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				System.out.println(so.name);
			}
			
			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				
				if(unknowTalk==0)
					System.out.println("你怎麼看的到我 ， 一個人有點無聊~~"); //你怎麼還在這裡
				else
					System.out.println("咻咻~~~ 碰 碰 碰 阿~~阿~~");
				unknowTalk++;
			}
		};
		
		Screen keyControl = new Screen() {
			
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				
			}
			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				if(TheKey==true) {
					System.out.println("打開了~~");
					Thedead=true;
				}else {
					System.out.println("居然有個奇怪的暗門但是打不開");
				}
			}
		};
		
		
		
		
		
		String[][] a = new String[10][10];           //地圖繪製
		//空格4格
		
		for(int i=0;i<10;i++) {                 //繪製圍牆
			for(int j=0;j<10;j++) {
				if(i==0 || j==0 || i==9 || j==9)
					a[i][j]="口 ";
				else
					a[i][j]="     ";
			}
		}
		a[1][3]="口 ";             //地圖細部
		a[2][3]="口 ";
		a[3][3]="口 ";
		a[3][1]="口 ";
		
		a[1][1]="人 ";
		
		a[1][5]="口 ";
		a[2][5]="口 ";
		a[3][5]="口 ";
		a[4][5]="口 ";
		a[4][7]="口 ";
		a[4][8]="口 ";
		a[2][8]="人 ";
		
		a[5][1]="口 ";
		a[5][2]="口 ";
		a[5][3]="口 ";
		a[6][3]="口 ";
		a[8][3]="口 ";
		a[8][1]="人 ";
		
		a[6][5]="口 ";
		a[6][6]="口 ";
		a[6][7]="口 ";
		a[7][5]="口 ";
		a[8][5]="口 ";
		a[7][6]="人 ";
		
		a[8][4]="我 ";
//		for(int i=0;i<10;i++) {          //可刪除
//			for(int j=0;j<10;j++) {
//				System.out.print(a[i][j]);
//			}
//			System.out.println("");
//		}
		int x=8;  //玩家開始位置
		int y=4;  //位置設定在外部使 移動 偵測都可以取得玩家位置進行解算
		
		while(Thedead!=true) { //a[8][4]="我";  //while迴圈 如果接收到死亡就跳出while
			if(controlED=true && controlLeft==true && a[x][y-1]!="口 " && a[x][y-1]!="人 ") {  //寫成 if not "    " 就不能前進  //改成移動才更新畫面
				
				a[x][y-1]="我 ";
				a[x][y]="     ";
				y--;
				controlLeft=false;
//				mapPrint(a);
			}
			
			else if(controlRight==true && a[x][y+1]!="口 " && a[x][y+1]!="人 ") {
				controlED=true;
				a[x][y+1]="我 ";
				a[x][y]="     ";
				y++;
				controlRight=false;
//				mapPrint(a);
			}
			else if(controlUp==true && a[x-1][y]!="口 " && a[x-1][y]!="人 ") {
				controlED=true;
				a[x-1][y]="我 ";
				a[x][y]="     ";
				x--;
				controlUp=false;
//				mapPrint(a);
			}
			else if(controlDown==true && a[x+1][y]!="口 " && a[x+1][y]!="人 ") {
				controlED=true;
				a[x+1][y]="我 ";
				a[x][y]="     ";
				x++;
				controlDown=false;
//				mapPrint(a);
			} 
			else if(controlED==true) {         //可刪除 未使用
				System.out.println("你撞到牆了");
				controlLeft=false;
				Thread.sleep(1000);
				controlED=false;
			}else {                      //如果都沒執行到if 進行else 所有命令歸位false 未執行造成順移
				controlLeft=false;  
				controlRight=false;
				controlUp=false;
				controlDown=false;
			}
			for(int i=0;i<10;i++) {                 //重複進行地圖更新 讓畫面接近及時制
				for(int j=0;j<10;j++) {
					System.out.print(a[i][j]);
				}
				System.out.println("");
			}
			if(controlTalk==true) { //so1.name.equals("rq") //按下ctrl進行對話 如果附近是對的人就找對的人的腳本進行對話
				if(a[x][y-1]==("人 ")){ //辨識語法問題   而外開一個進行測試 {成功} leftleftleftleftleftleftleftleftleftleftleftleft
					if(x==8 && y-1==1) { //學生位置
						Student.Character(StudentName);
						Student.PassTalk();
					}else if(x==7 && y-1==6) { //教官位置
						Instructor.Character(InstructorName);
						Instructor.PassTalk();
					}else if(x==2 && y-1==8) { //老師位置
						Teacher.Character(TeacherName);
						Teacher.PassTalk();
					}else if(x==1 && y-1==1) { //unknow位置
						unknow.Character(UnknowName);   
						unknow.PassTalk();
					}
					controlTalk=false;
					Thread.sleep(1500);
				}					//做CTRL 離開對話
				else if(a[x][y+1]==("人 ")){         //rightrightrightrightrightrightrightrightrightright
						if(x==8 && y+1==1) { //學生位置
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x==7 && y+1==6) { //教官位置
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x==2 && y+1==8) { //老師位置
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x==1 && y+1==1) { //unknow位置
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else if(x-1==0 && y==4 || a[x-1][y]==("人 ")){     //upupupupupupupupupupupupupupupupup  /test
						if(x-1==8 && y==1) { //學生位置
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x-1==7 && y==6) { //教官位置
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x-1==2 && y==8) { //老師位置
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x-1==1 && y==1) { //unknow位置
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}else if(x-1==0 && y==4) {  
							keyControl.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else if(a[x+1][y]==("人 ")){         //downdowndowndowndowndowndowndowndowndowndow 
						if(x+1==8 && y==1) { //學生位置
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x+1==7 && y==6) { //教官位置
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x+1==2 && y==8) { //老師位置
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x+1==1 && y==1) { //unknow位置
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else {
					controlTalk=false;
				}
			}
			Thread.sleep(100);  //延遲降低系統負荷   移除測試 (未進行)
			// ???   老師
			// 學生甲   教官
			//學生甲 給你一把
		}
		System.out.println("YOU DIED");
		System.out.println("Game Over");
	}
}

