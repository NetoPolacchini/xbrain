package com.netopolacchini.XBrain.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Resumo {

    @Id
    private Integer id;
    private String name;
    private Integer totalVendas;
    private float mediaVendas;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalVendas() {
        return this.totalVendas;
    }

    public void setTotalVendas(Integer totalVendas) {
        this.totalVendas = totalVendas;
    }


    public float getMediaVendas() {
        return this.mediaVendas;
    }

    public void setMediaVendas(float mediaVendas) {
        this.mediaVendas = mediaVendas;
    }


}
