import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.*;

/*
 * Class that has the gui the user interacts with to change what is drawn
 */

class ControlPanel extends JPanel implements ChangeListener,ActionListener
 {
 	CubePanel cPanel;
	JSlider xThetaSlider, yThetaSlider, zThetaSlider, customThetaSlider;
	private JTextField pxField;
	private JTextField pyField;
	private JTextField pzField;
	private JTextField zDirField;
	private JTextField xDirField;
	private JTextField yDirField;
	private JLabel plusLabel;
	private JLabel tLabel;
	private JToggleButton toggleCustomVector;
	private JToggleButton toggleWireFrame;
	private JButton btnNewButton;
	private JLabel lblCustomVectorAngle;
	private JPanel panel_3;
	private JTextArea txtrUseTheSlider;
 
 	public ControlPanel(CubePanel cp)
	{
		cPanel=cp;
		
    	xThetaSlider = new JSlider(JSlider.VERTICAL,0,360,0);
		xThetaSlider.setMajorTickSpacing(50);
		xThetaSlider.setMinorTickSpacing(10);
		xThetaSlider.setPaintTicks(true);
		xThetaSlider.setPaintLabels(true);
		xThetaSlider.addChangeListener(this);
		setLayout(new BorderLayout(0, 0));
		JLabel xyzLabel = new JLabel("x,y,z angle adjustment");
		JPanel r = new JPanel();
		r.setLayout(new BorderLayout(0, 0));
		r.add(xyzLabel, BorderLayout.NORTH);
    	r.add(xThetaSlider, BorderLayout.WEST);
		add(r, BorderLayout.WEST);
		
		
		yThetaSlider = new JSlider(JSlider.VERTICAL,0,360,0);
		r.add(yThetaSlider, BorderLayout.CENTER);
		yThetaSlider.addChangeListener(this);
		yThetaSlider.setMajorTickSpacing(50);
		yThetaSlider.setMinorTickSpacing(10);
		yThetaSlider.setPaintTicks(true);
		yThetaSlider.setPaintLabels(true);
		
      zThetaSlider = new JSlider(JSlider.VERTICAL,0,360,0);
      r.add(zThetaSlider, BorderLayout.EAST);
      zThetaSlider.addChangeListener(this);
      zThetaSlider.setMajorTickSpacing(50);
      zThetaSlider.setMinorTickSpacing(10);
      zThetaSlider.setPaintTicks(true);
      zThetaSlider.setPaintLabels(true);
		
		JPanel panel = new JPanel();
		
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		toggleCustomVector = new JToggleButton("Toggle Customm Axis");
		toggleCustomVector.addActionListener(this);
		panel.add(toggleCustomVector, BorderLayout.NORTH);
		
		toggleWireFrame = new JToggleButton("Toggle Wire Frame");
		toggleWireFrame.addActionListener(this);
		panel.add(toggleWireFrame, BorderLayout.SOUTH);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.EAST);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		customThetaSlider = new JSlider(SwingConstants.VERTICAL, 0, 360, 0);
		customThetaSlider.addChangeListener(this);
		customThetaSlider.setPaintTicks(true);
		customThetaSlider.setPaintLabels(true);
		customThetaSlider.setMinorTickSpacing(10);
		customThetaSlider.setMajorTickSpacing(50);
		panel_1.add(customThetaSlider, BorderLayout.WEST);
		
		panel_3 = new JPanel();
		panel_1.add(panel_3, BorderLayout.SOUTH);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_3.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(2, 4, 0, 0));
		
		pxField = new JTextField();
		pxField.setText("0");
		panel_2.add(pxField);
		pxField.setColumns(10);
		
		pyField = new JTextField();
		pyField.setText("0");
		panel_2.add(pyField);
		pyField.setColumns(10);
		
		pzField = new JTextField();
		pzField.setText("0");
		panel_2.add(pzField);
		pzField.setColumns(10);
		
		plusLabel = new JLabel("+");
		panel_2.add(plusLabel);
		
		xDirField = new JTextField();
		xDirField.setText("1");
		panel_2.add(xDirField);
		xDirField.setColumns(10);
		
		yDirField = new JTextField();
		yDirField.setText("1");
		panel_2.add(yDirField);
		yDirField.setColumns(10);
		
		zDirField = new JTextField();
		zDirField.setText("1");
		panel_2.add(zDirField);
		zDirField.setColumns(10);
		
		tLabel = new JLabel("t");
		panel_2.add(tLabel);
		
		btnNewButton = new JButton("Input Custom Vector");
		btnNewButton.addActionListener(this);
		panel_3.add(btnNewButton, BorderLayout.EAST);
		
		lblCustomVectorAngle = new JLabel("Custom Vector Angle Adjuster");
		panel_1.add(lblCustomVectorAngle, BorderLayout.NORTH);
		
		txtrUseTheSlider = new JTextArea();
		txtrUseTheSlider.setLineWrap(true);
		txtrUseTheSlider.setWrapStyleWord(true);
		txtrUseTheSlider.setText("Use the slider on the left to change angle. Input custom vector below by typing in numbers you want and pressing input button");
		txtrUseTheSlider.setEditable(false);
		panel_1.add(txtrUseTheSlider, BorderLayout.CENTER);
    
   }
   
   public void stateChanged(ChangeEvent ev) //sets the slider values to their appropriate attributes in CubePanel
   {
	   
	   cPanel.xTheta=xThetaSlider.getValue();
	   cPanel.yTheta=yThetaSlider.getValue();
	   cPanel.zTheta=zThetaSlider.getValue();
	   cPanel.customTheta=customThetaSlider.getValue();
  	  cPanel.repaint();
	  

	 }//end stateChanged
   
   public void actionPerformed(ActionEvent e){
	   cPanel.isCustomAxis=toggleCustomVector.isSelected();
	   cPanel.isWireFrame=toggleWireFrame.isSelected();
	   cPanel.customPointx=Double.parseDouble(pxField.getText());
	   cPanel.customPointy=Double.parseDouble(pyField.getText());
	   cPanel.customPointz=Double.parseDouble(pzField.getText());
	   cPanel.customDirx=Double.parseDouble(xDirField.getText());
	   cPanel.customDiry=Double.parseDouble(yDirField.getText());
	   cPanel.customDirz=Double.parseDouble(zDirField.getText());
	   cPanel.repaint();

	   
	   


   }

}