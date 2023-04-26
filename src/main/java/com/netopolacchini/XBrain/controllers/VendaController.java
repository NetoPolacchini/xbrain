package com.netopolacchini.XBrain.controllers;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netopolacchini.XBrain.models.Resumo;
import com.netopolacchini.XBrain.models.Venda;
import com.netopolacchini.XBrain.models.Vendedor;
import com.netopolacchini.XBrain.services.VendaService;
import com.netopolacchini.XBrain.services.VendedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping ("/venda")
@Validated
public class VendaController {

    @Autowired 
    VendaService vendaService;

    @Autowired
    private VendedorService vendedorService;

    @GetMapping ("/{id}")
    public ResponseEntity<Venda> findById(@PathVariable Long id){
        Venda obj = this.vendaService.findById(id);
        return ResponseEntity.ok(obj);
    }

    @GetMapping ("/vendedor/{vendedorId}")
    public ResponseEntity<List<Venda>> findAllByVendedorId (@PathVariable Long vendedorId){
        vendedorService.findById(vendedorId);
        List<Venda> objs = this.vendaService.findAllByVendedorId(vendedorId);
        return ResponseEntity.ok().body(objs);
    }

    @GetMapping ("/vendedor/{vendedorId}/resumo")
    public ResponseEntity<Resumo> findResumoVendedor (@PathVariable long vendedorId, @RequestBody Date startDate,
    @RequestBody Date endDate){

        Resumo resumo = new Resumo();
        Vendedor vendedor = this.vendedorService.findById(vendedorId);
        List<Venda> vendas = this.vendaService.findByDateBetween(startDate, endDate);

        resumo.setName(vendedor.getName());
        resumo.setTotalVendas(vendas.size());
        resumo.setMediaVendas(5);

        return ResponseEntity.ok().body(resumo);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> create (@Valid @RequestBody Venda obj){
        this.vendaService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
        .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update (@Valid @RequestBody Venda obj, @PathVariable Long id){
        obj.setId(id);
        this.vendaService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        this.vendaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
