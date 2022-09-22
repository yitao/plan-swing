package com.simile.plan.swing.example.custom.layout;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * FlowLayout，流式布局管理器。按水平方向依次排列放置组件，排满一行，换下一行继续排列。排列方向（左到右 或 右到左）取决于容器的componentOrientation属性（该属性属于Component），它可能的值如下:
 * <p>
 * ComponentOrientation.LEFT_TO_RIGHT（默认）
 * ComponentOrientation.RIGHT_TO_LEFT
 * <p>
 * 同一行（水平方向）的组件的对齐方式由 FlowLayout 的align属性确定，它可能的值如下:
 * <p>
 * FlowLayout.LEFT : 左对齐
 * FlowLayout.CENTER : 居中对齐（默认）
 * FlowLayout.RIGHT : 右对齐
 * FlowLayout.LEADING : 与容器方向的开始边对齐，例如，对于从左到右的方向，则与左边对齐
 * FlowLayout.TRAILING : 与容器方向的结束边对齐，例如，对于从左到右的方向，则与右边对齐。
 *
 * @Author yitao
 * @Created 2021/10/12
 */
public class FlowLayoutDemo1 {

    public static void main(String[] args) {
        int lineWidth = 800;
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(800, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        JPanel container = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JPanel line1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        line1.setPreferredSize(new Dimension(lineWidth, 50));
        line1.add(new Button("运行"));
        line1.setBorder(new LineBorder(Color.GRAY));
        container.add(line1);

        JPanel line2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        line2.setPreferredSize(new Dimension(lineWidth, 50));
        line2.add(new JTextField());
        line2.setBorder(new LineBorder(Color.GRAY));
        container.add(line2);


        JPanel line3 = new JPanel(new GridLayout(1, 3));
        line3.setPreferredSize(new Dimension(lineWidth, 400));
        for (int i = 0; i < 3; i++) {
            JPanel line31 = new JPanel(new GridLayout(0,1));
//            line31.setPreferredSize(new Dimension(800 / 3, 400));
            line31.setBorder(new LineBorder(Color.GRAY));
            for (int j = 20; j > 0; j--) {
                JTextArea textArea = new JTextArea(i + ":" + j);
                textArea.setPreferredSize(new Dimension(200, 80));
                textArea.setBorder(new LineBorder(Color.GRAY));
                line31.add(textArea);
            }
            JScrollPane line311 = new JScrollPane(
                    line31, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            line3.add(line311);
        }
        container.add(line3);


        jf.setContentPane(container);
        jf.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示
    }
}
