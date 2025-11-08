package br.com.fiap.dto;

import br.com.fiap.enums.ExamesPossiveisEnum;
import br.com.fiap.enums.InstitutoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ExameAgendamentosDto {
    private ExamesPossiveisEnum nomeExame;
    private String nomeMedico;
    private InstitutoEnum instituto;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataExame;

    public String getNomeMedico() {return nomeMedico;}
    public void setNomeMedico(String nomeMedico) {this.nomeMedico = nomeMedico;}

    public ExamesPossiveisEnum getNomeExame() {return nomeExame;}
    public void setNomeExame(ExamesPossiveisEnum nomeExame) {this.nomeExame = nomeExame;}

    public InstitutoEnum getInstituto() {return instituto;}
    public void setInstituto(InstitutoEnum instituto) {this.instituto = instituto;}

    public Date getDataExame() {return dataExame;}
    public void setDataExame(Date dataExame) {this.dataExame = dataExame;}
}
