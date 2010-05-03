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
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/**
 * @author austin
 */
public class MainGui extends javax.swing.JFrame {
    public static boolean MUTE = false;
    private boolean musicType= false;
    private boolean chord = false;
    private String noteType = "";
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
            octave = (Integer)octaveSet.getValue();
            super.paint(g);
            for (Key key: k.keys)
            {
                key.paint(g, octave);
            }
            drawOctave(g,k.keys.get(0),octave-1);
            drawOctave(g,k.keys.get(12),octave);
            drawOctave(g,k.keys.get(24),octave + 1);
        }

    }
    public void drawOctave(Graphics g, Key k, int octave)
    {
        String str = ""+ octave;
        g.setFont(new java.awt.Font("Arial",1,11));
        g.setColor(Color.black);
        g.drawString(str,k.Width - 10, k.Height - 35);

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
            System.out.println(ex.getMessage());
            }
        }
        @Override
        public void paint(Graphics g)
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabHolder.addTab("Review", jPanel2);

        jMenu1.setText("File");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem1.setText("Save Files");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Preferences");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.ALT_MASK));
        jMenuItem2.setText("Mute");
        jMenuItem2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MuteSound(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 822, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabHolder, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 655, Short.MAX_VALUE)
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
                System.out.println(noteType + " c" + keyOctave + "s");
                noteName = noteType + " c" + keyOctave + "s";
                
            }
            else if(note < 20)
            {
                System.out.println(noteType + " c" + keyOctave);
                noteName = noteType + " c" + keyOctave ;
            }
        }
        if (note >= 20 && note < 47 && y < whiteY && (y > blackY || note >27))
        {
            if (note >= 32 && note < 47 && y < blackY)
            {
                System.out.println(noteType + " d" + keyOctave + "s");
                noteName = noteType + " d" + keyOctave + "s";
            }
            else if (note < 40)
            {
                System.out.println(noteType + " d" + keyOctave);
                noteName = noteType +  " d" + keyOctave ;
            }
        }
        if (note >= 40 && note < 60 && y < whiteY && (y > blackY || note > 47))
        {
            System.out.println(noteType + " e" + keyOctave);
            noteName = noteType + " e" + keyOctave;
        }
        if (note >= 60 && note < 87 && y < whiteY )
        {
            if (note >= 72 && note < 87 && y < blackY)
            {
                System.out.println( noteType + " f" + keyOctave + "s");
                noteName =  noteType + " f" + keyOctave + "s";
            }
            else if(note < 80)
            {
                System.out.println( noteType + " f" + keyOctave);
                noteName = noteType +  " f" + keyOctave;
            }
        }
        if (note >= 80 && note < 107 && y < whiteY && (y > blackY || note > 87))
        {
            if (note >= 92 && note < 107 && y < blackY)
            {
                System.out.println( noteType + " g" + keyOctave + "s");
                noteName = noteType + " g" + keyOctave + "s";
            }
            else if(note < 100)
            {
                System.out.println( noteType + " g" + keyOctave);
                noteName = noteType + " g" + keyOctave;
            }
        }
       if (note >= 100 && note < 127 && y < whiteY && (y > blackY || note > 107))
        {
            if (note >= 112 && note < 127 && y < blackY)
            {
                System.out.println( noteType + " a" + keyOctave + "s");
                noteName =  noteType + " a" + keyOctave + "s";
            }
            else if (note < 120)
            {
                System.out.println( noteType + " a" + keyOctave);
                noteName =  noteType + " a" + keyOctave;
            }
        }
        if (note >= 120 && note < 140 && y < whiteY && (y > blackY || note > 127))
        {
            System.out.println( noteType + " b" + keyOctave);
            noteName = noteType + " b" + keyOctave ;
        }
        MusNote m= Music.note(noteName, new BigRational("1/4"), 80, "Piano");
        System.out.println(m);
        Performance result = new Performance(m);
        if(musicType)noteName = chord ? " ! "+noteName:" & "+noteName;
        EditorTools.smrtAddTxt(editPane, noteName);
        musicType=true;
        System.out.println("MAIN PIANO "+result.notes.size());
        result.perform();
    }//GEN-LAST:event_pianowindowMouseClicked
   
    private void RestLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RestLabelMouseClicked
        EditorTools.smrtAddTxt(editPane, this.noteType+" r");
    }//GEN-LAST:event_RestLabelMouseClicked

    private void melchordMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_melchordMouseClicked
        System.out.println("click melchrd");
        int x = evt.getX();
        int y = evt.getY();
        if (x >= 0 && x < 65 && y < 53)
        {
            MelChord m = (MelChord)this.melchord;
            m.setBoxIndicator(1);
        }
        if (x > 73 && x < 127 && y < 53)
        {
            MelChord m = (MelChord)this.melchord;
            m.setBoxIndicator(2);
        }
    }//GEN-LAST:event_melchordMouseClicked

    private void notepalletMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notepalletMouseClicked
        int x = evt.getX();
        int y = evt.getY();
        System.out.println("X = " + x + " Y = " + y);
        if(y < 40)
        {
            if(x <27)
            {
                noteType = "q";
            }
            else if(x >=27 && x < 54)
            {
                noteType = "h";
            }
            else if(x >= 54 && x < 81)
            {
                noteType = "e";
            }

        }
        if(y >= 40 && y < 76)
        {
            if(x <27)
            {
                noteType = "dq";
            }
            else if(x >=27 && x < 54)
            {
                noteType = "dh";
            }
            else if(x >= 54 && x < 81)
            {
                noteType = "de";
            }

        }
        if(y >= 76)
        {
            if(x <27)
            {
                noteType = "p";
            }
            if(x >=27 && x < 54)
            {
                noteType = "ds";
            }
            else if(x >= 54 && x < 81)
            {
                noteType = "s";
            }
        }
        System.out.println("note type = " + noteType);

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

    private void MuteSound(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MuteSound
        MUTE=!MUTE;
}//GEN-LAST:event_MuteSound


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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
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
