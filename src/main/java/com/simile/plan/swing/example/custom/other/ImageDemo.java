package com.simile.plan.swing.example.custom.other;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class ImageDemo {

    public static void main(String[] args) {
        final JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 400);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

//        Image image = Toolkit.getDefaultToolkit().getImage("demo.jpg");
        Image image = Toolkit.getDefaultToolkit().getImage("/Users/haizhi/Documents/workspace/work/plan-swing/src/main/resources/demo.jpg");
        ImageIcon imageIcon = new ImageIcon(image);
//        ImageIcon imageIcon = new ImageIcon("demo.jpg");
//        ImageIcon imageIcon = new ImageIcon("/Users/haizhi/Documents/workspace/work/plan-swing/src/main/resources/demo.jpg");
        JLabel label = new JLabel(imageIcon);
        panel.add(label);


        jf.setContentPane(panel);
        jf.setVisible(true);
    }


}
