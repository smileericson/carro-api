package br.com.senac.carros_api.services;


import br.com.senac.carros_api.dtos.CarroFiltroDTO;
import br.com.senac.carros_api.dtos.CarroRequestDTO;
import br.com.senac.carros_api.entidades.Carro;
import br.com.senac.carros_api.repositorios.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {

    private final CarroRepository carroRepository;

    public CarrosService(CarroRepository carroRepository){

        this.carroRepository =carroRepository;
    }
    public List<Carro>listar(CarroFiltroDTO filtro){
        if(filtro.getModelo() != null){
            return carroRepository.findByModelo(filtro.getModelo());
        }

        return carroRepository.findAll();
    }
    public Carro criar(CarroRequestDTO carro){
        Carro carroPersist = this.carroRequestDTOParaCarro(carro);
        return carroRepository.save(carroPersist);
    }
    public Carro atualizar(Long id,CarroRequestDTO cliente) {
        if (carroRepository.existsById(id)) {
            Carro clientePersit = this.carroRequestDTOParaCarro(cliente);
            clientePersit.setId(id);
            return carroRepository.save(clientePersit);
        }
        throw new RuntimeException("Carro não encontrado");
    }
    public void deletar(Long id) {
        if (carroRepository.existsById(id)) {
            carroRepository.deleteById(id);
        }
        throw new RuntimeException("Carro deletado com sucesso");
    }
    public Carro listarById(Long id){
        if(carroRepository.existsById(id)){
            return  carroRepository.findById(id).get();
        }
        throw new RuntimeException("Carro não existe");
    }

    private Carro carroRequestDTOParaCarro(
            CarroRequestDTO entrada){
        Carro saida = new Carro();
        saida.setModelo(entrada.getModelo());
        saida.setMarca(entrada.getMarca());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;
    }
}
