package com.simile.plan.swing.example.custom.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class JColorChooserDemo {

    public static void main(String[] args) throws Exception {
        final JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        // 创建一个标签, 用于显示选择的原色
        final JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(150, 150));
        label.setOpaque(true);
        panel.add(label);

        JButton btn = new JButton("选择颜色");
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示颜色选取器对话框, 返回选取的颜色（线程将被阻塞, 直到对话框被关闭）
                Color color = JColorChooser.showDialog(jf, "选取颜色", null);

                // 如果用户取消或关闭窗口, 则返回的 color 为 null
                if (color == null) {
                    return;
                }

                // 把选取的颜色设置为标签的背景
                label.setBackground(color);

                // 获取颜色的 ARGB 各个分量值
                int alpha = color.getAlpha();
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                label.setText("A=" + String.format("%02x", alpha) + ", " +
                        String.format("#%02x%02x%02x", red, green, blue));
            }
        });
        panel.add(btn);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }

}
