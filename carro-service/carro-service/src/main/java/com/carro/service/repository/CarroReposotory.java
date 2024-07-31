package com.carro.service.repository;

import com.carro.service.entity.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarroReposotory extends JpaRepository<Carro,Integer> {

    List<Carro> findByUsuarioId(int usuarioId);

}
