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
        Top.Initialize();
    }
    Env worldenv;
    public class PianoWindow  extends JPanel
    {
        Keyboard k = new Keyboard();

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
        int boxIndicator = 0;

        public MelChord(String path)
        {
            try
            {
                image = ImageIO.read(new File(path));
            }
            catch (IOException ex)
            {
            System.out.println("Chris write your own code");
            }
        }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
            {
                g.drawImage(image, 0, 0, this);
            }
            if (boxIndicator == 1)
            {
                g.setColor(Color.black);
                g.drawRect(0, 0, 64, 52);
            }
            if (boxIndicator == 2)
            {
                g.setColor(Color.black);
                g.drawRect(74, 0, 126, 52);
            }
        }
        public void setBoxIndicator(int set)
        {
            boxIndicator = set;
        }
    }
    public class NotePallet extends JPanel
    {
        BufferedImage image;
        int paintIndicator = 0;

        public NotePallet(String path)
        {
            try
            {
                image = ImageIO.read(new File(path));
            }
            catch (IOException ex)
            {
            System.out.println("Chris write your own code");
            }
        }
        @Override
        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            if (image != null)
            {
                g.drawImage(image, 0, 0, this);
            }
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
        melchord = new MelChord("melchord.gif");
        notepallet = new NotePallet("pallete.gif");
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

        melchord.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                melchordMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout melchordLayout = new javax.swing.GroupLayout(melchord);
        melchord.setLayout(melchordLayout);
        melchordLayout.setHorizontalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 127, Short.MAX_VALUE)
        );
        melchordLayout.setVerticalGroup(
            melchordLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 55, Short.MAX_VALUE)
        );

        notepallet.setPreferredSize(new java.awt.Dimension(234, 163));
        notepallet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                notepalletMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout notepalletLayout = new javax.swing.GroupLayout(notepallet);
        notepallet.setLayout(notepalletLayout);
        notepalletLayout.setHorizontalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        notepalletLayout.setVerticalGroup(
            notepalletLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 159, Short.MAX_VALUE)
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
                                .addComponent(melchord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, 434, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(notepallet, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(pianowindow, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(instrumentBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(RestLabel)
                                .addComponent(octaveSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(melchord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(notepallet, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
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
        System.out.println("tab "+tabHolder.getSelectedIndex());
        if(tabHolder.getSelectedIndex()==0)browsePane.setText("");
        if(!editPane.getText().isEmpty())ReviewTools.compile(editPane, browsePane);
    }//GEN-LAST:event_tabHolderStateChanged

    private void musicClick(javax.swing.event.HyperlinkEvent evt) {//GEN-FIRST:event_musicClick
        ReviewTools.action(evt, browsePane); 
    }//GEN-LAST:event_musicClick

    private void pianowindowMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pianowindowMouseClicked
        int x = evt.getX();
        int keyOctave;
        int y = evt.getY();
        int hiX = x;
        int note = x % 140;
        final int whiteY = 70;
        final int blackY = 40;
        String noteName = "";
        keyOctave = (Integer)octaveSet.getValue();
        while (hiX > 0)
        {
            keyOctave++;
            hiX -= 140;
        }
        if (note < 27 && y >=0 && y < whiteY)
        {
            if (note >= 12 && note < 27 && y < blackY)
            {
                System.out.println("c" + keyOctave + "s");
                noteName = "c" + keyOctave + "s";
            }
            else if(note < 20)
            {
                System.out.println("c" + keyOctave);
                noteName = "c" + keyOctave ;
            }
        }
        if (note >= 20 && note < 47 && y < whiteY && (y > blackY || note >27))
        {
            if (note >= 32 && note < 47 && y < blackY)
            {
                System.out.println("d" + keyOctave + "s");
                noteName = "d" + keyOctave + "s";
            }
            else if (note < 40)
            {
                System.out.println("d" + keyOctave);
                noteName = "d" + keyOctave ;
            }
        }
        if (note >= 40 && note < 60 && y < whiteY && (y > blackY || note > 47))
        {
            System.out.println("e" + keyOctave);
            noteName = "e" + keyOctave;
        }
        if (note >= 60 && note < 87 && y < whiteY )
        {
            if (note >= 72 && note < 87 && y < blackY)
            {
                System.out.println("f" + keyOctave + "s");
                noteName = "f" + keyOctave + "s";
            }
            else if(note < 80)
            {
                System.out.println("f" + keyOctave);
                noteName = "f" + keyOctave;
            }
        }
        if (note >= 80 && note < 107 && y < whiteY && (y > blackY || note > 87))
        {
            if (note >= 92 && note < 107 && y < blackY)
            {
                System.out.println("g" + keyOctave + "s");
                noteName = "g" + keyOctave + "s";
            }
            else if(note < 100)
            {
                System.out.println("g" + keyOctave);
                noteName = "g" + keyOctave;
            }
        }
       if (note >= 100 && note < 127 && y < whiteY && (y > blackY || note > 107))
        {
            if (note >= 112 && note < 127 && y < blackY)
            {
                System.out.println("a" + keyOctave + "s");
                noteName = "a" + keyOctave + "s";
            }
            else if (note < 120)
            {
                System.out.println("a" + keyOctave);
                noteName = "a" + keyOctave;
            }
        }
        if (note >= 120 && note < 140 && y < whiteY && (y > blackY || note > 127))
        {
            System.out.println("b" + keyOctave);
            noteName = "b" + keyOctave ;
        }
        if(musicType)noteName = chord ? " ! "+noteName:" & "+noteName;
        EditorTools.smrtAddTxt(editPane, noteName);
        musicType=true;
    }//GEN-LAST:event_pianowindowMouseClicked
   
    private void RestLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestLabelMouseClicked

    }//GEN-LAST:event_RestLabelMouseClicked

    private void melchordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melchordMouseClicked
        int x = evt.getX();
        int y = evt.getY();
        if (x >= 0 && x < 65 && y < 53)
        {
            //setBoxIndicator(1);
        }
        if (x > 73 && x < 127 && y < 53)
        {
            //setBoxIndicator(2);
        }
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
        this.musicType = false;
    }//GEN-LAST:event_anyType

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
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new MainGui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel RestLabel;
    public javax.swing.JEditorPane browsePane;
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
