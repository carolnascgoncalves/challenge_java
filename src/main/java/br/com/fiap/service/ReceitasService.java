package br.com.fiap.service;

import br.com.fiap.dao.ReceitaDao;
import br.com.fiap.dto.ReceitaMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class ReceitasService {
    @Inject
    ReceitaDao receitaDao;

    public List<ReceitaMostrarDto> listar(String tipo, int id){
        return receitaDao.listar(tipo, id);
    }
}
