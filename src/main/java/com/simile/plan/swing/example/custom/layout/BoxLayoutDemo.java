package com.simile.plan.swing.example.custom.layout;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class BoxLayoutDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JButton btn01 = new JButton("Button01");
        JButton btn02 = new JButton("Button02");
        JButton btn03 = new JButton("Button03");
        JButton btn04 = new JButton("Button04");
        JButton btn042 = new JButton("Button042");
        JButton btn05 = new JButton("Button05");

btn01.revalidate();

        // 创建第一个水平箱容器
        Box hBox01 = Box.createHorizontalBox();
        hBox01.setBorder(new LineBorder(Color.GRAY));

        btn01.setPreferredSize(new Dimension(100,50));
        btn01.setMinimumSize(new Dimension(100,50));
        btn01.setMaximumSize(new Dimension(100,50));

        btn02.setPreferredSize(new Dimension(50,50));
//        btn02.setSize(new Dimension(50,50));
        btn02.setMinimumSize(new Dimension(50,50));
        btn02.setMaximumSize(new Dimension(50,50));

        btn03.setPreferredSize(new Dimension(200,50));
        btn03.setMinimumSize(new Dimension(200,50));
        btn03.setMaximumSize(new Dimension(200,50));


        hBox01.add(btn01);
        hBox01.add(btn02);
        hBox01.add(btn03);

        // 创建第二水平箱容器
        Box hBox02 = Box.createHorizontalBox();
        hBox02.setBorder(new LineBorder(Color.GRAY));
        hBox02.add(btn04);
//        hBox02.add(Box.createHorizontalGlue()); // 添加一个水平方向胶状的不可见组件，撑满剩余水平空间
        hBox02.add(btn042);
        hBox02.add(Box.createHorizontalGlue()); // 添加一个水平方向胶状的不可见组件，撑满剩余水平空间
//        hBox02.add(btn05);

        // 创建一个垂直箱容器，放置上面两个水平箱（Box组合嵌套）
        Box vBox = Box.createVerticalBox();
        vBox.add(hBox01);

        Component mbox = Box.createVerticalStrut(400);

        vBox.add(mbox);
        vBox.add(hBox02);

        // 把垂直箱容器作为内容面板设置到窗口
        jf.setContentPane(vBox);

        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(true);
    }
}
