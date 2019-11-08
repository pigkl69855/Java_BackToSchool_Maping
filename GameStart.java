package com.pccu.tsai.backtoschool;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
 
import javax.swing.*;

//BackToSchoolHomeWork   
//**testing playground port3 ���ժA
//19/11/15 ��אּ 1.0.0 ������
public class GameStart  extends JFrame{
	//java�p�󰻴���J ����         (����)
	//���@�ӿ�X�ϧ@��a�� �ް��@���i���s    (����)
	//�]�p���U�@�ӫ���i���� (�����n�b�}�⭱�e)  (����)
	//���a�p�G�Q����i�歭��    (����)
	//�Щx�����ܤ���N�����Y��  (����)
	
	//���ctrltalk �ץ��S������� �qtrue�令false  (����) 
	//�N�����אּ someone screen ���� (����)
	
	//�ץ����a�i�H�첾�Y���H��   (����)

	
	static Boolean controlLeft=false;
	static Boolean controlRight=false;
	static Boolean controlUp=false;
	static Boolean controlDown=false;
	static Boolean controlED=false;
	static Boolean controlTalk=false;
	static Boolean TheKey=false;
	static Boolean Thedead=false;

	static void mapPrint(String a[][]) {  //�a�Ͽ�X �ثe�L�Υi�R��
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				System.out.print(a[i][j]);
			}
			System.out.println("");
		}
	}
	
	public GameStart() {           //�������  {�����W����i�� �������ק�}
		setTitle("Hern");
		setBounds(400, 400, 400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final JLabel label = new JLabel();
		label.setText("�п�J");
		add(label, BorderLayout.WEST);
		
		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		JTextArea textArea = new JTextArea();
		textArea.addKeyListener(new KeyListener() {
			
			public void keyPressed(KeyEvent e) { //����Q���U��Ĳ�o
				
				String keyText = KeyEvent.getKeyText(e.getKeyCode());//��o�y�zkeyCode������
				if (e.isActionKey()) {  //�P�_���U���O�_���ʧ@��
		
//					System.out.println("�A���U���O�ʧ@�䡧" + keyText + "��");
					int keyCode = e.getKeyCode();
					switch (keyCode) {             //�קﳡ��********
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
					
//					System.out.print("�A���U���O�D�ʧ@�䡧" + keyText + "��");
					int keyCode = e.getKeyCode();  //��o�P���ƥ�������p���r��
					
					switch (keyCode) {
						case KeyEvent.VK_CONTROL:   //�P�_���U���O�_��ctrl��
//							System.out.print("�ACtrl��Q���U");
							controlTalk=true;                            //�����
							break;
						case KeyEvent.VK_ALT:  //�P�_���U���O�_��alt��
//							System.out.print("�AAlt��Q���U");
							break;
						case KeyEvent.VK_SHIFT:  //�P�_���U���O�_��shift��
//							System.out.print("�AShift��Q���U");
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
			
//			public void keyTyped(KeyEvent e) { //�o������ƥ�ɳQĲ�o
//				System.out.println("�o����J���O��" + e.getKeyChar() + "��");//��o��J���r��
//			}
//			
//			public void keyReleased(KeyEvent e) { //����Q����ɳQĲ�o
//				String keyText = KeyEvent.getKeyText(e.getKeyCode());//��o�y�zkeycode������
//				System.out.println("�A���񪺬O��" + keyText + "����");
//				System.out.println();
//			}
		});
		
		textArea.setLineWrap(true);  //�]�m�奻��۰ʴ���
		textArea.setRows(3);        //�]�m�奻����
		textArea.setColumns(5);     //�]�m�奻�쪺�C��
		scrollPane.setViewportView(textArea);
		
		setVisible(true);
	}
	
	
	public static void main(String[] args) throws InterruptedException { 
		// TODO Auto-generated method stub
		
		GameStart test = new GameStart();
		
		SomeOne StudentName =new SomeOne();
		StudentName.name=" [�ǥ�]";
		Screen Student = new Screen() {  //�H�����ܸ}��
			int studentTalk=0;
			@Override
			public void Character(SomeOne so) {
				// TODO Auto-generated method stub
				System.out.println(so.name);
			}

			@Override
			public void PassTalk() {
				// TODO Auto-generated method stub
				
				if(studentTalk==0) {                      //studentTalk�аO�O�_���򪱮a��͹L�A���L���ܤ��A����
					System.out.println("�ڳo���@���_�͵��A");
					System.out.println("++��o�@���_��++");
					TheKey=true;
				}else if(studentTalk==1){
					System.out.println("�٦����n�i�D�Щx�ڦb�o��");
				}else {
					System.out.println("�U�ƫ��U�A�F");
				}
				studentTalk++;
			}
	
		};
		
		SomeOne InstructorName =new SomeOne();
		InstructorName.name=" [�Щx]";
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
					System.out.println("�F����!! �W�Үɶ��٦b�~�����V");
				}else {
					System.out.println("�O�G�ڧ�A!!!");
				}
				InstructorTalk++;
			}
		
		};
		
		SomeOne TeacherName =new SomeOne();
		TeacherName.name=" [�Ѯv]";
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
					System.out.println("�w�g���F~ �֦^��y��W���n"); //�A����٦b�o��
				else
					System.out.println("�A����٦b�o��");
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
					System.out.println("�A���ݪ���� �A �@�ӤH���I�L��~~"); //�A����٦b�o��
				else
					System.out.println("����~~~ �I �I �I ��~~��~~");
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
					System.out.println("���}�F~~");
					Thedead=true;
				}else {
					System.out.println("�~�M���ө_�Ǫ��t�����O�����}");
				}
			}
		};
		
		
		
		
		
		String[][] a = new String[10][10];           //�a��ø�s
		//�Ů�4��
		
		for(int i=0;i<10;i++) {                 //ø�s����
			for(int j=0;j<10;j++) {
				if(i==0 || j==0 || i==9 || j==9)
					a[i][j]="�f ";
				else
					a[i][j]="     ";
			}
		}
		a[1][3]="�f ";             //�a�ϲӳ�
		a[2][3]="�f ";
		a[3][3]="�f ";
		a[3][1]="�f ";
		
		a[1][1]="�H ";
		
		a[1][5]="�f ";
		a[2][5]="�f ";
		a[3][5]="�f ";
		a[4][5]="�f ";
		a[4][7]="�f ";
		a[4][8]="�f ";
		a[2][8]="�H ";
		
		a[5][1]="�f ";
		a[5][2]="�f ";
		a[5][3]="�f ";
		a[6][3]="�f ";
		a[8][3]="�f ";
		a[8][1]="�H ";
		
		a[6][5]="�f ";
		a[6][6]="�f ";
		a[6][7]="�f ";
		a[7][5]="�f ";
		a[8][5]="�f ";
		a[7][6]="�H ";
		
		a[8][4]="�� ";
