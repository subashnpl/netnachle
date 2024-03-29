package gui;

import controller.Controller;
import domain.Movie;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.util.Stack;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class RateMovies extends JFrame {
    
    private JFrame _parent;
    private JFrame _this;
    private Controller _controller;
    private String[][] _movies2rate;
    private Vector<Movie> _movies2RateShai;
    private Stack<Integer[][]>  _rateForward;
    private Stack<Integer[][]>  _rateBackword;
    private Vector<JLabel> _jLabelsMovies     = new Vector<JLabel>();
    private Vector<JLabel> _jLabelsDirectors  = new Vector<JLabel>();
    private Vector<JLabel> _jLabelsPictures   = new Vector<JLabel>();
    private Vector<JComboBox> jComboBoxsRates= new Vector<JComboBox>();

    public RateMovies(JFrame parent, Controller controller) {
        this._this = this;
        _rateBackword=new Stack<Integer[][]>();
        _rateForward=new Stack<Integer[][]>();
        this._parent = parent;
        this._controller = controller;
	initComponents();
        collectLabels();
        this.setMoviesNdirectors2Select("f");
    }
    
    void collectLabels(){
        _jLabelsMovies.addElement(jLabelMovie1);
        _jLabelsMovies.addElement(jLabelMovie2);
        _jLabelsMovies.addElement(jLabelMovie3);
        _jLabelsMovies.addElement(jLabelMovie4);
        _jLabelsMovies.addElement(jLabelMovie5);
        _jLabelsMovies.addElement(jLabelMovie6);
        _jLabelsMovies.addElement(jLabelMovie7);
        _jLabelsDirectors.addElement(jLabelDirector1);
        _jLabelsDirectors.addElement(jLabelDirector2);
        _jLabelsDirectors.addElement(jLabelDirector3);
        _jLabelsDirectors.addElement(jLabelDirector4);
        _jLabelsDirectors.addElement(jLabelDirector5);
        _jLabelsDirectors.addElement(jLabelDirector6);
        _jLabelsDirectors.addElement(jLabelDirector7);
        _jLabelsPictures.addElement(jLabelPicture1);
        _jLabelsPictures.addElement(jLabelPicture2);
        _jLabelsPictures.addElement(jLabelPicture3);
        _jLabelsPictures.addElement(jLabelPicture4);
        _jLabelsPictures.addElement(jLabelPicture5);
        _jLabelsPictures.addElement(jLabelPicture6);
        _jLabelsPictures.addElement(jLabelPicture7);
        jComboBoxsRates.addElement(jComboBoxRate1);
        jComboBoxsRates.addElement(jComboBoxRate2);
        jComboBoxsRates.addElement(jComboBoxRate3);
        jComboBoxsRates.addElement(jComboBoxRate4);
        jComboBoxsRates.addElement(jComboBoxRate5);
        jComboBoxsRates.addElement(jComboBoxRate6);
        jComboBoxsRates.addElement(jComboBoxRate7);
        
    }
    
    private void makeLinksToDetails(){
        // Set maximum size for all labels:
        for (int i = 0; i < _jLabelsMovies.size(); i++) {
            _jLabelsMovies.elementAt(i).setMaximumSize(new Dimension(9, 14));
            _jLabelsDirectors.elementAt(i).setMaximumSize(new Dimension(9, 14));
            jComboBoxsRates.elementAt(i).setMaximumSize(new Dimension(9, 14));
        }
        final Vector<Movie> movies = this._movies2RateShai;
        for (int i = 0; i < _jLabelsMovies.size(); i++) {
            final JLabel labelMovieName = _jLabelsMovies.elementAt(i);
            JLabel labelDirector = _jLabelsDirectors.elementAt(i);
            
            final int j = i;
            //System.out.println("length: "+labelMovieName.getListeners(MouseListener.class).length);
            //System.out.println("before removing:");
            for (int k = 0; k < labelMovieName.getListeners(MouseListener.class).length; k++) {
                labelMovieName.removeMouseListener(labelMovieName.getListeners(MouseListener.class)[k]);
            }
            labelDirector.setToolTipText(movies.elementAt(i).get_director());
            labelMovieName.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    Movie movie = movies.elementAt(j);      
                    MovieDetails movieDetails = new MovieDetails(_this, movie);
                    movieDetails.setVisible(true);
                }
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    GeneralJFrame.setLinkEntered(labelMovieName, GeneralJFrame.linkHeaderColor);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    GeneralJFrame.setLinkEntered(labelMovieName, GeneralJFrame.regularFontColor);
                }
            });
        }
    }
    
    public void setVisible(boolean b){
        GeneralJFrame.setFrameAtCenter(this);
        super.setVisible(b);
    }    
    
    private void setMoviesNdirectors2Select(String for_back){
        
        //Important _controller.getMoviesToRate return { {movName , movDirctor , movId}, ..... and so on }
        int rateASindex = -1;
        //_movies2rate = this._controller.getMoviesToRate(for_back);
        this._movies2RateShai = this._controller.getMoviesToRateShai(for_back);
        makeLinksToDetails();
        for (int i=0; i<7; i++){
            Movie movie = _movies2RateShai.elementAt(i);
            _jLabelsMovies.elementAt(i).setText(movie.get_name());
            _jLabelsDirectors.elementAt(i).setText(movie.get_director());
            try {
                _jLabelsPictures.elementAt(i).setIcon(new ImageIcon(getClass().getResource("/Images/" + movie.get_name() + ".jpg")));
            } catch (Exception e) {
                _jLabelsPictures.elementAt(i).setIcon(new ImageIcon(getClass().getResource("/Images/NetNachleVerySmall_narrow.jpg")));
            }
            rateASindex = _controller.userSaw(new Integer(movie.get_id()));// will return 0 if haven't seen
            jComboBoxsRates.elementAt(i).setSelectedIndex(rateASindex);
            
            
            /*
            //String movieName = movies.elementAt(i).get_name();
            String movieName = _movies2rate[i][0];
            System.out.println("movieName: "+movieName);
            _jLabelsMovies.elementAt(i).setText(_movies2rate[i][0]);
            _jLabelsDirectors.elementAt(i).setText(_movies2rate[i][1]);
            try {
                _jLabelsPictures.elementAt(i).setIcon(new ImageIcon(getClass().getResource("/Images/" + movieName + ".jpg")));
            } catch (Exception e) {
                System.out.println("bla");
            }
            rateASindex = _controller.userSaw(new Integer(_movies2rate[i][2]));// will return 0 if haven't seen
            jComboBoxsRates.elementAt(i).setSelectedIndex(rateASindex);
            */
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
        jLabelRateMoviesTitle = new javax.swing.JLabel();
        jLabelMovieName = new javax.swing.JLabel();
        jButtonNextStep = new javax.swing.JButton();
        jComboBoxRate1 = new javax.swing.JComboBox();
        jLabelMovieName1 = new javax.swing.JLabel();
        jLabelMovieName2 = new javax.swing.JLabel();
        jLabelMovie1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelDirector1 = new javax.swing.JLabel();
        jLabelPicture1 = new javax.swing.JLabel();
        jLabelMovie2 = new javax.swing.JLabel();
        jLabelDirector2 = new javax.swing.JLabel();
        jComboBoxRate2 = new javax.swing.JComboBox();
        jLabelPicture2 = new javax.swing.JLabel();
        jLabelMovie3 = new javax.swing.JLabel();
        jLabelDirector3 = new javax.swing.JLabel();
        jComboBoxRate3 = new javax.swing.JComboBox();
        jLabelPicture3 = new javax.swing.JLabel();
        jLabelMovie4 = new javax.swing.JLabel();
        jLabelDirector4 = new javax.swing.JLabel();
        jComboBoxRate4 = new javax.swing.JComboBox();
        jLabelPicture4 = new javax.swing.JLabel();
        jLabelMovie5 = new javax.swing.JLabel();
        jLabelDirector5 = new javax.swing.JLabel();
        jComboBoxRate5 = new javax.swing.JComboBox();
        jLabelPicture5 = new javax.swing.JLabel();
        jLabelMovie6 = new javax.swing.JLabel();
        jLabelDirector6 = new javax.swing.JLabel();
        jComboBoxRate6 = new javax.swing.JComboBox();
        jLabelPicture6 = new javax.swing.JLabel();
        jLabelMovie7 = new javax.swing.JLabel();
        jLabelDirector7 = new javax.swing.JLabel();
        jComboBoxRate7 = new javax.swing.JComboBox();
        jLabelPicture7 = new javax.swing.JLabel();
        jButtonPreviousStep = new javax.swing.JButton();
        jButtonFinish = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setBackground(gui.GeneralJFrame.backgroundColor);

        jPanel2.setBackground(GeneralJFrame.backgroundColor);
        jPanel2.setForeground(new java.awt.Color(212, 208, 200));

        jPanel1.setBackground(GeneralJFrame.backgroundColor);
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setForeground(GeneralJFrame.headersFontColor);

        jLabelRateMoviesTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        jLabelRateMoviesTitle.setForeground(GeneralJFrame.headerColor);
        jLabelRateMoviesTitle.setText("Rate Movies");

        jLabelMovieName.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelMovieName.setForeground(GeneralJFrame.headersFontColor);
        jLabelMovieName.setText("Movie's Name");

        jButtonNextStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AT_next.gif"))); // NOI18N
        jButtonNextStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNextStepActionPerformed(evt);
            }
        });

        jComboBoxRate1.setMaximumRowCount(10);
        jComboBoxRate1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelMovieName1.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelMovieName1.setForeground(GeneralJFrame.headersFontColor);
        jLabelMovieName1.setText("Director's Name");

        jLabelMovieName2.setFont(new java.awt.Font("Tahoma", 1, 11));
        jLabelMovieName2.setForeground(GeneralJFrame.headersFontColor);
        jLabelMovieName2.setText("Rate");

        jLabelMovie1.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie1.setText("Shrek1");
        jLabelMovie1.setMaximumSize(new java.awt.Dimension(10, 14));

        jLabelDirector1.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector1.setText("Mel Gibson");

        jLabelPicture1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie2.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie2.setText("Shrek1");

        jLabelDirector2.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector2.setText("Mel Gibson");

        jComboBoxRate2.setMaximumRowCount(10);
        jComboBoxRate2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelPicture2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie3.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie3.setText("Shrek1");

        jLabelDirector3.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector3.setText("Mel Gibson");

        jComboBoxRate3.setMaximumRowCount(10);
        jComboBoxRate3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelPicture3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie4.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie4.setText("Shrek1");

        jLabelDirector4.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector4.setText("Mel Gibson");

        jComboBoxRate4.setMaximumRowCount(10);
        jComboBoxRate4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelPicture4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie5.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie5.setText("Shrek1");

        jLabelDirector5.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector5.setText("Mel Gibson");

        jComboBoxRate5.setMaximumRowCount(10);
        jComboBoxRate5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelPicture5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie6.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie6.setText("Shrek1");

        jLabelDirector6.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector6.setText("Mel Gibson");

        jComboBoxRate6.setMaximumRowCount(10);
        jComboBoxRate6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));

        jLabelPicture6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jLabelMovie7.setForeground(GeneralJFrame.regularFontColor);
        jLabelMovie7.setText("Shrek1");

        jLabelDirector7.setForeground(GeneralJFrame.regularFontColor);
        jLabelDirector7.setText("Mel Gibson");

        jComboBoxRate7.setMaximumRowCount(10);
        jComboBoxRate7.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "haven't seen", "1", "2", "3", "4", "5", "6", "7", "8", "9" }));
        jComboBoxRate7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxRate7ActionPerformed(evt);
            }
        });

        jLabelPicture7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/NetNachleVerySmall.JPG"))); // NOI18N

        jButtonPreviousStep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/AT_back.gif"))); // NOI18N
        jButtonPreviousStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreviousStepActionPerformed(evt);
            }
        });

        jButtonFinish.setText("Press To Finish");
        jButtonFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFinishActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jLabelRateMoviesTitle)
                .addContainerGap(491, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelMovieName)
                .add(68, 68, 68)
                .add(jLabelMovieName1)
                .add(154, 154, 154)
                .add(jLabelMovieName2)
                .add(165, 165, 165))
            .add(jPanel1Layout.createSequentialGroup()
                .add(21, 21, 21)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelDirector1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 105, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelDirector2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelDirector3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 116, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(jLabelDirector4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabelDirector5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 128, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelDirector6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabelMovie7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jLabelDirector7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
                .add(40, 40, 40)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jComboBoxRate7, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate2, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate1, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate3, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate4, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate5, 0, 100, Short.MAX_VALUE)
                    .add(jComboBoxRate6, 0, 100, Short.MAX_VALUE))
                .add(98, 98, 98)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture7)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture6)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture5)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture4)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture1)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture2)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabelPicture3))
                .add(35, 35, 35))
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButtonPreviousStep)
                .add(141, 141, 141)
                .add(jButtonFinish)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 156, Short.MAX_VALUE)
                .add(jButtonNextStep, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(new java.awt.Component[] {jLabelDirector1, jLabelDirector2}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabelRateMoviesTitle)
                .add(18, 18, 18)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabelMovieName1)
                    .add(jLabelMovieName)
                    .add(jLabelMovieName2))
                .add(5, 5, 5)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(27, 27, 27)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelDirector1)
                            .add(jLabelMovie1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 41, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jComboBoxRate1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabelPicture1)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie2)
                            .add(jLabelDirector2)
                            .add(jComboBoxRate2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie3)
                            .add(jLabelDirector3)
                            .add(jComboBoxRate3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture3))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie4)
                            .add(jLabelDirector4)
                            .add(jComboBoxRate4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture4))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie5)
                            .add(jLabelDirector5)
                            .add(jComboBoxRate5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture5))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie6)
                            .add(jLabelDirector6)
                            .add(jComboBoxRate6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture6))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(21, 21, 21)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabelMovie7)
                            .add(jLabelDirector7)
                            .add(jComboBoxRate7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabelPicture7))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 75, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButtonPreviousStep)
                    .add(jButtonNextStep)
                    .add(jButtonFinish))
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

