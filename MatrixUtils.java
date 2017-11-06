import java.util.ArrayList;

public class MatrixUtils {
	
	boolean zOfCrossNegative(double[]u, double[]v){
		double[]cross=crossProduct(u, v);
		if(cross[2]<-0.1){
			return true;
		}
		return false;
	}
	
	double[]crossProduct(double[]u, double[]v){
        double i = u[1] * v[2] - v[1] * u[2];
        double j = v[0] * u[2] - u[0] * v[2];
        double k = u[0] * v[1] - v[0] * u[1];
        double[]ans={i,j,k};
        return ans;
	}
	
	

	public double[][] convert3DPointTo4Matrix(Point3D p){
		
		double[][]d={{p.x},{p.y},{p.z},{1}};
		return d;
	}
	
	public Point3D convert4MatrixTo3DPoint(double[][]d){
		Point3D p=new Point3D(d[0][0]/d[3][0], d[1][0]/d[3][0], d[2][0]/d[3][0]);
		return p;
	}
	
	
	
	ArrayList<Point3D> rotateAllPointsAroundArbitrary(ArrayList<Point3D> a, double vector_p_x, double vector_p_y, double vector_p_z, double vx, double vy, double vz, double theta){
    	ArrayList<Point3D>ans=new ArrayList<Point3D>();
    	for(int i=0; i<a.size(); i++){
    		Point3D p=a.get(i);
    		p=rotateAroundArbitraryAxis(p, vector_p_x, vector_p_y, vector_p_z, vx, vy, vz, theta);
    		ans.add(p);
    	}
    	return ans;
    }
	
	
	Point3D rotateAroundArbitraryAxis(Point3D p, double vector_p_x, double vector_p_y, double vector_p_z, double vx, double vy, double vz, double theta){
		double v_length=Math.pow(vx, 2.0)+Math.pow(vy, 2.0)+Math.pow(vz, 2.0);
		v_length=Math.pow(v_length, 1.0/2.0);
		double a=vx/v_length;
		double b=vy/v_length;
		double c=vz/v_length;
		double d=Math.pow(b,2.0)+Math.pow(c,2.0);
		d=Math.pow(d, 1.0/2.0);
		p=translate(p, -vector_p_x, -vector_p_y, -vector_p_z);
		if(c!=0.0&&b!=0.0){
		p=arbitraryHelperOne(p,a, b, c, d);
		}
		p=arbitraryHelperTwo(p, a, b, c, d);
		p=rotateAroundZ(p, theta);
		p=arbitraryHelperTwo(p, -a, b, c, d);
		if(c!=0.0&&b!=0.0){
		p=arbitraryHelperOne(p,a,-b,c,d);
		}
		p=translate(p, vector_p_x, vector_p_y, vector_p_z);
		return p;
	}
	
	
	
	Point3D arbitraryHelperTwo(Point3D p, double a, double b, double c, double d){
		double[][]arr=convert3DPointTo4Matrix(p);
		double[][]trans={{d,0,-a,0}, {0,1,0,0},{a,0,d,0},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, arr);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	
	Point3D arbitraryHelperOne(Point3D p, double a, double b, double c, double d){
		double[][]arr=convert3DPointTo4Matrix(p);
		double[][]trans={{1,0,0,0}, {0,c/d,-b/d,0},{0,b/d,c/d,0},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, arr);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	Point3D translate(Point3D p ,double x,double  y,double z){
		double[][]d=convert3DPointTo4Matrix(p);
		double[][]trans={{1,0,0,x}, {0,1,0,y},{0,0,1,z},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, d);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	
	
	ArrayList<Point3D> rotateAllPointsAroundZ(ArrayList<Point3D> a, double theta){
    	ArrayList<Point3D>ans=new ArrayList<Point3D>();
    	for(int i=0; i<a.size(); i++){
    		Point3D p=a.get(i);
    		p=rotateAroundZ(p,theta);
    		ans.add(p);
    	}
    	return ans;
    }
    
	
	public Point3D rotateAroundZ(Point3D p, double theta){
		theta=Math.toRadians(theta);
		double[][]d=convert3DPointTo4Matrix(p);
		double[][]trans={{Math.cos(theta),-Math.sin(theta),0,0}, {Math.sin(theta),Math.cos(theta),0,0},{0,0,1,0},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, d);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	
	
	
	
	ArrayList<Point3D> rotateAllPointsAroundY(ArrayList<Point3D> a, double theta){
    	ArrayList<Point3D>ans=new ArrayList<Point3D>();
    	for(int i=0; i<a.size(); i++){
    		Point3D p=a.get(i);
    		p=rotateAroundY(p,theta);
    		ans.add(p);
    	}
    	return ans;
    }
	
	
	public Point3D rotateAroundY(Point3D p, double theta){
		theta=Math.toRadians(theta);
		double[][]d=convert3DPointTo4Matrix(p);
		double[][]trans={{Math.cos(theta),0,Math.sin(theta),0},{0,1,0,0}, {-Math.sin(theta),0,Math.cos(theta),0},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, d);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	
	
	
	
	ArrayList<Point3D> rotateAllPointsAroundX(ArrayList<Point3D> a, double theta){
    	ArrayList<Point3D>ans=new ArrayList<Point3D>();
    	for(int i=0; i<a.size(); i++){
    		Point3D p=a.get(i);
    		p=rotateAroundX(p,theta);
    		ans.add(p);
    	}
    	return ans;
    }
    
	
	public Point3D rotateAroundX(Point3D p, double theta){
		theta=Math.toRadians(theta);
		double[][]d=convert3DPointTo4Matrix(p);
		double[][]trans={{1,0,0,0},{0,Math.cos(theta),-Math.sin(theta),0}, {0,Math.sin(theta),Math.cos(theta),0},{0,0,0,1}};
		double[][]ans=multiplyMatricies(trans, d);
		Point3D ans_point=convert4MatrixTo3DPoint(ans);
		return ans_point;
	}
	
	
	
	public double[][] multiplyMatricies(double[][] firstMatrix, double[][] secondMatrix) {

        int firstRows = firstMatrix.length;
        int aColumns = firstMatrix[0].length;
        int bRows = secondMatrix.length;
        int bColumns = secondMatrix[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[firstRows][bColumns];
        for (int i = 0; i < firstRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < firstRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }

        return C;
    }
	
	
	
}
