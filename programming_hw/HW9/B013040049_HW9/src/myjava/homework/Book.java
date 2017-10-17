package myjava.homework;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {
	/* Three attributes in class Book */
	private String title;
	private String author;
	private Date publishDate;
	
	public Book (String title, String author, String date) {
		setTitle (title);
		setAuthor (author);
		setPublishDate (date);
	}
	
	public Book (String title) {
		setTitle (title);
		setAuthor (null);
		setPublishDate (null);
	}
	
	public Book () {
	}
	
	@Override
	public String toString() {
		DateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
		return "Book: "+ title + "\n " + 
				     " Author: " + author + "\n  " + 
				     "publishDate: " + sdf.format (publishDate);
	}

	@Override
	public int hashCode() {
		/* Each instance of Book has its own hash code. */
		/* It's necessary for you to use method contains outside. */
		DateFormat sdf = new SimpleDateFormat ("yyyy/MM/dd");
		
		int result = title.hashCode() + author.hashCode() + sdf.format (publishDate).hashCode(); /*§Úªºhash code*/
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		/* Each instance of Book has its own hash code. */
		/* It's necessary for you to use method contains outside. */
		boolean result = false;
		
		Book Bo = (Book)obj;
		if(Bo.hashCode()==this.hashCode()){
			return true;
		}
		
	    return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		/* PublishDate format is "yyyy/MM/dd" */	
        this.publishDate = new Date();
		
		if (publishDate != null)
		{
			/* Remember to catch ParseException when parse publishDate */
			try
			{
				String[] pD = publishDate.split("/");
				/* wrong date, end program */
				if(pD.length!=3 || Integer.parseInt(pD[0])<0 || Integer.parseInt(pD[1])<=0 || Integer.parseInt(pD[2])<=0 || Integer.parseInt(pD[1])>12
						|| (Integer.parseInt(pD[1]) == 1 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 3 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 5 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 7 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 8 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 10 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 12 && Integer.parseInt(pD[2]) > 31)
						|| (Integer.parseInt(pD[1]) == 4 && Integer.parseInt(pD[2]) > 30)
						|| (Integer.parseInt(pD[1]) == 6 && Integer.parseInt(pD[2]) > 30)
						|| (Integer.parseInt(pD[1]) == 9 && Integer.parseInt(pD[2]) > 30)
						|| (Integer.parseInt(pD[1]) == 11 && Integer.parseInt(pD[2]) > 30)
					    || (Integer.parseInt(pD[1]) == 2 && Integer.parseInt(pD[2]) > 29)
					    )
				{
					System.out.println("Book: " + title + " has wrong date format! End Program!");
					System.exit(1);
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				this.publishDate = dateFormat.parse(publishDate);
			}
			catch(ParseException e)
			{
				System.out.println("Book: " + title + " has wrong date format! End Program!");
				System.exit(1);
			}
			catch(NumberFormatException e)
			{
				System.out.println("Book: " + title + " has no number format! End Program!");
				System.exit(1);
			}
		}
	}
}
