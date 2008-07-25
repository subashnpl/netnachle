package gui;

import domain.User;
import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SignUp extends JFrame {
    private Controller _controller;
    private JFrame _parent;
    private String _sex;

    public SignUp(Controller controller, JFrame parent) {
        this._controller = controller;
        this._parent = parent;
	initComponents();
        validateFields();
    }
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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldUserName = new javax.swing.JTextField();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jButtonNextStep = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBoxLocation = new javax.swing.JComboBox();
        jComboBoxGender = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldLastName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabelID = new javax.swing.JLabel();
        jButtonCancel = new javax.swing.JButton();
        jFormattedTextFieldId = new javax.swing.JFormattedTextField();
        jLabelMustBe9Digits = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(gui.GeneralJFrame.backgroundColor);

        jPanel2.setBackground(GeneralJFrame.backgroundColor);
        jPanel2.setForeground(new java.awt.Color(212, 208, 200));

        jPanel1.setBackground(GeneralJFrame.backgroundColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(GeneralJFrame.headerColor);
        jLabel1.setText("SignUp");

        jLabel3.setForeground(GeneralJFrame.regularFontColor);
        jLabel3.setText("Choose Username: *");

        jLabel4.setForeground(GeneralJFrame.regularFontColor);
        jLabel4.setText("Choose Password: *");

        jTextFieldUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUserNameActionPerformed(evt);
            }
        });
        jTextFieldUserName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldUserNameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldUserNameKeyReleased(evt);
            }
        });

        jPasswordFieldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldPasswordActionPerformed(evt);
            }
        });
        jPasswordFieldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jPasswordFieldPasswordKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldPasswordKeyReleased(evt);
            }
        });

        jButtonNextStep.setText("Next Step");
        jButtonNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextStepActionPerformed(evt);
            }
        });

        jLabel6.setForeground(GeneralJFrame.regularFontColor);
        jLabel6.setText("Gender:");

        jLabel7.setForeground(GeneralJFrame.regularFontColor);
        jLabel7.setText("Location:");

        jComboBoxLocation.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Israel", "Turkey", "Irak", "USA", "Russia" }));

        jComboBoxGender.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Male", "Female", "Lesbo", "Gay (like Ofir)" }));
        jComboBoxGender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxGenderActionPerformed(evt);
            }
        });

        jLabel8.setForeground(GeneralJFrame.regularFontColor);
        jLabel8.setText("Last Name:");

        jTextFieldLastName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldLastNameActionPerformed(evt);
            }
        });

        jLabel9.setForeground(GeneralJFrame.regularFontColor);
        jLabel9.setText("First Name:");

        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });

        jLabel2.setForeground(GeneralJFrame.regularFontColor);
        jLabel2.setText("Let's rate some movies...");

        jLabelID.setForeground(GeneralJFrame.regularFontColor);
        jLabelID.setText("Enter Your ID: *");

        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelActionPerformed(evt);
            }
        });

        try {
            jFormattedTextFieldId.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jFormattedTextFieldId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldIdKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldIdKeyReleased(evt);
            }
        });

        jLabelMustBe9Digits.setForeground(GeneralJFrame.regularFontColor);
        jLabelMustBe9Digits.setText("(Must be 9 digits)");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(10, 10, 10)
                        .add(jLabel1))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButtonCancel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 232, Short.MAX_VALUE)
                        .add(jButtonNextStep))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jLabel2)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jLabel3)
                                    .add(jLabel4)
                                    .add(jLabel9)
                                    .add(jLabel8)
                                    .add(jLabel7)
                                    .add(jLabel6)
                                    .add(jLabelID))
                                .add(51, 51, 51)
                                .add(jLabelMustBe9Digits)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                    .add(jComboBoxGender, 0, 138, Short.MAX_VALUE)
                                    .add(jComboBoxLocation, 0, 138, Short.MAX_VALUE)
                                    .add(jFormattedTextFieldId, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .add(jTextFieldUserName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .add(jPasswordFieldPassword, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .add(jTextFieldFirstName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
                                    .add(jTextFieldLastName, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel3)
                    .add(jTextFieldUserName))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jPasswordFieldPassword))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelID)
                    .add(jFormattedTextFieldId, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabelMustBe9Digits))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE, false)
                    .add(jLabel9)
                    .add(jTextFieldFirstName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel8)
                    .add(jTextFieldLastName, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jComboBoxLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jComboBoxGender, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(69, 69, 69)
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonNextStep)
                    .add(jButtonCancel))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jTextFieldFirstName, jTextFieldLastName}, org.jdesktop.layout.GroupLayout.VERTICAL);

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
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUserNameActionPerformed

}//GEN-LAST:event_jTextFieldUserNameActionPerformed

