package com.simile.plan.swing.app.ide;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @Author yitao
 * @Created 2022/11/07
 */
public class CodeEditor implements ListSelectionListener {
    private JFrame parent;
    private JComponent root;

    public CodeEditor(JFrame parent) {
        this.parent = parent;
        initUi();
    }


    private void initUi() {
        Border border = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
        root = new JPanel(new BorderLayout());

//        root.add(buildList(), BorderLayout.WEST);
//        root.add(buildTop(), BorderLayout.NORTH);
        root.add(buildContent(), BorderLayout.CENTER);

//        root.setVisible(false);
        parent.add(root);
    }

    private JTextField textField;

    private JComponent buildTop() {
        Box top = Box.createHorizontalBox();

        JPanel inputPanel = new JPanel();
//        Dimension dimension = new Dimension(800, 80);
//        inputPanel.setSize(dimension);
//        inputPanel.setPreferredSize(dimension);
//        inputPanel.setMaximumSize(dimension);
        inputPanel.setBorder(new EmptyBorder(20, 0, 20, 0));
        inputPanel.add(new JLabel("请输入文件地址:"), BorderLayout.CENTER);
        textField = new JTextField("");
        textField.setText("/Users/haizhi/Documents/workspace/work/graphworks-server/graphworks-space/src/main/java/com/haizhi/graphworks/space/model/po/");
        textField.setColumns(40);
        inputPanel.add(textField, BorderLayout.CENTER);

        JButton parseBtn = new JButton("解析");
        parseBtn.setActionCommand("解析");
        parseBtn.addActionListener(new ParserListener());
        inputPanel.add(parseBtn, BorderLayout.CENTER);
        top.add(inputPanel);
        return top;
    }

    private EditorComponent textArea;
    private EditorComponent textArea2;

    private JComponent buildContent() {
        Box center = Box.createVerticalBox();
        textArea = new EditorComponent(null);
        textArea2 = new EditorComponent(null);

        textArea.getTextArea().setKeywordHolder(new KeywordHolder.JavaKeywordHolder());
        textArea2.getTextArea().setKeywordHolder(new KeywordHolder.MysqlKeywordHolder());

        JScrollPane scrollPane = new JScrollPane(
                textArea.getRoot(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        JScrollPane scrollPane2 = new JScrollPane(
                textArea2.getRoot(),
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
        );

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                scrollPane, scrollPane2);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        JSplitPane splitPane2 = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                buildList(), splitPane);
        splitPane2.setResizeWeight(0.1);
        splitPane2.setOneTouchExpandable(true);
        splitPane2.setContinuousLayout(true);

        center.add(splitPane2);
        return center;
    }

    private JList list;
    private DefaultListModel listModel;

    private JComponent buildList() {
        listModel = new DefaultListModel();
        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);
        return listScrollPane;
    }


    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {
            if (list.getSelectedIndex() != -1) {
                //Selection, enable the fire button.
                String txt = (String) listModel.get(list.getSelectedIndex());
                System.out.println(list.getSelectedIndex() + ":" + txt);
                File poFile = new File(dir, txt);
            }
        }
    }

    private File dir;

    class ParserListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String dirPath = textField.getText();
            dir = new File(dirPath);
            if (dir.isDirectory()) {
                listModel.removeAllElements();
                for (String s : dir.list()) {
                    listModel.addElement(s);
                }
            }
        }
    }


}
