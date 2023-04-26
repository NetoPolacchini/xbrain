package com.netopolacchini.XBrain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netopolacchini.XBrain.models.Vendedor;
import com.netopolacchini.XBrain.repositories.VendedorRepository;

import jakarta.transaction.Transactional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public Vendedor findById (Long id){
        Optional<Vendedor> vendedor = this.vendedorRepository.findById(id);
        return vendedor.orElseThrow( () -> new RuntimeException(
            "Usuário não encontrado! ID: "+id
        ));
    }

    public List<Vendedor> findAll(){
        List<Vendedor> vendedores = this.vendedorRepository.findAll();
        return vendedores;
    }

    @Transactional
    public Vendedor create (Vendedor obj){
        obj.setId(null);
        obj = this.vendedorRepository.save(obj);
        return obj;
    }

    @Transactional
    public Vendedor update (Vendedor obj){
        Vendedor newObj = findById(obj.getId());
        newObj.setName(obj.getName());
        return this.vendedorRepository.save(newObj);
    }

    @Transactional
    public void delete (Long id){
        findById(id);
        try {
            this.vendedorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(
                "Não é possível excluir."
            );
        }
    }
    
}
