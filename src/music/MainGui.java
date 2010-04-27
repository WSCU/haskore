/*
 * gui.java
 *g
 * Created on Apr 2, 2010, 1:08:51 PM
 */
package music;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkEvent.EventType;

/**
 *
 * @author austin
 */
public class MainGui extends javax.swing.JFrame {
    private boolean musicType= false;
    private boolean chord = false;
    public int octave = 0;
    String cm = "Click to construct a melody with the & operator";
    String cch = "Click to construct chords with the ! operator";
    /** Creates new form gui */
    public MainGui() {
        Symbol.init();
        initComponents();
        
    }
    Env worldenv;
    public class PianoWindow  extends JPanel
    {
        Keyboard k = new Keyboard();
        //int octave = 0;

        public PianoWindow()
        {

        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);
            for (Key key: k.keys)
            {
                key.paint(g, octave);
            }
        }
    }
    public class MelChord extends JPanel
    {
        BufferedImage image;
        public MelChord(String path)
        {
            try
            {
                image = ImageIO.read(new File(path));
            }
            catch (IOException ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        @Override
        public void paint(Graphics g)
        {
            super.paint(g);

            g.drawImage(image, 0, 0, null);
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
            super.paint(g);
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
        melchord = new MelChord("music/nm.jpg");
        notepallet = new NotePallet();
        instrumentBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        chordCheck = new javax.swing.JCheckBox();
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

        editPane.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                anyType(evt);
            }
        });
        jScrollPane1.setViewportView(editPane);

        pianowindow.setPreferredSize(new java.awt.Dimension(375, 70));
        pianowindow.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pianowindowMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pianowindowLayout = new javax.swing.GroupLayout(pianowindow);
        pianowindow.setLayout(pianowindowLayout);
        pianowindowLayout.setHorizontalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );
        pianowindowLayout.setVerticalGroup(
            pianowindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 81, Short.MAX_VALUE)
        );

        octaveSet.setValue(1);
        octaveSet.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                octaveSetStateChanged(evt);
            }
        });

        RestLabel.setText("Rest");
        RestLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RestLabelMouseClicked(evt);
            }
        });

        melchord.setBackground(new java.awt.Color(239, 126, 126));
        melchord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                melchordMouseClicked(evt);
            }
        });

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

        notepallet.setBackground(new java.awt.Color(83, 230, 47));
        notepallet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notepalletMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout notepalletLayout = new javax.swing.GroupLayout(notepallet);
        notepallet.setLayout(notepalletLayout);
        notepalletLayout.setHorizontalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 336, Short.MAX_VALUE)
        );
        notepalletLayout.setVerticalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 163, Short.MAX_VALUE)
        );

        instrumentBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Piano", "Flute", "Drums", "Oboe" }));

        jLabel2.setText("Octave:");

        chordCheck.setText("Chords");
        chordCheck.setToolTipText(cch);
        chordCheck.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                toggleChord(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
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
                                .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chordCheck))
                            .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(notepallet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(chordCheck)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(RestLabel)
                                    .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(notepallet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(495, 495, 495))))
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
        ReviewTools.action(evt, browsePane);
        
    }//GEN-LAST:event_musicClick

    private void pianowindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pianowindowMouseClicked
        int x = evt.getX();
        int y = evt.getY();
        int hiX = x;
        int note = x % 125;
        final int whiteY = 70;
        final int blackY = 40;
        String noteName = "";
        
        while (hiX > 0)
        {
            octave++;
            hiX -= 125;
        }
        System.out.println("x = " + x + "y = " + y);
        if (note < 20 && y >=0 && y < whiteY)
        {
            if (note >= 12 && note < 27 && y < blackY)
            {
                System.out.println("c" + octave + "s");
                noteName = "c" + octave + "s";
            }
            else
            {
                System.out.println("c" + octave);
                noteName = "c" + octave ;
            }

        }
        if (note >= 20 && note < 40 && y < whiteY)
        {
            if (note >= 32 && note < 47 && y < blackY)
            {
                System.out.println("d" + octave + "s");
                noteName = "d" + octave + "s";
            }
            else
            {
                System.out.println("d" + octave);
                noteName = "d" + octave ;
            }
        }
        if (note >= 40 && note < 60 && y < whiteY)
        {
            System.out.println("e" + octave);
            noteName = "e" + octave;
        }
        if (note >= 60 && note < 80 && y < whiteY)
        {
            if (note >= 72 && note < 87 && y < blackY)
            {
                System.out.println("f" + octave + "s");
                noteName = "f" + octave + "s";
            }
            else
            {
                System.out.println("f" + octave);
                noteName = "f" + octave;
            }
        }
        if (note >= 80 && note < 100 && y < whiteY)
        {
            if (note >= 92 && note < 107 && y < blackY)
            {
                System.out.println("g" + octave + "s");
                noteName = "g" + octave + "s";
            }
            else
            {
                System.out.println("g");
                noteName = "g" + octave;
            }
        }
        if (note >= 100 && note < 120 && y < whiteY)
        {
            if (note >= 112 && note < 127 && y < blackY)
            {
                System.out.println("a" + octave + "s");
                noteName = "a" + octave + "s";
            }
            else
            {
                System.out.println("a");
                noteName = "a" + octave;
            }
        }
        if (note >= 120 && note < 140 && y < whiteY)
        {
            System.out.println("b");
            noteName = "b" + octave ;
        }
        if(musicType)noteName = chord ? " ! "+noteName:" & "+noteName;
        EditorTools.smrtAddTxt(editPane, noteName);
        musicType=true;
    }//GEN-LAST:event_pianowindowMouseClicked
    
    private void RestLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestLabelMouseClicked

    }//GEN-LAST:event_RestLabelMouseClicked

    private void melchordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melchordMouseClicked

    }//GEN-LAST:event_melchordMouseClicked

    private void notepalletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notepalletMouseClicked

    }//GEN-LAST:event_notepalletMouseClicked

    private void octaveSetStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_octaveSetStateChanged
        if ((Integer)octaveSet.getValue() > 0 || (Integer)octaveSet.getValue() < 8)
        {
            octave = (Integer)octaveSet.getValue();
        }
    }//GEN-LAST:event_octaveSetStateChanged

    private void anyType(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_anyType
        // TODO add your handling code here:
        this.musicType = false;
    }//GEN-LAST:event_anyType

    private void toggleChord(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_toggleChord
            chord=!chord;
            
            if(chord)chordCheck.setToolTipText(cch);
            else chordCheck.setToolTipText(cm);
    }//GEN-LAST:event_toggleChord


    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        
    }
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
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
    public javax.swing.JEditorPane browsePane;
    private javax.swing.JCheckBox chordCheck;
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
