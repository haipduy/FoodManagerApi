package fptu.sumer.foodstore_api.entity;

import javax.persistence.*;

@Entity
@Table(name = "DetailOrder", schema = "dbo", catalog = "FoodSystem")
public class DetailOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Basic
    @Column(name = "OrderId")
    private int orderId;
    @Basic
    @Column(name = "ProductId")
    private String productId;
    @Basic
    @Column(name = "Quantity")
    private int quantity;
    @Basic
    @Column(name = "Price")
    private float price;


    public DetailOrderEntity() {
    }


    public DetailOrderEntity(int orderId, String productId, int quantity, float price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
