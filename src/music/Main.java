package music;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.event.HyperlinkListener;

/**
 * The Functional Music project
 * @author Western State College, CIS412 class
 */
public class Main extends javax.swing.JFrame {

    public String HTML;
    public String program_text;
    public ArrayList<PianoKey> whiteKeys = new ArrayList<PianoKey>();
    public ArrayList<PianoKey> blackKeys = new ArrayList<PianoKey>();

    /** Creates new form Main */
    public Main() {
        Symbol.init();
        initComponents();
        HyperlinkListener hyperlinkListener = new ActivatedHyperlinkListener(display_field);
        display_field.addHyperlinkListener(hyperlinkListener);

        //create the 7 white keys
        for (int i = 0; i < 7; i++) {
            int width = keyboard.getWidth() / 7;
            int height = keyboard.getHeight() - 2;
            PianoKey temp = new PianoKeyWhite(i, width * i, 0, width, height);
            whiteKeys.add(temp);
        }
        //create the 5 black keys

        for (int i = 0; i < 5; i++) {
            int width = (int) (keyboard.getWidth() / 7 * .75);
            int height = keyboard.getHeight() / 2;
            int placement = i;
            
            int position = (keyboard.getWidth()/7)+placement;
            PianoKey temp = new PianoKeyBlack(i, position * (i + 1), 0, width, height);
            blackKeys.add(temp);
        }
    }

