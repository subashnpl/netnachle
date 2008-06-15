package gui;

import domain.controller.Controller;
import java.awt.Color;
import javax.swing.JFrame;

public class Settings extends JFrame {
    
    private JFrame _parent;
    private Controller _controller;
    private int _currentBackground;
    
    
    /** Creates new form NewJFrame1 */
    public Settings(JFrame parent, Controller controller, int currentBackground) {
        this._controller = controller;
        this._parent = parent;
        this._currentBackground = currentBackground;
	initComponents();
        this.jComboBoxBackGround.setSelectedIndex(currentBackground);
    }
    @Override
    public void setVisible(boolean b){
        GeneralJFrame.setFrameAtCenter(this);
        super.setVisible(b);
    }    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButtonSaveNClose = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxBackGround = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Settings");
        setBackground(gui.GeneralJFrame.backgroundColor);

        jPanel2.setBackground(GeneralJFrame.backgroundColor);
        jPanel2.setForeground(new java.awt.Color(212, 208, 200));

        jPanel1.setBackground(GeneralJFrame.backgroundColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(GeneralJFrame.headerColor);
        jLabel1.setText("Settings");

        jButtonSaveNClose.setText("Save & Close");
        jButtonSaveNClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveNCloseActionPerformed(evt);
            }
        });

        jLabel7.setForeground(GeneralJFrame.regularFontColor);
        jLabel7.setText("Background Color:");

        jComboBoxBackGround.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Default", "Orange", "LightGray", "Black" }));
        jComboBoxBackGround.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxBackGroundActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabel1)
                .addContainerGap(241, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel7)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 35, Short.MAX_VALUE)
                .add(jComboBoxBackGround, 0, 140, Short.MAX_VALUE)
                .add(51, 51, 51))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(217, Short.MAX_VALUE)
                .add(jButtonSaveNClose)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jComboBoxBackGround, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 309, Short.MAX_VALUE)
                .add(jButtonSaveNClose)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButtonSaveNCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveNCloseActionPerformed
    //setBackgroundColor(new Color(jComboBoxBackGround.getSelectedIndex()));
    
    switch (jComboBoxBackGround.getSelectedIndex()){
        case 0: GeneralJFrame.setBackgroundColor(Color.DARK_GRAY);
                break;
        case 1: GeneralJFrame.setBackgroundColor(Color.ORANGE);
                break;
        case 2: GeneralJFrame.setBackgroundColor(Color.LIGHT_GRAY);
                break;
        case 3: GeneralJFrame.setBackgroundColor(Color.BLACK);
                break;
    }
    ((MainFrame)_parent).rePaintPanels();
    ((MainFrame)_parent).setCurrentBackground(jComboBoxBackGround.getSelectedIndex());
    setVisible(false);
    _parent.setEnabled(true);
    _parent.setVisible(true);
}//GEN-LAST:event_jButtonSaveNCloseActionPerformed

private void jComboBoxBackGroundActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxBackGroundActionPerformed


}//GEN-LAST:event_jComboBoxBackGroundActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveNClose;
    private javax.swing.JComboBox jComboBoxBackGround;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JComboBox getJComboBoxBackGround() {
        return jComboBoxBackGround;
    }

    public javax.swing.JPanel getJPanel1() {
        return jPanel1;
    }
    
}
