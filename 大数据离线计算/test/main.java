/*
 * Created by JFormDesigner on Thu Jun 10 09:24:41 CST 2021
 */

package test;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author license
 */
public class main extends JFrame {
    public main() {
        initComponents(builttree());

        this.setBounds(700,300,700,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static String[] items;//装字段的数组
    public static Vector<String> databasename=new Vector<String>();//数据库名
    private DefaultTableModel tb=new DefaultTableModel();
    int countrow=0;
    int countcol=1;
    private void button3MouseClicked(MouseEvent e) {
        try {
            String[] sentence=textArea2.getText().split(";");
            for(int k=0;k<sentence.length;k++){
            ResultSet resultset = Demo.statement().executeQuery(sentence[k]);
            JTable tab=new JTable();
            tab.setModel(showtable(resultset));
            FitTableColumns(tab);
            JScrollPane scr=new JScrollPane();
            scr.setViewportView(tab);
            tabbedPane1.addTab("result"+k,scr);
            resultset.close();
            }

        }catch(Exception e1){
            e1.printStackTrace();
    }
    }

    private void button4MouseClicked(MouseEvent e) {
        textArea2.setText(null);
    }

    public static TextField textField1;
    private void initComponents(DefaultMutableTreeNode n) {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("te");
        splitPane1 = new JSplitPane();
        splitPane2 = new JSplitPane();
        scrollPane1 = new JScrollPane();
        tree1 = new JTree(n);
        panel2 = new JPanel();
        toolBar1 = new JToolBar();
        button3 = new JButton();
        button4 = new JButton();
        textField4 = new JTextField();
        scrollPane4 = new JScrollPane();
        textArea2 = new JTextArea();
        scrollPane2 = new JScrollPane();
        tabbedPane1 = new JTabbedPane();
        scrollPane3 = new JScrollPane();
        textArea1 = new JTextArea();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== splitPane1 ========
        {
            splitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
            splitPane1.setResizeWeight(0.65);

            //======== splitPane2 ========
            {

                //======== scrollPane1 ========
                {
                    scrollPane1.setViewportView(tree1);
                }
                splitPane2.setLeftComponent(scrollPane1);

                //======== panel2 ========
                {
                    panel2.setLayout(new BorderLayout());

                    //======== toolBar1 ========
                    {

                        //---- button3 ----
                        button3.setText(bundle.getString("button3.text"));
                        button3.setBackground(Color.black);
                        button3.setForeground(new Color(204, 255, 255));
                        button3.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button3MouseClicked(e);
                            }
                        });
                        toolBar1.add(button3);

                        //---- button4 ----
                        button4.setText(bundle.getString("button4.text"));
                        button4.setBackground(Color.black);
                        button4.setForeground(new Color(204, 255, 255));
                        button4.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                button4MouseClicked(e);
                            }
                        });
                        toolBar1.add(button4);

                        //---- textField4 ----
                        textField4.setEnabled(false);
                        textField4.setEditable(false);
                        toolBar1.add(textField4);
                    }
                    panel2.add(toolBar1, BorderLayout.NORTH);

