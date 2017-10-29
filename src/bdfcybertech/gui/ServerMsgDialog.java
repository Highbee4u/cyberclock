/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

/**
 *
 * @author MUSTAFA
 */
public class ServerMsgDialog extends JDialog{

    private JButton hideBtn;
    private JButton logOutBtn;
    private JTextArea msgArea;
    private JPanel outterPanel;
    private JLabel serverLbl;

    public ServerMsgDialog(){
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(303, 216);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((x.width - 303) , (x.height - 216));
        myComponents();
        setVisible(true);
        actionsList();
    }

    private void myComponents(){
        outterPanel = new JPanel();
        hideBtn = new JButton();
        serverLbl = new JLabel();
        logOutBtn = new JButton();
        msgArea = new JTextArea();

        outterPanel.setBackground(new Color(6, 161, 225));
        outterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(166, 170, 254), 8), BorderFactory.createLineBorder(new Color(0, 51, 51), 4)));

        hideBtn.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        hideBtn.setText("Hide");

        serverLbl.setFont(new Font("Tahoma", 1, 14)); // NOI18N
        serverLbl.setForeground(new Color(255, 255, 255));
        serverLbl.setHorizontalAlignment(SwingConstants.CENTER);
        serverLbl.setText("SERVER MESSAGE");
        serverLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        logOutBtn.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        logOutBtn.setText("Log Out");

        msgArea.setColumns(20);
        msgArea.setFont(new Font("GungsuhChe", 1, 14)); // NOI18N
        msgArea.setLineWrap(true);
        msgArea.setRows(5);
        msgArea.setWrapStyleWord(true);
        msgArea.setAutoscrolls(false);
        msgArea.setBorder(BorderFactory.createCompoundBorder(new LineBorder(new Color(166, 170, 254), 3, true), new LineBorder(new Color(255, 255, 255), 2, true)));
        //msgArea.getAccessibleContext().setAccessibleParent(null);
        msgArea.setEditable(false);

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(serverLbl, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addComponent(hideBtn, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(logOutBtn, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
                    .addComponent(msgArea, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE))
                .addContainerGap())
        );
        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(serverLbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(msgArea, javax.swing.GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(hideBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    private void actionsList(){
        hideBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });
    }

    public static void main(String []args){
        new ServerMsgDialog();
    }
}
