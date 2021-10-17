import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class FrameForDisplay {
	JFrame frame;
	Font font;
	JLabel Background;
	JLabel photo;
	JButton next,previous,displayAuto,cancel = null;
	JTextArea comment;
	JTextField title;
	JPanel panel;
	JButton saveChanges,delete;
	JLabel label,dateModified,creationDate;
	//*******************************************************************************************************************
	//Constructor
	public FrameForDisplay() {
	frame=new JFrame("نمایش عکسهای من");
	frame.setResizable(false);
	font=new Font("IRBadr", Font.LAYOUT_RIGHT_TO_LEFT, 20);//change font
	
	try {
		Background = new JLabel(new ImageIcon(ImageIO.read(new File("icons\\back.jpg"))));
		frame.setContentPane(Background);
		Image icon = Toolkit.getDefaultToolkit().getImage("icons\\icon1.jpg");
        frame.setIconImage(icon);
        
        panel=new JPanel();
	    panel.setBounds(1100,50,150,580);
	    panel.setLayout(null);
	    panel.setBackground(Color.LIGHT_GRAY);
        
		next=new RoundButton("بعدی");
		next.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,15));
		next.setBackground(new Color(250,99,100));
		next.setBounds(25,40,100,100);
		panel.add(next);
		
		displayAuto=new RoundButton("نمایش خودکار");
		displayAuto.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,13));
		displayAuto.setBackground(Color.pink);
		displayAuto.setBounds(25,160,100,100);
		panel.add(displayAuto);
		
		previous=new RoundButton("قبلی");
		previous.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,15));
		previous.setBackground(new Color(250,99,100));
		previous.setBounds(25,280,100,100);
		panel.add(previous);
		
		cancel=new RoundButton("خروج");
		cancel.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,12));
		cancel.setBackground(new Color(250,50,52));
		cancel.setBounds(25,400,100,100);
		panel.add(cancel);
		
		
		
	    Background.add(panel);
	    
		photo=new JLabel();
		
		JScrollPane js = new JScrollPane(photo);
		js.setBounds(100,50,1000,550);
	    js.setPreferredSize(new Dimension(200,200));
	    Background.add(js);
	    
	    panel=new JPanel();
	    panel.setBounds(100,600,1150,155);
	    panel.setLayout(null);
	    panel.setBackground(Color.LIGHT_GRAY);
	    
	    label=new JLabel("عنوان");
	    label.setBounds(1080,0,100,80);
		label.setFont(font);
		panel.add(label);
		
		title=new JTextField();
	    title.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,14));
	    title.setBounds(850,20,200,30);
        title.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    panel.add(title);
		
	   
	    
	    label=new JLabel("توضیحات");
	    label.setBounds(1060,40,90,100);
		label.setFont(font);
		panel.add(label);
		
		comment=new JTextArea();
	    comment.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,14));
	    comment.setBounds(700,60,350,80);
        comment.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
	    panel.add(comment);
	    
	    label=new JLabel("تاریخ افزوده شدن به گالری");
	    label.setBounds(250,18,300,50);
		label.setFont(font);
		panel.add(label);
		
		creationDate=new JLabel();
		creationDate.setBounds(10,0,300,90);
		creationDate.setFont(font);
		panel.add(creationDate);
		
	    label=new JLabel("تاریخ آخرین ویرایش");
	    label.setBounds(290,70,300,50);
		label.setFont(font);
		panel.add(label);
	    
		dateModified=new JLabel();
		dateModified.setBounds(10,50,300,90);
		dateModified.setFont(font);
		panel.add(dateModified);
		
	    saveChanges=new RoundButton("ثبت تغییرات");
	    saveChanges.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,13));
		saveChanges.setBackground(new Color(250,99,100));
	    saveChanges.setBounds(553,60,90,90);
	    panel.add(saveChanges);
	    
	    delete=new RoundButton("حذف");
	    delete.setFont(new Font("B Nazanin", Font.LAYOUT_RIGHT_TO_LEFT,12));
	    delete.setBackground(Color.pink);
	    delete.setBounds(567,1,60,60);
	    panel.add(delete);
	    
	    Background.add(panel);

		} catch (Exception e) {
			e.printStackTrace();
		}
		frame.setSize(1400,850);
	    frame.setLayout(null); 
	    frame.setVisible(true);
	}

}
