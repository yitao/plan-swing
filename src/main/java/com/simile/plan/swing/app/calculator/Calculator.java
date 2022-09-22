package com.simile.plan.swing.app.calculator;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * 计算器
 *
 * @Author yitao
 * @Created 2021/11/16
 */
public class Calculator {

    public static void main(String[] args) {
        List<String> inputs = new ArrayList<>();
        JFrame frame = new JFrame("计算器v1.0");
        frame.setSize(400, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        /*
        GridLayout的rows和cols
        如果指定了rows为非0的数，那么这个容器中将以rows的大小作为限制，cols将不再生效
        假设rows指定了4，如果有12个元素，那么每行固定是3个元素，不管cols的值是多少。
        如果有15个元素，那么前三行都会有4个元素，最后一行则只有3个元素。
        同理如果设置cols也会有此种规则（前提rows为0）
        layout添加的元素将均分容器
         */
        GridLayout layout = new GridLayout(0, 1, 0, 10);
        JPanel panel = new JPanel(layout);

//        JPanel p1 = new JPanel();
        Box p1 = Box.createVerticalBox();

        Box p11 = Box.createHorizontalBox();
        JLabel showInputBox = new JLabel("");
        p11.add(showInputBox);
        p1.add(p11);

        Box p12 = Box.createHorizontalBox();
        JLabel inputBox = new JLabel("");
        p12.add(inputBox);
        p1.add(p12);

        panel.add(p1);


        GridLayout l2 = new GridLayout(4, 4, 0, 0);
        JPanel p2 = new JPanel(l2);
        String[] t2 = {"7", "8", "9", "+", "4", "5", "6", "*", "1", "2", "3", "/", "0", ".", "-", "="};
        for (String t : t2) {
            JButton btn = new JButton(t);
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    //TODO 输入合法性校验，操作符不能相连，数字间不能出现多个点，不能出现多个等号
                    inputs.add(cmd);
                    if ("=".equals(cmd)) {
                        String res = calc(inputs);
                        showInputBox.setText(String.join("", inputs) + res);
                        inputs.clear();
                    }
                    inputBox.setText(String.join("", inputs));
                }
            });
            p2.add(btn);
        }
        panel.add(p2);

        JPanel p22 = new JPanel();
        p22.setSize(400, 100);
        panel.add(p22);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }

    private static String calc(List<String> inputs) {
        //111,*,222,+,333,=
        Stack<String> stack = new Stack<>();
        String num = "";
        String lop = null;
        for (String input : inputs) {
            if (isNumber(input)) {
                num += input;
            }
            if (".".equals(input)) {
                num += input;
            }
            if ("=".equals(input)) {
                stack.push(num);
                String res = calcCore(stack);
                stack.push(res);
            }
            if (isOperator(input)) {
                stack.push(num);
                if (lop == null) {
                    stack.push(input);
                } else {
                    if ("*".equals(input) || "/".equals(input)) {
                        if ("*".equals(lop) || "/".equals(lop)) {
                            //最后一次的操作是乘除，计算
                            String res = calcCore(stack);
                            stack.push(res);
                        } else {
                            //最后一次的操作是加减，保留
                        }
                        stack.push(input);
                    } else {
                        //如果是+，-，则先之前的表达式计算结果
                        String res = calcCore(stack);
                        stack.push(res);
                        stack.push(input);
                    }
                }
                lop = input;
                num = "";
            }
        }
        return calcCore(stack);
    }

    private static String calcCore(Stack<String> stack) {
        if (stack.size() == 1) {
            return stack.pop();
        }
        String num2 = stack.pop();
        String op = stack.pop();
        String num1 = stack.pop();
        String res = null;
        switch (op) {
            case "+":
                res = add(num1, num2);
                break;
            case "-":
                res = sub(num1, num2);
                break;
            case "*":
                res = mul(num1, num2);
                break;
            case "/":
                res = div(num1, num2);
                break;
        }
        return res;
    }

    private static Double parseNumber(String s) {
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            return null;
        }
    }

    private static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private static boolean isOperator(String s) {
        return ("+".equals(s) || "-".equals(s) || "*".equals(s) || "/".equals(s));
    }

    private static String add(String num1, String num2) {
        return String.valueOf(parseNumber(num1) + parseNumber(num2));
    }

    private static String sub(String num1, String num2) {
        return String.valueOf(parseNumber(num1) - parseNumber(num2));
    }

    private static String mul(String num1, String num2) {
        return String.valueOf(parseNumber(num1) * parseNumber(num2));
    }

    private static String div(String num1, String num2) {
        return String.valueOf(parseNumber(num1) / parseNumber(num2));
    }

}
