package br.com.senac.carros_api.repositorios;

import br.com.senac.carros_api.entidades.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {

    List<Carro> findByModelo(String nome);
}
