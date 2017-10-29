/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.gui.handler.ParentStateHandler;
import bdfcybertech.util.Workers;
import bdfcybertech.gui.handler.SettingsHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author MUSTAFA
 */
public class SysConfigDialog extends JDialog{

    private JDialog parent;

    private Workers work;
    private SettingsHandler set;
    private JLabel ipLbl;
    private JTextField ipText;
    private JPanel outterPanel;
    private JButton saveBtn;
    private JLabel sysNameLbl;
    private JTextField sysNameText;

    private Thread d;
    private ParentStateHandler stateParent;
    private JDialog self = this;
    private Action saveA;
    
    public SysConfigDialog(JDialog in){
        parent = in;
        set = new SettingsHandler(self);
        MyLookAndFeel.setLook();
        init();
    }

    private void init(){
        
        stateParent =  new ParentStateHandler(parent, self);
        
        setTitle("| SETUP Administrator");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(372, 171);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((x.width - 372) / 2, (x.height - 171) / 2);

        //
        work = new Workers(parent);
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
        myComponents();
        stateParent.startCheck();
        setVisible(true);
        saveActions();
    }

    public void myComponents(){

        outterPanel = new JPanel();
        sysNameLbl = new JLabel();
        sysNameText = new JTextField(set.getSysName());
        saveBtn = new JButton();
        ipLbl = new JLabel();
        ipText = new JTextField(set.getServerIp());

        outterPanel.setBackground(new Color(6, 161, 225));
        outterPanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(166, 170, 254), 8), BorderFactory.createLineBorder(new Color(0, 51, 51), 4)));

        sysNameLbl.setText("System Name  :");

        sysNameText.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

        saveBtn.setLabel("Save Settings");

        ipLbl.setText("Server IP / Name  :");

        ipText.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(sysNameLbl, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sysNameText, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                    .addGroup(GroupLayout.Alignment.TRAILING, outterPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ipLbl, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(ipText, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE))
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outterPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sysNameText, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(sysNameLbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(ipText, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(ipLbl, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(saveBtn, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        outterPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {sysNameLbl, sysNameText});

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(outterPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

        private void saveActions(){
        saveA = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                work.doSaveSettings(self, sysNameText.getText(), ipText.getText());
                //self.dispose();
            }
        };
        saveA.putValue(Action.NAME, "Save Settings");
        saveA.putValue(Action.SHORT_DESCRIPTION, "Enter Serve IP & System Unique name and Enter");
        saveA.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/button_t.png")));
        saveBtn.setAction(saveA);
        ipText.setAction(saveA);
        sysNameText.setAction(saveA);
    }

}
