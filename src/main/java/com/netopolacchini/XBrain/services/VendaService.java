package com.netopolacchini.XBrain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netopolacchini.XBrain.models.Venda;
import com.netopolacchini.XBrain.models.Vendedor;
import com.netopolacchini.XBrain.repositories.VendaRepository;

import jakarta.transaction.Transactional;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendedorService vendedorService;

    public Venda findById (Long id){
        Optional<Venda> vendedor = this.vendaRepository.findById(id);
        return vendedor.orElseThrow( () -> new RuntimeException(
            "Venda não localizada. ID: "+id
        ));
    }

    public List<Venda> findAllByVendedorId (Long id){
        List<Venda> vendas = this.vendaRepository.findByVendedor_Id(id);
        return vendas;
    }

    public List<Venda> findByDateBetween (Date startDate, Date endDate){
        List<Venda> vendas = this.vendaRepository.findByDateBetween(startDate, endDate);
        return vendas;
    }

    @Transactional
    public Venda create(Venda obj){
        Vendedor vendedor = this.vendedorService.findById(obj.getVendedor().getId());
        obj.setId(null);
        obj.setVendedor(vendedor);

        obj = this.vendaRepository.save(obj);
        return obj;
    }

    @Transactional
    public Venda update (Venda obj){
        Venda newObj = findById(obj.getId());
        newObj.setDate(obj.getDate());
        newObj.setPrice(obj.getPrice());
        return this.vendaRepository.save(newObj);
    }

    @Transactional
    public void delete (Long id) {
        findById(id);
        try {
            this.vendaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                "Não foi possível deletar a venda. ID: "+id
            );
        }
    }
    
}
