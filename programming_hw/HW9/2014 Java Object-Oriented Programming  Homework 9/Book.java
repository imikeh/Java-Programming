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
		int result;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		/* Each instance of Book has its own hash code. */
		/* It's necessary for you to use method contains outside. */
		boolean result = false;
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
		this.publishDate = new Date ();
		
		if (publishDate != null) {
			/* Remember to catch ParseException when parse publishDate */
		}
	}
}
