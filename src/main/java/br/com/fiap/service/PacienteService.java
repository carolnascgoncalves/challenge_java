package br.com.fiap.service;

import br.com.fiap.dao.PacienteDao;
import br.com.fiap.dto.PacienteCadastroDto;
import br.com.fiap.dto.PacienteLoginDto;
import br.com.fiap.dto.PacienteMostrarDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
@ApplicationScoped
public class PacienteService {
    @Inject
    PacienteDao pacienteDao;

    public void cadastrar(PacienteCadastroDto paciente) throws SQLException{
        pacienteDao.cadastrar(paciente.convertToPaciente(paciente));
    }

    public PacienteMostrarDto mostrarInfos(int id){
        return pacienteDao.mostrarInfos(id);
    }

    public String login(PacienteLoginDto dto){
        return pacienteDao.login(dto.convertToPaciente(dto));
    }

    public PacienteLoginDto buscar(String cpf) throws SQLException {
        PacienteLoginDto dto = new PacienteLoginDto();
        return dto.convertToDto(pacienteDao.buscarLoginPac(cpf));
    }

    public String mostrarDocumento(int id) throws SQLException {return pacienteDao.mostrarDocumento(id);}
}
