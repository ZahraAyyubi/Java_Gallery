import java.awt.Color;
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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame {
	JFrame MainFrame=new JFrame("گالری من");
	JLabel Background = null;
	JButton addNewPhoto;
	JButton seePhotos;
	JButton exit;
	Font font;
	JMenuBar menuBar;
	JMenu file;
	JMenuItem addPhoto;
	JMenuItem displayPhotos;
	//******************************************************************************************************************
	//Constructor
	public MainFrame() {
		font=new Font("IranNastaliq", Font.LAYOUT_RIGHT_TO_LEFT, 28);
		try {
			//set a image as a background
			Background=new JLabel(new ImageIcon(ImageIO.read(new File("icons\\22.jpg"))));
            MainFrame.setContentPane(Background);
            Image icon = Toolkit.getDefaultToolkit().getImage("icons\\icon1.jpg");
            MainFrame.setIconImage(icon);
            
            Background.setLayout(null);
            
            menuBar = new JMenuBar(); 
            file = new JMenu("امکانات");
            addPhoto=new JMenuItem("اضافه کردن عکس به گالری");
            displayPhotos=new JMenuItem("نمایش عکس های  گالری");
           
            file.add(addPhoto);
            file.addSeparator();
            file.add(displayPhotos);
            
            menuBar.add(file);
            MainFrame.setJMenuBar(menuBar);
            
            addNewPhoto=new RoundButton("افزودن  عکس جدید به گالری");
			addNewPhoto.setFont(font);
			addNewPhoto.setBackground(Color.pink);
			addNewPhoto.setBounds(50, 300, 200, 200);
			addNewPhoto.setToolTipText("ّبرای اضافه کردن عکس به گالری روی این دکمه کلیک نمایید");
			Background.add(addNewPhoto);
			
			seePhotos=new RoundButton("نمایش عکسهای من");
			seePhotos.setFont(font);
			seePhotos.setBackground(Color.PINK);
			seePhotos.setBounds(255, 240, 220, 220);
			seePhotos.setToolTipText("برای نمایش عکس های گالری روی این دکمه کلیک نمایید");
			Background.add(seePhotos);
			
			exit=new RoundButton("خروج از برنامه");
			exit.setFont(font);
			exit.setBackground(Color.PINK);
			exit.setBounds(235, 495, 150, 150);
			Background.add(exit);
			
        } catch (IOException e) {
            e.printStackTrace();
        }
		MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		MainFrame.setSize(600, 600);
        MainFrame.pack();
		MainFrame.setVisible(true);
	}	
}
