package com.simile.plan.swing.example.custom.layout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * GridLayout，网格布局管理器。它以矩形网格形式对容器的组件进行布置，把容器按行列分成大小相等的矩形网格，一个网格中放置一个组件，组件宽高自动撑满网格。
 * <p>
 * 以行数和总数优先: 通过构造方法或 setRows 和 setColumns 方法将行数和列数都设置为非零值时，指定的列数将被忽略。列数通过指定的行数和布局中的组件总数来确定。因此，例如，如果指定了三行和两列，在布局中添加了九个组件，则它们将显示为三行三列。仅当将行数设置为零时，指定列数才对布局有效。
 *
 * @Author yitao
 * @Created 2021/10/12
 */
public class GridLayoutDemo3 {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(400, 600);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);

        // 创建 3 行 3 列 的网格布局
        GridLayout layout = new GridLayout(3, 1);


        JPanel panel = new JPanel(layout);

        JPanel line1 = new JPanel();
        line1.setBorder(new LineBorder(Color.GRAY));
        line1.add(new Label("第一行"));
        panel.add(line1);


        JPanel line2 = new JPanel();
        line2.setBorder(new LineBorder(Color.GRAY));
        line2.setPreferredSize(new Dimension(400, 300));
        line2.add(new JTextArea("第二行"));
        panel.add(line2);

        JPanel line3 = new JPanel();
        line3.setBorder(new LineBorder(Color.GRAY));
        line3.add(new JButton("第二行"));
        panel.add(line3);

        jf.setContentPane(panel);
        jf.setVisible(true);
    }
}
