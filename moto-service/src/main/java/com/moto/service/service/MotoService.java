package com.moto.service.service;

import com.moto.service.entity.Moto;
import com.moto.service.repository.MotoReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotoService {

    @Autowired
    private MotoReposotory motoReposotory;

    public List<Moto> getAll(){
        return motoReposotory.findAll();
    }

    public Moto getMotoById(int id){
        return motoReposotory.findById(id).orElse(null);
    }

    public Moto save(Moto moto){
        Moto nuevaMoto = motoReposotory.save(moto);
        return nuevaMoto;
    }

    public List<Moto> byUsuarioId(int usuarioId){
        return motoReposotory.findByUsuarioId(usuarioId);
    }

}
