package square;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class squareSpiral extends JPanel implements KeyListener {

    int v;
    int e = 2;
    int n = 100;
    protected double[][][] a;
    boolean r = true;
    Color[] col = {Color.MAGENTA, Color.RED, Color.PINK, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
    boolean c = false;
    boolean s = false;
    boolean u = false;
    boolean d = false;
    int ss = n;
    int dd = e;

    public squareSpiral() {
        addKeyListener(this);
        v = Math.min(getSize().height, getSize().width);
        set();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        v = Math.min(getSize().height, getSize().width);
        set();

        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getSize().width, getSize().height);
        g.setColor(Color.black);
        g.fillRect(0, 0, v, v);

        for (int q = 0; q < n; q++) {
            if (!c) {
                g.setColor(Color.white);
            } else {
                g.setColor(col[q % col.length]);
            }

            g.drawLine((int) a[q][0][0], (int) a[q][0][1], (int) a[q][1][0], (int) a[q][1][1]);
            g.drawLine((int) a[q][1][0], (int) a[q][1][1], (int) a[q][2][0], (int) a[q][2][1]);
            g.drawLine((int) a[q][2][0], (int) a[q][2][1], (int) a[q][3][0], (int) a[q][3][1]);
            g.drawLine((int) a[q][3][0], (int) a[q][3][1], (int) a[q][0][0], (int) a[q][0][1]);

            if (q == n - 1) {
                break;
            }

            for (int w = 0; w < 4; w++) {
                a[q + 1][w][0] = fraction(e, a[q][w][0], a[q][(w + 1) % 4][0]);
                a[q + 1][w][1] = fraction(e, a[q][w][1], a[q][(w + 1) % 4][1]);

                //a[q + 1][w][0] = (int) (((((((a[q][w][0] + a[q][(w + 1) % 4][0]) / 2.0) + a[q][w][0]) / 2.0) + a[q][w][0]) / 2.0)+.5);
                //a[q + 1][w][1] = (int) (((((((a[q][w][1] + a[q][(w + 1) % 4][1]) / 2.0) + a[q][w][1]) / 2.0) + a[q][w][1]) / 2.0)+.5);
            }

            if (r) {
                g.setColor(Color.red);

                for (int m = 0; m < 4; m++) {
                    g.drawLine((int) a[q][m][0], (int) a[q][m][1], (int) a[q + 1][m][0], (int) a[q + 1][m][1]);
                }
            }
        }
        if (s) {
            if (u) {
                n++;
                if (n == ss) {
                    u = false;
                }
            } else if (!u) {
                n--;
                if (n == 2) {
                    u = true;
                }
            }
            repaint();
        }
        if (d) {
            if (u) {
                e++;
                if (e == dd) {
                    u = false;
                }
            } else if (!u) {
                e--;
                if (e == 2) {
                    u = true;
                }
            }
            repaint();
        }

    }

    public void set() {
        a = new double[n][4][2];

        a[0][0][0] = 0;
        a[0][0][1] = 0;

        a[0][1][0] = 0;
        a[0][1][1] = v;

        a[0][2][0] = v;
        a[0][2][1] = v;

        a[0][3][0] = v;
        a[0][3][1] = 0;
    }

    public double fraction(int f, double x1, double x2) {
        return (((f - 1) * x1 + x2) / (double) f);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        //setting the e value
        if (k.getKeyCode() == 0x26) {
            if (d) {
                dd++;
                return;
            }
            e++;
        }
        if (k.getKeyCode() == 0x28) {
            if (d) {
                dd--;
                if (dd == 1) {
                    dd = 2;
                }
                return;
            }
            e--;
            if (e == 1) {
                e = 2;
            }
        }
        if (k.getKeyCode() == 0x30) {
            if (d) {
                dd += 10;
                return;
            }
            e += 10;
        }
        if (k.getKeyCode() == 0x35) {
            if (d) {
                dd += 5;
                return;
            }
            e += 5;
        }
        if (k.getKeyCode() == 0x32) {
            if(d){
                dd = 2;
                return;
            }
            e = 2;
        }

        //setting the n value
        if (k.getKeyCode() == 0x25) {
            if (s) {
                ss -= 5;
                if (ss == 0) {
                    ss = 5;
                }
                return;
            }
            n -= 5;
            if (n == 0) {
                n = 5;
            }
        }
        if (k.getKeyCode() == 0x27) {
            if (s) {
                ss += 5;
                return;
            }
            n += 5;
        }
        if (k.getKeyCode() == 0x20) {
            if (s) {
                ss = 100;
                return;
            }
            n = 100;
        }

        //red outline       
        if (k.getKeyCode() == 0x52) {
            r = !r;
        }
        //colored boxes
        if (k.getKeyCode() == 0x43) {
            c = !c;
        }
        //showing
        if (k.getKeyCode() == 0x53) {
            if (s) {
                n = ss;
            }
            s = !s;
            if (d) {
                d = false;
                e = dd;
            }
            ss = n;
            u = false;
        }

        if (k.getKeyCode() == 0x44) {
            if (d) {
                e = dd;
            }
            d = !d;
            if (s) {
                s = false;
                n = ss;
            }
            dd = e;
            u = false;
        }
        //System.out.println(k.getKeyCode());
        System.out.printf("e: %d\nn: %d\n\n", e, n);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
