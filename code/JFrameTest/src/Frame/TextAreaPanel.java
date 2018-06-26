/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *设置mainFrame的文档编辑区为富文本编辑区
 * @author 蓝云甫
 */
public class TextAreaPanel extends JPanel{
    static JFrame mf;
    private JComboBox combox_name, combox_size;// 字体、字号组合框  
    private JButton button_larger,button_smaller,button_color;//字体变大变小和颜色选择器  
    private JCheckBox checkb_bold, checkb_italic;// 粗体、斜体复选框  
    private JPopupMenu popupmenu;  
    public JTextArea ta = new JTextArea();  
    private JScrollPane sp = new JScrollPane(ta);
    
    public TextAreaPanel(JFrame mf){
        this.mf = mf;
        this.setLayout(new BorderLayout());
        
        JToolBar toolbar = new JToolBar(); // 创建工具栏  
        this.add(toolbar, BorderLayout.NORTH); // 工具栏添加到窗格北部  
        this.add(sp);  
        ta.setLineWrap(true);// 换行  
        //////////////////字体  
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();  //获得当前电脑的字体环境
        String[] fontsName = ge.getAvailableFontFamilyNames(); // 获得系统字体  
        combox_name = new JComboBox(fontsName);  
        toolbar.add(combox_name);  
        combox_name.addActionListener(new ActionListener() {// 字号  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形 （原有字形） 
                int size = font.getSize();  	//获得字体大小（原有大小）
                ta.setFont(new Font(fontname, style, size));  //重新设置字体格式
            }  
        });  
        /////////////////字号  
        String sizestr[] = { "20", "30", "40", "50", "60", "70" ,"80","90","100"};  
        combox_size = new JComboBox(sizestr);  
        combox_size.setEditable(true);  
        toolbar.add(combox_size);  
        combox_size.addActionListener(new ActionListener() {// 字号  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                int size = Integer.parseInt((String)combox_size.getSelectedItem());  //获得选择的需要更改的字号名
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////////字号加减按钮  
        button_larger=new JButton("A+");  
        toolbar.add(button_larger);  
        button_larger.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形  
                int size = font.getSize()+5; 		//在当前的字体大小的基础上加5然后重新设置 
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        button_smaller=new JButton("A-");  
        toolbar.add(button_smaller);  
        button_smaller.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形  
                int size = font.getSize()-5;  
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        /////////////////J  
        /////////////////粗体和斜体  
        checkb_bold = new JCheckBox("粗体"); //字形复选框  
        toolbar.add(checkb_bold);  
        checkb_bold.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形  
                int size = font.getSize();  
                style = style ^ 1;     			//位与运算，这个操作可以将当前字体编程粗体
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        checkb_italic = new JCheckBox("斜体");  
        toolbar.add(checkb_italic);  
        checkb_italic.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                String fontname = (String)combox_name.getSelectedItem();//获得字体名  
                Font font = ta.getFont();     //获得文本区的当前字体对象  
                int style = font.getStyle();      //获得字形  
                int size = font.getSize();  
                style = style ^ 2;  			//这个操作可以将当前字体变成斜体
                ta.setFont(new Font(fontname, style, size));  
            }  
        });  
        ////////////////  
        JRadioButton radiob_color[];  						//java单选框，在同一个组内虽然有多个单选框存在，然而同一时刻只能有一个单选框处于选中状态
        String colorstr[]={"红","绿","蓝"};  
        ButtonGroup bgroup_color = new ButtonGroup();      //按钮组  
        radiob_color = new JRadioButton[colorstr.length];  //颜色单选按钮数组  
        for (int i=0; i<radiob_color.length; i++){  
            radiob_color[i]=new JRadioButton(colorstr[i]);   
            bgroup_color.add(radiob_color[i]); //添加到按钮组  
            toolbar.add(radiob_color[i]);     //添加到工具栏  
        }          
        radiob_color[0].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.red);// 设置颜色  
            }  
        });  
        radiob_color[1].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.green);  
            }  
        });  
        radiob_color[2].addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                ta.setForeground(Color.blue);  
            }  
        });  
        ///////////////颜色选择器  
        button_color=new JButton("其他");  
        toolbar.add(button_color);  
        button_color.addActionListener(new ActionListener() {  
            public void actionPerformed(ActionEvent e) {  
                Color color;  
                color=JColorChooser.showDialog(TextAreaPanel.this,"颜色选择", Color.black);  
                ta.setForeground(color);// 设置颜色  
            }  
        });  
        ////////////////鼠标事件  
        ta.addMouseListener(new MouseAdapter() {// 鼠标事件处理方法，右击弹出菜单  
            public void mouseClicked(MouseEvent e) {  
                if (e.getModifiers() == MouseEvent.BUTTON3_MASK) // 单击的是鼠标右键  
                    popupmenu.show(ta, e.getX(), e.getY()); // 在鼠标单击处显示快捷菜单  
            }  
        });  
        this.setVisible(true);
    }
    
    public static void main(String[] args){
        JFrame jf = new JFrame("测试面板");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);  
        //Dimension dim = getToolkit().getScreenSize(); // 获得屏幕分辨率  
        jf.setBounds(900, 900, 700, 480);
        /**
        jf.setContentPane(new TextAreaPanel(jf));
        jf.pack();
        * **/
        jf.setVisible(true);
      
    }
}
