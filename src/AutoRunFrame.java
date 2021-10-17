
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AutoRunFrame extends Thread {
	JFrame frame;
	JButton cancel;
	Font font;
	JLabel Background;
	JLabel photo;
	int counter;
	boolean flag=true;//for stopping unlimited loop
	ArrayList<Photo> photos;
	//************************************************************************************************************
	//constructor
	public AutoRunFrame(int counter,ArrayList<Photo> photos) {
		this.counter=counter;
		this.photos=photos;//for display auto
		frame=new JFrame("نمایش خودکار عکسها");
		frame.setResizable(false);
		font=new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT, 22);
		
		
		try {
			//use an image as a background
			Background = new JLabel(new ImageIcon(ImageIO.read(new File("icons\\im1.jpg"))));
			frame.setContentPane(Background);
			
			Image icon = Toolkit.getDefaultToolkit().getImage("icons\\icon1.jpg");
            frame.setIconImage(icon);
            
			photo=new JLabel();
			photo.setBounds(175,140,1150,480);
			Background.add(photo);
			
			cancel=new RoundButton("لغو نمایش خودکار");
			cancel.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,15));
			cancel.setBackground(Color.PINK);
			cancel.setBounds(700,620,100,100);
			
			//action listener of cancel button that stop display photos auto
			cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					flag=false;
					frame.setVisible(false);
					
				}
			});
			Background.add(cancel);
			
		}catch (IOException e) {
		e.printStackTrace();
	    }
		frame.setSize(1500,800);
	    frame.setLayout(null); 
	    frame.setVisible(true);
	}
	//display photos
	public void run() {
		while(flag) {
			counter++;
			if(counter==photos.size()) 
				counter=0;
			try {
				photo.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(new File(photos.get(counter).address))).getImage().getScaledInstance(photo.getWidth(),photo.getHeight(), Image.SCALE_DEFAULT)));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
