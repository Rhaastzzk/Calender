package 日历;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
 
class mouthpass_Exception extends Exception{
private static final long serialVersionUID = 1L;
public mouthpass_Exception(){}
public mouthpass_Exception (String message){
      super(message);
}
public String mess(){
      return "输入异常";
}
}//自定义异常类
 
 
class myCal extends JFrame {
	private static final long serialVersionUID = 1L;
	Calendar times = Calendar.getInstance();
    private JPanel p1, p2, p3;
    private JLabel text, year, month;
    private  static JLabel nowdays;
    private JTextField y;
    int yearSet = times.get(Calendar.YEAR);
    private JTextField m;
    int monthSet = times.get(Calendar.MONTH) + 1;
    private JButton sure, front, next;
    private JDialog  JDtextl;
 
 
    //p2
    private String[] week = {"  周日", "  周一", "  周二", "  周三", "  周四", "  周五", "  周六"};
    private JButton[] weekbutton = new JButton[week.length];
    //p3
    private JButton[] day = new JButton[42];
 
 
    public myCal() {
 
        //获取现在的时间
        times.setTime(new Date());
        String years = String.valueOf(times.get(Calendar.YEAR)),
                months = String.valueOf(times.get(Calendar.MONTH) + 1);
 
 
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        JDtextl=new JDialog();
 
        text = new JLabel("请输入日期：");
        year = new JLabel("年份");
        month = new JLabel("月份");
        nowdays = new  JLabel(String.valueOf(times.getTime()));
        y = new JTextField(years, 4);
        m = new JTextField(months, 4);
        sure = new JButton("确定");
        front = new JButton("上一月");
        next = new JButton("下一月");
 
    }
 
