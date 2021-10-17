import java.util.Calendar;

public class Photo {
	String title;
	String comment;
	String address;
	Calendar creationDate;
	Calendar dateModified;
	//****************************************************************************************************************
	public Photo(String title,String comment,String address,Calendar creationDate,Calendar dateModified) {//for creating object for the time that data loaded from file
		this.title=title;
		this.comment=comment;
		this.address=address;
		this.creationDate=creationDate;
		this.dateModified=dateModified;
	}
	public Photo(String title,String comment,String address) {//for creating object for the time that user entered information 
		this.title=title;
		this.comment=comment;
		this.address=address;
		this.creationDate=Calendar.getInstance();
		this.dateModified=Calendar.getInstance();
	}
}
