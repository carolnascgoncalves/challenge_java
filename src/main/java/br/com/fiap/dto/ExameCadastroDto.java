package br.com.fiap.dto;

import br.com.fiap.dao.MedicoDao;
import br.com.fiap.dao.PacienteDao;
import br.com.fiap.enums.ExamesPossiveisEnum;
import br.com.fiap.enums.InstitutoEnum;
import br.com.fiap.enums.TipoExameEnum;
import br.com.fiap.models.Exame;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ExameCadastroDto {
    private ExamesPossiveisEnum exame;
    private String nomeMedico;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataExame;
    private String cpfPaciente;
    private InstitutoEnum unidade;

    public Exame convertToExame(ExameCadastroDto dto){
        ExamesPossiveisEnum exameEnum = dto.getExame();
        MedicoDao medDao = new MedicoDao();
        PacienteDao pacDao = new PacienteDao();
        Exame exame = new Exame();

        exame.setNome(dto.getExame().toString());
        exame.setValidade(dto.getDataExame());
        exame.setMedico(medDao.buscarPorNome(nomeMedico));
        exame.setPaciente(pacDao.buscarPac(cpfPaciente));
        exame.setUnidade(dto.getUnidade());

        exame.setTipoExame(TipoExameEnum.valueOf(exameEnum.getTipo()));

        return exame;
    }

    public ExamesPossiveisEnum getExame() {
        return exame;
    }
    public void setExame(ExamesPossiveisEnum exame) {
        this.exame = exame;
    }

    public Date getDataExame() {
        return dataExame;
    }
    public void setDataExame(Date dataExame) {
        this.dataExame = dataExame;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }
    public String getNomeMedico() {
        return nomeMedico;
    }

    public InstitutoEnum getUnidade() {
        return unidade;
    }
    public void setUnidade(InstitutoEnum unidade) {
        this.unidade = unidade;
    }

    public String getCpfPaciente() {return cpfPaciente;}
    public void setCpfPaciente(String cpfPaciente) {this.cpfPaciente = cpfPaciente;}
}
