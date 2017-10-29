/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.ticket.Ticket;
import bdfcybertech.ticket.AddTicket;
import bdfcybertech.util.Workers;
import bdfcybertech.gui.handler.ParentStateHandler;
import bdfcybertech.network.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author MUSTAFA
 */
public class TimePreviewDialog extends JDialog{
    
    private MainGUI main;
    private TimerDialog time;
    private JDialog parent;
    private JButton cancelBtn;
    private JButton continueBtn;
    private JLabel expireLbl;
    private JLabel minRemLbl;
    private JLabel mnutesLbl;
    private JPanel outterPanel;

    private Thread d;
    private ParentStateHandler stateParent;
    private JDialog self = this;

    private int minutesVal = 0;
    private int ticketID;

    private Ticket ticket;
    private Workers work;
    private AddTicket addTicket;
    private String minsRem = "Minutes Remaining";
    private String expire = "Ticket Expires in 0 Day(s)";

    private HandleRMI rmi;

    public TimePreviewDialog(MainGUI in, JDialog d, int t, int min, HandleRMI h){
        parent = d;
        main = in;
        minutesVal = min;
        ticketID = t;
        ticket = new Ticket();
        rmi = h;
        //MyLookAndFeel.setLook();
        init();
        actionsList();
        stateParent.startCheck();
    }

    public TimePreviewDialog(Workers w, TimerDialog in, JDialog d, int t, int min, String m, String ex){
        parent = d;
        time = in;
        minutesVal = min;
        ticketID = t;
        minsRem = m;
        expire = ex;
        work = w;
        addTicket = work.getAdd();
        //MyLookAndFeel.setLook();
        init();
        addTicketActions();
    }
    
    private void init(){
        
        stateParent =  new ParentStateHandler(parent, self);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(342, 182);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((x.width - 342) / 2, (x.height - 182) / 2);
        myComponents();
        d = new Thread(new Runnable(){
            public void run(){
                stateParent.doStateHandler();
            }
        });
        try{
            d.start();
            Thread.sleep(1000);
        }
        catch(InterruptedException er){
            System.err.println(er.getMessage());
        }
        setVisible(true);
    }

    private void myComponents(){

        outterPanel = new JPanel();
        cancelBtn = new JButton();
        mnutesLbl = new JLabel();
        continueBtn = new JButton();
        minRemLbl = new JLabel();
        expireLbl = new JLabel();

        outterPanel.setBackground(new Color(6, 161, 225));
        outterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(166, 170, 254), 8), BorderFactory.createLineBorder(new Color(0, 51, 51), 4)));

        cancelBtn.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/date.png"))); // NOI18N
        cancelBtn.setText("Cancel");

        mnutesLbl.setFont(new Font("Tahoma", 1, 26)); // NOI18N
        mnutesLbl.setForeground(new Color(255, 255, 255));
        mnutesLbl.setHorizontalAlignment(SwingConstants.CENTER);
        mnutesLbl.setText(String.valueOf(minutesVal));
        mnutesLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        continueBtn.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/date.png"))); // NOI18N
        continueBtn.setText("Continue");

        minRemLbl.setFont(new Font("Segoe UI Semibold", 0, 11)); // NOI18N
        minRemLbl.setHorizontalAlignment(SwingConstants.CENTER);
        minRemLbl.setText(minsRem);

        expireLbl.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        expireLbl.setForeground(new Color(90, 62, 30));
        expireLbl.setHorizontalAlignment(SwingConstants.CENTER);
        expireLbl.setText(expire);
        expireLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(expireLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                    .addComponent(minRemLbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                    .addComponent(mnutesLbl, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 298, GroupLayout.PREFERRED_SIZE)
                    .addGroup(GroupLayout.Alignment.LEADING, outterPanelLayout.createSequentialGroup()
                        .addComponent(continueBtn, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        outterPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {expireLbl, minRemLbl, mnutesLbl});

        outterPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {cancelBtn, continueBtn});

        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mnutesLbl, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minRemLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(expireLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(continueBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
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
        continueBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        new TimerDialog(main, parent, ticketID, minutesVal, rmi);
                        //then make changes to database
                        Thread t = new Thread(new Runnable(){
                            public void run(){
                                ticket.ticketLogin(ticketID, minutesVal);
                            }
                        });
                        t.start();
                    }
                });
                
                parent.dispose();
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                ticket.tempCancel(ticketID);
                dispose();
            }
        });
    }

    private void addTicketActions(){
        continueBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        //then , add ticket
                        addTicket.ticketContinue();
                        time.setNewTime(minutesVal, ticketID);
                        time.reShow();
                    }
                });

                parent.dispose();
                dispose();
            }
        });

        cancelBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                addTicket.tempCancel(ticketID);
                parent.dispose();
                dispose();
            }
        });
    }

}
