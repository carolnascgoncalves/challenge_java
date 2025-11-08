package br.com.fiap.service;

import br.com.fiap.dao.ExameDao;
import br.com.fiap.dto.ExameAgendamentosDto;
import br.com.fiap.dto.ExameCadastroDto;
import br.com.fiap.dto.ExameReagendarDto;
import br.com.fiap.dto.ExameResultadosMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class ExameService {
    @Inject
    ExameDao exameDao;

    public void cadastrar(ExameCadastroDto dto) throws SQLException {
        exameDao.cadastrar(dto.convertToExame(dto));
    }

    public List<ExameResultadosMostrarDto> listar(String tipo, int id) throws SQLException{
        return exameDao.listar(tipo, id);
    }

    public List<ExameAgendamentosDto> listarAgendamentos(int id){
        return exameDao.listarAgendamentos(id);
    }

    public boolean deletar(int idExame, int idPac) throws SQLException{
        return exameDao.deletarExame(idExame, idPac);
    }

    public void reagendar(int idExame, int idPac, ExameReagendarDto novoExame) throws SQLException{
        exameDao.alterarExame(idExame, idPac, novoExame);
    }
}
