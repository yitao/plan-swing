package com.simile.plan.swing.example.custom.layout;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
public class FlowLayoutDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 250);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        FlowLayout clayout = new FlowLayout(FlowLayout.LEFT);
        JPanel container = new JPanel(clayout);
//        container.setPreferredSize(new Dimension(800,250));
//        container.setMinimumSize(new Dimension(800,250));
        // 创建内容面板，指定使用 流式布局
        for (int i = 0; i < 3; i++) {
            FlowLayout layout = new FlowLayout(FlowLayout.LEFT);

            JPanel panel = new JPanel(layout);
            panel.setBorder(new LineBorder(Color.GRAY));

            for (int j = 0; j < 13; j++) {
                JButton btn01 = new JButton("按钮" + j);
                panel.add(btn01);
            }

            JScrollPane scrollPane = new JScrollPane(panel
                    , JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            container.add(scrollPane);
        }


        jf.setContentPane(container);
        jf.setVisible(true);        // PS: 最后再设置为可显示(绘制), 所有添加的组件才会显示
    }
}
