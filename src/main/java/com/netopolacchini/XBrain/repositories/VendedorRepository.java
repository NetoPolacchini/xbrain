package com.netopolacchini.XBrain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netopolacchini.XBrain.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Vendedor findByName (String name);
}
