package gui;

import exceptions.RateNotAtRangeException;
import domain.Movie;
import controller.Controller;
import java.awt.Color;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
public class MainFrame extends JFrame {
    
    private Controller _controller;
    private Vector<JPanel> _jPanels = new Vector<JPanel>();
    private Vector<JLabel> _jLabelsMovies = new Vector<JLabel>();
    private Vector<JLabel> _jLabelsRates = new Vector<JLabel>();
    private Vector<JLabel> _jLabelsPictures = new Vector<JLabel>();
    private int _currentBackground;
    private MainFrame _this;
    private MovieDetails _movieDetails;
    
    /** Creates new form NewJFrame1 */
    public MainFrame(Controller controller) {
        this._controller = controller;
        this._this = this;
        this._movieDetails = null;
        this._currentBackground = 0;
	initComponents();
        this._jPanels.addElement(jPanel1);
        this._jPanels.addElement(jPanel2);
        this._jPanels.addElement(jPanel3);
        collectLabels();
        setMostRecommendedMovies();
    }
    @Override
    public void setVisible(boolean b){
        GeneralJFrame.setFrameAtCenter(this);
        super.setVisible(b);
    }
    
    void collectLabels(){
        this._jLabelsMovies.addElement(jLabelMovie1);
        this._jLabelsMovies.addElement(jLabelMovie2);
        this._jLabelsMovies.addElement(jLabelMovie3);
        this._jLabelsMovies.addElement(jLabelMovie4);
        this._jLabelsMovies.addElement(jLabelMovie5);
        this._jLabelsMovies.addElement(jLabelMovie6);
        this._jLabelsMovies.addElement(jLabelMovie7);
        this._jLabelsMovies.addElement(jLabelMovie8);
        this._jLabelsMovies.addElement(jLabelMovie9);
        this._jLabelsMovies.addElement(jLabelMovie10);
        this._jLabelsRates.addElement(jLabelRate1);
        this._jLabelsRates.addElement(jLabelRate2);
        this._jLabelsRates.addElement(jLabelRate3);
        this._jLabelsRates.addElement(jLabelRate4);
        this._jLabelsRates.addElement(jLabelRate5);
        this._jLabelsRates.addElement(jLabelRate6);
        this._jLabelsRates.addElement(jLabelRate7);
        this._jLabelsRates.addElement(jLabelRate8);
        this._jLabelsRates.addElement(jLabelRate9);
        this._jLabelsRates.addElement(jLabelRate10);
        this._jLabelsPictures.addElement(jLabelPicture1);
        this._jLabelsPictures.addElement(jLabelPicture2);
        this._jLabelsPictures.addElement(jLabelPicture3);
        this._jLabelsPictures.addElement(jLabelPicture4);
        this._jLabelsPictures.addElement(jLabelPicture5);
        this._jLabelsPictures.addElement(jLabelPicture6);
        this._jLabelsPictures.addElement(jLabelPicture7);
        this._jLabelsPictures.addElement(jLabelPicture8);
        this._jLabelsPictures.addElement(jLabelPicture9);
        this._jLabelsPictures.addElement(jLabelPicture10);
        
        Vector<Movie> tmpMovies = null;
        try {
            tmpMovies = _controller.get_Strategy().getRecomendations(_controller.getCurrentUser().getId())[0];
        } catch (RateNotAtRangeException ex) {
            JOptionPane.showMessageDialog(this, "Rate Not At Range\n" + ex.getMessage(),
                    "Rate", JOptionPane.ERROR_MESSAGE);
        }
        final Vector<Movie> movies = tmpMovies;
        for (int i = 0; i < _jLabelsMovies.size(); i++) {
            final JLabel label = _jLabelsMovies.elementAt(i);
            final int j = i;
            label.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Movie movie = movies.elementAt(j);
                    MovieDetails movieDetails = new MovieDetails(_this, movie);
                    movieDetails.setVisible(true);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    GeneralJFrame.setLinkEntered(label, GeneralJFrame.linkHeaderColor);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    GeneralJFrame.setLinkEntered(label, GeneralJFrame.regularFontColor);
                }
            });
        }
    }
    
    // Next function is for the movies pictures:
    private Icon loadIcon(String path) throws MalformedURLException {
        File tIconFile = new File(path);
        URL tIconUrl;
        if (tIconFile.exists()) {
                tIconUrl = tIconFile.toURL();
        } else {
                // No file - Look in the load path in a jar
                tIconUrl = getClass().getClassLoader().getResource(path);
                if (tIconUrl == null) {
                        throw new MalformedURLException("Could not load icon " + path);
                }
        }
        return new ImageIcon(tIconUrl);
    }    
    
    public void setMostRecommendedMovies(){
        Vector[] _rated = null;
        try {
            _rated = _controller.get_Strategy().getRecomendations(_controller.getCurrentUser().getId());
        } catch (RateNotAtRangeException ex) {
            JOptionPane.showMessageDialog(this, "Rate Not At Range\n" + ex.getMessage(),
                    "Rate", JOptionPane.ERROR_MESSAGE);
        }
        Vector<Movie> movies = _rated[0];
        Vector<Integer> rates = _rated[1];
        
        for (int i=0; i < Math.min(10, movies.size()); i++){
            String movieName = movies.elementAt(i).get_name();
            _jLabelsMovies.elementAt(i).setText(movieName);
            _jLabelsRates.elementAt(i).setText(rates.elementAt(i).toString());
            try {
                 _jLabelsPictures.elementAt(i).setIcon(new ImageIcon(getClass().getResource("/Images/"+ movieName +".jpg")));
            } catch (Exception ex) {
                 // Do nothing. The default picture stays.
            }
        }
        // deleting rest of the movies' labels from screen:
        for (int i=movies.size(); i < 10; i++){
            _jLabelsMovies.elementAt(i).setVisible(false);
            _jLabelsRates.elementAt(i).setVisible(false);
            _jLabelsPictures.elementAt(i).setVisible(false);
        }
    }
    public void rePaintPanels(){
        for (Iterator<JPanel> it = _jPanels.iterator(); it.hasNext();) {
            JPanel jPanel = it.next();
            jPanel.setBackground(GeneralJFrame.backgroundColor);
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
        jLabelHello = new javax.swing.JLabel();
        jLabelUserName = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelPermissionMode = new javax.swing.JLabel();
        jLabelLogOut = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabelMostRecommendedMovies = new javax.swing.JLabel();
        jLabelRate1 = new javax.swing.JLabel();
        jLabelRate3 = new javax.swing.JLabel();
        jLabelPicture1 = new javax.swing.JLabel();
        jLabelPicture2 = new javax.swing.JLabel();
        jLabelMovie3 = new javax.swing.JLabel();
        jLabelMovie4 = new javax.swing.JLabel();
        jLabelMovie1 = new javax.swing.JLabel();
        jLabelRate4 = new javax.swing.JLabel();
        jLabelPicture5 = new javax.swing.JLabel();
        jLabelRate2 = new javax.swing.JLabel();
        jLabelPicture4 = new javax.swing.JLabel();
        jLabelMovie2 = new javax.swing.JLabel();
        jLabelMovie5 = new javax.swing.JLabel();
        jLabelRate5 = new javax.swing.JLabel();
        jLabelPicture3 = new javax.swing.JLabel();
        jLabelPicture10 = new javax.swing.JLabel();
        jLabelPicture9 = new javax.swing.JLabel();
        jLabelPicture8 = new javax.swing.JLabel();
        jLabelPicture7 = new javax.swing.JLabel();
        jLabelPicture6 = new javax.swing.JLabel();
        jLabelMovie8 = new javax.swing.JLabel();
        jLabelMovie7 = new javax.swing.JLabel();
        jLabelMovie10 = new javax.swing.JLabel();
        jLabelMovie6 = new javax.swing.JLabel();
        jLabelMovie9 = new javax.swing.JLabel();
        jLabelRate8 = new javax.swing.JLabel();
        jLabelRate10 = new javax.swing.JLabel();
        jLabelRate7 = new javax.swing.JLabel();
        jLabelRate6 = new javax.swing.JLabel();
        jLabelRate9 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jButtonRateMovies = new javax.swing.JButton();
        jButtonSystemManagement = new javax.swing.JButton();
        jButtonSettings = new javax.swing.JButton();
        jLabelBigHeader = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        jPanel2.setBackground(GeneralJFrame.backgroundColor);
        jPanel2.setForeground(new java.awt.Color(212, 208, 200));

        jPanel1.setBackground(GeneralJFrame.backgroundColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(GeneralJFrame.headersFontColor);

        jLabelHello.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelHello.setForeground(GeneralJFrame.headersFontColor);
        jLabelHello.setText("Hello");

        jLabelUserName.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelUserName.setForeground(GeneralJFrame.headersFontColor);
        jLabelUserName.setText(_controller.getCurrentUser().getName());

        jLabelPermissionMode.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelPermissionMode.setForeground(GeneralJFrame.headersFontColor);
        jLabelPermissionMode.setText(_controller.getCurrentUser().getPermission().toLowerCase());

        jLabelLogOut.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelLogOut.setForeground(GeneralJFrame.headersFontColor);
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

        jPanel3.setBackground(GeneralJFrame.backgroundColor);
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabelMostRecommendedMovies.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelMostRecommendedMovies.setForeground(GeneralJFrame.headersFontColor);
        jLabelMostRecommendedMovies.setText("Most Recommended Movies");

        jLabelRate1.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate1.setText("--");

        jLabelRate3.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate3.setText("--");

        jLabelPicture1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N
        jLabelPicture1.setAutoscrolls(true);

        jLabelPicture2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie3.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie3.setText("-------");

        jLabelMovie4.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie4.setText("-------");

        jLabelMovie1.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie1.setText("-------");
        jLabelMovie1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMovie1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMovie1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMovie1MouseExited(evt);
            }
        });

        jLabelRate4.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate4.setText("--");

        jLabelPicture5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelRate2.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate2.setText("--");

        jLabelPicture4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie2.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie2.setText("-------");

        jLabelMovie5.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie5.setText("-------");

        jLabelRate5.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate5.setText("--");

        jLabelPicture3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelPicture6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie8.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie8.setText("-------");

        jLabelMovie7.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie7.setText("-------");

        jLabelMovie10.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie10.setText("-------");

        jLabelMovie6.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie6.setText("-------");

        jLabelMovie9.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie9.setText("-------");

        jLabelRate8.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate8.setText("--");

        jLabelRate10.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate10.setText("--");

        jLabelRate7.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate7.setText("--");

        jLabelRate6.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate6.setText("--");

        jLabelRate9.setForeground(GeneralJFrame.regularFontColor);
        jLabelRate9.setText("--");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelPicture5)
                            .add(jLabelPicture4)
                            .add(jLabelPicture3)
                            .add(jLabelPicture2)
                            .add(jLabelPicture1))
                        .add(43, 43, 43)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelMovie1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 138, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelMovie3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 56, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jLabelMovie2)
                            .add(jLabelMovie4)
                            .add(jLabelMovie5))
                        .add(27, 27, 27)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelRate4)
                            .add(jLabelRate1)
                            .add(jLabelRate3)
                            .add(jLabelRate2)
                            .add(jLabelRate5))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 196, Short.MAX_VALUE)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelPicture9)
                            .add(jLabelPicture8)
                            .add(jLabelPicture7)
                            .add(jLabelPicture6)
                            .add(jLabelPicture10))
                        .add(43, 43, 43)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelMovie8)
                            .add(jLabelMovie7)
                            .add(jLabelMovie6)
                            .add(jLabelMovie10)
                            .add(jLabelMovie9))
                        .add(46, 46, 46)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabelRate10)
                            .add(jLabelRate9)
                            .add(jLabelRate7)
                            .add(jLabelRate6)
                            .add(jLabelRate8))
                        .add(20, 20, 20))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabelMostRecommendedMovies)
                        .addContainerGap(628, Short.MAX_VALUE))))
        );

        jPanel3Layout.linkSize(new java.awt.Component[] {jLabelMovie1, jLabelMovie2, jLabelMovie3, jLabelMovie4, jLabelMovie5, jLabelMovie6}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelMostRecommendedMovies)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(18, 18, 18)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabelPicture6)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture7)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture8)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture9)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jLabelPicture10))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabelPicture1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture2)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture3)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture4)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelPicture5))
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel3Layout.createSequentialGroup()
                                        .add(20, 20, 20)
                                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                            .add(jLabelRate6)
                                            .add(jLabelMovie6))
                                        .add(47, 47, 47)
                                        .add(jLabelRate7)
                                        .add(53, 53, 53))
                                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                                        .add(jLabelMovie7)
                                        .add(45, 45, 45)))
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelRate8)
                                    .add(jLabelMovie8))
                                .add(52, 52, 52)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel3Layout.createSequentialGroup()
                                        .add(jLabelMovie9)
                                        .add(48, 48, 48)
                                        .add(jLabelMovie10))
                                    .add(jPanel3Layout.createSequentialGroup()
                                        .add(jLabelRate9)
                                        .add(48, 48, 48)
                                        .add(jLabelRate10)))))
                        .addContainerGap(32, Short.MAX_VALUE))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(38, 38, 38)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                            .add(jPanel3Layout.createSequentialGroup()
                                .add(jLabelRate1)
                                .add(236, 236, 236))
                            .add(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelMovie1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelMovie2)
                                    .add(jLabelRate2))
                                .add(45, 45, 45)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelMovie3)
                                    .add(jLabelRate3))
                                .add(51, 51, 51)
                                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                    .add(jLabelMovie4)
                                    .add(jLabelRate4))
                                .add(43, 43, 43)))
                        .add(8, 8, 8)
                        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie5)
                            .add(jLabelRate5))
                        .addContainerGap())))
        );

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jButtonRateMovies.setText("Rate Movies");
        jButtonRateMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRateMoviesActionPerformed(evt);
            }
        });

        if (!_controller.getCurrentUser().getPermission().equalsIgnoreCase("administrator"))
        jButtonSystemManagement.setVisible(false);
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

        jLabelBigHeader.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabelBigHeader.setForeground(GeneralJFrame.headerColor);
        jLabelBigHeader.setText("Recommendation Movies System");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 801, Short.MAX_VALUE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jLabelHello)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelUserName)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 551, Short.MAX_VALUE)
                                .add(jLabelPermissionMode)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jLabelLogOut))
                            .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(257, 257, 257)
                        .add(jLabelBigHeader))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jButtonRateMovies)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonSettings)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jButtonSystemManagement)))
                .addContainerGap())
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(0, 411, Short.MAX_VALUE)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 410, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabelHello)
                        .add(jLabelUserName)
                        .add(jLabelPermissionMode))
                    .add(jLabelLogOut)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabelBigHeader)
                .add(11, 11, 11)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButtonRateMovies)
                    .add(jButtonSettings)
                    .add(jButtonSystemManagement))
                .addContainerGap())
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(jPanel1Layout.createSequentialGroup()
                    .add(0, 301, Short.MAX_VALUE)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(0, 217, Short.MAX_VALUE)))
        );

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
                .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
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

