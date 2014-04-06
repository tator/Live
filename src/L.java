

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.3
 */public class L extends JApplet {
     JFrame frame;
     Live Live;
    
    public void init() {
        //cb = new cb(); 
        //getContentPane().add(new cb()); 
        setBackground(Color.white);

        frame = new JFrame();
        frame.setVisible(false);
        frame.setSize(750, 750);
        frame.setLocation(0, 0);
        frame.setTitle("Live");
        Container contentpane = frame.getContentPane();
        contentpane.add(Live = new Live());
        Live.requestFocus();
        frame.setVisible(true);
    }

}