private void jButtonNextStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNextStepActionPerformed
    writeDown7Selection();
    setMoviesNdirectors2Select("f");
}//GEN-LAST:event_jButtonNextStepActionPerformed

private void jButtonPreviousStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreviousStepActionPerformed
    writeDown7Selection();
    setMoviesNdirectors2Select("b");
}//GEN-LAST:event_jButtonPreviousStepActionPerformed
/*
 * write down current 7 on screen selections to _controller
 **/
private void writeDown7Selection(){//oz1
    Integer[]  moviesId=new Integer[7];
    Integer[] rates = new Integer[7];
    /*
    moviesId[0] = new Integer(_movies2rate[0][2]);
    rates[0] = jComboBoxRate1.getSelectedIndex();
    moviesId[1] = new Integer(_movies2rate[1][2]);
    rates[1] = jComboBoxRate2.getSelectedIndex();
    moviesId[2] = new Integer(_movies2rate[2][2]);
    rates[2] = jComboBoxRate3.getSelectedIndex();
    moviesId[3] = new Integer(_movies2rate[3][2]);
    rates[3] = jComboBoxRate4.getSelectedIndex();
    moviesId[4] = new Integer(_movies2rate[4][2]);
    rates[4] = jComboBoxRate5.getSelectedIndex();
    moviesId[5] = new Integer(_movies2rate[5][2]);
    rates[5] = jComboBoxRate6.getSelectedIndex();
    moviesId[6] = new Integer(_movies2rate[6][2]);
    rates[6] = jComboBoxRate7.getSelectedIndex();
    */
    
    for (int i = 0; i < jComboBoxsRates.size(); i++) {      // shaig
        moviesId[i] = new Integer(_movies2RateShai.elementAt(i).get_id());
        rates[i] = jComboBoxsRates.elementAt(i).getSelectedIndex();
    }
        
    Integer[][]  movie_reates=new Integer[2][];
    movie_reates[0] = moviesId;
    movie_reates[1] =rates;
    _rateForward.push(movie_reates);
    while(!_rateForward.isEmpty()){
        _rateBackword.push(_rateForward.pop());
    }
  
    Stack<Integer> movIdStk=new Stack<Integer>();
    Stack<Integer>  rateStk=new Stack<Integer>();
     
    while (!_rateBackword.isEmpty()){
        movie_reates=_rateBackword.pop();
        moviesId=movie_reates[0];
        rates=movie_reates[1];
        for (int i=0;i<7;i++){
       //  if (!(rates[i].equals(new Integer(0)))) {
                movIdStk.push(moviesId[i]);
                rateStk.push(rates[i]);
          //  } //if
        }//for
    }//while
    int[] movId=new int[movIdStk.size()];
    int[] rate=new int[rateStk.size()];
    for (int i=0;i<movId.length;i++){
        movId[i]=movIdStk.pop().intValue();
        rate[i]=rateStk.pop().intValue();
    }
    _controller.setRatesByUser(movId, rate, _controller.getCurrentUser().getId());
} 
private void jButtonFinishActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFinishActionPerformed
    writeDown7Selection();
    if (_controller.lowRateUser()){
        JOptionPane.showMessageDialog(this, "Dear user, this is not a hore house please rate movies",
                "RATING ERROR", JOptionPane.ERROR_MESSAGE);
    } else {
        this.setVisible(false);
        MainFrame Mf = new MainFrame(_controller);
        Mf.setVisible(true);
    }
}//GEN-LAST:event_jButtonFinishActionPerformed

