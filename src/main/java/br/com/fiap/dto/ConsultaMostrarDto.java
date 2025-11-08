package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ConsultaMostrarDto {
    private String nomeMedico;
    private String especialidadeConsulta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataConsulta;

    public String getNomeMedico() {return nomeMedico;}
    public void setNomeMedico(String nomeMedico) {this.nomeMedico = nomeMedico;}

    public Date getDataConsulta() {return dataConsulta;}
    public void setDataConsulta(Date dataConsulta) {this.dataConsulta = dataConsulta;}

    public String getEspecialidadeConsulta() {return especialidadeConsulta;}
    public void setEspecialidadeConsulta(String especialidadeConsulta) {this.especialidadeConsulta = especialidadeConsulta;}
}
