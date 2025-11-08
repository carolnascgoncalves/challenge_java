package br.com.fiap.models;

import br.com.fiap.enums.InstitutoEnum;
import br.com.fiap.enums.TipoExameEnum;

public class Exame extends Emissao{
    private Integer idEx;
    private TipoExameEnum tipoExame;
    private InstitutoEnum unidade;


    //Encapsulamento
    public Integer getIdEx() {return idEx;}
    public void setIdEx(Integer idEm) {this.idEx = idEm;}

    public TipoExameEnum getTipoExame() {return tipoExame;}
    public void setTipoExame(TipoExameEnum tipoExame) {
        this.tipoExame = tipoExame;}

    public InstitutoEnum getUnidade() {return unidade;}
    public void setUnidade(InstitutoEnum unidade) {this.unidade = unidade;}
}
