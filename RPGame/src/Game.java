import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Game  extends JFrame{
	public static void main(String[] args) {
		DisplayMode dm = new DisplayMode(1280,1024,16,DisplayMode.REFRESH_RATE_UNKNOWN);
		Game x = new Game();
		x.run(dm);
	}
	private GameFrame frame;
	private Image bg;
	private Animation a;
	
	public void loadPics()	{
		bg = new ImageIcon(Game.class.getResource("background.png")).getImage();
		Image sword[] = new Image[11];
		sword[0] = new ImageIcon(Game.class.getResource("sword1.png")).getImage();
		sword[1] = new ImageIcon(Game.class.getResource("sword2.png")).getImage();
		sword[2] = new ImageIcon(Game.class.getResource("sword3.png")).getImage();
		sword[3] = new ImageIcon(Game.class.getResource("sword4.png")).getImage();
		sword[4] = new ImageIcon(Game.class.getResource("sword5.png")).getImage();
		sword[5] = new ImageIcon(Game.class.getResource("sword6.png")).getImage();
		sword[6] = new ImageIcon(Game.class.getResource("sword7.png")).getImage();
		sword[7] = new ImageIcon(Game.class.getResource("sword8.png")).getImage();
		sword[8] = new ImageIcon(Game.class.getResource("sword9.png")).getImage();
		sword[9] = new ImageIcon(Game.class.getResource("sword10.png")).getImage();
		sword[10] = new ImageIcon(Game.class.getResource("sword11.png")).getImage();
		a = new Animation();
		for(int i=0; i<11;i++)	{
			a.addScene(sword[i],50);
		}
		
	}
	
	public void run(DisplayMode dm)	{
		frame = new GameFrame();
		try	{
			frame.setFullScreen(dm, new JFrame());
			loadPics();
			movieLoop();
		} finally	{
			frame.restoreScreen();
		}
	}
	public void movieLoop()	{
		long startingTime = System.currentTimeMillis();
		long totalTime = startingTime;
		
		while(totalTime - startingTime < 5000 )	{
			long timePassed = System.currentTimeMillis()-totalTime;
			totalTime += timePassed;
			a.update(timePassed);
			
			Graphics g = frame.getFullScreenWindow().getGraphics();
			draw(g);
			g.dispose();
			try	{
				Thread.sleep(20);
			}catch(Exception ex){}
		}
	}
	public void draw(Graphics g)	{
		g.drawImage(bg, 0,0, null);
		g.drawImage(a.getImage(),0,0,null);
	}
	
	public void paint(Graphics g)	{
		if(g instanceof Graphics2D)	{
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		}
		g.drawString("TEST", 400, 300);
	}

}