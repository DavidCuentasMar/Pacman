
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;
public class Principal extends JFrame {

    public Thread movieLoop;
    
    public Canvas c;
    public Pacman P1;
    BufferedImage imagenL;
    /*public int[][] mundo = {{1,1,1,1,1,1,1,1},
        {1,0,0,0,0,0,0,1},
        {1,0,0,0,0,0,0,1},
        {1,0,0,0,1,0,0,1},
        {1,0,0,0,0,0,0,1},
        {1,1,1,1,1,1,1,1}
    };*/

    public int[][] mundo = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public static int[][] food = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,1,1,1,1,0,1,1,1,0,1,0,1,1,1,0,1,1,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1},
            {1,0,1,1,1,0,1,1,0,1,1,0,0,1,1,0,1,1,1,0,1,0,1,0,1},
            {1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,1,0,1,1,1,1},
            {1,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,1,0,1,0,1,0,1,1,1,0,1,0,1,1,1,1,1,1,1,0,1,0,1},
            {1,0,0,0,1,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1},
            {1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,0,1,0,1,1,1,1,1,0,1},
            {1,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };
    public Principal(int w, int h)throws Exception{
        c= new Canvas();
        this.setSize(w, h);
        c.setSize(w, h);
        this.add(c);
        this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
              
            }

            @Override
            public void keyPressed(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{P1.currentDirection=Pacman.UP; break;}
                   case KeyEvent.VK_DOWN:{P1.currentDirection=Pacman.DOWN; break;}
                   case KeyEvent.VK_LEFT:{P1.currentDirection=Pacman.LEFT; break;}
                   case KeyEvent.VK_RIGHT:{P1.currentDirection=Pacman.RIGTH;break;}
               }
            }

            @Override
            public void keyReleased(KeyEvent e) {
               switch(e.getKeyCode()){
                   case KeyEvent.VK_UP:{P1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_DOWN:{P1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_LEFT:{P1.currentDirection=Pacman.NONE; break;}
                   case KeyEvent.VK_RIGHT:{P1.currentDirection=Pacman.NONE; break;}
               }
            }
        });
        P1=new Pacman(40, 40, 4, 4, "BomberBlanco");
        String []names={"arriba","adelante","abajo","atras"};
        P1.loadPics(names);
        movieLoop=new Thread(new Runnable() {
                    InputStream input = new FileInputStream("MAPA.png");
            ImageInputStream imageInput = ImageIO.createImageInputStream(input);            
            BufferedImage imagenL = ImageIO.read(imageInput);         

            @Override
            public void run() {
                c.createBufferStrategy(2);
                Graphics g=c.getBufferStrategy().getDrawGraphics();
              
                long startTime=System.currentTimeMillis();
                long currentTime=0;
                while(true){
                    try{
                        g.setColor(Color.BLACK);
                        g.fillRect(0,0, c.getWidth(), c.getHeight());                             
                        for (int i = 0; i < 15; i++) {
                            for (int j = 0; j < 25; j++) {
                                
                                if(mundo[i][j]==1){
                                    g.setColor(Color.blue);
                                    g.fillRect(40*j,40*i, 40, 40);
                                    
                                }else{
                                    g.setColor(Color.black);
                                    g.fillRect(40*j,40*i, 40, 40);
                                }
                                if (food[i][j]==2) {
                                        g.setColor(Color.red);
                                        
                                        g.fillOval((40*j)+10, (40*i)+10, 20, 20);
                                        
                                }
                            }
                        }
                        g.setColor(Color.green);
                        g.fillRect(40,40, 1, 1);
                        g.fillRect((56+16),(56-16), 1, 1);
                        g.fillRect((56+16),(56+16), 1, 1);
                        g.fillRect((56-16),(56+16), 1, 1);
                        currentTime=System.currentTimeMillis()-startTime;
                        switch(P1.currentDirection){  
                            case Pacman.RIGTH:{if(verificarMove("RIGTH"))P1.moveRigth(currentTime);break;}
                            case Pacman.DOWN:{if(verificarMove("DOWN"))P1.moveDown(currentTime); break;}
                            case Pacman.LEFT:{if(verificarMove("LEFT"))P1.moveLeft(currentTime); break;}
                            case Pacman.UP:{if(verificarMove("UP"))P1.moveUp(currentTime); break;}   
                        }
                        
                        P1.draw(g);
                        Thread.sleep(30);
                        c.getBufferStrategy().show();
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            private boolean verificarMove(String move) {
                if (move.equals("RIGTH")) {
                    if (mundo[(P1.realy-15)/40][(P1.realx+16+P1.vx)/40]==0 && mundo[(P1.realy+15)/40][(P1.realx+16+P1.vx)/40]==0) {
                        if (food[P1.realy/40][P1.realx/40]==2) {
                            food[P1.realy/40][P1.realx/40]=0;
                        }
                        return true;
                    }
                        
                    
                }
                if (move.equals("LEFT")) {
                    if (mundo[(P1.realy-15)/40][(P1.realx-16-P1.vx)/40]==0 && mundo[(P1.realy+15)/40][(P1.realx-16-P1.vx)/40]==0) {
                        if (food[P1.realy/40][P1.realx/40]==2) {
                            food[P1.realy/40][P1.realx/40]=0;
                        }
                        return true;
                    }
                    
                }
                if (move.equals("DOWN")) {
                    if (mundo[(P1.realy+15+P1.vy)/40][(P1.realx-16)/40]==0 && mundo[(P1.realy+15+P1.vy)/40][(P1.realx+15)/40]==0) {
                        if (food[P1.realy/40][P1.realx/40]==2) {
                            food[P1.realy/40][P1.realx/40]=0;
                        }
                        return true;
                    }
                }
                if (move.equals("UP")) {
                    if (mundo[(P1.realy-15-P1.vy)/40][(P1.realx-16)/40]==0 && mundo[(P1.realy-15-P1.vy)/40][(P1.realx+16)/40]==0) {
                        if (food[P1.realy/40][P1.realx/40]==2) {
                            food[P1.realy/40][P1.realx/40]=0;
                        }
                        return true;
                    }
                }
                
                return false;
            }
        });
    }
    
    public static void main(String[] args) {
        try{
            
            Principal p= new Principal(1000,625);
            p.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            p.setVisible(true);
            p.movieLoop.start();
            int cont = 10;
            Random rand = new Random();
            while(cont>=0){
                int i = rand.nextInt(14) + 1;
                int j = rand.nextInt(24) + 1;
                if (food[i][j] != 1 && food[i][j] != 2 ) {
                    food[i][j] = 2;
                    cont--;
                }        
            }
            
 
            
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

