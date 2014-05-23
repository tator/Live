
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 *
 * <strong>Author:</strong> Tator & SeaSee
 * <p>
 * <strong>Version:</strong> win.1.10
 */
public class Live extends JPanel implements MouseListener, MouseMotionListener, KeyListener, Runnable {

    private int h = 0, w = 0;
    public static boolean[][] a;
    public static boolean active = true;
    int t, o = 0;
    Thread thread;
    Point one;
    Point two;
    ArrayList<hold> holdR, holdA;
    int max = 0;
    int birth = 3, die = 4, overing = 1;
    boolean New = false;
    boolean over = false;
    boolean up = false;
    int speed = 0;
    int[][] gun = {
        {5, 6, 5, 6,  5,  6,  7,  4,  8,  3,  9,  3,  9,  6,  4,  8,  5,  6,  7,  6,  3,  4,  5,  3,  4,  5,  2,  6,  1,  2,  6,  7,  3,  4,  3,  4},
        {1, 1, 2, 2, 11, 11, 11, 12, 12, 13, 13, 14, 14, 15, 16, 16, 17, 17, 17, 18, 21, 21, 21, 22, 22, 22, 23, 23, 25, 25, 25, 25, 35, 35, 36, 36}
    };

    public Live() {
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setSize(75, 75);
        setFocusable(true);
        requestFocusInWindow();
        a = new boolean[752][752];
        repaint();
    }

    public void st() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (o == 0) {
            st();
            o++;
        }

        setBackground(Color.black);
        if (New || h != getSize().height || w != getSize().width) {
            New = false;
            h = getSize().height;
            w = getSize().width;
            a = new boolean[w + 2][h + 2];
            holdA = new ArrayList<>();
        }
        g.setColor(Color.green);
        g.drawString("b: " + birth + " d: " + die + " s: " + speed + " " + (over ? "O: " + overing : " "), 100, 100);
        for (int q = 1; q <= w + 1; q++) {
            for (int r = 1; r <= h + 1; r++) {
                /*int m = (int)(Math.random()*6);
                 switch(m){
                 case 0:
                 g.setColor(Color.BLUE);
                 break;
                 case 1:
                 g.setColor(Color.red);
                 break;
                 case 2:
                 g.setColor(Color.GREEN);
                 break;
                 case 3:
                 g.setColor(Color.YELLOW);
                 break;
                 case 4:
                 g.setColor(Color.CYAN);
                 break;
                 case 5:
                 g.setColor(Color.WHITE);
                 }*/

                g.setColor(Color.ORANGE);
                if (a[q][r]) {
                    g.drawLine(q, r, q, r);
                }
            }

        }
    }
    /*
     public boolean same(int q, int r) {
     try {
     for (hold hold : holdA) {
     if (hold.x == q && hold.y == r) {
     return false;
     }
     }
     } catch (Exception e) {
     }
     return true;
     }*/

    public void increment() {
        int c = 0;
        holdR = new ArrayList<>();
        holdA = new ArrayList<>();
        for (int q = 1; q <= w; q++) {
            for (int r = 1; r <= h; r++) {
                c = 0;

                if (a[q - 1][r - 1]) {
                    c++;
                }
                if (a[q - 1][r]) {
                    c++;
                }
                try {
                    if (a[q - 1][r + 1]) {
                        c++;
                    }
                } catch (Exception m) {
                }
                if (a[q][r + 1]) {
                    c++;
                }
                if (a[q + 1][r + 1]) {
                    c++;
                }
                if (a[q + 1][r]) {
                    c++;
                }
                if (a[q + 1][r - 1]) {
                    c++;
                }
                if (a[q][r - 1]) {
                    c++;
                }
                if (!a[q][r] && (c == birth)) {
                    holdA.add(new hold(q, r));
                }
                if (a[q][r] && (c >= die)) {
                    holdR.add(new hold(q, r));
                }
                if (over) {
                    if (a[q][r] && (c <= overing)) {
                        holdR.add(new hold(q, r));
                    }
                }
            }
        }
        for (hold s : holdA) {
            a[s.x][s.y] = true;
        }
        for (hold s : holdR) {
            a[s.x][s.y] = false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        one = e.getPoint();
        int y = one.y;
        int x = one.x;
        if (e.getButton() == 1) {
            a[x][y] = true;
        }
        if (e.getButton() == 3&&!up) {
            for (int q = 0; q < gun[0].length; q++) {
                a[gun[0][q] + x][gun[1][q] + y] = true;
            }
        }
        if (e.getButton() == 2&&!up) {
            for (int q = 0 ; q < gun[0].length; q++) {
                a[(10-gun[0][q]) + x][gun[1][q] + y] = true;
            }
        }
        if (e.getButton() == 2&&up) {
            for (int q = 0; q < gun[0].length; q++) {
                a[gun[0][q] + x][10-gun[1][q] + y] = true;
            }
        }
        if (e.getButton() == 3&&up) {
            for (int q = 0 ; q < gun[0].length; q++) {
                a[(10-gun[0][q]) + x][(10-gun[1][q]) + y] = true;
            }
        }
        thread.suspend();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        thread.resume();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        active = true;
        thread.resume();
        repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        active = false;
        thread.suspend();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //n
        if (e.getKeyCode() == 0x4e) {
            New = true;
            repaint();
            return;
        }
        //up arrow
        if (e.getKeyCode() == 0x26) {
            die++;
            if (die == 9) {
                die = 8;
            }
        } else //down arrow
        if (e.getKeyCode() == 0x28) {
            die--;
            if (die == 0) {
                die = 1;
            }

        }
        if(e.getKeyChar()=='u'){
            up = !up;
        }
        
        if (e.getKeyCode() == 0x27) {
            overing++;
            if (overing == 9) {
                overing = 8;
            }
        } else //down arrow
        if (e.getKeyCode() == 0x2f) {
            overing--;
            if (overing == 0) {
                overing = 1;
            }

        }
        if (e.getKeyCode() == 0x4f) {
            over = !over;
        }
        if (e.getKeyCode() == 0x20) {
            active = !active;
            if (active) {
                thread.resume();
            }
            if (!active) {
                thread.suspend();
            }
            repaint();
        }
        //1 2 3 4 5 6 7 8
        if ((((int) e.getKeyChar()) - 0x30) >= 1 && (((int) e.getKeyChar()) - 0x30) <= 8) {
            birth = ((int) e.getKeyChar()) - 0x30;
        }
        //d
        if (e.getKeyCode() == 0x44) {
            birth = 3;
            die = 4;
        }
        //page up
        if (e.getKeyCode() == 0x21) {
            speed += 10;
        }
        //page down
        if (e.getKeyCode() == 0x22) {
            speed -= 10;
            if (speed <= 0) {
                speed = 0;
            }
        }
        //end
        if (e.getKeyCode() == 0x23) {
            speed = 00;
        }
        //System.out.println(e.getKeyCode());
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (active) {
            increment();
            repaint();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        two = e.getPoint();
        int y = two.y;
        int x = two.x;
        if (e.getButton() == 0) {
            try {
                a[x][y] = true;
            } catch (Exception m) {
            }
        }
        
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
