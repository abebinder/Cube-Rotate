


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

public class ColorPanel extends JPanel
{
	double xTheta;
	double yTheta;
	double zTheta;
	double customTheta;
	MatrixUtils utils;
	Color[] colors;
	boolean isWireFrame;
	boolean isCustomAxis;
	double customPointx;
	double customPointy;
	double customPointz;
	double customDirx;
	double customDiry;
	double customDirz;
	 
    public ColorPanel ()
	 {
    	isWireFrame=false;
    	isCustomAxis=false;
    	xTheta=0;
    	yTheta=0;
    	zTheta=0;
    	customPointx=0;
    	customPointy=0;
    	customPointz=0;
    	customDirx=1;
    	customDiry=1;
    	customDirz=1;
		setPreferredSize(new Dimension(500,400));
		setBackground(Color.lightGray);
		utils=new MatrixUtils();
		colors=new Color[6];
		colors[0]=Color.BLACK;
		colors[1]=Color.BLUE;
		colors[2]=Color.YELLOW;
		colors[3]=Color.RED;
		colors[4]=Color.MAGENTA;
		colors[5]=Color.ORANGE;




		/* Below, I defined each unique shape. All the circles and their intersects. */
		
    }

    public void paintComponent(Graphics g)
    {
    	/* Each shape  is painted the proper color combination made
    	 * up using numbers on the sliders.
    	 *  */
    	
		super.paintComponent(g);  //without this no background color set.
	
		Graphics2D g2d = (Graphics2D)g; //cast so we can use JAVA2D.
		g2d.translate(getWidth()/2,getHeight()/2);
		g2d.scale(1, -1);
		
		ArrayList<Point3D>v=new ArrayList<Point3D>();
		v.add(new Point3D(1, 1, 1));
		v.add(new Point3D(1, -1, 1));
		v.add(new Point3D(-1,-1,1));
		v.add(new Point3D(-1, 1,1));
		v.add(new Point3D(-1,1,-1));
		v.add(new Point3D(-1,-1,-1));
		v.add(new Point3D(1,-1,-1));
		v.add(new Point3D(1,1,-1));
		
		ArrayList<Face>f=new ArrayList<Face>();
		f.add(new Face(0,1,2,3));
		f.add(new Face(3,2,5,4));
		f.add(new Face(4,5,6,7));
		f.add(new Face(7,6,1,0));
		f.add(new Face(7,0,3,4));
		f.add(new Face(1,6,5,2));
		ArrayList<Point3D>updated=new ArrayList<Point3D>();
		if(!isCustomAxis){
			updated=utils.rotateAllPointsAroundX((ArrayList<Point3D>)v.clone(), xTheta);
			updated=utils.rotateAllPointsAroundY(updated, yTheta);
			updated=utils.rotateAllPointsAroundZ(updated, zTheta);
		}
		
		else{
			updated=utils.rotateAllPointsAroundArbitrary((ArrayList<Point3D>)v.clone(),
					customPointx, customPointy, customPointz, customDirx, customDiry,customDirz, customTheta);
		}
		
		scalePoint3D(updated);
		
		ArrayList<Point2D.Double> two_d=convert3DPointsTo2DPoints(updated, 1000);
		
		if(isWireFrame){
			drawCube(g2d, f, two_d);
		}
		else{
		
		paintCube(g2d, f, two_d, updated);
		}
		
		
		
		


	 }
    
    
   
    
    
	 
    ArrayList<Point2D.Double> convert3DPointsTo2DPoints(ArrayList<Point3D> a, double e){
		ArrayList<Point2D.Double>two_list=new ArrayList<Point2D.Double>();
    	for(int i=0; i<a.size(); i++){
			Point3D p=a.get(i);
			double x=p.x;
			double y=p.y;
			double z=p.z;
			Point2D.Double n=new Point2D.Double(x/(1-z/e), y/(1-z/e));
			two_list.add(n);
		}
		return two_list;
	}
    
    void scalePoint3D(ArrayList<Point3D> a){
    	for(int i=0; i<a.size(); i++){
    		a.get(i).x=a.get(i).x*50;
    		a.get(i).y=a.get(i).y*50;
    		a.get(i).z=a.get(i).z*50;


    	}
    }
	
    
    void paintCube(Graphics2D g2d, ArrayList<Face> f, ArrayList<Point2D.Double> a, ArrayList<Point3D> three){
		for(int i=0; i<6; i++){
			Face face=f.get(i);
			double p0x=three.get(face.v0index).x;
			double p0y=three.get(face.v0index).y;
			double p0z=three.get(face.v0index).z;

			double p1x=three.get(face.v1index).x;
			double p1y=three.get(face.v1index).y;
			double p1z=three.get(face.v1index).z;

			double p2x=three.get(face.v2index).x;
			double p2y=three.get(face.v2index).y;
			double p2z=three.get(face.v2index).z;

			double[]v1={p0x-p1x,p0y-p1y,p0z-p1z};
			double[]v2={p1x-p2x,p1y-p2y,p1z-p2z};
			if(utils.zOfCrossNegative(v1, v2)){
			g2d.setColor(colors[i]);
			paintFace(g2d, f.get(i), a);
			}
		}
	}
    
    void paintFace(Graphics2D g2d, Face f, ArrayList<Point2D.Double> a){
		Point2D.Double p0=a.get(f.v0index);
		Point2D.Double p1=a.get(f.v1index);
		Point2D.Double p2=a.get(f.v2index);
		Point2D.Double p3=a.get(f.v3index);
		
		Path2D.Double path=new Path2D.Double();
		path.moveTo(p0.x, p0.y);
		path.lineTo(p1.x,p1.y);
		path.lineTo(p2.x,p2.y);
		path.lineTo(p3.x,p3.y);
		path.closePath();
		g2d.fill(path);


	}
    
    
    
	void drawCube(Graphics2D g2d, ArrayList<Face> f, ArrayList<Point2D.Double> a){
		for(int i=0; i<6; i++){
			drawFace(g2d, f.get(i), a);
		}
	}
	
	void drawFace(Graphics2D g2d, Face f, ArrayList<Point2D.Double> a){
		Point2D.Double p0=a.get(f.v0index);
		Point2D.Double p1=a.get(f.v1index);
		Point2D.Double p2=a.get(f.v2index);
		Point2D.Double p3=a.get(f.v3index);

		g2d.draw(new Line2D.Double(p0,p1));
		g2d.draw(new Line2D.Double(p1,p2));
		g2d.draw(new Line2D.Double(p2,p3));
		g2d.draw(new Line2D.Double(p3,p0));


	}



}
