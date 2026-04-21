package br.com.senac.carros_api.repositorios;

import br.com.senac.carros_api.entidades.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
