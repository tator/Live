package square;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ResourceBundle;
import javax.swing.JApplet;
import javax.swing.JFrame;

/**
 *
 * <strong>Author:</strong> Tator
 * <p>
 * <strong>Version:</strong> win.2.0
 */
public class square extends JApplet {

    JFrame frame;
    squareSpiral sq;

    @Override
    public void init() {
        frame = new JFrame();
        setSize(500, 500);
        frame.setTitle("Square Spiral");
        
        //getContentPane().add(frame.add(sq = new squareSpiral()));
        getContentPane().add(sq = new squareSpiral());
        sq.requestFocus();
    }

}