//		for(int i=0;i<10;i++) {          //�i�R��
//			for(int j=0;j<10;j++) {
//				System.out.print(a[i][j]);
//			}
//			System.out.println("");
//		}
		int x=8;  //���a�}�l��m
		int y=4;  //��m�]�w�b�~���� ���� �������i�H���o���a��m�i��Ѻ�
		
		while(Thedead!=true) { //a[8][4]="��";  //while�j�� �p�G�����즺�`�N���Xwhile
			if(controlED=true && controlLeft==true && a[x][y-1]!="�f " && a[x][y-1]!="�H ") {  //�g�� if not "    " �N����e�i  //�令���ʤ~��s�e��
				
				a[x][y-1]="�� ";
				a[x][y]="     ";
				y--;
				controlLeft=false;
//				mapPrint(a);
			}
			
			else if(controlRight==true && a[x][y+1]!="�f " && a[x][y+1]!="�H ") {
				controlED=true;
				a[x][y+1]="�� ";
				a[x][y]="     ";
				y++;
				controlRight=false;
//				mapPrint(a);
			}
			else if(controlUp==true && a[x-1][y]!="�f " && a[x-1][y]!="�H ") {
				controlED=true;
				a[x-1][y]="�� ";
				a[x][y]="     ";
				x--;
				controlUp=false;
//				mapPrint(a);
			}
			else if(controlDown==true && a[x+1][y]!="�f " && a[x+1][y]!="�H ") {
				controlED=true;
				a[x+1][y]="�� ";
				a[x][y]="     ";
				x++;
				controlDown=false;
//				mapPrint(a);
			} 
			else if(controlED==true) {         //�i�R�� ���ϥ�
				System.out.println("�A������F");
				controlLeft=false;
				Thread.sleep(1000);
				controlED=false;
			}else {                      //�p�G���S�����if �i��else �Ҧ��R�O�k��false ������y������
				controlLeft=false;  
				controlRight=false;
				controlUp=false;
				controlDown=false;
			}
			for(int i=0;i<10;i++) {                 //���ƶi��a�ϧ�s ���e������ήɨ�
				for(int j=0;j<10;j++) {
					System.out.print(a[i][j]);
				}
				System.out.println("");
			}
			if(controlTalk==true) { //so1.name.equals("rq") //���Uctrl�i���� �p�G����O�諸�H�N��諸�H���}���i����
				if(a[x][y-1]==("�H ")){ //���ѻy�k���D   �ӥ~�}�@�Ӷi����� {���\} leftleftleftleftleftleftleftleftleftleftleftleft
					if(x==8 && y-1==1) { //�ǥͦ�m
						Student.Character(StudentName);
						Student.PassTalk();
					}else if(x==7 && y-1==6) { //�Щx��m
						Instructor.Character(InstructorName);
						Instructor.PassTalk();
					}else if(x==2 && y-1==8) { //�Ѯv��m
						Teacher.Character(TeacherName);
						Teacher.PassTalk();
					}else if(x==1 && y-1==1) { //unknow��m
						unknow.Character(UnknowName);   
						unknow.PassTalk();
					}
					controlTalk=false;
					Thread.sleep(1500);
				}					//��CTRL ���}���
				else if(a[x][y+1]==("�H ")){         //rightrightrightrightrightrightrightrightrightright
						if(x==8 && y+1==1) { //�ǥͦ�m
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x==7 && y+1==6) { //�Щx��m
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x==2 && y+1==8) { //�Ѯv��m
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x==1 && y+1==1) { //unknow��m
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else if(x-1==0 && y==4 || a[x-1][y]==("�H ")){     //upupupupupupupupupupupupupupupupup  /test
						if(x-1==8 && y==1) { //�ǥͦ�m
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x-1==7 && y==6) { //�Щx��m
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x-1==2 && y==8) { //�Ѯv��m
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x-1==1 && y==1) { //unknow��m
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}else if(x-1==0 && y==4) {  
							keyControl.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else if(a[x+1][y]==("�H ")){         //downdowndowndowndowndowndowndowndowndowndow 
						if(x+1==8 && y==1) { //�ǥͦ�m
							Student.Character(StudentName);
							Student.PassTalk();
						}else if(x+1==7 && y==6) { //�Щx��m
							Instructor.Character(InstructorName);
							Instructor.PassTalk();
						}else if(x+1==2 && y==8) { //�Ѯv��m
							Teacher.Character(TeacherName);
							Teacher.PassTalk();
						}else if(x+1==1 && y==1) { //unknow��m
							unknow.Character(UnknowName);   
							unknow.PassTalk();
						}
						controlTalk=false;
						Thread.sleep(1500);
				}else {
					controlTalk=false;
				}
			}
			Thread.sleep(100);  //���𭰧C�t�έt��   �������� (���i��)
			// ???   �Ѯv
			// �ǥͥ�   �Щx
			//�ǥͥ� ���A�@��
		}
		System.out.println("YOU DIED");
		System.out.println("Game Over");
	}
}

