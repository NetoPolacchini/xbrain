package com.netopolacchini.XBrain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = Vendedor.TABLE_NAME)
public class Vendedor {
    public static final String TABLE_NAME = "vendedor";

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true)
    private Long id;

    @Column (name = "name", length = 100, nullable = false)
    @NotNull
    @NotEmpty
    @Size (min=2, max=100)
    private String name;
}
