import java.awt.*;
import javax.swing.JFrame;

public class GameFrame{
	private GraphicsDevice vc;	//video card
	public GameFrame()	{
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc =  env.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm, JFrame window)	{
		window.setUndecorated(true);	//makes sure default setting to the window is off
		window.setResizable(false);		//makes frame unable to resize in full screen mode
		vc.setFullScreenWindow(window);	//tells the video card what window to display
		if(dm != null && vc.isDisplayChangeSupported())	{	//this makes sure that the video card default settings
			try	{
				vc.setDisplayMode(dm);
			}catch(Exception ex){}
		}
	}
	public Window getFullScreenWindow()	{	//makes the windows full screen
		return vc.getFullScreenWindow();
	}
	public void restoreScreen()	{	//this method restores the screen because it can get locked up w/o this method
		Window w = vc.getFullScreenWindow();
		if(w != null)	{
			w.dispose();	//frees the resources
		}
		vc.setFullScreenWindow(null);	//this makes sure that it isnt in full screen
	}
	
}
