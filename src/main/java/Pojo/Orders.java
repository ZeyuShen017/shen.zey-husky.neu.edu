package Pojo;



import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Collection;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Orders {
    private long oId;
    @NotEmpty(message = "zipcode cannot be empty")
    private String zipcode;
    @NotEmpty(message = "address cannot be empty")
    private String address;
    private double total;
    private Collection<OrderItem> orderItemsByOId;
    private Userinfo userinfoByUserId;

    @Id
    @Column(name = "oId")
    public long getoId() {
        return oId;
    }

    public void setoId(long oId) {
        this.oId = oId;
    }

    @Basic
    @Column(name = "zipcode")
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "total")
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pojo.Orders orders = (Pojo.Orders) o;

        if (oId != orders.oId) return false;
        if (Double.compare(orders.total, total) != 0) return false;
        if (zipcode != null ? !zipcode.equals(orders.zipcode) : orders.zipcode != null) return false;
        if (address != null ? !address.equals(orders.address) : orders.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (oId ^ (oId >>> 32));
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @OneToMany(mappedBy = "ordersByOid")
    public Collection<OrderItem> getOrderItemsByOId() {
        return orderItemsByOId;
    }

    public void setOrderItemsByOId(Collection<OrderItem> orderItemsByOId) {
        this.orderItemsByOId = orderItemsByOId;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false)
    public Userinfo getUserinfoByUserId() {
        return userinfoByUserId;
    }

    public void setUserinfoByUserId(Userinfo userinfoByUserId) {
        this.userinfoByUserId = userinfoByUserId;
    }


}
