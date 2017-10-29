/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.util.Workers;
import bdfcybertech.gui.handler.ButtonStateHandler;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author MUSTAFA
 */
public class AddTicketDialog extends JDialog{

    private JButton addBtn;
    private JLabel conStatus;
    private JPanel outterPanel;
    private JPasswordField pinField;
    private JLabel sysNameLbl;
    private int currTime;
    private int currID;

    private JDialog self = this;

    private ButtonStateHandler btnH;
    private Thread btnT;
    private TimerDialog time;

    private Workers worker;

    private Dimension d;

    public AddTicketDialog(){
        init();
    }
    
    public AddTicketDialog(TimerDialog t, int c, int id){
        currTime = c;
        time = t;
        currID = id;

        btnT = new Thread(new Runnable(){
            public void run(){
                btnH = new ButtonStateHandler(time, self);
                btnH.doStateHandler();
            }
        });
        try{
            btnT.start();
            Thread.sleep(100);
        }
        catch(InterruptedException er){
            System.err.println(er.getMessage());
        }
        MyLookAndFeel.setLook();
        
        init();
        
        btnH.startCheck();

    }

    private void init(){
        d = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("BDF Cyber Tech | Enter new PIN to add time");
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(312, 113);
        setLocation((d.width - 312) / 2 , (d.height - 113) /  2);
        myComponents();

        setVisible(true);

        addBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addBtn.setEnabled(false);
                worker = new Workers(time, self);
                worker.doAddingTime(pinField, addBtn, currTime, currID);
            }
        });

        pinField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(self, "Use the mouse to click the 'ADD' Button",
                        "SORRY | ALERT !", JOptionPane.PLAIN_MESSAGE);
                //addBtn.setEnabled(false);
                //worker = new Workers(time, self);
                //worker.doAddingTime(pinField, addBtn, currTime, currID);
            }
        });
    }

    private void myComponents(){
        
        outterPanel = new JPanel();
        addBtn = new JButton();
        sysNameLbl = new JLabel();
        conStatus = new JLabel();
        pinField = new JPasswordField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        outterPanel.setBackground(new Color(115, 200, 235));
        outterPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        outterPanel.setVerifyInputWhenFocusTarget(false);

        addBtn.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/moved.gif"))); // NOI18N
        addBtn.setText("Add Ticket");

        sysNameLbl.setFont(new Font("Serif", 0, 10));
        sysNameLbl.setHorizontalAlignment(SwingConstants.CENTER);

        conStatus.setFont(new Font("Serif", 0, 10));
        conStatus.setHorizontalAlignment(SwingConstants.CENTER);

        pinField.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(pinField, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                            .addGroup(outterPanelLayout.createSequentialGroup()
                                .addComponent(sysNameLbl, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pinField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(sysNameLbl, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        outterPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {conStatus, sysNameLbl});

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.PREFERRED_SIZE, 312, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

}
