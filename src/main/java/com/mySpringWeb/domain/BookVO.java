package com.mySpringWeb.domain;

public class BookVO {
    private String title;
    private String link;
    private String image;
    private String author;
    private String pubdate;
    private String description;
    private String isbn;
    
	public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getPubdate() {
        return pubdate;
    }
    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
//    @Override
//    public String toString() {
//        return "Book [title=" + title + ", link=" + link + ", image=" + image + ", author=" + author + ", pubdate=" + pubdate + ", description=" + description + "isbn=" + isbn +"]";
//    }
    
}