import java.awt.*;
import javax.swing.*;
/*
 * Puts the cubepanel and controlpanel next to each other and links them and 
 * displays
 */
public class Main 
{
  public static void main(String[] args) 
  {
    JFrame frame = new JFrame();
    frame.setTitle("Colors");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
    CubePanel cPanel = new CubePanel();
	 ControlPanel sliders = new ControlPanel(cPanel);
	 Container cp = frame.getContentPane();
	 cp.setLayout(new BorderLayout());
    cp.add(cPanel, BorderLayout.CENTER);
    cp.add(sliders,BorderLayout.EAST);

    frame.pack();
    frame.setVisible(true);
  }
  
}
  
  
 