package br.com.fiap.models;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Receita extends Emissao{
    private Integer idRec;

    //Encapsulamento
    public Integer getIdRec() {return idRec;}
    public void setIdRec(Integer idRec) {this.idRec = idRec;}
}