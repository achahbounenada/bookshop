package com.example.projectBook.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "author")
    private String author;
    @Column(name = "book_language")
    private String bookLanguage;
    @Column(name = "price")
    private Double price;
    @Column(name = "reserved")
    private Boolean reserved;
    @Column(name = "genre")
    private String genre;
    @Column(name = "scoresSum")
    private Integer scoresSum;
    @Column(name = "nbScores")
    private Integer nbScores;

    public Book() {
    }
    public Book(long id, String title, String description, String author, String bookLanguage,String genre, Double price, Boolean reserved) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.author = author;
        this.bookLanguage = bookLanguage;
        this.price = price;
        this.reserved = reserved;
        this.genre = genre;
    }
    public Book(String title, String description, String author, String bookLanguage, String genre, Integer scoresSum, Integer nbScores, Double price, Boolean reserved) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.bookLanguage = bookLanguage;
        this.genre = genre;
        this.scoresSum = scoresSum;
        this.nbScores = nbScores;
        this.price = price;
        this.reserved = reserved;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getScoresSum() {
        return scoresSum;
    }

    public void setScoresSum(Integer scoresSum) {
        this.scoresSum = scoresSum;
    }

    public Integer getNbScores() {
        return nbScores;
    }

    public void setNbScores(Integer nbScores) {
        this.nbScores = nbScores;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", author='" + author + '\'' +
                ", bookLanguage='" + bookLanguage + '\'' +
                ", price=" + price +
                ", reserved=" + reserved +
                '}';
    }
}
