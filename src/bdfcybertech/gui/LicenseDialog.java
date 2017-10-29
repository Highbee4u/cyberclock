/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bdfcybertech.gui;
import bdfcybertech.gui.handler.ParentStateHandler;
import bdfcybertech.util.SettingsHandler;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 *
 * @author MUSTAFA
 */
public class LicenseDialog extends JDialog{

    private Action accessA;
    private JDialog parent;
    private JButton accessBtn;
    private JLabel licenseLbl;
    private JTextField licenseText;
    private JPanel outterPanel;
    private JLabel conStatus;

    private Thread d;
    private JDialog self = this;
    private ParentStateHandler stateParent;
    
    public LicenseDialog(JDialog in){
        parent = in;
        init();
    }

    private void init(){
        //MyLookAndFeel.setLook();
        stateParent =  new ParentStateHandler(parent, self);
        setTitle("| Enter LICENSE KEY to Change Settings");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
        setSize(312, 113);
        Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((x.width - 312) / 2, (x.height - 113) / 2);
        myComponents();
        d = new Thread(new Runnable(){
            public void run(){
                stateParent.doStateHandler();
            }
        });
        try{
            d.start();
            Thread.sleep(100);
        }
        catch(InterruptedException er){
            System.err.println(er.getMessage());
        }

        stateParent.startCheck();

        setVisible(true);
        accessActions();
    }

    private void accessActions(){
        accessA = new AbstractAction(){
            public void actionPerformed(ActionEvent e){
                SwingUtilities.invokeLater(new Runnable(){
                    public void run(){
                        new SettingsHandler(parent, licenseText.getText());
                    }
                });                
                dispose();
            }
        };
        accessA.putValue(Action.NAME, "Access Settings");
        accessA.putValue(Action.SHORT_DESCRIPTION, "Enter Product Key Provided by developer to CONFIGURE settings");
        accessBtn.setAction(accessA);
        licenseText.setAction(accessA);
    }


    private void myComponents(){

        outterPanel = new JPanel();
        accessBtn = new JButton();
        licenseLbl = new JLabel();
        conStatus = new JLabel();
        licenseText = new JTextField();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        outterPanel.setBackground(new Color(115, 200, 235));
        outterPanel.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));
        outterPanel.setVerifyInputWhenFocusTarget(false);

        accessBtn.setIcon(new ImageIcon(getClass().getResource("/bdfcybertech/gui/images/moved.gif"))); // NOI18N
        accessBtn.setText("Access Settings");

        licenseLbl.setFont(new Font("Serif", 0, 10));
        licenseLbl.setHorizontalAlignment(SwingConstants.CENTER);

        conStatus.setFont(new Font("Serif", 0, 10));
        conStatus.setHorizontalAlignment(SwingConstants.CENTER);

        licenseText.setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255), 2));

        GroupLayout outterPanelLayout = new GroupLayout(outterPanel);
        outterPanel.setLayout(outterPanelLayout);
        outterPanelLayout.setHorizontalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(licenseText, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
                            .addGroup(outterPanelLayout.createSequentialGroup()
                                .addComponent(licenseLbl, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))))
                    .addGroup(outterPanelLayout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(accessBtn, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        outterPanelLayout.setVerticalGroup(
            outterPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(outterPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(licenseText, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(accessBtn, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                .addGap(150, 150, 150)
                .addGroup(outterPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(licenseLbl, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
                    .addComponent(conStatus, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        outterPanelLayout.linkSize(SwingConstants.VERTICAL, new Component[] {conStatus, licenseLbl});

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