    public class GPanel extends JPanel {

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (PianoKey key : whiteKeys) {
                key.draw(g);
            }
            for (PianoKey key : blackKeys) {
                key.draw(g);
            }
        }
    }

    //helper methods
    private void insert(String text) {
        program_text = display_field.getText();
        int start = display_field.getSelectionStart();
        String first_part = program_text.substring(0, start);
        String last_part = program_text.substring(start, program_text.length());
        String selected_text = display_field.getSelectedText();
        if (selected_text == null) {
            program_text = first_part + text + "( )" + last_part;
        } else {
            String replacement = text + "(" + selected_text + ")";
            program_text = program_text.replaceFirst(selected_text, replacement);
        }
        display_field.setEditable(true);
        display_field.setContentType("text");
        display_field.setText(program_text);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        keyboard = new GPanel();
        octave = new javax.swing.JPanel();
        tempoInsert = new javax.swing.JButton();
        tempoLabel = new javax.swing.JLabel();
        tempoSelection = new javax.swing.JComboBox();
        instruments = new javax.swing.JPanel();
        instrumentInsert = new javax.swing.JButton();
        instrumentLabel = new javax.swing.JLabel();
        InstrumentSelection = new javax.swing.JComboBox();
        help = new javax.swing.JPanel();
        helpButton = new javax.swing.JButton();
        sampleMusicButton = new javax.swing.JButton();
        helpLabel = new javax.swing.JLabel();
        compile = new javax.swing.JPanel();
        runProgramButton = new javax.swing.JButton();
        compileLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        display_field = new javax.swing.JEditorPane();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        keyboard.setBackground(new java.awt.Color(255, 255, 255));
        keyboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keyboardMouseClicked(evt);
            }
        });
        org.jdesktop.layout.GroupLayout keyboardLayout = new org.jdesktop.layout.GroupLayout(keyboard);
        keyboard.setLayout(keyboardLayout);
        keyboardLayout.setHorizontalGroup(
            keyboardLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 452, Short.MAX_VALUE)
        );
        keyboardLayout.setVerticalGroup(
            keyboardLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 202, Short.MAX_VALUE)
        );
        octave.setBackground(new java.awt.Color(255, 255, 255));
        tempoInsert.setText("Insert");
        tempoInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoInsertActionPerformed(evt);
            }
        });
        tempoLabel.setText("Tempo:");
        tempoSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "half", "quarter", "eighth" }));
        tempoSelection.setName("Piano\nTrumpet\nGuitar"); // NOI18N
        tempoSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tempoSelectionActionPerformed(evt);
            }
        });
        org.jdesktop.layout.GroupLayout octaveLayout = new org.jdesktop.layout.GroupLayout(octave);
        octave.setLayout(octaveLayout);
        octaveLayout.setHorizontalGroup(
            octaveLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(octaveLayout.createSequentialGroup()
                .add(octaveLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(octaveLayout.createSequentialGroup()
                        .add(48, 48, 48)
                        .add(octaveLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(tempoInsert)
                            .add(tempoLabel)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, octaveLayout.createSequentialGroup()
                        .addContainerGap()
                        .add(tempoSelection, 0, 122, Short.MAX_VALUE)))
                .addContainerGap())
        );
        octaveLayout.setVerticalGroup(
            octaveLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(octaveLayout.createSequentialGroup()
                .addContainerGap()
                .add(tempoLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(tempoSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 92, Short.MAX_VALUE)
                .add(tempoInsert)
                .addContainerGap())
        );
        instruments.setBackground(new java.awt.Color(255, 255, 255));
        instrumentInsert.setText("Insert");
        instrumentInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                instrumentInsertActionPerformed(evt);
            }
        });
        instrumentLabel.setText("Instrument:");
        InstrumentSelection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Piano", "Harpsichord", "Vibes", "Organ", "Guitar", "Electric", "Bass", "Violin", "Viola", "Cello", "Trumpet", "Trombone", "Horn", "Sax", "Oboe", "Bassoon", "Clarinet", "Flute", "PanFlute", "Kalimba", "Woodblock" }));
        InstrumentSelection.setName("Piano\nTrumpet\nGuitar"); // NOI18N
        InstrumentSelection.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InstrumentSelectionActionPerformed(evt);
            }
        });
        org.jdesktop.layout.GroupLayout instrumentsLayout = new org.jdesktop.layout.GroupLayout(instruments);
        instruments.setLayout(instrumentsLayout);
        instrumentsLayout.setHorizontalGroup(
            instrumentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(instrumentsLayout.createSequentialGroup()
                .add(34, 34, 34)
                .add(instrumentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(InstrumentSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(instrumentInsert)
                    .add(instrumentLabel))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        instrumentsLayout.setVerticalGroup(
            instrumentsLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(instrumentsLayout.createSequentialGroup()
                .addContainerGap()
                .add(instrumentLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(InstrumentSelection, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 92, Short.MAX_VALUE)
                .add(instrumentInsert)
                .addContainerGap())
        );
        help.setBackground(new java.awt.Color(255, 255, 255));
        helpButton.setText("Help");
        sampleMusicButton.setText("Sample Music");
        helpLabel.setText("Help");
        org.jdesktop.layout.GroupLayout helpLayout = new org.jdesktop.layout.GroupLayout(help);
        help.setLayout(helpLayout);
        helpLayout.setHorizontalGroup(
            helpLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(helpLayout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(helpLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, helpLayout.createSequentialGroup()
                        .add(helpLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, helpButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, sampleMusicButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .add(20, 20, 20))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, helpLayout.createSequentialGroup()
                        .add(helpLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 48, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(38, 38, 38))))
        );
        helpLayout.setVerticalGroup(
            helpLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, helpLayout.createSequentialGroup()
                .addContainerGap()
                .add(helpLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 91, Short.MAX_VALUE)
                .add(sampleMusicButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(helpButton)
                .addContainerGap())
        );
        compile.setBackground(new java.awt.Color(255, 255, 255));
        runProgramButton.setText("Run Program");
        runProgramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runProgramButtonActionPerformed(evt);
            }
        });
        compileLabel.setText("Run");
        org.jdesktop.layout.GroupLayout compileLayout = new org.jdesktop.layout.GroupLayout(compile);
        compile.setLayout(compileLayout);
        compileLayout.setHorizontalGroup(
            compileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(compileLayout.createSequentialGroup()
                .add(77, 77, 77)
                .add(compileLabel)
                .addContainerGap(96, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, compileLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .add(runProgramButton)
                .add(27, 27, 27))
        );
        compileLayout.setVerticalGroup(
            compileLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(compileLayout.createSequentialGroup()
                .addContainerGap()
                .add(compileLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 125, Short.MAX_VALUE)
                .add(runProgramButton)
                .addContainerGap())
        );
        jScrollPane2.setViewportView(display_field);
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane2)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                        .add(keyboard, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(instruments, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(octave, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(help, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(compile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(keyboard, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                        .add(compile, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(help, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(octave, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(instruments, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(18, 18, 18)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 330, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void runProgramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_runProgramButtonActionPerformed
        if (runProgramButton.getText().equals("Run Program")) {
            runProgramButton.setText("Edit Program");
            program_text = display_field.getText();

            
            
            HTML = HTMLObject.generate(Lexer.lexString(program_text));
            display_field.setContentType("text/html");
            display_field.setText(HTML);
            display_field.setEditable(false);
            
        } else {
            runProgramButton.setText("Run Program");
            display_field.setEditable(true);
            display_field.setContentType("text");
            display_field.setText(program_text);
        }


}//GEN-LAST:event_runProgramButtonActionPerformed

    private void InstrumentSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InstrumentSelectionActionPerformed
}//GEN-LAST:event_InstrumentSelectionActionPerformed

    private void tempoSelectionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoSelectionActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_tempoSelectionActionPerformed

    private void instrumentInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_instrumentInsertActionPerformed
        insert((String) InstrumentSelection.getSelectedItem());
    }//GEN-LAST:event_instrumentInsertActionPerformed

    private void tempoInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tempoInsertActionPerformed
        insert((String) tempoSelection.getSelectedItem());
    }//GEN-LAST:event_tempoInsertActionPerformed

    private void keyboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keyboardMouseClicked
        int mouseX = evt.getX();
        int mouseY = evt.getY();
        for (PianoKey key : whiteKeys) {
            if (key.within(mouseX, mouseY)) {
                System.out.println("You clicked the " + key.note + " key");
            }
        }
    }//GEN-LAST:event_keyboardMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox InstrumentSelection;
    private javax.swing.JPanel compile;
    private javax.swing.JLabel compileLabel;
    private javax.swing.JEditorPane display_field;
    private javax.swing.JPanel help;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel helpLabel;
    private javax.swing.JButton instrumentInsert;
    private javax.swing.JLabel instrumentLabel;
    private javax.swing.JPanel instruments;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel keyboard;
    private javax.swing.JPanel octave;
    private javax.swing.JButton runProgramButton;
    private javax.swing.JButton sampleMusicButton;
    private javax.swing.JButton tempoInsert;
    private javax.swing.JLabel tempoLabel;
    private javax.swing.JComboBox tempoSelection;
    // End of variables declaration//GEN-END:variables
}
