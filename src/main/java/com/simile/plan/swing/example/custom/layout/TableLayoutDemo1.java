package com.simile.plan.swing.example.custom.layout;

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
public class TableLayoutDemo1 {

    public static void main(String[] args) {
    }
}
