/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package triangle;

import java.awt.*;
import javax.swing.*;
public class triangle extends JApplet{
    int height,width;
    Image backbuffer;
    Graphics backg;
    public void init(){
        width = getSize().width;
        height = getSize().height;
        backbuffer = createImage(width, height);
        backg = backbuffer.getGraphics();
        tri(backg);
    }
    public void tri(Graphics g){
        
        setBackground(Color.white);
        int row=1024;
        int[][] b = new int[row][row+1];
        int color;
        b[0][0]=1;
        for(int s=1;s<=row;s++){
            b[0][s]=0;
        }
        for(int z = 1;z<=row-1;z++){
            for(int x = 0;x<=row;x++){
                try{
                    b[z][x]=(b[z-1][x]+b[z-1][x-1]);
                }catch(Exception e){
                    b[z][x]=b[z-1][x];
                }
            }
        }
        Color q = new Color(0,0,0);
        Color w = new Color(25,25,25);
        Color e = new Color(50,50,50);
        Color r = new Color(75,75,75);
        //-----------------------------------------------------------------------------
        int remander = 4;
        //-----------------------------------------------------------------------------
        int pixel = 5;
        //-----------------------------------------------------------------------------
        for(int c = 0;c<=row-1;c++){
            
            for(int v = 0;v<=row;v++){
                if(b[c][v]!=0){
                    if(b[c][v]<=-1){
                         b[c][v]*=-1;
                    }
                    color = b[c][v]%remander;
                        switch(color){
                            case 0:
                                g.setColor(Color.blue);
                                break;
                            case 1:
                                g.setColor(q);
                                break;
                            case 2:
                                g.setColor(Color.orange);
                                break;
                            case 3:
                                g.setColor(w);
                                break;
                            case 4:
                                g.setColor(Color.green);
                                break;
                            case 5:
                                g.setColor(w);
                                break;
                            case 6:
                                g.setColor(Color.yellow);
                                break;
                            case 7:
                                g.setColor(e);
                                break;
                        }
                    g.fillRect(v*pixel,c*pixel,pixel,pixel);
                }
            }
        }
        int x =400,y=50;
        g.setColor(Color.black);
        g.drawString("KEY",x,y-30);
        g.drawString("remander "+remander,x,y-15);
        if(remander>=1){
            g.drawString("= 0",x,y);
        }
        if(remander>=2){
            g.drawString("= 1",x,y+10);
        }
        if(remander>=3){
            g.drawString("= 2",x,y+20);
        }
        if(remander>=4){
            g.drawString("= 3",x,y+30);
        }
        if(remander>=5){
            g.drawString("= 4",x,y+40);
        }
        if(remander>=6){
            g.drawString("= 5",x,y+50);
        }
        if(remander>=7){
            g.drawString("= 6",x,y+60);
        }
        if(remander>=8){
            g.drawString("= 7",x,y+70);
        }
        if(remander>=1){
            g.setColor(Color.blue);
            g.fillRect(x-10,y-10,10,10);
        }
        if(remander>=2){
            g.setColor(q);
            g.fillRect(x-10,y,10,10);
        }
        if(remander>=3){
            g.setColor(Color.orange);
            g.fillRect(x-10,y+10,10,10);
        }
        if(remander>=4){
           g.setColor(w);
           g.fillRect(x-10,y+20,10,10);
        }
        if(remander>=5){
            g.setColor(Color.green);
            g.fillRect(x-10,y+30,10,10);
        }
        if(remander>=6){
            g.setColor(e);
            g.fillRect(x-10,y+40,10,10);
        }
        if(remander>=7){
            g.setColor(Color.yellow);
            g.fillRect(x-10,y+50,10,10);
        }
        if(remander>=8){
            g.setColor(r);
            g.fillRect(x-10,y+60,10,10);
        }
    }
    public void update(Graphics g) {
        g.drawImage(backbuffer, 0, 0, this);
        showStatus("width: " + (width) + " ,height " + (height));
    }
    public void paint(Graphics g) {
        setBackground(Color.white);
        width = getSize().width;
        height = getSize().height;
        tri(g);
        update(g);
    }
}

