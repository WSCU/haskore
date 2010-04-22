/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * gui.java
 *g
 * Created on Apr 2, 2010, 1:08:51 PM
 */
package music;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent.EventType;

/**
 *
 * @author austin
 */
public class MainGui extends javax.swing.JFrame {

    /** Creates new form gui */
    public MainGui() {
        Symbol.init();
        initComponents();
    }
    Env worldenv;
    public class PianoWindow  extends JPanel
    {
        Keyboard k = new Keyboard();
        int octave = 0;

        public PianoWindow()
        {

        }
        @Override
        public void paint(Graphics g)
        {
            for (int i=0; i<k.keys.size();i++)
            {
                k.keys.get(i).paint(g, octave);
            }
        }
    }
    public class MelChord extends JPanel
    {
        public MelChord()
        {

        }
        @Override
        public void paint(Graphics g)
        {

        }
    }
    public class NotePallet extends JPanel
    {
        public NotePallet()
        {

        }
        @Override
        public void paint(Graphics g)
        {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        playwindow = new javax.swing.JInternalFrame();
        tabHolder = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        editPane = new javax.swing.JEditorPane();
        pianowindow = new PianoWindow();
        octaveSet = new javax.swing.JSpinner();
        RestLabel = new javax.swing.JLabel();
        melchord = new MelChord();
        notepallet = new NotePallet();
        instrumentBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        browsePane = new javax.swing.JEditorPane();

        playwindow.setVisible(true);

        javax.swing.GroupLayout playwindowLayout = new javax.swing.GroupLayout(playwindow.getContentPane());
        playwindow.getContentPane().setLayout(playwindowLayout);
        playwindowLayout.setHorizontalGroup(
            playwindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        playwindowLayout.setVerticalGroup(
            playwindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Haskore-Haskell~midi music");
        setLocationByPlatform(true);

        tabHolder.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                tabHolderStateChanged(evt);
            }
        });

        jScrollPane1.setViewportView(editPane);

        pianowindow.setBackground(new java.awt.Color(255, 255, 255));
        pianowindow.setPreferredSize(new java.awt.Dimension(375, 70));

        javax.swing.GroupLayout pianowindowLayout = new javax.swing.GroupLayout(pianowindow);
        pianowindow.setLayout(pianowindowLayout);
        pianowindowLayout.setHorizontalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        pianowindowLayout.setVerticalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        octaveSet.setValue(1);

        RestLabel.setText("Rest");

        melchord.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout melchordLayout = new javax.swing.GroupLayout(melchord);
        melchord.setLayout(melchordLayout);
        melchordLayout.setHorizontalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );
        melchordLayout.setVerticalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        notepallet.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout notepalletLayout = new javax.swing.GroupLayout(notepallet);
        notepallet.setLayout(notepalletLayout);
        notepalletLayout.setHorizontalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 234, Short.MAX_VALUE)
        );
        notepalletLayout.setVerticalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        instrumentBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Piano", "Flute", "Drums", "Oboe" }));

        jLabel2.setText("Octave:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(RestLabel)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(90, 90, 90)
                        .addComponent(notepallet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RestLabel)
                                .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(notepallet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );

        pianowindow.getAccessibleContext().setAccessibleName("");

        tabHolder.addTab("Edit", jPanel1);

        browsePane.setContentType("text/html");
        browsePane.setEditable(false);
        browsePane.addHyperlinkListener(new javax.swing.event.HyperlinkListener() {
            public void hyperlinkUpdate(javax.swing.event.HyperlinkEvent evt) {
                musicClick(evt);
            }
        });
        jScrollPane2.setViewportView(browsePane);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabHolder.addTab("Review", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
        );

        tabHolder.getAccessibleContext().setAccessibleName("Display");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabHolderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabHolderStateChanged
        System.out.println(tabHolder.getSelectedIndex());
        if(!editPane.getText().isEmpty())ReviewTools.compile(editPane, browsePane);
    }//GEN-LAST:event_tabHolderStateChanged

    private void musicClick(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_musicClick
        ReviewTools.action(evt);
        
    }//GEN-LAST:event_musicClick


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
//        Keyboard k = new Keyboard();
//        int octave = 0;
//        for (int i=0; i<k.keys.size();i++)
//        {
//            k.keys.get(i).paint(g, octave);
//        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainGui().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RestLabel;
    private javax.swing.JEditorPane browsePane;
    private javax.swing.JEditorPane editPane;
    private javax.swing.JComboBox instrumentBox;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel melchord;
    private javax.swing.JPanel notepallet;
    private javax.swing.JSpinner octaveSet;
    private javax.swing.JPanel pianowindow;
    private javax.swing.JInternalFrame playwindow;
    private javax.swing.JTabbedPane tabHolder;
    // End of variables declaration//GEN-END:variables
}