private void jButtonRateMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRateMoviesActionPerformed
    this.setEnabled(false);
    new RateMovies(this, _controller).setVisible(true);
    this.setVisible(false);
}//GEN-LAST:event_jButtonRateMoviesActionPerformed

private void jButtonSystemManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSystemManagementActionPerformed
    this.setEnabled(false);
    new SystemManagement(_controller, this).setVisible(true);
}//GEN-LAST:event_jButtonSystemManagementActionPerformed

private void jButtonSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSettingsActionPerformed
    this.setEnabled(false);
    Settings settings = new Settings(this, _controller, _currentBackground);
    settings.setVisible(true);
}//GEN-LAST:event_jButtonSettingsActionPerformed

private void jLabelLogOutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseClicked
    _controller.logout();
    this.setVisible(false);
    GeneralJFrame.setBackgroundColor(Color.DARK_GRAY);
    Entrance entrance = new Entrance(_controller);
    entrance.setVisible(true);
}//GEN-LAST:event_jLabelLogOutMouseClicked

private void jLabelLogOutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseEntered
    GeneralJFrame.setLinkEntered(jLabelLogOut, GeneralJFrame.linkHeaderColor);
}//GEN-LAST:event_jLabelLogOutMouseEntered

private void jLabelLogOutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelLogOutMouseExited
    GeneralJFrame.setLinkExited(jLabelLogOut, GeneralJFrame.headersFontColor);
}//GEN-LAST:event_jLabelLogOutMouseExited

