package br.com.senac.carros_api.controllers;


import br.com.senac.carros_api.dtos.CarroFiltroDTO;
import br.com.senac.carros_api.dtos.CarroRequestDTO;
import br.com.senac.carros_api.entidades.Carro;
import br.com.senac.carros_api.services.CarrosService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/carro")
@CrossOrigin

public class CarroController {

    private final CarrosService carrosService;

    public CarroController (CarrosService carrosService){
    this.carrosService = carrosService;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Carro>> listar(CarroFiltroDTO filtro){
        List<Carro> clienteList = carrosService.listar(filtro);

        if (clienteList.isEmpty()){
            return ResponseEntity.status(204).body(null);
        }
        return ResponseEntity.ok(clienteList);
    }
    @PostMapping("/criar")
    public ResponseEntity<Carro> criar (@RequestBody CarroRequestDTO cliente){
        try{
            return  ResponseEntity.ok(carrosService.criar(cliente));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Carro> atualizar(@RequestBody CarroRequestDTO cliente, @PathVariable Long id){
        try {
            return ResponseEntity.ok(carrosService.atualizar(id, cliente));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }

    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        try {
            carrosService.deletar(id);
            return ResponseEntity.ok().body(null);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
    @GetMapping("/listar/{id}")
    public ResponseEntity<Carro> listarById(@PathVariable Long id){
        try {
            return ResponseEntity.ok(carrosService.listarById(id));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }


}
