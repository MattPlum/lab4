

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel{
	final int frameCount = 10;
    int picNum = 0;					//picNum is the index of pics[], 
    BufferedImage[] pics;
    int xloc = 0;
    int yloc = 0;
    
    static int imgWidth = 165;
    static int imgHeight = 165;
    final int xIncr = 8;
    final int yIncr = 2;
    
    int thisXIncr = xIncr;
    int thisYIncr = yIncr;
    
    public Object getImageWidth() {
    	this.imgWidth = imgWidth;
    	return imgWidth;
    }

    public Object getImageHeight() {
    	this.imgHeight = imgHeight;
    	return imgHeight;
    }
    
    public void update(Graphics x, Object y, Object direct) {
    	// TODO Auto-generated method stub
    	
    }
    
    public void paint(Graphics g) {//logic
    	picNum = (picNum + 1) % frameCount;
    	
    	//Switch x or y direction in case of wall collision
    	if(xloc>(Model.frameWidth - imgWidth) || xloc < -20) { 			//if x location of the image is greater than where collision occurs, OR 
    		thisXIncr = (-1)*thisXIncr;								//negate its direction
    	}
    	if(yloc>(Model.frameHeight - imgHeight - 25) || yloc < -20) {		//if y location of the image is greater than where collision occurs,
    		thisYIncr = (-1)*thisYIncr;								//negate its direction
    	}
    	
    	//Choose correct image
    	if(thisYIncr < 0 && thisXIncr > 0) {		//if y location is negative and x location is positive, 
    		picNum = picNum+frameCount;				//pic[10]
    	}
    	if(thisYIncr > 0 && thisXIncr < 0) {
    		picNum = picNum+(2*frameCount);
    	}
    	if(thisYIncr<0 && thisXIncr<0) {
    		picNum = picNum+(3*frameCount);
    	}
    	if(thisYIncr>0 && thisXIncr>0) {
    		//picNum = picNum+(1*frameCount);
    	}
    	
    	g.drawImage(pics[picNum], xloc+=thisXIncr, yloc+=thisYIncr, Color.gray, this);
    	
    }
    
    public View(){//View
    	File southeast = new File("images/orc/orc_forward_southeast.png");
    	File northeast = new File("images/orc/orc_forward_northeast.png");
    	File southwest = new File("images/orc/orc_forward_southwest.png");
    	File northwest = new File("images/orc/orc_forward_northwest.png");
    	File south = new File("images/orc/orc_forward_south.png");
    	File north = new File("images/orc/orc_forward_north.png");
    	File west = new File("images/orc/orc_forward_west.png");
    	File east = new File("images/orc/orc_forward_east.png");
    	BufferedImage img = createImage(southeast);
    	BufferedImage img2 = createImage(northeast);
    	BufferedImage img3 = createImage(southwest);
    	BufferedImage img4 = createImage(northwest);
    	BufferedImage img5 = createImage(south);
    	BufferedImage img6 = createImage(north);
    	BufferedImage img7 = createImage(west);
    	BufferedImage img8 = createImage(east);
    	pics = new BufferedImage[80];
    	for(int i = 0; i < frameCount; i++) {
    		pics[i] = img.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+frameCount] = img2.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(2*frameCount)] = img3.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(3*frameCount)] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(4*frameCount)] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(5*frameCount)] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(6*frameCount)] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    		pics[i+(7*frameCount)] = img4.getSubimage(imgWidth*i, 0, imgWidth, imgHeight);
    	}
    }  
    private BufferedImage createImage(File filo){//View
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(filo);
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	
    }
    
public static void main(String[] args) {//view
    	JFrame frame = new JFrame();
    	frame.getContentPane().add(new View());
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(Model.frameWidth, Model.frameHeight);
    	frame.setVisible(true);
    	for(int i = 0; i < 1000; i++){
    		frame.repaint();
    		try {
    			Thread.sleep(100);
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    	
    	
    }





}