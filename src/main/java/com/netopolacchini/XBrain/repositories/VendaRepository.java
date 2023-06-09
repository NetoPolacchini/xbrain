package com.netopolacchini.XBrain.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netopolacchini.XBrain.models.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
    List<Venda> findByVendedor_Id(Long id);

    List<Venda> findByDateBetween(Date startDate, Date endDate);
    List<Venda> findAll();
}
