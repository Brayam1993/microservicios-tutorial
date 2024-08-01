package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignClients.CarroFeignClient;
import com.usuario.service.feignClients.MotoFeignClient;
import com.usuario.service.model.Carro;
import com.usuario.service.model.Moto;
import com.usuario.service.repository.UsuarioReposotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioReposotory usuarioReposotory;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;

    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://carro-service/carro/usuario/" + usuarioId , List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://moto-service/moto/usuario/" + usuarioId, List.class );
        return motos;
    }

    public Carro saveCarro(int usuarioId,Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public List<Usuario> getAll(){
        return usuarioReposotory.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioReposotory.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario nuevoUsuario = usuarioReposotory.save(usuario);
        return nuevoUsuario;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevaMoto = motoFeignClient.save(moto);
        return nuevaMoto;
    }

    public Map<String,Object> getUsuarioAndVehiculos(int usuarioId){
        Map<String,Object> resultado = new HashMap<>();
        Usuario usuario = usuarioReposotory.findById(usuarioId).orElse(null);

        if(usuario == null){
            resultado.put("Mensaje","El usuario no existe");
            return resultado;
        }

        resultado.put("Usuario",usuario);
        List<Carro> carros = carroFeignClient.getCarros(usuarioId);
        if(carros.isEmpty()){
            resultado.put("Carros","El usuario no tiene carros");
        }else{
            resultado.put("Carros",carros);
        }

        List<Moto> motos = motoFeignClient.getMotos(usuarioId);
        if(motos.isEmpty()){
            resultado.put("Motos","El usuario no tiene motos");
        }else{
            resultado.put("Motos", motos);
        }
        return resultado;
    }

}
