package com.simile.plan.swing.flatlaf;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatDarculaLaf;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class FlatLafDemo {

    public static void main(String[] args) {
//        FlatLightLaf.setup();
        FlatDarculaLaf.setup();
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        jf.add(panel);

        JLabel label = new JLabel("Hello Swing");
        panel.add(label);
        panel.add(new JButton("Button"));

        //自动计算内容大小进行展示
        jf.pack();
//        jf.setContentPane(panel);
        jf.setVisible(true);
    }


}
