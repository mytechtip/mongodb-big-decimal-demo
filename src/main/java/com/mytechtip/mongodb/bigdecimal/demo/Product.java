package com.mytechtip.mongodb.bigdecimal.demo;

import java.math.BigDecimal;
import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;

/**
 *
 * @author cwang
 */
public class Product {
    
    @Id
    private String id;
    
    private String name;
    
    private BigDecimal price;
    
    public Product() {
        
    }

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + '}';
    }

    
    
}
