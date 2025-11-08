package br.com.fiap.service;

import br.com.fiap.dao.ConsultaDao;
import br.com.fiap.dto.ConsultaMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ConsultaService {
    @Inject
    ConsultaDao consultaDao;

    public List<ConsultaMostrarDto> listar(int id){
        return consultaDao.listar(id);
    }
}
