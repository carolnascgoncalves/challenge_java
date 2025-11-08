package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ReceitaMostrarDto {
    private String medicoResponsavel;
    private String prescricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataEmissao;

    public String getMedicoResponsavel() {
        return medicoResponsavel;
    }
    public void setMedicoResponsavel(String medicoResponsavel) {
        this.medicoResponsavel = medicoResponsavel;
    }

    public String getPrescricao() {
        return prescricao;
    }
    public void setPrescricao(String prescricao) {
        this.prescricao = prescricao;
    }

    public Date getDataEmissao() {
        return dataEmissao;
    }
    public void setDataEmissao(Date dataEmissao) {
        this.dataEmissao = dataEmissao;
    }
}
