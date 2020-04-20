package Pojo;

import javax.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy=GenerationType.TABLE,generator="tableGenerator")
    @TableGenerator(name="tableGenerator",initialValue=0,allocationSize=1)
    private int itemId;
    private int quantity;
    private Books booksByBid;
    private Orders ordersByOid;


    @Column(name = "itemId")
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "quantity")
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem orderItem = (OrderItem) o;

        if (itemId != orderItem.itemId) return false;
        if (quantity != orderItem.quantity) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + quantity;
        return result;
    }



    @ManyToOne
    @JoinColumn(name = "bid", referencedColumnName = "bid", nullable = false)
    public Books getBooksByBid() {
        return booksByBid;
    }

    public void setBooksByBid(Books booksByBid) {
        this.booksByBid = booksByBid;
    }

    @ManyToOne
    @JoinColumn(name = "oid", referencedColumnName = "oId", nullable = false)
    public Orders getOrdersByOid() {
        return ordersByOid;
    }

    public void setOrdersByOid(Orders ordersByOid) {
        this.ordersByOid = ordersByOid;
    }
}
