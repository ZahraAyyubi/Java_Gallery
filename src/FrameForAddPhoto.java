import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FrameForAddPhoto {
	JFrame frame;
	Font font;
	JLabel Background;
	JPanel panel;
	JLabel label,photoLabel;
	JButton addphoto;
	JTextField title,address;
	JTextArea comment;
	JButton save,cancle;
	//********************************************************************************************************************************
	public FrameForAddPhoto() {
		frame=new JFrame("افزودن عکس جدید");
		frame.setResizable(false);
		font=new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT, 18);//change font
		try {
			//set an image as a background
			Background = new JLabel(new ImageIcon(ImageIO.read(new File("icons\\back2.jpg"))));
			frame.setContentPane(Background);
			Image icon = Toolkit.getDefaultToolkit().getImage("icons\\icon1.jpg");
            frame.setIconImage(icon);
            
			panel=new JPanel();
			panel.setBackground(Color.darkGray);
			panel.setBounds(0,0, 700, 800);
			
			label=new JLabel("عنوان");
			label.setForeground(Color.lightGray);
			label.setFont(font);
			label.setBounds(600,2,50,50);
			panel.add(label);
			
			title=new JTextField("");
			title.setBounds(20,15,500,30);
            title.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
            title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panel.add(title);
			
			label=new JLabel("آدرس");
			label.setForeground(Color.lightGray);
			label.setFont(font);
			label.setBounds(600,60,100,50);
			panel.add(label);
			
			
			address=new JTextField();
			address.setBounds(120,72,400,30);
			panel.add(address);
			
			addphoto=new JButton("جستجو");
			addphoto.setFont(new Font("IRBadr", Font.LAYOUT_RIGHT_TO_LEFT,15));
			addphoto.setBackground(Color.lightGray);
			addphoto.setBounds(20,72,100,30);
			panel.add(addphoto);
			
			label=new JLabel("توضیحات");
			label.setForeground(Color.lightGray);
			label.setFont(font);
			label.setBounds(575,120,90,50);
			panel.add(label);
			
			comment=new JTextArea();
            comment.setFont(new Font("B Nazanin",Font.LAYOUT_RIGHT_TO_LEFT, 15));
			comment.setBounds(20,120,500,100);
            comment.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
			panel.add(comment);
			
			photoLabel=new JLabel();
			
			JScrollPane js = new JScrollPane(photoLabel);
			js.setBounds(20,240,640,460);
		    js.setPreferredSize(new Dimension(200,200));
			panel.add(js);
			
			save=new JButton("ذخیره اطلاعات وخروج");
			save.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,18));
			save.setBackground(Color.lightGray);
			save.setBounds(350,700,310,40);
			panel.add(save);

			cancle=new JButton("لغو");
			cancle.setFont(new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT,18));
			cancle.setBackground(Color.lightGray);
			cancle.setBounds(20,700,330,40);
			
			panel.add(cancle);
			
			panel.setLayout(null);
			frame.add(panel);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setSize(700, 800);
	    frame.setLayout(null); 
	    frame.setVisible(true);
	}
	
	
}