private void jLabelMovie1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMovie1MouseEntered
    GeneralJFrame.setLinkEntered(jLabelMovie1, GeneralJFrame.linkHeaderColor);
}//GEN-LAST:event_jLabelMovie1MouseEntered

private void jLabelMovie1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMovie1MouseExited
    GeneralJFrame.setLinkExited(jLabelMovie1, GeneralJFrame.regularFontColor);
}//GEN-LAST:event_jLabelMovie1MouseExited

private void jLabelMovie1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMovie1MouseClicked
    
}//GEN-LAST:event_jLabelMovie1MouseClicked
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonRateMovies;
    private javax.swing.JButton jButtonSettings;
    private javax.swing.JButton jButtonSystemManagement;
    private javax.swing.JLabel jLabelBigHeader;
    private javax.swing.JLabel jLabelHello;
    private javax.swing.JLabel jLabelLogOut;
    private javax.swing.JLabel jLabelMostRecommendedMovies;
    private javax.swing.JLabel jLabelMovie1;
    private javax.swing.JLabel jLabelMovie10;
    private javax.swing.JLabel jLabelMovie2;
    private javax.swing.JLabel jLabelMovie3;
    private javax.swing.JLabel jLabelMovie4;
    private javax.swing.JLabel jLabelMovie5;
    private javax.swing.JLabel jLabelMovie6;
    private javax.swing.JLabel jLabelMovie7;
    private javax.swing.JLabel jLabelMovie8;
    private javax.swing.JLabel jLabelMovie9;
    private javax.swing.JLabel jLabelPermissionMode;
    private javax.swing.JLabel jLabelPicture1;
    private javax.swing.JLabel jLabelPicture10;
    private javax.swing.JLabel jLabelPicture2;
    private javax.swing.JLabel jLabelPicture3;
    private javax.swing.JLabel jLabelPicture4;
    private javax.swing.JLabel jLabelPicture5;
    private javax.swing.JLabel jLabelPicture6;
    private javax.swing.JLabel jLabelPicture7;
    private javax.swing.JLabel jLabelPicture8;
    private javax.swing.JLabel jLabelPicture9;
    private javax.swing.JLabel jLabelRate1;
    private javax.swing.JLabel jLabelRate10;
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

    public int getCurrentBackground() {
        return _currentBackground;
    }

    public void setCurrentBackground(int currentBackground) {
        this._currentBackground = currentBackground;
    }
}
