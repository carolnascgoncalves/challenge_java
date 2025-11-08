package br.com.fiap.dto;

import br.com.fiap.enums.InstitutoEnum;
import br.com.fiap.enums.OpAvaliacaoEnum;
import br.com.fiap.models.Avaliacao;
import br.com.fiap.models.Exame;

public class AvaliacaoCadastroDto {
    private OpAvaliacaoEnum internacao;
    private OpAvaliacaoEnum teleconsulta;
    private OpAvaliacaoEnum prontoSocorro;
    private OpAvaliacaoEnum ambulancia;
    private OpAvaliacaoEnum farmacia;
    private String comentario;


    public OpAvaliacaoEnum getInternacao() {return internacao;}
    public void setInternacao(OpAvaliacaoEnum internacao) {this.internacao = internacao;}

    public OpAvaliacaoEnum getTeleconsulta() {return teleconsulta;}
    public void setTeleconsulta(OpAvaliacaoEnum teleconsulta) {this.teleconsulta = teleconsulta;}

    public OpAvaliacaoEnum getProntoSocorro() {return prontoSocorro;}
    public void setProntoSocorro(OpAvaliacaoEnum prontoSocorro) {this.prontoSocorro = prontoSocorro;}

    public OpAvaliacaoEnum getAmbulancia() {return ambulancia;}
    public void setAmbulancia(OpAvaliacaoEnum ambulancia) {this.ambulancia = ambulancia;}

    public OpAvaliacaoEnum getFarmacia() {return farmacia;}
    public void setFarmacia(OpAvaliacaoEnum farmacia) {this.farmacia = farmacia;}

    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}
}
