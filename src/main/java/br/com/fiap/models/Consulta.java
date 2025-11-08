package br.com.fiap.models;

import br.com.fiap.enums.InstitutoEnum;

public class Consulta extends Emissao{
    private Integer idCon;
    private InstitutoEnum instituto;

    //Encapsulamento
    public Integer getIdCon() {
        return idCon;
    }
    public void setIdCon(Integer idCon) {
        this.idCon = idCon;
    }
    public InstitutoEnum getInstituto() {return instituto;}
    public void setInstituto(InstitutoEnum instituto) {this.instituto = instituto;}
}

