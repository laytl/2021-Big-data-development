package test;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


/*
 * Created by JFormDesigner on Wed Jun 09 16:24:12 CST 2021
 */



/**
 * @author license
 */
public class login extends JFrame {
    private main f;
    public login() {
        initComponents();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setBounds(600,400,500,500);
    }
    public static String acc="0";
    public static String pas="0";
    private void okButtonMouseClicked(MouseEvent e) {
        acc=textField1.getText();
        pas=textField2.getText();
        String s= "user27";
        String p="pass@bingo27";
        String u="jdbc:hive2://bigdata118.depts.bingosoft.net:22118/user27_db";
        String d="org.apache.hive.jdbc.HiveDriver";
        if(acc.equals(s)&&pas.equals(p)&&textField4.getText().equals(u)&&textField5.getText().equals(d)){
            this.dispose();
            f=new main();
        }
        else{
            textField1.setText(null);
            textField2.setText(null);
        }
    }

    private void cancelButtonMouseClicked(MouseEvent e) {
       textField1.setText(null);
       textField2.setText(null);
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        ResourceBundle bundle = ResourceBundle.getBundle("te");
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label1 = new JLabel();
        buttonBar = new JPanel();
        contentPanel11 = new JPanel();
        label13 = new JLabel();
        panel1 = new JPanel();
        label2 = new JLabel();
        textField1 = new JTextField();
        contentPanel8 = new JPanel();
        label10 = new JLabel();
        contentPanel7 = new JPanel();
        label9 = new JLabel();
        contentPanel14 = new JPanel();
        label18 = new JLabel();
        label3 = new JLabel();
        textField2 = new JTextField();
        contentPanel10 = new JPanel();
        label12 = new JLabel();
        contentPanel3 = new JPanel();
        label5 = new JLabel();
        contentPanel15 = new JPanel();
        label19 = new JLabel();
        label14 = new JLabel();
        textField4 = new JTextField();
        contentPanel12 = new JPanel();
        label15 = new JLabel();
        contentPanel16 = new JPanel();
        label20 = new JLabel();
        label17 = new JLabel();
        textField5 = new JTextField();
        contentPanel13 = new JPanel();
        label16 = new JLabel();
        contentPanel17 = new JPanel();
        label21 = new JLabel();
        okButton = new JButton();
        contentPanel5 = new JPanel();
        label7 = new JLabel();
        cancelButton = new JButton();
        scrollPane1 = new JScrollPane();
        contentPanel9 = new JPanel();
        label11 = new JLabel();
        contentPanel2 = new JPanel();
        label4 = new JLabel();
        contentPanel4 = new JPanel();
        label6 = new JLabel();

        //======== this ========
        setShape(null);
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(2, 2));

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setMinimumSize(new Dimension(20, 24));
                contentPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                //---- label1 ----
                label1.setText(bundle.getString("label1.text"));
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getSize() + 10f));
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel.add(label1);
            }
            dialogPane.add(contentPanel, BorderLayout.NORTH);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 85, 0, 0, 0, 85, 0, 0, 0, 0};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};

                //======== contentPanel11 ========
                {
                    contentPanel11.setMinimumSize(new Dimension(20, 24));
                    contentPanel11.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label13 ----
                    label13.setFont(label13.getFont().deriveFont(label13.getFont().getSize() + 3f));
                    label13.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel11.add(label13);
                }
                buttonBar.add(contentPanel11, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== panel1 ========
                {
                    panel1.setLayout(new FlowLayout());

                    //---- label2 ----
                    label2.setText(bundle.getString("label2.text"));
                    label2.setFont(label2.getFont().deriveFont(label2.getFont().getSize() + 4f));
                    panel1.add(label2);
                }
                buttonBar.add(panel1, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField1 ----
                textField1.setFont(textField1.getFont().deriveFont(textField1.getFont().getSize() + 4f));
                buttonBar.add(textField1, new GridBagConstraints(1, 1, 10, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== contentPanel8 ========
                {
                    contentPanel8.setMinimumSize(new Dimension(20, 24));
                    contentPanel8.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label10 ----
                    label10.setFont(label10.getFont().deriveFont(label10.getFont().getSize() + 3f));
                    label10.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel8.add(label10);
                }
                buttonBar.add(contentPanel8, new GridBagConstraints(7, 1, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel7 ========
                {
                    contentPanel7.setMinimumSize(new Dimension(20, 24));
                    contentPanel7.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label9 ----
                    label9.setFont(label9.getFont().deriveFont(label9.getFont().getSize() + 3f));
                    label9.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel7.add(label9);
                }
                buttonBar.add(contentPanel7, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel14 ========
                {
                    contentPanel14.setMinimumSize(new Dimension(20, 24));
                    contentPanel14.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label18 ----
                    label18.setFont(label18.getFont().deriveFont(label18.getFont().getSize() + 3f));
                    label18.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel14.add(label18);
                }
                buttonBar.add(contentPanel14, new GridBagConstraints(2, 3, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label3 ----
                label3.setText(bundle.getString("label3.text"));
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setFont(label3.getFont().deriveFont(label3.getFont().getSize() + 4f));
                buttonBar.add(label3, new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField2 ----
                textField2.setFont(textField2.getFont().deriveFont(textField2.getFont().getSize() + 4f));
                buttonBar.add(textField2, new GridBagConstraints(1, 4, 10, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== contentPanel10 ========
                {
                    contentPanel10.setMinimumSize(new Dimension(20, 24));
                    contentPanel10.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label12 ----
                    label12.setFont(label12.getFont().deriveFont(label12.getFont().getSize() + 3f));
                    label12.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel10.add(label12);
                }
                buttonBar.add(contentPanel10, new GridBagConstraints(8, 4, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel3 ========
                {
                    contentPanel3.setMinimumSize(new Dimension(20, 24));
                    contentPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label5 ----
                    label5.setFont(label5.getFont().deriveFont(label5.getFont().getSize() + 3f));
                    label5.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel3.add(label5);
                }
                buttonBar.add(contentPanel3, new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel15 ========
                {
                    contentPanel15.setMinimumSize(new Dimension(20, 24));
                    contentPanel15.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label19 ----
                    label19.setFont(label19.getFont().deriveFont(label19.getFont().getSize() + 3f));
                    label19.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel15.add(label19);
                }
                buttonBar.add(contentPanel15, new GridBagConstraints(2, 6, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label14 ----
                label14.setText(bundle.getString("label14.text"));
                label14.setFont(label14.getFont().deriveFont(label14.getFont().getSize() + 4f));
                label14.setHorizontalAlignment(SwingConstants.CENTER);
                buttonBar.add(label14, new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField4 ----
                textField4.setFont(textField4.getFont().deriveFont(textField4.getFont().getSize() + 4f));
                buttonBar.add(textField4, new GridBagConstraints(1, 7, 10, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== contentPanel12 ========
                {
                    contentPanel12.setMinimumSize(new Dimension(20, 24));
                    contentPanel12.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label15 ----
                    label15.setFont(label15.getFont().deriveFont(label15.getFont().getSize() + 3f));
                    label15.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel12.add(label15);
                }
                buttonBar.add(contentPanel12, new GridBagConstraints(4, 8, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel16 ========
                {
                    contentPanel16.setMinimumSize(new Dimension(20, 24));
                    contentPanel16.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label20 ----
                    label20.setFont(label20.getFont().deriveFont(label20.getFont().getSize() + 3f));
                    label20.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel16.add(label20);
                }
                buttonBar.add(contentPanel16, new GridBagConstraints(2, 9, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- label17 ----
                label17.setText(bundle.getString("label17.text"));
                label17.setFont(label17.getFont().deriveFont(label17.getFont().getSize() + 2f));
                label17.setHorizontalAlignment(SwingConstants.CENTER);
                buttonBar.add(label17, new GridBagConstraints(0, 10, 2, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- textField5 ----
                textField5.setFont(textField5.getFont().deriveFont(textField5.getFont().getSize() + 4f));
                buttonBar.add(textField5, new GridBagConstraints(1, 10, 10, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 0), 0, 0));

                //======== contentPanel13 ========
                {
                    contentPanel13.setMinimumSize(new Dimension(20, 24));
                    contentPanel13.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label16 ----
                    label16.setFont(label16.getFont().deriveFont(label16.getFont().getSize() + 3f));
                    label16.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel13.add(label16);
                }
                buttonBar.add(contentPanel13, new GridBagConstraints(4, 11, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //======== contentPanel17 ========
                {
                    contentPanel17.setMinimumSize(new Dimension(20, 24));
                    contentPanel17.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label21 ----
                    label21.setFont(label21.getFont().deriveFont(label21.getFont().getSize() + 3f));
                    label21.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel17.add(label21);
                }
                buttonBar.add(contentPanel17, new GridBagConstraints(2, 12, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 5, 5), 0, 0));

                //---- okButton ----
                okButton.setText(bundle.getString("okButton.text_2"));
                okButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        okButtonMouseClicked(e);
                    }
                });
                buttonBar.add(okButton, new GridBagConstraints(2, 13, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //======== contentPanel5 ========
                {
                    contentPanel5.setMinimumSize(new Dimension(20, 24));
                    contentPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label7 ----
                    label7.setFont(label7.getFont().deriveFont(label7.getFont().getSize() + 3f));
                    label7.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel5.add(label7);
                }
                buttonBar.add(contentPanel5, new GridBagConstraints(4, 13, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText(bundle.getString("cancelButton.text_2"));
                cancelButton.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        cancelButtonMouseClicked(e);
                    }
                });
                buttonBar.add(cancelButton, new GridBagConstraints(6, 13, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.CENTER);

            //======== scrollPane1 ========
            {

                //======== contentPanel9 ========
                {
                    contentPanel9.setMinimumSize(new Dimension(20, 24));
                    contentPanel9.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                    //---- label11 ----
                    label11.setFont(label11.getFont().deriveFont(label11.getFont().getSize() + 3f));
                    label11.setHorizontalAlignment(SwingConstants.CENTER);
                    contentPanel9.add(label11);
                }
                scrollPane1.setViewportView(contentPanel9);
            }
            dialogPane.add(scrollPane1, BorderLayout.EAST);

            //======== contentPanel2 ========
            {
                contentPanel2.setMinimumSize(new Dimension(20, 24));
                contentPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                //---- label4 ----
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getSize() + 3f));
                label4.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel2.add(label4);
            }
            dialogPane.add(contentPanel2, BorderLayout.WEST);

            //======== contentPanel4 ========
            {
                contentPanel4.setMinimumSize(new Dimension(20, 24));
                contentPanel4.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 2));

                //---- label6 ----
                label6.setText(bundle.getString("label6.text"));
                label6.setFont(label6.getFont().deriveFont(label6.getFont().getSize() + 3f));
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                contentPanel4.add(label6);
            }
            dialogPane.add(contentPanel4, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label1;
    private JPanel buttonBar;
    private JPanel contentPanel11;
    private JLabel label13;
    private JPanel panel1;
    private JLabel label2;
    private JTextField textField1;
    private JPanel contentPanel8;
    private JLabel label10;
    private JPanel contentPanel7;
    private JLabel label9;
    private JPanel contentPanel14;
    private JLabel label18;
    private JLabel label3;
    private JTextField textField2;
    private JPanel contentPanel10;
    private JLabel label12;
    private JPanel contentPanel3;
    private JLabel label5;
    private JPanel contentPanel15;
    private JLabel label19;
    private JLabel label14;
    private JTextField textField4;
    private JPanel contentPanel12;
    private JLabel label15;
    private JPanel contentPanel16;
    private JLabel label20;
    private JLabel label17;
    private JTextField textField5;
    private JPanel contentPanel13;
    private JLabel label16;
    private JPanel contentPanel17;
    private JLabel label21;
    private JButton okButton;
    private JPanel contentPanel5;
    private JLabel label7;
    private JButton cancelButton;
    private JScrollPane scrollPane1;
    private JPanel contentPanel9;
    private JLabel label11;
    private JPanel contentPanel2;
    private JLabel label4;
    private JPanel contentPanel4;
    private JLabel label6;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
