package test;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class cj
{
 public static Monitor mymonitor = new Monitor();
 public static JLabel welcome, usename1, password1;
 public static TextField usename2, password2;
 public static JButton enter1, close1;
 public static JFrame Enterfrm;
 public static void main(String[] args)
 {
  cj myke = new cj();
 }
 public cj()
 {
  Enterfrm = new JFrame("登陆");
  Toolkit kit = Toolkit.getDefaultToolkit();
  Dimension screenSize = kit.getScreenSize();
  int screenWidth = screenSize.width;
  int screenHeight = screenSize.height;
  welcome = new JLabel("成绩管理系统");
  welcome.setFont(new Font("宋体", Font.BOLD, 22));
  usename1 = new JLabel("用户名:");
  password1 = new JLabel("密  码:");
  usename2 = new TextField(12);
  password2 = new TextField(12);
  enter1 = new JButton("确定");
  close1 = new JButton("退出");
  JPanel p1 = new JPanel();
  JPanel p2 = new JPanel();
  JPanel p3 = new JPanel();
  JPanel p4 = new JPanel();
  JPanel p5 = new JPanel();
  password2.setEchoChar('*');
  p4.add(usename1);
  p4.add(usename2);
  p4.add(enter1);
  p5.add(password1);
  p5.add(password2);
  p5.add(close1);
  p3.setLayout(new GridLayout(2, 1));
  p2.add("Center", welcome);
  p3.add(p4);
  p3.add(p5);
  p1.add(p2);
  p1.add(p3);
  p1.setLayout(new GridLayout(2, 1));
  Container k = Enterfrm.getContentPane();
  k.add(p1);
  Enterfrm.setSize(screenWidth / 3, screenHeight / 3);
  Enterfrm.setLocation(480, 200);
  Enterfrm.addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    System.exit(0);
   }
  });
  Enterfrm.show();
  enter1.addActionListener(mymonitor);
  close1.addActionListener(mymonitor);
 }
}
class Monitor implements ActionListener
{
 public String un1 = "1", pw1 = "1";
 public String un2 = "2", pw2 = "2";
 public String un3 = "1", pw3 = "1";
 public void actionPerformed(ActionEvent e)
 {
  if (e.getSource() == cj.enter1)
  {
   if ((cj.usename2.getText().equals(un1) && cj.password2.getText()
     .equals(pw1))
     || (cj.usename2.getText().equals(un2) && cj.password2
       .getText().equals(pw2))
     || (cj.usename2.getText().equals(un3) && cj.password2
       .getText().equals(pw3)))
   {
    cj.Enterfrm.setVisible(false);
    mainfrm mf = new mainfrm();
   } else
   {
    JOptionPane.showMessageDialog(null, "输入有误，请重新输入！");
    cj.usename2.setText("");
    cj.password2.setText("");
   }
  }
  if (e.getSource() == cj.close1)
  {
   System.exit(0);
  }
 }
}
// 主窗体
class mainfrm implements ActionListener
{
 public static JMenu TeacherMenu, StudentMenu, MarkMenu, HelpMenu;
 public static JMenuItem TMfind, TMedit, TMdelete, SMfind, SMedit, SMdelete,
   MMfind, MMedit, MMalter, HMabout;
 public static JFrame mainf;
 public static JLabel room, room1;
 public mainfrm()
 {
  mainf = new JFrame("主窗体");
  JMenuBar myMenuBar = new JMenuBar(); // 创建菜单栏
  MarkMenu = new JMenu(" 成绩管理");
  MMfind = new JMenuItem("成绩查询");
  MMfind.addActionListener(this);
  MMedit = new JMenuItem("成绩录入");
  MMedit.addActionListener(this);
  MMalter = new JMenuItem("修改");
  MMalter.addActionListener(this);
  mainf.setJMenuBar(myMenuBar);
  myMenuBar.add(MarkMenu);
  MarkMenu.add(MMedit);
  MarkMenu.add(MMfind);
  MarkMenu.add(MMalter);
  JPanel pa1 = new JPanel();
  pa1.setLayout(new GridLayout(4, 1));
  JPanel pa2 = new JPanel();
  JPanel pa3 = new JPanel();
  JPanel pa4 = new JPanel();
  room = new JLabel("成绩管理系统");
  room.setFont(new Font("宋体", Font.BOLD, 35));
  mainf.getContentPane().add(pa1, BorderLayout.NORTH);
  pa1.add(pa3);
  pa1.add(pa2);
  pa1.add(pa4);
  pa2.add(room);
  mainf.setSize(700, 500);
  mainf.setLocation(400, 80);
  mainf.setResizable(false);
  mainf.addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    System.exit(0);
   }
  });
  mainf.show();
 }
 public void actionPerformed(ActionEvent e)
 {
  if (e.getSource() == mainfrm.MMfind)
  {
   markfrm1 mf1 = new markfrm1();
  }
  if ((e.getSource() == mainfrm.MMalter)
    || (e.getSource() == mainfrm.MMedit))
  {
   markfrm2 mf2 = new markfrm2();
  }
 }
}
class markfrm1 implements ActionListener
{
 String id, stuname, coname, type, grade, teanum, mark;
 Connection conn;
 ResultSet rs;
 Statement st;
 public JLabel NumL, stunameL, conameL, typeL, gradeL, teanameL, markL;
 public JTextField tf, Num, sName, cName, Type1, Grade1, teanameT, Chengj;
 public static JFrame markf;
 public JPanel p1;
 public JButton search, closeT;
 public markfrm1()
 {
  markf = new JFrame("查询学生成绩记录");
  markf.setSize(360, 400);
  markf.setResizable(false);
  id = new String();
  stuname = new String();
  coname = new String();
  type = new String();
  grade = new String();
  teanum = new String();
  mark = new String();
  NumL = new JLabel("学       号:");
  stunameL = new JLabel("姓      名:");
  conameL = new JLabel("课 程 名:");
  typeL = new JLabel("课程类型:");
  gradeL = new JLabel("年      级:");
  teanameL = new JLabel("任课教师:");
  markL = new JLabel("成       绩:");
  tf = new JTextField();
  Num = new JTextField();
  sName = new JTextField();
  cName = new JTextField();
  Type1 = new JTextField();
  Grade1 = new JTextField();
  teanameT = new JTextField();
  Chengj = new JTextField();
  p1 = new JPanel();
  search = new JButton("查找");
  search.setLocation(90, 10);
  search.setSize(60, 20);
  tf.setLocation(190, 10);
  tf.setSize(100, 20);
  p1.add(search ,"Center");
  p1.add(tf, "Center");
  closeT = new JButton("退出");
  closeT.addActionListener(this);
  closeT.setLocation(146, 280);
  closeT.setSize(80, 30);
  p1.add(closeT, "Center");
  NumL.setLocation(30, 40);
  NumL.setSize(80, 20);
  p1.add(NumL, "Center");
  Num.setLocation(130, 40);
  Num.setSize(180, 20);
  Num.setEnabled(false);
  p1.add(Num, "Center");
  stunameL.setLocation(30, 70);
  stunameL.setSize(80, 20);
  p1.add(stunameL, "Center");
  sName.setLocation(130, 70);
  sName.setSize(180, 20);
  p1.add(sName, "Center");
  conameL.setLocation(30, 100);
  conameL.setSize(80, 20);
  p1.add(conameL, "Center");
  cName.setLocation(130, 100);
  cName.setSize(180, 20);
  p1.add(cName, "Center");
  typeL.setLocation(30, 130);
  typeL.setSize(80, 20);
  p1.add(typeL, "Center");
  Type1.setLocation(130, 130);
  Type1.setSize(180, 20);
  p1.add(Type1, "Center");
  gradeL.setLocation(30, 160);
  gradeL.setSize(80, 20);
  p1.add(gradeL, "Center");
  Grade1.setLocation(130, 160);
  Grade1.setSize(180, 20);
  p1.add(Grade1, "Center");
  teanameL.setLocation(30, 190);
  teanameL.setSize(80, 20);
  p1.add(teanameL, "Center");
  teanameT.setLocation(130, 190);
  teanameT.setSize(180, 20);
  p1.add(teanameT, "Center");
  markL.setLocation(30, 220);
  markL.setSize(80, 20);
  p1.add(markL, "Center");
  Chengj.setLocation(130, 220);
  Chengj.setSize(180, 20);
  p1.add(Chengj, "Center");
  p1.setLayout(new BorderLayout());
  Container c = markf.getContentPane();
  c.add(p1);
  markf.addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    markfrm1.markf.dispose();
   }
  });
  markf.setLocation(200, 200);
  markf.show();
  search.addActionListener(this);
  conDB();
 }
 public void conDB()
 {
  try
  { 
   Class.forName("com.mysql.jdbc.Driver");
  }
  catch (ClassNotFoundException e)
  {
   JOptionPane.showMessageDialog(null, "数据库加载失败！");
  }
  try
  {
   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chengji", "root", "");
   st = conn.createStatement();
  }
  catch (SQLException e)
  {
   JOptionPane.showMessageDialog(null, "数据库连接失败！");
  }
 }
 public void closeDB()
 {
  try
  {
   conn.close();
  }
  catch (SQLException e)
  {
   JOptionPane.showMessageDialog(null, "数据库关闭失败！");
  }
 }
 public void actionPerformed(ActionEvent e)
 {
  if (e.getSource() == search)
  {
   try
   {
    String id = tf.getText();
    String strSQL = "select stunum,stuname,coname,type,grade,teanum,mark"
      + " from student where stunum= '" + id + "' ";
    ResultSet rs = st.executeQuery(strSQL);
    int count = 0;
    while (rs.next())
    {
     id = rs.getString("stunum");
     stuname = rs.getString("stuname");
     coname = rs.getString("coname");
     type = rs.getString("type");
     grade = rs.getString("grade");
     teanum = rs.getString("teanum");
     mark = rs.getString("mark");
     ++count;
    }
    if (count == 0)
     JOptionPane.showMessageDialog(null, "对不起，没有您要查找的学生！");
    else
    {
     Num.setText(id);
     sName.setText(stuname);
     cName.setText(coname);
     Type1.setText(type);
     Grade1.setText(grade);
     teanameT.setText(teanum);
     Chengj.setText(mark);
    }
   }
   catch (SQLException ex)
   {
    Num.setText(ex.getMessage());
    sName.setText(ex.getMessage());
    cName.setText(ex.getMessage());
    Type1.setText(ex.getMessage());
    Grade1.setText(ex.getMessage());
    teanameT.setText(ex.getMessage());
    Chengj.setText(ex.getMessage());
   }
  }
  if (e.getSource() == closeT)
  {
   closeDB();
   markfrm1.markf.dispose();
  }
 }
}
class markfrm2 implements ActionListener
{
 String id, stuname, coname, type, grade, teanum, mark;
 Connection conn;
 ResultSet rs;
 Statement st;
 public JLabel NumL, stunameL, conameL, typeL, gradeL, teanumL, markL;
 public JTextField tf, Num, sName, cName, Type1, Grade1, Tnum, Chengj;
 public static JFrame markf;
 public JPanel p1;
 public JButton alter, sj, insertM, closeT;
 public markfrm2()
 {
  markf = new JFrame("添加、修改学生成绩");
  markf.setSize(360, 400);
  markf.setResizable(false);
  id = new String();
  stuname = new String();
  coname = new String();
  type = new String();
  grade = new String();
  teanum = new String();
  mark = new String();
  NumL = new JLabel("学       号:");
  stunameL = new JLabel("姓      名:");
  conameL = new JLabel("课 程 名:");
  typeL = new JLabel("课程类型:");
  gradeL = new JLabel("年      级:");
  teanumL = new JLabel("任课教师:");
  markL = new JLabel("成       绩:");
  tf = new JTextField();
  Num = new JTextField();
  sName = new JTextField();
  cName = new JTextField();
  Type1 = new JTextField();
  Grade1 = new JTextField();
  Tnum = new JTextField();
  Chengj = new JTextField();
  p1 = new JPanel();
  alter = new JButton("修改");
  tf.setLocation(190, 10);
  tf.setSize(100, 20);
  p1.add(tf, "Center");
  alter.setLocation(50, 10);
  alter.setSize(60, 20);
  sj = new JButton("查找");
  sj.setLocation(120, 10);
  sj.setSize(60, 20);
  p1.add(alter, "Center");
  p1.add(sj, "Center");
  insertM = new JButton("添加");
  insertM.setLocation(86, 280);
  insertM.setSize(80, 30);
  closeT = new JButton("退出");
  closeT.addActionListener(this);
  closeT.setLocation(156, 280);
  closeT.setSize(80, 30);
  p1.add(closeT, "Center");
  p1.add(insertM, "Center");
  NumL.setLocation(30, 40);
  NumL.setSize(80, 20);
  p1.add(NumL, "Center");
  Num.setLocation(130, 40);
  Num.setSize(180, 20);
  p1.add(Num, "Center");
  stunameL.setLocation(30, 70);
  stunameL.setSize(80, 20);
  p1.add(stunameL, "Center");
  sName.setLocation(130, 70);
  sName.setSize(180, 20);
  p1.add(sName, "Center");
  conameL.setLocation(30, 100);
  conameL.setSize(80, 20);
  p1.add(conameL, "Center");
  cName.setLocation(130, 100);
  cName.setSize(180, 20);
  p1.add(cName, "Center");
  typeL.setLocation(30, 130);
  typeL.setSize(80, 20);
  p1.add(typeL, "Center");
  Type1.setLocation(130, 130);
  Type1.setSize(180, 20);
  p1.add(Type1, "Center");
  gradeL.setLocation(30, 160);
  gradeL.setSize(80, 20);
  p1.add(gradeL, "Center");
  Grade1.setLocation(130, 160);
  Grade1.setSize(180, 20);
  p1.add(Grade1, "Center");
  teanumL.setLocation(30, 190);
  teanumL.setSize(80, 20);
  p1.add(teanumL, "Center");
  Tnum.setLocation(130, 190);
  Tnum.setSize(180, 20);
  p1.add(Tnum, "Center");
  markL.setLocation(30, 220);
  markL.setSize(80, 20);
  p1.add(markL, "Center");
  Chengj.setLocation(130, 220);
  Chengj.setSize(180, 20);
  p1.add(Chengj, "Center");
  p1.setLayout(new BorderLayout());
  Container c = markf.getContentPane();
  c.add(p1);
  markf.addWindowListener(new WindowAdapter()
  {
   public void windowClosing(WindowEvent e)
   {
    markfrm2.markf.dispose();
   }
  });
  ;
  markf.setLocation(200, 200);
  markf.show();
  insertM.addActionListener(this);
  alter.addActionListener(this);
  sj.addActionListener(this);
  conDB();
 }
 public void conDB()
 {
  try
  {
   Class.forName("com.mysql.jdbc.Driver");
  }
  catch (ClassNotFoundException e)
  {
   JOptionPane.showMessageDialog(null, "数据库加载失败！");
  }
  try
  {
   conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/chengji", "root", "");
   st = conn.createStatement();
  }
  catch (SQLException e)
  {
   JOptionPane.showMessageDialog(null, "数据库连接失败！");
  }
 }
 public void closeDB()
 {
  try
  {
   conn.close();
  }
  catch (SQLException e)
  {
   JOptionPane.showMessageDialog(null, "数据库关闭失败！");
  }
 }
 public void actionPerformed(ActionEvent e)
 {
  if (e.getSource() == insertM)
   if ((Num.getText().trim()).equals("")
     || (sName.getText().trim()).equals("")
     || (cName.getText().trim()).equals("")
     || (Type1.getText().trim()).equals("")
     || (Grade1.getText().trim()).equals("")
     || (Tnum.getText().trim()).equals("")
     || (Chengj.getText().trim()).equals(""))
    JOptionPane.showMessageDialog(null, "请输入信息！");
   else
   {
    id = Num.getText();
    stuname = sName.getText();
    coname = cName.getText();
    type = Type1.getText();
    grade = Grade1.getText();
    teanum = Tnum.getText();
    mark = Chengj.getText();
    String strSQL = "insert into student ( stunum,stuname,coname,type,grade,teanum,mark)values( '"
      + id
      + "','"
      + stuname
      + "','"
      + coname
      + "','"
      + type
      + "','" + grade + "','" + teanum + "','" + mark + "')";
    try
    {
     st.executeUpdate(strSQL);
    } catch (Exception exx)
    {
     JOptionPane.showMessageDialog(null, "数据库中已经存在您要添加的学号");
     return;
    }
    JOptionPane.showMessageDialog(null, "添加成功");
    Num.setText("");
    sName.setText("");
    cName.setText("");
    Type1.setText("");
    Grade1.setText("");
    Tnum.setText("");
    Chengj.setText("");
   }
  if (e.getSource() == alter)
  {
   if ((Num.getText().trim()).equals("")
     || (sName.getText().trim()).equals("")
     || (cName.getText().trim()).equals("")
     || (Type1.getText().trim()).equals("")
     || (Grade1.getText().trim()).equals("")
     || (Tnum.getText().trim()).equals("")
     || (Chengj.getText().trim()).equals(""))
    JOptionPane.showMessageDialog(null, "请输入信息");
   else
   {
    id = Num.getText();
    stuname = sName.getText();
    coname = cName.getText();
    type = Type1.getText();
    grade = Grade1.getText();
    teanum = Tnum.getText();
    mark = Chengj.getText();
    String strSQL = "update student set stuname='" + stuname
      + "',coname='" + coname + "',type='" + type
      + "',grade='" + grade + "',teanum='" + teanum
      + "',mark='" + mark + "'" + "where stunum='" + id + "'";
    try
    {
     st.executeUpdate(strSQL);
    } catch (Exception exx)
    {
     JOptionPane.showMessageDialog(null, "数据库中已经存在您要修改的记录");
     return;
    }
    JOptionPane.showMessageDialog(null, "修改成功");
   }
  }
  if (e.getSource() == sj)
  {
   try
   {
    String id = tf.getText();
    String strSQL = "select stunum,stuname,coname,type,grade,teanum,mark"
      + " from student  where stunum= '" + id + "' ";
    ResultSet rs = st.executeQuery(strSQL);
    int count = 0;
    while (rs.next())
    {
     id = rs.getString("stunum");
     stuname = rs.getString("stuname");
     coname = rs.getString("coname");
     type = rs.getString("type");
     grade = rs.getString("grade");
     teanum = rs.getString("teanum");
     mark = rs.getString("mark");
     ++count;
    }
    if (count == 0)
     JOptionPane.showMessageDialog(null, "对不起，没有您要查找的学生！");
    else
    {
     Num.setText(id);
     sName.setText(stuname);
     cName.setText(coname);
     Type1.setText(type);
     Grade1.setText(grade);
     Tnum.setText(teanum);
     Chengj.setText(mark);
     Num.setEnabled(false);
    }
   } catch (SQLException ex)
   {
    Num.setText(ex.getMessage());
    sName.setText(ex.getMessage());
    cName.setText(ex.getMessage());
    Type1.setText(ex.getMessage());
    Grade1.setText(ex.getMessage());
    Tnum.setText(ex.getMessage());
    Chengj.setText(ex.getMessage());
    JOptionPane.showMessageDialog(null, "程序异常");
   }
  }
  if (e.getSource() == closeT)
  {
   closeDB();
   markfrm2.markf.dispose();
  }
 }
}