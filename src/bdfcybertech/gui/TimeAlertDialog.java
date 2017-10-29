/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.util.Timing;
import bdfcybertech.util.TrayApp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author MUSTAFA
 */
public class TimeAlertDialog extends JDialog{

    private JButton addTimeBtn;
    private JButton hideBtn;
    private JButton showBtn;
    private JLabel minRemLbl;
    private JLabel mnutesLbl;
    private JPanel outterPanel;
    private JLabel questionLbl;

    private int timeRem;
    private int ticketID; //user

    //private JDialog self = this;

    private Timer timeHandler;
    private Timing theTime;
    private TimerDialog time;
    private TrayApp tray;
    private Thread tr;
    
    public TimeAlertDialog(TrayApp tr, Timing tim, TimerDialog ti, int tick, int t){
        theTime = tim;
        timeRem = t;
        ticketID = tick;
        time = ti;
        tray = tr;

        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        setFocusable(false);
        setSize(303, 182);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((x.width - 303) , (x.height - 182));
        MyLookAndFeel.setLook();
        myComponents();
        actionsList();

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
               timeHandler.start();
            }
        });
    }

    private synchronized void addTime(){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new AddTicketDialog(time, timeRem, ticketID);
            }
        });
    }

    private void myComponents(){
        outterPanel = new JPanel();
        hideBtn = new JButton();
        mnutesLbl = new JLabel();
        addTimeBtn = new JButton();
        minRemLbl = new JLabel();
        questionLbl = new JLabel();
        showBtn = new JButton();

        outterPanel.setBackground(new Color(6, 161, 225));
        outterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(166, 170, 254), 8), BorderFactory.createLineBorder(new Color(0, 51, 51), 4)));

        hideBtn.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        hideBtn.setText("Hide");

        mnutesLbl.setFont(new Font("Tahoma", 1, 26)); // NOI18N
        mnutesLbl.setForeground(new Color(255, 255, 255));
        mnutesLbl.setHorizontalAlignment(SwingConstants.CENTER);
        mnutesLbl.setText(String.valueOf(timeRem));
        mnutesLbl.setHorizontalTextPosition(SwingConstants.CENTER);


        addTimeBtn.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        addTimeBtn.setText("Add Time");

        minRemLbl.setFont(new Font("Segoe UI Semibold", 0, 11)); // NOI18N
        minRemLbl.setHorizontalAlignment(SwingConstants.CENTER);
        minRemLbl.setText("Minute(s) Remaining");

        questionLbl.setFont(new Font("Tahoma", 1, 11)); // NOI18N
        questionLbl.setForeground(new Color(90, 62, 30));
        questionLbl.setHorizontalAlignment(SwingConstants.CENTER);
        questionLbl.setText("Hey .. | what do you want to do next?");
        questionLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        showBtn.setFont(new Font("Tahoma", 0, 10)); // NOI18N
        showBtn.setText("About");

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addGroup(GroupLayout.Alignment.LEADING, outterPanelLayout.createSequentialGroup()
                        .addComponent(addTimeBtn, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hideBtn, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showBtn, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.LEADING, outterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                        .addComponent(questionLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minRemLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(mnutesLbl, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        outterPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {addTimeBtn, hideBtn, showBtn});

        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(mnutesLbl, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(minRemLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(questionLbl)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addTimeBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(hideBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                    .addComponent(showBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
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

        addTimeBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tray.removeTray();
                addTime();
                dispose();
            }
        });
        
        timeHandler = new Timer(3000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                timeRem = theTime.getRemTime();
                mnutesLbl.setText(String.valueOf(timeRem));
                if(timeRem <= 5){
                    mnutesLbl.setForeground(Color.red);
                }
                else{
                    mnutesLbl.setForeground(Color.WHITE);
                }
                mnutesLbl.updateUI();
            }
        });

        showBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        //new TimerDialog(us, timeRem);
                    }
                });
                
            }
        });
    }

    public void displayAlert(){
        setVisible(true);
    }

    public void disposeAlert(){
        dispose();
    }
}
