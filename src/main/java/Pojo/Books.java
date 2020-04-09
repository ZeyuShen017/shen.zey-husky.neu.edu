package Pojo;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Books {
    private int bid;
    private String title;
    private double price;
    private String author;
    private String isbn;
    private Category categoryByCid;
    private Collection<OrderItem> orderItemsByBid;

    @Id
    @Column(name = "bid")
    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "author")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Basic
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (bid != books.bid) return false;
        if (Double.compare(books.price, price) != 0) return false;
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (author != null ? !author.equals(books.author) : books.author != null) return false;
        if (isbn != null ? !isbn.equals(books.isbn) : books.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = bid;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "cid", referencedColumnName = "cid", nullable = false)
    public Category getCategoryByCid() {
        return categoryByCid;
    }

    public void setCategoryByCid(Category categoryByCid) {
        this.categoryByCid = categoryByCid;
    }

    @OneToMany(mappedBy = "booksByBid")
    public Collection<OrderItem> getOrderItemsByBid() {
        return orderItemsByBid;
    }

    public void setOrderItemsByBid(Collection<OrderItem> orderItemsByBid) {
        this.orderItemsByBid = orderItemsByBid;
    }
}
