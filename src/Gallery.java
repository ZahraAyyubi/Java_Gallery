import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



public class Gallery implements FileIO{
	MainFrame mainFrame;
	ArrayList<Photo> myPhotos=new ArrayList<Photo>();//for holding information of all photos
	int counter=0;//index of photo that is displaying in arrayList
	
	//**********************************************************************************************************************
	//constructor
	public Gallery() {
		
		mainFrame=new MainFrame();
		//action of addNewPhoto button in main frame
		mainFrame.addNewPhoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addNewPhotoToGallery();
					
		}});
		//action listener of seePhotos button in main frame				
		mainFrame.seePhotos.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				displayPhotos();	
		}});
		//action listener of exit button in main frame
		mainFrame.exit.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
					
				int a = JOptionPane.showConfirmDialog(mainFrame.MainFrame,"  قصد خارج شدن دارید؟  ");
				if (a == JOptionPane.YES_OPTION) { 
					//mainFrame.MainFrame.setDefaultCloseOperation(mainFrame.MainFrame.EXIT_ON_CLOSE);
					write();//save and write informations of photos in a file
					System.exit(0);
				}
							
				
			}
		});
		//action of addPhoto that is a menuItem
		mainFrame.addPhoto.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				addNewPhotoToGallery();

			}
		});
		//action of addPhoto that is a menuItem
		mainFrame.displayPhotos.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				displayPhotos();
			}
		});
	}

	@Override
	public void read() {//read data from file
		Scanner sc = null;
		try {
			sc=new Scanner(new FileReader("data.txt"));
			while(sc.hasNext()) {
				myPhotos.add(new Photo(sc.nextLine(),sc.nextLine(),sc.nextLine(),new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt()),new GregorianCalendar(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt())));
				sc.nextLine();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			sc.close();
		}
	}

	@Override
	public void write() {//writing data from arrayList to a file
		PrintWriter out=null;
		try {
			out=new PrintWriter("data.txt");
			for(Photo photo:myPhotos) {
				out.println(photo.title);
				out.println(photo.comment);
				out.println(photo.address);
				out.println(photo.creationDate.get(Calendar.YEAR)+" "+photo.creationDate.get(Calendar.MONTH)+" "+photo.creationDate.get(Calendar.DATE)+" "+photo.creationDate.get(Calendar.HOUR)+" "+photo.creationDate.get(Calendar.MINUTE)+" "+photo.dateModified.get(Calendar.YEAR)+" "+photo.dateModified.get(Calendar.MONTH)+" "+photo.dateModified.get(Calendar.DATE)+" "+photo.dateModified.get(Calendar.HOUR)+" "+photo.dateModified.get(Calendar.MINUTE));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			out.close();
		}
		
	}
	
	//This method add new photo to array of photos
	public void addNewPhotoToGallery() {
		
		FrameForAddPhoto frameForAddPhoto=new FrameForAddPhoto();
		
		//action listener of addPhoto button that add path of photo that user choose
		frameForAddPhoto.addphoto.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser("E:\\UniFiles\\Projects\\Gallery\\img");//for choose photo from user intended path
				int i = fc.showOpenDialog(new JFrame() {}); 
				if (i == JFileChooser.APPROVE_OPTION) {  
					File f = fc.getSelectedFile();   
					String filepath = f.getAbsolutePath(); 
					frameForAddPhoto.address.setText(filepath);
					try {
						frameForAddPhoto.photoLabel.setIcon(new ImageIcon( new ImageIcon(ImageIO.read(new File(filepath))).getImage().getScaledInstance(frameForAddPhoto.photoLabel.getWidth(), frameForAddPhoto.photoLabel.getHeight(), Image.SCALE_DEFAULT) ));
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
					
			}
				
		});
			
			
		//action listener of save button in frame of adding new photo that get informations that user entered then add photo to array
		frameForAddPhoto.save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(frameForAddPhoto.address.getText().isEmpty()) {//checking that address of photo not be empty
					JOptionPane.showMessageDialog(frameForAddPhoto.frame,"فیلد آدرس خالی است","Alert",JOptionPane.WARNING_MESSAGE);   
				}else {
					myPhotos.add(new Photo(frameForAddPhoto.title.getText(),frameForAddPhoto.comment.getText(),frameForAddPhoto.address.getText()));
					write();
					frameForAddPhoto.frame.setVisible(false);
				}
			}
				
		});
			
		frameForAddPhoto.cancle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frameForAddPhoto.frame.setVisible(false);
			}
				
		});
			
	}//end of method of addNewPhotoToGallery
	
	//this method display photos 
	public void displayPhotos() {
		//imageIcons=new ArrayList<ImageIcon>();//for holding images
		myPhotos.sort((Photo photo1,Photo photo2) -> photo1.title.compareToIgnoreCase(photo2.title));//sort photos respectively their title
		FrameForDisplay frameForDisplay=new FrameForDisplay();//display frame for displaying photo

		try {
			frameForDisplay.photo.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(new File(myPhotos.get(counter).address))).getImage().getScaledInstance(frameForDisplay.photo.getWidth(), frameForDisplay.photo.getHeight(), Image.SCALE_DEFAULT)));
			frameForDisplay.title.setText(myPhotos.get(counter).title);
			frameForDisplay.comment.setText(myPhotos.get(counter).comment);
			frameForDisplay.creationDate.setText(myPhotos.get(counter).creationDate.getTime().toString());
			frameForDisplay.dateModified.setText(myPhotos.get(counter).dateModified.getTime().toString());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//action of saveChanges button that save the changes of title and comment of current photo
		frameForDisplay.saveChanges.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myPhotos.get(counter).title=frameForDisplay.title.getText();
				myPhotos.get(counter).comment=frameForDisplay.comment.getText();
				JOptionPane.showMessageDialog(frameForDisplay.Background, "   !تغییرات با موفقیت ثبت شد ;)"); 
			}
		});
		
		//action of delete button that remove current photo from array of photos
		frameForDisplay.delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				myPhotos.remove(counter);
				//displaying next photo instead of photo that removed
				if(counter>=myPhotos.size()) 
					counter=0;
				try {
					frameForDisplay.photo.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(new File(myPhotos.get(counter).address))).getImage().getScaledInstance(frameForDisplay.photo.getWidth(), frameForDisplay.photo.getHeight(), Image.SCALE_DEFAULT)));
					frameForDisplay.title.setText(myPhotos.get(counter).title);
					frameForDisplay.comment.setText(myPhotos.get(counter).comment);
					frameForDisplay.creationDate.setText(myPhotos.get(counter).creationDate.getTime().toString());
					frameForDisplay.dateModified.setText(myPhotos.get(counter).dateModified.getTime().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		} );
		
		//action of next button that display next photo
		frameForDisplay.next.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				counter++;
				if(counter>=myPhotos.size()) 
					counter=0;
				try {
					frameForDisplay.photo.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(new File(myPhotos.get(counter).address))).getImage().getScaledInstance(frameForDisplay.photo.getWidth(), frameForDisplay.photo.getHeight(), Image.SCALE_DEFAULT)));
					frameForDisplay.title.setText(myPhotos.get(counter).title);
					frameForDisplay.comment.setText(myPhotos.get(counter).comment);
					frameForDisplay.creationDate.setText(myPhotos.get(counter).creationDate.getTime().toString());
					frameForDisplay.dateModified.setText(myPhotos.get(counter).dateModified.getTime().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		//action listener of previous button that display
		frameForDisplay.previous.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				counter--;
				if(counter<=-1) 
					counter=myPhotos.size()-1;
				try {
					frameForDisplay.photo.setIcon(new ImageIcon(new ImageIcon(ImageIO.read(new File(myPhotos.get(counter).address))).getImage().getScaledInstance(frameForDisplay.photo.getWidth(), frameForDisplay.photo.getHeight(), Image.SCALE_DEFAULT)));
					frameForDisplay.title.setText(myPhotos.get(counter).title);
					frameForDisplay.comment.setText(myPhotos.get(counter).comment);
					frameForDisplay.creationDate.setText(myPhotos.get(counter).creationDate.getTime().toString());
					frameForDisplay.dateModified.setText(myPhotos.get(counter).dateModified.getTime().toString());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		
		//action listener of displayAuto button that display photos automatically
		frameForDisplay.displayAuto.addActionListener(new ActionListener() {
				
			@Override
			public void actionPerformed(ActionEvent e) {
				AutoRunFrame autoRunFrame=new AutoRunFrame(counter, myPhotos);//display frame of autoRun
				autoRunFrame.start();//an thread that changes photo every 2 second

			}
		});
		
		//action listener of cancel button in frameForDisplay 
		frameForDisplay.cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frameForDisplay.frame.setVisible(false);
			}
		});
	
	}
}