                    //======== scrollPane4 ========
                    {

                        //---- textArea2 ----
                        textArea2.setFont(textArea2.getFont().deriveFont(textArea2.getFont().getSize() + 5f));
                        textArea2.setBackground(new Color(204, 204, 204));
                        textArea2.setForeground(Color.black);
                        scrollPane4.setViewportView(textArea2);
                    }
                    panel2.add(scrollPane4, BorderLayout.CENTER);
                }
                splitPane2.setRightComponent(panel2);
            }
            splitPane1.setTopComponent(splitPane2);

            //======== scrollPane2 ========
            {

                //======== tabbedPane1 ========
                {
                    tabbedPane1.setMinimumSize(new Dimension(206, 200));

                    //======== scrollPane3 ========
                    {

                        //---- textArea1 ----
                        textArea1.setText(bundle.getString("textArea1.text"));
                        textArea1.setFont(textArea1.getFont().deriveFont(textArea1.getFont().getSize() + 6f));
                        textArea1.setRows(5);
                        scrollPane3.setViewportView(textArea1);
                    }
                    tabbedPane1.addTab("Result list:", scrollPane3);
                }
                scrollPane2.setViewportView(tabbedPane1);
            }
            splitPane1.setBottomComponent(scrollPane2);
        }
        contentPane.add(splitPane1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public void FitTableColumns(JTable myTable){
        JTableHeader header = myTable.getTableHeader();
        int rowCount = myTable.getRowCount();

        Enumeration columns = myTable.getColumnModel().getColumns();

        while(columns.hasMoreElements()){
            TableColumn column = (TableColumn)columns.nextElement();
            int col = header.getColumnModel().getColumnIndex(column.getIdentifier());
            int width = (int)myTable.getTableHeader().getDefaultRenderer()
                    .getTableCellRendererComponent(myTable, column.getIdentifier()
                            , false, false, -1, col).getPreferredSize().getWidth();
            for(int row = 0; row<rowCount; row++){
                int preferedWidth = (int)myTable.getCellRenderer(row, col).getTableCellRendererComponent(myTable,
                        myTable.getValueAt(row, col), false, false, row, col).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth);
            }
            header.setResizingColumn(column);
            column.setWidth(width+myTable.getIntercellSpacing().width+10);
            myTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }


    private DefaultMutableTreeNode builttree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("数据库");
        try {
            getdatabase();//获取数据库名存在databasename
            for(int l=0;l<databasename.size();l++) {
                Vector<String> tablenames= gettables(databasename.elementAt(l));//获取数据库下表名存tablenames中，对应childindex
                int childindex = 0;
                DefaultMutableTreeNode secroot=new DefaultMutableTreeNode(databasename.elementAt(l));
                while (childindex < tablenames.size()) {
                    String tableName = tablenames.elementAt(childindex);
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(tableName);
                    int sonindex = 0;
                    if(databasename.elementAt(l).equals("user27_db")){//如果是自己数据库就接着获取字段
                        getitems(tablenames,childindex);
                        while (sonindex < items.length) {
                            String columnName = items[sonindex];
                            DefaultMutableTreeNode son = new DefaultMutableTreeNode(columnName);
                            child.insert(son, sonindex);
                            sonindex = sonindex + 1;
                        }
                    }
                    secroot.insert(child, childindex);
                    childindex++;
                }
                root.insert(secroot,l);
            }
        }catch(Exception e4){
                e4.printStackTrace();
            }

            return root;
    }

    private DefaultTableModel showtable(ResultSet res){
        DefaultTableModel t=new DefaultTableModel();
        try {
            int cnn = res.getMetaData().getColumnCount();
            items = new String[cnn];
            int i = 0;
            while (i < cnn) {
                String colname = res.getMetaData().getColumnName(i + 1);
                items[i] = colname;
                i++;
            }
            for (int j1 = 0; j1 < items.length; j1++) {
                t.addColumn(items[j1]);
            }

            while (res.next()) {
                String[] a = new String[cnn];
                for (int y = 0; y < cnn; y++) {
                    a[y] = res.getString(y + 1);
                }
                t.addRow(a);
            }
        }catch(Exception e8){
            e8.printStackTrace();
        }
        return t;
    }

    private void getdatabase(){
        try{
                ResultSet resultS=Demo.statement().executeQuery("show databases");
                while(resultS.next()){
                    String woc=resultS.getString(1);
                    databasename.addElement(woc);
                }
        }catch(Exception e9){
            e9.printStackTrace();
        }
    }

    private Vector<String> gettables(String ss){
        Vector<String> tablenames=new Vector<String>();//装表名的数组
        try {
            ResultSet resultSet = Demo.statement().executeQuery("show tables from "+ss);
            while (resultSet.next()) {
                tablenames.addElement(resultSet.getString(1));
            }
            resultSet.close();
        }catch (Exception e6){
            e6.printStackTrace();
        }
        return tablenames;
    }

    private void getitems(Vector<String> tablenames,int j){
        try{
            int i=0;
            ResultSet resultSet = Demo.statement().executeQuery("select * from "+tablenames.elementAt(j)+" limit 10");
            int cnn=resultSet.getMetaData().getColumnCount();
            items=new String[cnn];
            while(i<cnn){
                String colname=resultSet.getMetaData().getColumnName(i+1);
                items[i]=colname;
                i++;
            }
            resultSet.close();
        }catch(Exception e7){
            e7.printStackTrace();
        }
    }
    public static void main(String[] args){
        login f=new login();
    }
    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JSplitPane splitPane1;
    private JSplitPane splitPane2;
    private JScrollPane scrollPane1;
    private JTree tree1;
    private JPanel panel2;
    private JToolBar toolBar1;
    private JButton button3;
    private JButton button4;
    private JTextField textField4;
    private JScrollPane scrollPane4;
    private JTextArea textArea2;
    private JScrollPane scrollPane2;
    private JTabbedPane tabbedPane1;
    private JScrollPane scrollPane3;
    private JTextArea textArea1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
