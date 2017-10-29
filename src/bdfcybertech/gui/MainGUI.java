/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.util.*;
import bdfcybertech.gui.handler.SettingsHandler;
import bdfcybertech.network.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author MUSTAFA
 */
public class MainGUI extends JDialog{

    private JDialog self = this;
    private MainGUI main = this;
    
    private ImagePanel outterPanel;
    private JButton loginBtn;
    private JPasswordField pinField;
    private JButton settingsBtn;
    private JLabel worldLbl;
    private JLabel sysNameLbl;
    private JLabel conStatus;

    private SettingsHandler set;
    
    private Workers worker;
    //
    private HandleRMI rmi;
    private RMIdatabaseConfig remoteConfig;
    
    public MainGUI(){
        MyLookAndFeel.setLook();
        init();
    }

    public void showMe(){
        setVisible(true);
        setFocusable(true);
    }
    
    private void init(){
        
        set = new SettingsHandler(self);
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setAlwaysOnTop(true);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(x.width, x.height);

        worker = new Workers(main, self, rmi);
        
        //try to make RMI start
        try{
            rmi = new HandleRMI(main, worker);
            try{
                rmi.setHost(InetAddress.getLocalHost().getHostAddress().toString());
                rmi.connectionBind();
                //System.out.println("Connected to (LOGIN) : " + InetAddress.getLocalHost().getHostAddress().toString());
                //if RMI has started .. then, try saving sysname and its IP address to database
                remoteConfig = new RMIdatabaseConfig(set.getSysName(), InetAddress.getLocalHost().getHostAddress().toString());
                remoteConfig.configureRemoteAccess();
            }
            catch(UnknownHostException h){
                System.err.println("Error trying to get the IP (LOGIN) : " + h.getMessage());
            }
        }
        catch(RemoteException re){
            System.err.println("Error when trying to start REMOTE server (LOGIN) : " + re.getMessage());
        }
        myComponents();
        
        

        connectivityCheck();

        loginBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                loginBtn.setEnabled(false);
                worker.doLoginWork(pinField, loginBtn);
            }
        });
        pinField.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(main, "Use the mouse to click the 'LOGIN' Button",
                        "SORRY | ALERT !", JOptionPane.PLAIN_MESSAGE);
                //loginBtn.setEnabled(false);
                //worker.doLoginWork(pinField, loginBtn);
            }
        });

        settingsBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        new LicenseDialog(self);
                    }
                });
            }
        });
    }

    private void connectivityCheck(){
        worker.doNetworkCheck(conStatus);
    }


    private void myComponents() {

        outterPanel = new ImagePanel(new ImageIcon(getClass().getResource("images/cyber_home_bg.gif")).getImage());
        worldLbl = new JLabel();
        loginBtn = new JButton();
        settingsBtn = new JButton();
        pinField = new JPasswordField();
        sysNameLbl = new JLabel(set.getSysName());
        conStatus = new JLabel();

        getContentPane().setLayout(new GridBagLayout());

        outterPanel.setBackground(new Color(115, 200, 235));
        outterPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        outterPanel.setPreferredSize(new Dimension(912, 432));
        outterPanel.setVerifyInputWhenFocusTarget(false);

        worldLbl.setHorizontalAlignment(SwingConstants.CENTER);
        worldLbl.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/misc.gif"))); // NOI18N
        worldLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        loginBtn.setBackground(new Color(0, 0, 187));
        loginBtn.setForeground(new Color(255, 255, 255));
        loginBtn.setText("Login");
        //loginBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 136, 0), 2));
        loginBtn.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/moved.gif"))); // NOI18N

        settingsBtn.setBackground(new Color(0, 0, 187));
        settingsBtn.setForeground(new Color(255, 255, 255));
        settingsBtn.setText("CFG");
        //settingsBtn.setBorder(BorderFactory.createLineBorder(new Color(0, 136, 0), 2));

        pinField.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

        sysNameLbl.setFont(new Font("Serif", 0, 10)); // NOI18N
        sysNameLbl.setHorizontalAlignment(SwingConstants.CENTER);
        sysNameLbl.setHorizontalTextPosition(SwingConstants.CENTER);

        conStatus.setFont(new Font("Serif", 0, 10)); // NOI18N
        conStatus.setHorizontalAlignment(SwingConstants.CENTER);
        conStatus.setHorizontalTextPosition(SwingConstants.CENTER);
        conStatus.setText("Checking Connectivity ...");
        conStatus.setForeground(Color.red);

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 220, GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 278, Short.MAX_VALUE)
                        .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(pinField, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addGroup(outterPanelLayout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
                            .addComponent(sysNameLbl, GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(worldLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(338, 338, 338))))
        );

        outterPanelLayout.linkSize(SwingConstants.HORIZONTAL, new Component[] {pinField, sysNameLbl});

        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(worldLbl, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sysNameLbl, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(settingsBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addComponent(pinField, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21)
                        .addComponent(loginBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        
        getContentPane().add(outterPanel, new GridBagConstraints());
        /*
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 906, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outterPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
         * */

        pack();
    }

 
}