package br.com.fiap.models;

public class Medico extends Usuario{
    private Integer idMed;
    private String areaAtuacao;

    //Encapsulamento
    public Integer getIdMed() {return idMed;}
    public void setIdMed(Integer idMed) {this.idMed = idMed;}

    public String getAreaAtuacao() {return areaAtuacao;}
    public void setAreaAtuacao(String areaAtuacao) {this.areaAtuacao = areaAtuacao;}
}