private void jComboBoxRate7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxRate7ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jComboBoxRate7ActionPerformed
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFinish;
    private javax.swing.JButton jButtonNextStep;
    private javax.swing.JButton jButtonPreviousStep;
    private javax.swing.JComboBox jComboBoxRate1;
    private javax.swing.JComboBox jComboBoxRate2;
    private javax.swing.JComboBox jComboBoxRate3;
    private javax.swing.JComboBox jComboBoxRate4;
    private javax.swing.JComboBox jComboBoxRate5;
    private javax.swing.JComboBox jComboBoxRate6;
    private javax.swing.JComboBox jComboBoxRate7;
    private javax.swing.JLabel jLabelDirector1;
    private javax.swing.JLabel jLabelDirector2;
    private javax.swing.JLabel jLabelDirector3;
    private javax.swing.JLabel jLabelDirector4;
    private javax.swing.JLabel jLabelDirector5;
    private javax.swing.JLabel jLabelDirector6;
    private javax.swing.JLabel jLabelDirector7;
    private javax.swing.JLabel jLabelMovie1;
    private javax.swing.JLabel jLabelMovie2;
    private javax.swing.JLabel jLabelMovie3;
    private javax.swing.JLabel jLabelMovie4;
    private javax.swing.JLabel jLabelMovie5;
    private javax.swing.JLabel jLabelMovie6;
    private javax.swing.JLabel jLabelMovie7;
    private javax.swing.JLabel jLabelMovieName;
    private javax.swing.JLabel jLabelMovieName1;
    private javax.swing.JLabel jLabelMovieName2;
    private javax.swing.JLabel jLabelPicture1;
    private javax.swing.JLabel jLabelPicture2;
    private javax.swing.JLabel jLabelPicture3;
    private javax.swing.JLabel jLabelPicture4;
    private javax.swing.JLabel jLabelPicture5;
    private javax.swing.JLabel jLabelPicture6;
    private javax.swing.JLabel jLabelPicture7;
    private javax.swing.JLabel jLabelRateMoviesTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
    
}