private void jPasswordFieldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordActionPerformed

}//GEN-LAST:event_jPasswordFieldPasswordActionPerformed

private void jComboBoxGenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxGenderActionPerformed

}//GEN-LAST:event_jComboBoxGenderActionPerformed


private void jButtonNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextStepActionPerformed
    String _name = this.jTextFieldUserName.getText();
    boolean idValid = this.jFormattedTextFieldId.isEditValid();
    char[] passwordChars1 = this.jPasswordFieldPassword.getPassword(); 
    String _password = new String(passwordChars1);
    if (_name.equals("") ||
            !idValid ||
            _password.equals("")){
        JOptionPane.showMessageDialog(this, "Please enter all details Lesbo",
                    "", JOptionPane.ERROR_MESSAGE);
    } else{
        int _id = Integer.parseInt(this.jFormattedTextFieldId.getText());
        if (_controller.isUserExist(_id)){
            JOptionPane.showMessageDialog(this, "This ID already exists.\nPlease contact administrator at net-nachle@googlegroups.com",
                    "ID Already Exits", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String _secret = null;
            try {
                _secret = new String(GeneralJFrame.encrypt(_password));
            } catch (Exception ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
            _sex = this.jComboBoxGender.getSelectedItem().toString();
            sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
            String encoded=encoder.encode(_secret.getBytes());
            User tuser = new  User( encoded, "user", _id, _name, _sex);
            _controller.addUser(tuser);
            _controller.setCurrentUser(tuser);
            exit();
            this.setVisible(false);
            new RateMovies(this, _controller).setVisible(true);
        }
    }
}//GEN-LAST:event_jButtonNextStepActionPerformed
private void validateFields(){
    int id = getIdAsInteger();
    if ((!this.jTextFieldUserName.getText().equals("")) &&
            (this.jPasswordFieldPassword.getPassword().length != 0) &&
            (this.jFormattedTextFieldId.isEditValid()) &&
            (id >= 0) && (id <= 999999999)){
        enableNextStep(true);
    } else{
        enableNextStep(false);
    }
}

private int getIdAsInteger(){
    int id = -1;
    if (this.jPasswordFieldPassword.getPassword().length != 0){
        try{
            id = Integer.parseInt(this.jFormattedTextFieldId.getText());
        } catch (NumberFormatException e) {}
    }
    return id;
}

private void enableNextStep(boolean b){
    this.jButtonNextStep.setEnabled(b);
}
private void exit(){
    this.setEnabled(false);
    this.setVisible(false);
    this._parent.setEnabled(true);
    this._parent.setVisible(true);
}
private void jTextFieldLastNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldLastNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldLastNameActionPerformed

private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jTextFieldFirstNameActionPerformed

private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelActionPerformed
    exit();
}//GEN-LAST:event_jButtonCancelActionPerformed

private void jTextFieldUserNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUserNameKeyPressed

}//GEN-LAST:event_jTextFieldUserNameKeyPressed

private void jPasswordFieldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordKeyPressed
    validateFields();
}//GEN-LAST:event_jPasswordFieldPasswordKeyPressed

private void jFormattedTextFieldIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldIdKeyPressed
    
}//GEN-LAST:event_jFormattedTextFieldIdKeyPressed

private void jTextFieldUserNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldUserNameKeyReleased
    validateFields();
}//GEN-LAST:event_jTextFieldUserNameKeyReleased

private void jPasswordFieldPasswordKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldPasswordKeyReleased
    validateFields();
}//GEN-LAST:event_jPasswordFieldPasswordKeyReleased

private void jFormattedTextFieldIdKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldIdKeyReleased
    validateFields();
}//GEN-LAST:event_jFormattedTextFieldIdKeyReleased
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonNextStep;
    private javax.swing.JComboBox jComboBoxGender;
    private javax.swing.JComboBox jComboBoxLocation;
    private javax.swing.JFormattedTextField jFormattedTextFieldId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelID;
    private javax.swing.JLabel jLabelMustBe9Digits;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldLastName;
    private javax.swing.JTextField jTextFieldUserName;
    // End of variables declaration//GEN-END:variables
    
}
