import java.awt.*;
import javax.swing.*;

public class ColorTest 
{
  public static void main(String[] args) 
  {
    JFrame frame = new JFrame();
    frame.setTitle("Colors");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
    ColorPanel cPanel = new ColorPanel();
	 ControlPanel sliders = new ControlPanel(cPanel);
	 Container cp = frame.getContentPane();
	 cp.setLayout(new BorderLayout());
    cp.add(cPanel, BorderLayout.CENTER);
    cp.add(sliders,BorderLayout.EAST);

    frame.pack();
    frame.setVisible(true);
  }//end main
  
}
  
  
 