package com.simile.plan.swing;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class FirstDemo {

    public static void main(String[] args) {
//        emptyApp();
        lableApp2();
    }


    public static void lableApp2() {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        jf.add(panel);

        JLabel label = new JLabel("Hello Swing");
        panel.add(label);

        //自动计算内容大小进行展示
        jf.pack();
//        jf.setContentPane(panel);
        jf.setVisible(true);
    }


    //窗体内容无区域
    public static void lableApp() {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        jf.add(panel);

        JLabel label = new JLabel("Hello Swing");
        panel.add(label);

        jf.setVisible(true);
    }

    //窗体内容无区域
    public static void onlyLableApp() {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Hello Swing");
        jf.add(label);

        jf.setVisible(true);
    }


    //窗体内容无区域
    public static void emptyApp() {
        JFrame jf = new JFrame();
        //关闭窗体，程序也退出
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jf.setVisible(true);
    }


}
