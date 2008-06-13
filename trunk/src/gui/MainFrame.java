package gui;

import domain.Movie;
import domain.controller.Controller;
import java.util.Iterator;
import java.util.Vector;
public class MainFrame extends GeneralJFrame {
    
    private Controller _controller;
    private Vector[] _rated;
    
    /** Creates new form NewJFrame1 */
    public MainFrame(Controller controller) {
        this._controller = controller;
        setFrameAtCenter(this.getWidth(), this.getHeight());
	initComponents();
        setMostRecommendedMovies();
    }
    
    private void setMostRecommendedMovies(){
         _rated = _controller.get_Strategy().get10Recomendations(_controller.getCurrentUser().getId());
         Vector<Movie> movies = _rated[0];
         Vector<Integer> rates = _rated[1];

         if (movies.size() > 0){
             this.jLabelMovie11.setText(movies.elementAt(0).get_name());
             this.jLabelRate11.setText(rates.elementAt(0).toString());
         }
         if (movies.size() > 1){
             this.jLabelMovie12.setText(movies.elementAt(1).get_name());
             this.jLabelRate12.setText(rates.elementAt(1).toString());
         }
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
        jPanel3 = new javax.swing.JPanel();
        jLabelPicture8 = new javax.swing.JLabel();
        jLabelPicture9 = new javax.swing.JLabel();
        jLabelPicture10 = new javax.swing.JLabel();
        jLabelPicture11 = new javax.swing.JLabel();
        jLabelPicture12 = new javax.swing.JLabel();
        jLabelPicture13 = new javax.swing.JLabel();
        jLabelPicture14 = new javax.swing.JLabel();
        jLabelPermission2 = new javax.swing.JLabel();
        jLabelMovie3 = new javax.swing.JLabel();
        jLabelMovie2 = new javax.swing.JLabel();
        jLabelMovie5 = new javax.swing.JLabel();
        jLabelMovie1 = new javax.swing.JLabel();
        jLabelMovie4 = new javax.swing.JLabel();
        jLabelMovie6 = new javax.swing.JLabel();
        jLabelMovie7 = new javax.swing.JLabel();
        jLabelRate7 = new javax.swing.JLabel();
        jLabelRate3 = new javax.swing.JLabel();
        jLabelRate5 = new javax.swing.JLabel();
        jLabelRate2 = new javax.swing.JLabel();
        jLabelRate1 = new javax.swing.JLabel();
        jLabelRate4 = new javax.swing.JLabel();
        jLabelRate6 = new javax.swing.JLabel();
        jLabelHello = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelPermissionMode = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabelPermission3 = new javax.swing.JLabel();
        jLabelRate11 = new javax.swing.JLabel();
        jLabelRate8 = new javax.swing.JLabel();
        jLabelRate9 = new javax.swing.JLabel();
        jLabelPicture21 = new javax.swing.JLabel();
        jLabelRate14 = new javax.swing.JLabel();
        jLabelPicture20 = new javax.swing.JLabel();
        jLabelMovie8 = new javax.swing.JLabel();
        jLabelMovie13 = new javax.swing.JLabel();
        jLabelMovie12 = new javax.swing.JLabel();
        jLabelMovie11 = new javax.swing.JLabel();
        jLabelRate13 = new javax.swing.JLabel();
        jLabelPicture17 = new javax.swing.JLabel();
        jLabelRate12 = new javax.swing.JLabel();
        jLabelPicture16 = new javax.swing.JLabel();
        jLabelPicture18 = new javax.swing.JLabel();
        jLabelMovie9 = new javax.swing.JLabel();
        jLabelMovie10 = new javax.swing.JLabel();
        jLabelRate10 = new javax.swing.JLabel();
        jLabelPicture15 = new javax.swing.JLabel();
        jLabelPicture19 = new javax.swing.JLabel();
        jLabelMovie14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonRateMovies = new javax.swing.JButton();
        jButtonSystemManagement = new javax.swing.JButton();
        jButtonSettings = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBackground(backgroundColor);
        jPanel2.setForeground(new java.awt.Color(212, 208, 200));

        jPanel1.setBackground(backgroundColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(headersFontColor);

        jPanel3.setBackground(backgroundColor);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelPicture8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPermission2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelPermission2.setForeground(headersFontColor);
        jLabelPermission2.setText("New Movies");

        jLabelMovie3.setForeground(regularFontColor);
        jLabelMovie3.setText("Shrek1");

        jLabelMovie2.setForeground(regularFontColor);
        jLabelMovie2.setText("Shrek1");

        jLabelMovie5.setForeground(regularFontColor);
        jLabelMovie5.setText("Shrek1");

        jLabelMovie1.setForeground(regularFontColor);
        jLabelMovie1.setText("Shrek1");

        jLabelMovie4.setForeground(regularFontColor);
        jLabelMovie4.setText("Shrek1");

        jLabelMovie6.setForeground(regularFontColor);
        jLabelMovie6.setText("Shrek1");

        jLabelMovie7.setForeground(regularFontColor);
        jLabelMovie7.setText("Shrek1");

        jLabelRate7.setForeground(regularFontColor);
        jLabelRate7.setText("1");

        jLabelRate3.setForeground(regularFontColor);
        jLabelRate3.setText("1");

        jLabelRate5.setForeground(regularFontColor);
        jLabelRate5.setText("1");

        jLabelRate2.setForeground(regularFontColor);
        jLabelRate2.setText("1");

        jLabelRate1.setForeground(regularFontColor);
        jLabelRate1.setText("1");

        jLabelRate4.setForeground(regularFontColor);
        jLabelRate4.setText("1");

        jLabelRate6.setForeground(regularFontColor);
        jLabelRate6.setText("1");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 408, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabelPicture8)
                        .add(jLabelPicture9)
                        .add(jLabelPicture10)
                        .add(jLabelPicture11)
                        .add(jLabelPicture12)
                        .add(jLabelPicture13)
                        .add(jLabelPicture14)
                        .add(jLabelPermission2))
                    .add(28, 28, 28)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabelMovie3)
                        .add(jLabelMovie2)
                        .add(jLabelMovie5)
                        .add(jLabelMovie1)
                        .add(jLabelMovie4)
                        .add(jLabelMovie6)
                        .add(jLabelMovie7))
                    .add(46, 46, 46)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jLabelRate7)
                        .add(jLabelRate3)
                        .add(jLabelRate5)
                        .add(jLabelRate2)
                        .add(jLabelRate1)
                        .add(jLabelRate4)
                        .add(jLabelRate6))
                    .addContainerGap(219, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 508, Short.MAX_VALUE)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(jPanel3Layout.createSequentialGroup()
                            .add(jLabelPermission2)
                            .add(18, 18, 18)
                            .add(jLabelPicture14)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture13)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture12)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture11)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture10)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture9)
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                            .add(jLabelPicture8))
                        .add(jPanel3Layout.createSequentialGroup()
                            .add(52, 52, 52)
                            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                                .add(jPanel3Layout.createSequentialGroup()
                                    .add(jLabelMovie1)
                                    .add(47, 47, 47)
                                    .add(jLabelMovie2)
                                    .add(43, 43, 43)
                                    .add(jLabelMovie3)
                                    .add(44, 44, 44)
                                    .add(jLabelMovie4)
                                    .add(46, 46, 46)
                                    .add(jLabelMovie5)
                                    .add(42, 42, 42)
                                    .add(jLabelMovie6)
                                    .add(46, 46, 46)
                                    .add(jLabelMovie7))
                                .add(jPanel3Layout.createSequentialGroup()
                                    .add(jLabelRate1)
                                    .add(47, 47, 47)
                                    .add(jLabelRate2)
                                    .add(43, 43, 43)
                                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelRate7)
                                        .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                                            .add(jLabelRate3)
                                            .add(44, 44, 44)
                                            .add(jLabelRate4)
                                            .add(46, 46, 46)
                                            .add(jLabelRate5)
                                            .add(42, 42, 42)
                                            .add(jLabelRate6)
                                            .add(60, 60, 60)))))))
                    .addContainerGap(58, Short.MAX_VALUE)))
        );

        jLabelHello.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelHello.setForeground(headersFontColor);
        jLabelHello.setText("Hello");

        jLabelUserName.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelUserName.setForeground(headersFontColor);
        jLabelUserName.setText(_controller.getCurrentUser().getName());

        jLabelPermissionMode.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabelPermissionMode.setForeground(headersFontColor);
        jLabelPermissionMode.setText(_controller.getCurrentUser().getPermission().toLowerCase());

        jLabelLogOut.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelLogOut.setForeground(headersFontColor);
        jLabelLogOut.setText("Log Out");
        jLabelLogOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelLogOutMouseExited(evt);
            }
        });

        jPanel4.setBackground(backgroundColor);
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelPermission3.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelPermission3.setForeground(headersFontColor);
        jLabelPermission3.setText("Most Recommended Movies");

        jLabelRate11.setForeground(regularFontColor);
        jLabelRate11.setText("1");

        jLabelRate8.setForeground(regularFontColor);
        jLabelRate8.setText("1");

        jLabelRate9.setForeground(regularFontColor);
        jLabelRate9.setText("1");

        jLabelPicture21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelRate14.setForeground(regularFontColor);
        jLabelRate14.setText("1");

        jLabelPicture20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie8.setForeground(regularFontColor);
        jLabelMovie8.setText("Shrek1");

        jLabelMovie13.setForeground(regularFontColor);
        jLabelMovie13.setText("Shrek1");

        jLabelMovie12.setForeground(regularFontColor);
        jLabelMovie12.setText("Shrek1");

        jLabelMovie11.setForeground(regularFontColor);
        jLabelMovie11.setText("Shrek1");

        jLabelRate13.setForeground(regularFontColor);
        jLabelRate13.setText("1");

        jLabelPicture17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelRate12.setForeground(regularFontColor);
        jLabelRate12.setText("1");

        jLabelPicture16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie9.setForeground(regularFontColor);
        jLabelMovie9.setText("Shrek1");

        jLabelMovie10.setForeground(regularFontColor);
        jLabelMovie10.setText("Shrek1");

        jLabelRate10.setForeground(regularFontColor);
        jLabelRate10.setText("1");

        jLabelPicture15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie14.setForeground(regularFontColor);
        jLabelMovie14.setText("Shrek1");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabelPermission3)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelPicture15)
                            .add(jLabelPicture16)
                            .add(jLabelPicture17)
                            .add(jLabelPicture18)
                            .add(jLabelPicture19)
                            .add(jLabelPicture20)
                            .add(jLabelPicture21))
                        .add(43, 43, 43)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelMovie8)
                            .add(jLabelMovie9)
                            .add(jLabelMovie10)
                            .add(jLabelMovie11)
                            .add(jLabelMovie12)
                            .add(jLabelMovie13)
                            .add(jLabelMovie14))
                        .add(46, 46, 46)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelRate8)
                            .add(jLabelRate9)
                            .add(jLabelRate10)
                            .add(jLabelRate12)
                            .add(jLabelRate11)
                            .add(jLabelRate13)
                            .add(jLabelRate14))))
                .addContainerGap(214, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelPermission3)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jLabelPicture21)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture20)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture19)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture18)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture17)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture16)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture15))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jLabelMovie11)
                                .add(47, 47, 47)
                                .add(jLabelMovie9)
                                .add(43, 43, 43)
                                .add(jLabelMovie8)
                                .add(44, 44, 44)
                                .add(jLabelMovie12)
                                .add(46, 46, 46)
                                .add(jLabelMovie10)
                                .add(42, 42, 42)
                                .add(jLabelMovie13)
                                .add(46, 46, 46)
                                .add(jLabelMovie14))
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jLabelRate11)
                                .add(47, 47, 47)
                                .add(jLabelRate12)
                                .add(43, 43, 43)
                                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelRate8)
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .add(jLabelRate9)
                                        .add(44, 44, 44)
                                        .add(jLabelRate13)
                                        .add(46, 46, 46)
                                        .add(jLabelRate10)
                                        .add(42, 42, 42)
                                        .add(jLabelRate14)
                                        .add(60, 60, 60)))))))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonRateMovies.setText("Rate Movies");
        jButtonRateMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRateMoviesActionPerformed(evt);
            }
        });

        jButtonSystemManagement.setText("System Management");
        jButtonSystemManagement.setEnabled(_controller.getCurrentUser().getPermission().equalsIgnoreCase("Administrator"));
        jButtonSystemManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSystemManagementActionPerformed(evt);
            }
        });

        jButtonSettings.setText("Settings");
        jButtonSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSettingsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabel1.setForeground(headerColor);
        jLabel1.setText("Recommendation Movies System");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 865, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabelHello)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelUserName)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 615, Short.MAX_VALUE)
                                .add(jLabelPermissionMode)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelLogOut))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jButtonRateMovies)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonSettings)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jButtonSystemManagement))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(18, 18, 18)
                                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(164, 164, 164))))
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(0, 442, Short.MAX_VALUE)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 443, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jPanel3, jPanel4}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabelHello)
                        .add(jLabelUserName)
                        .add(jLabelLogOut)
                        .add(jLabelPermissionMode))
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .add(24, 24, 24)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(37, 37, 37)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jButtonRateMovies)
                            .add(jButtonSettings)
                            .add(jButtonSystemManagement)))
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(0, 342, Short.MAX_VALUE)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 343, Short.MAX_VALUE)))
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jPanel3, jPanel4}, org.jdesktop.layout.GroupLayout.VERTICAL);

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButtonRateMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRateMoviesActionPerformed
    this.setEnabled(false);
    new RateMovies(this, _controller).setVisible(true);
}//GEN-LAST:event_jButtonRateMoviesActionPerformed

