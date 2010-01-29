/**
 * 
 * The Functional Music project
 * @author Western State College, CIS412 class
 */

package music;


import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;


public class ActivatedHyperlinkListener implements HyperlinkListener {

  JEditorPane editorPane;

  public ActivatedHyperlinkListener(JEditorPane editorPane) {
    this.editorPane = editorPane;
  }

  public void hyperlinkUpdate(HyperlinkEvent hyperlinkEvent) {
    System.out.println("Hyperlink is working");
        HyperlinkEvent.EventType type = hyperlinkEvent.getEventType();
        if (type == HyperlinkEvent.EventType.ENTERED)
            System.out.println("You are hovering over: " + hyperlinkEvent.getDescription());
        else if (type == HyperlinkEvent.EventType.ACTIVATED)
            System.out.println("Now playing music " + hyperlinkEvent.getDescription());
        else if (type == HyperlinkEvent.EventType.EXITED)
            System.out.println("You are no longer hovering over " + hyperlinkEvent.getDescription());
  }
}