    public void init() {//初始化
        this.setTitle("日历");
        this.setResizable(false);
        this.setSize(630, 270);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        this.add(p1, BorderLayout.NORTH);
        this.add(p2, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);
 
        p1.setLayout(new FlowLayout());
        p1.add(text);
        p1.add(year);
        p1.add(y);
        p1.add(month);
        p1.add(m);
        p1.add(sure);
        sure.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {get_text();
                    setDay(yearSet, monthSet);}catch(mouthpass_Exception u){
                    JDtextl.setTitle("提示");
                    JLabel t=new JLabel("月份输入异常");
                    JDtextl.add(t);
                    JDtextl.setLocationRelativeTo(null);
                    JDtextl.setSize(90,80);
                    t.setSize(70,70);
                    JDtextl.setVisible(true);
 
                }
                setDay(yearSet, monthSet);
 
                if((times.get(Calendar.YEAR)==Integer.parseInt(y.getText()))&&(times.get(Calendar.MONTH)+1)==Integer.parseInt(m.getText())) {
                    GregorianCalendar dateSet = new GregorianCalendar(yearSet, monthSet - 1, 1);
                    int weeknum = dateSet.get(Calendar.DAY_OF_WEEK);
                    int I = weeknum - 2 + times.get(Calendar.DAY_OF_MONTH);
                    day[I].setForeground(Color.RED);
                    day[I].setBackground(Color.ORANGE);
                }
 
            }
        });
 
        p1.add(front);//上一个月
        front.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {get_text();
                    setDay(yearSet, monthSet);}catch(mouthpass_Exception u){
                    JDtextl.setTitle("提示");
                    JLabel t=new JLabel("月份输入异常");
                    JDtextl.add(t);
                    JDtextl.setLocationRelativeTo(null);
                    JDtextl.setSize(90,80);
                    t.setSize(70,70);
                    JDtextl.setVisible(true);
 
                }
                if (monthSet == 1) {
                    yearSet = yearSet - 1;
                    monthSet = 12;
                    setDay(yearSet, monthSet);
                } else {
                    monthSet = monthSet - 1;
                    setDay(yearSet, monthSet);
                }
                y.setText(Integer.toString(yearSet));
                m.setText(Integer.toString(monthSet));
                setDay(yearSet, monthSet);
 
 
 
            }
        });
 
        p1.add(next);//下一个月
        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {get_text();
                    setDay(yearSet, monthSet);} catch(mouthpass_Exception u){
                    JDtextl.setTitle("提示");
                    JLabel t=new JLabel("月份输入异常");
                    JDtextl.add(t);
                    JDtextl.setLocationRelativeTo(null);
                    JDtextl.setSize(90,80);
                    t.setSize(70,70);
                    JDtextl.setVisible(true);
 
                }
                if (monthSet == 12) {
                    yearSet = yearSet + 1;
                    monthSet = 1;
                    setDay(yearSet, monthSet);
 
 
                } else {
                    monthSet = monthSet + 1;
                    setDay(yearSet, monthSet);
 
                }
                y.setText(Integer.toString(yearSet));
                m.setText(Integer.toString(monthSet));
                setDay(yearSet, monthSet);
 
 
            }
        });
        p1.add(nowdays);
 
 
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });//关闭
 
        //p2
        p2.setLayout(new GridLayout(1, 7, 2, 3));
        for (int i = 0; i < week.length; i++) {
            weekbutton[i] = new JButton(week[i]);
            weekbutton[i].setBackground(Color.LIGHT_GRAY);
            p2.add(weekbutton[i]);
        }
        weekbutton[0].setBackground(Color.red);
        weekbutton[6].setBackground(Color.red);
 
        //p3
        p3.setLayout(new GridLayout(6, 7, 2, 3));
        for (int i = 0; i < 42; i++) {
            day[i] = new JButton(i + " ");
            p3.add(day[i]);
 
        }
        try {get_text();
            setDay(yearSet, monthSet);}catch(mouthpass_Exception u){
            JDtextl.setTitle("提示");
            JLabel t=new JLabel("月份输入异常");
            JDtextl.add(t);
            JDtextl.setLocationRelativeTo(null);
            JDtextl.setSize(90,80);
            t.setSize(70,70);
            JDtextl.setVisible(true);
 
        }
        this.setVisible(true);
    }
 
    public void get_text() throws mouthpass_Exception {
        monthSet = Integer.parseInt(m.getText());
        if (monthSet > 12 || monthSet < 1) {
            throw new mouthpass_Exception("月份错误");
        }
        yearSet = Integer.parseInt(y.getText());
 
 
 
 
    }//获得文本框的信息
 
    private void setDay(int yearSet, int monthSet) {
 
        GregorianCalendar dateSet = new GregorianCalendar(yearSet, monthSet - 1, 1);
        int weeknum = dateSet.get(Calendar.DAY_OF_WEEK);
        boolean isleap = dateSet.isLeapYear(yearSet);
 
        int mouthDays = getMouthDays(monthSet, isleap);
        int mouthDaysFront=getMouthDays(monthSet-1,isleap);
 
 
 
 
      //上个月剩余
       for(int i=0,count=mouthDaysFront-weeknum+2;i<weeknum-1;i++,count++){
           day[i].setText(Integer.toString(count));
           day[i].setBackground(Color.GRAY);
           day[i].setForeground(Color.BLACK);
       }
       //下个月
        for(int i=mouthDays+weeknum-1,count=1;i<42;i++,count++){
            day[i].setText(Integer.toString(count));
            day[i].setBackground(Color.GRAY);
            day[i].setForeground(Color.BLACK);
 
        }
 
        for (int i = weeknum - 1, count = 1; count <= mouthDays; i++, count++) {
            day[i].setText(Integer.toString(count));
            day[i].setBackground(Color.white);
            day[i].setForeground(Color.BLACK);
 
        }
       
        if((times.get(Calendar.YEAR)==Integer.parseInt(y.getText()))&&(times.get(Calendar.MONTH)+1)==Integer.parseInt(m.getText())) {
            int I = weeknum - 2 + times.get(Calendar.DAY_OF_MONTH);
            day[I].setForeground(Color.RED);
            day[I].setBackground(new Color(199,237,204));
        }
 
    }//设置每天的方法
 
    public int getMouthDays(int month, boolean isleap) {
       
        if (isleap) {//闰年
            if (month == 2)
                return 28;
            else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                return 31;
            else
                return 30;
        } else {//
            if (month == 2)
                return 29;
            else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
                return 31;
            else
                return 30;
        }
    }//判断月的天数
    public static void updateTime() {
       Timer timer = new Timer(true);//关联的线程作为守护进程运行，则为true。
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                nowdays.setText(df.format(new Date().getTime()));
            }
        }, 0, 1000);
 
    }//更新时间
 
}
 
 
public class myCalendar {
 
    public static void main(String[] args) {
 
        myCal Calendar1 = new myCal();
 
            Calendar1.init();
            myCal.updateTime();
 
    }
}