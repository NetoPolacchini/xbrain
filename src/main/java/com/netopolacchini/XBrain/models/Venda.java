package com.netopolacchini.XBrain.models;

import java.util.Date;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = Venda.TABLE_NAME)
public class Venda {
    public static final String TABLE_NAME = "venda";

    @Id
    @GeneratedValue
    @Column (name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn (name = "vendedor_id", nullable = false, updatable = false)
    private Vendedor vendedor;

    @Column (name = "price", nullable = false)
    private float price;

    @Column(name = "date")
    private Date date;


    public Venda() {
    }

    public Venda(Long id, Vendedor vendedor, float price, Date date) {
        this.id = id;
        this.vendedor = vendedor;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Venda id(Long id) {
        setId(id);
        return this;
    }

    public Venda vendedor(Vendedor vendedor) {
        setVendedor(vendedor);
        return this;
    }

    public Venda price(float price) {
        setPrice(price);
        return this;
    }

    public Venda date(Date date) {
        setDate(date);
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Venda)) {
            return false;
        }
        Venda other = (Venda) obj;
        return Objects.equals(this.id, other.id) && Objects.equals(this.vendedor, other.vendedor) && this.price == other.price && Objects.equals(this.date, other.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, vendedor, price, date);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", vendedor='" + getVendedor() + "'" +
            ", price='" + getPrice() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

    
}