private void jButtonSystemManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemManagementActionPerformed
    new SystemManagement(_controller).setVisible(true);
}//GEN-LAST:event_jButtonSystemManagementActionPerformed

private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
    new Settings(this, _controller).setVisible(true);
}//GEN-LAST:event_jButtonSettingsActionPerformed

private void jLabelLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseClicked
    _controller.shutDown();
    System.exit(1);// TODO add your handling code here:
}//GEN-LAST:event_jLabelLogOutMouseClicked

private void jLabelLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseEntered
    setLinkEntered(jLabelLogOut, linkHeaderColor);// TODO add your handling code here:
}//GEN-LAST:event_jLabelLogOutMouseEntered

private void jLabelLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseExited
    setLinkExited(jLabelLogOut, this.headersFontColor);// TODO add your handling code here:
}//GEN-LAST:event_jLabelLogOutMouseExited
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRateMovies;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JButton jButtonSystemManagement;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelHello;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelMovie1;
    private javax.swing.JLabel jLabelMovie10;
    private javax.swing.JLabel jLabelMovie11;
    private javax.swing.JLabel jLabelMovie12;
    private javax.swing.JLabel jLabelMovie13;
    private javax.swing.JLabel jLabelMovie14;
    private javax.swing.JLabel jLabelMovie2;
    private javax.swing.JLabel jLabelMovie3;
    private javax.swing.JLabel jLabelMovie4;
    private javax.swing.JLabel jLabelMovie5;
    private javax.swing.JLabel jLabelMovie6;
    private javax.swing.JLabel jLabelMovie7;
    private javax.swing.JLabel jLabelMovie8;
    private javax.swing.JLabel jLabelMovie9;
    private javax.swing.JLabel jLabelPermission2;
    private javax.swing.JLabel jLabelPermission3;
    private javax.swing.JLabel jLabelPermissionMode;
    private javax.swing.JLabel jLabelPicture10;
    private javax.swing.JLabel jLabelPicture11;
    private javax.swing.JLabel jLabelPicture12;
    private javax.swing.JLabel jLabelPicture13;
    private javax.swing.JLabel jLabelPicture14;
    private javax.swing.JLabel jLabelPicture15;
    private javax.swing.JLabel jLabelPicture16;
    private javax.swing.JLabel jLabelPicture17;
    private javax.swing.JLabel jLabelPicture18;
    private javax.swing.JLabel jLabelPicture19;
    private javax.swing.JLabel jLabelPicture20;
    private javax.swing.JLabel jLabelPicture21;
    private javax.swing.JLabel jLabelPicture8;
    private javax.swing.JLabel jLabelPicture9;
    private javax.swing.JLabel jLabelRate1;
    private javax.swing.JLabel jLabelRate10;
    private javax.swing.JLabel jLabelRate11;
    private javax.swing.JLabel jLabelRate12;
    private javax.swing.JLabel jLabelRate13;
    private javax.swing.JLabel jLabelRate14;
    private javax.swing.JLabel jLabelRate2;
    private javax.swing.JLabel jLabelRate3;
    private javax.swing.JLabel jLabelRate4;
    private javax.swing.JLabel jLabelRate5;
    private javax.swing.JLabel jLabelRate6;
    private javax.swing.JLabel jLabelRate7;
    private javax.swing.JLabel jLabelRate8;
    private javax.swing.JLabel jLabelRate9;
    private javax.swing.JLabel jLabelUserName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    // End of variables declaration//GEN-END:variables
    //private Color backgroundColor = Color.DARK_GRAY;
    //private Color headerColor = new Color(255,51,51);
    //private Color regularFontColor = Color.LIGHT_GRAY;
    //private Color headersFontColor = Color.WHITE;

    public javax.swing.JPanel getJPanel1() {
        return jPanel1;
    }

    public javax.swing.JPanel getJPanel2() {
        return jPanel2;
    }

    public javax.swing.JPanel getJPanel3() {
        return jPanel3;
    }

    public javax.swing.JPanel getJPanel4() {
        return jPanel4;
    }
    
}
