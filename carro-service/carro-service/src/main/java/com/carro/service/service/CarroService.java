package com.carro.service.service;

import com.carro.service.entity.Carro;
import com.carro.service.repository.CarroReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    @Autowired
    private CarroReposotory carroReposotory;

    public List<Carro> getAll(){
        return carroReposotory.findAll();
    }

    public Carro getCarroById(int id){
        return carroReposotory.findById(id).orElse(null);
    }

    public Carro save(Carro carro){
        Carro nuevoCarro = carroReposotory.save(carro);
        return nuevoCarro;
    }

    public List<Carro> byUsuarioId(int usuarioId) {
        return carroReposotory.findByUsuarioId(usuarioId);
    }

}
