package com.simile.plan.swing.example.custom.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * @Author yitao
 * @Created 2021/10/12
 */
public class JTreeDemo {

    public static void main(String[] args) {
        JFrame jf = new JFrame("测试窗口");
        jf.setSize(300, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());

        // 创建根节点
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("中国");

        // 创建二级节点
        DefaultMutableTreeNode gdNode = new DefaultMutableTreeNode("广东");
        DefaultMutableTreeNode fjNode = new DefaultMutableTreeNode("福建");
        DefaultMutableTreeNode shNode = new DefaultMutableTreeNode("上海");
        DefaultMutableTreeNode twNode = new DefaultMutableTreeNode("台湾");

        // 把二级节点作为子节点添加到根节点
        rootNode.add(gdNode);
        rootNode.add(fjNode);
        rootNode.add(shNode);
        rootNode.add(twNode);

        // 创建三级节点
        DefaultMutableTreeNode gzNode = new DefaultMutableTreeNode("广州");
        DefaultMutableTreeNode szNode = new DefaultMutableTreeNode("深圳");

        DefaultMutableTreeNode fzNode = new DefaultMutableTreeNode("福州");
        DefaultMutableTreeNode xmNode = new DefaultMutableTreeNode("厦门");

        DefaultMutableTreeNode tbNode = new DefaultMutableTreeNode("台北");
        DefaultMutableTreeNode gxNode = new DefaultMutableTreeNode("高雄");
        DefaultMutableTreeNode jlNode = new DefaultMutableTreeNode("基隆");

        // 把三级节点作为子节点添加到相应的二级节点
        gdNode.add(gzNode);
        gdNode.add(szNode);

        fjNode.add(fzNode);
        fjNode.add(xmNode);

        twNode.add(tbNode);
        twNode.add(gxNode);
        twNode.add(jlNode);

        // 使用根节点创建树组件
        JTree tree = new JTree(rootNode);

        // 设置树显示根节点句柄
        tree.setShowsRootHandles(true);

        // 设置树节点可编辑
        tree.setEditable(true);
        tree.setDragEnabled(true);
        tree.setDropMode(DropMode.INSERT);
        tree.setTransferHandler(new TreeTransferHandler());
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        // 设置节点选中监听器
//        tree.addTreeSelectionListener(new TreeSelectionListener() {
//            @Override
//            public void valueChanged(TreeSelectionEvent e) {
//                System.out.println("当前被选中的节点: " + e.getPath());
//                System.out.println(String.join(",",
//                        Arrays.asList(e.getPath().getPath()).stream().map(String::valueOf).collect(Collectors.toList())));
//            }
//        });

        tree.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Object s = e.getSource();
                if (!(s instanceof JTree)) {
                    return;
                }
                JTree tree = (JTree) s;
                // 如果是鼠标右键，则显示弹出菜单
                if (e.isMetaDown()) {
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    System.out.println("mouseClicked" + selPath);
                    showPopupMenu(e.getComponent(), e.getX(), e.getY());
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        // 创建滚动面板，包裹树（因为树节点展开后可能需要很大的空间来显示，所以需要用一个滚动面板来包裹）
        JScrollPane scrollPane = new JScrollPane(tree);

        // 添加滚动面板到那内容面板
        panel.add(scrollPane, BorderLayout.CENTER);

        // 设置窗口内容面板并显示
        jf.setContentPane(panel);
        jf.setVisible(true);
    }

    public static void showPopupMenu(Component invoker, int x, int y) {
        // 创建 弹出菜单 对象
        JPopupMenu popupMenu = new JPopupMenu();

        // 创建 一级菜单
        JMenuItem copyMenuItem = new JMenuItem("复制");
        JMenuItem pasteMenuItem = new JMenuItem("粘贴");
        JMenu editMenu = new JMenu("编辑");   // 需要 添加 二级子菜单 的 菜单，使用 JMenu
        JMenuItem fileMenu = new JMenuItem("文件");

        // 创建 二级菜单
        JMenuItem findMenuItem = new JMenuItem("查找");
        JMenuItem replaceMenuItem = new JMenuItem("替换");
        // 添加 二级菜单 到 "编辑"一级菜单
        editMenu.add(findMenuItem);
        editMenu.add(replaceMenuItem);

        // 添加 一级菜单 到 弹出菜单
        popupMenu.add(copyMenuItem);
        popupMenu.add(pasteMenuItem);
        popupMenu.addSeparator();       // 添加一条分隔符
        popupMenu.add(editMenu);
        popupMenu.add(fileMenu);

        // 添加菜单项的点击监听器
        copyMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("复制 被点击");
            }
        });
        findMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("查找 被点击");
            }
        });
        // ......

        // 在指定位置显示弹出菜单
        popupMenu.show(invoker, x, y);
    }

    static class TreeSelection implements Transferable {
        public static DataFlavor flavor = new DataFlavor(DefaultMutableTreeNode.class, "TreeNode");
        DefaultMutableTreeNode data;

        public TreeSelection(DefaultMutableTreeNode data) {
            this.data = data;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{flavor};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return this.flavor.equals(flavor);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return data;
        }
    }


    static class TreeTransferHandler extends TransferHandler {

        protected Transferable createTransferable(JComponent c) {
            JTree list = (JTree) c;
            TreePath selectionPath = list.getSelectionPath();
            DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath.getLastPathComponent();
            return new TreeSelection(treeNode);
        }

        public int getSourceActions(JComponent c) {
            return TransferHandler.COPY_OR_MOVE;
        }

        public boolean canImport(TransferHandler.TransferSupport info) {
            // Check for String flavor
            if (!info.isDataFlavorSupported(TreeSelection.flavor)) {
                return false;
            }
            Transferable t = info.getTransferable();
            DefaultMutableTreeNode sourceNode;
            try {
                sourceNode = (DefaultMutableTreeNode) t.getTransferData(TreeSelection.flavor);
            } catch (Exception e) {
                return false;
            }
            if (sourceNode.isRoot()) {
                return false;
            }
            JTree.DropLocation dl = (JTree.DropLocation) info.getDropLocation();
            JTree jTree = (JTree) info.getComponent();

            TreePath targetPath = dl.getPath();

            DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) targetPath.getLastPathComponent();

            if (targetNode.getUserObject().equals(sourceNode.getUserObject())) {
                //自己不能往自己移动
                return false;
            }

            DefaultMutableTreeNode parentTargetNode = (DefaultMutableTreeNode) targetNode.getParent();
            while (parentTargetNode != null) {
                if (sourceNode.getUserObject().equals(parentTargetNode.getUserObject())) {
                    //上级不能往自己的下级移动，避免出现环
                    return false;
                }
                parentTargetNode = (DefaultMutableTreeNode) parentTargetNode.getParent();
            }
            return true;
        }

        public boolean importData(TransferHandler.TransferSupport info) {
            if (!info.isDrop()) {
                return false;
            }
            Transferable t = info.getTransferable();
            DefaultMutableTreeNode sourceNode;
            try {
                sourceNode = (DefaultMutableTreeNode) t.getTransferData(TreeSelection.flavor);
            } catch (Exception e) {
                return false;
            }

            JTree.DropLocation dl = (JTree.DropLocation) info.getDropLocation();
            JTree jTree = (JTree) info.getComponent();

            TreePath targetPath = dl.getPath();
            DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) targetPath.getLastPathComponent();

            targetNode.add(sourceNode);
            jTree.updateUI();

            return true;
        }

        protected void exportDone(JComponent c, Transferable data, int action) {
            if (action == TransferHandler.MOVE) {
                JTree jTree = (JTree) c;
                DefaultMutableTreeNode targetNode = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
                if (targetNode == null) {
                    return;
                }
                DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) targetNode.getParent();
                if (parentNode == null) {
                    return;
                }
                parentNode.remove(targetNode);
                jTree.updateUI();
            }
        }

    }
}
