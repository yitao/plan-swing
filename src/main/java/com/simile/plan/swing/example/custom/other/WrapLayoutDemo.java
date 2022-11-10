package com.simile.plan.swing.example.custom.other;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class WrapLayoutDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 250);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        Box box = Box.createHorizontalBox();
        box.setBorder(LineBorder.createGrayLineBorder());


        WrapLayout layout = new WrapLayout(FlowLayout.LEFT);
        JPanel panel = new JPanel(layout);
        panel.setBorder(new LineBorder(Color.GRAY));

        for (int j = 0; j < 13; j++) {
            JButton btn01 = new JButton("按钮" + j);
            panel.add(btn01);
        }

        box.add(panel);
        jf.setContentPane(box);
        jf.setVisible(true);
    }
}
