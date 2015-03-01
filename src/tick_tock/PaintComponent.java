package tick_tock;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class PaintComponent extends JComponent {
private Clock clock;
public PaintComponent(){
	this.clock= new Clock();
}
public Clock getClock(){
	return clock;
}

protected void paintComponent(Graphics g) {
	//I made the frame 500x700 but based the clock as if it was in a frame of 500x500.
	//the remaining 200 shoukd be used for setting the alarms
	super.paintComponent(g);
	g.setColor(Color.BLACK);
	g.drawString(clock.getTime().toString(), 10, 10);
	g.fillOval(25, 10, 450, 450);
	g.setColor(Color.YELLOW);
	//12
	g.fillOval(240, 35, 25, 25);
	//6
	g.fillOval(240, 410, 25, 25);
	//9
	g.fillOval(50, 215, 25,25);
	//3
	g.fillOval(425, 215, 25,25);
	
	g.setColor(Color.WHITE);
	//button for handles
	g.fillOval(245, 225, 10, 10);
	//min handle
	
	//hour handle
	
	//second handle
	
}
}

