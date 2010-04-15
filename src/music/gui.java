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
public class gui extends javax.swing.JFrame {

    /** Creates new form gui */
    public gui() {
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
        jLabel1 = new javax.swing.JLabel();
        melchord = new MelChord();
        notepallet = new NotePallet();
        instrumentBox = new javax.swing.JComboBox();
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

        javax.swing.GroupLayout pianowindowLayout = new javax.swing.GroupLayout(pianowindow);
        pianowindow.setLayout(pianowindowLayout);
        pianowindowLayout.setHorizontalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 394, Short.MAX_VALUE)
        );
        pianowindowLayout.setVerticalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );

        octaveSet.setValue(1);

        jLabel1.setText("Rest");

        melchord.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout melchordLayout = new javax.swing.GroupLayout(melchord);
        melchord.setLayout(melchordLayout);
        melchordLayout.setHorizontalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 181, Short.MAX_VALUE)
        );
        melchordLayout.setVerticalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
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
            .addGap(0, 271, Short.MAX_VALUE)
        );

        instrumentBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Piano", "Flute", "Drums", "Oboe" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                    .addComponent(pianowindow, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notepallet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(notepallet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 415, Short.MAX_VALUE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(227, Short.MAX_VALUE)
                .addComponent(tabHolder, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabHolder.getAccessibleContext().setAccessibleName("Display");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tabHolderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_tabHolderStateChanged
        if(tabHolder.isEnabledAt(1)){
            reviewTools.compile(editPane, browsePane);
        }
    }//GEN-LAST:event_tabHolderStateChanged

    private void musicClick(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_musicClick
        reviewTools.action(evt);
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
                new gui().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JEditorPane browsePane;
    private javax.swing.JEditorPane editPane;
    private javax.swing.JComboBox instrumentBox;
    private javax.swing.JLabel jLabel1;
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
