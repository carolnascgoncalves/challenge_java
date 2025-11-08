package br.com.fiap.models;

import java.sql.Date;

public class Emissao {
    protected Integer idEm;
    protected String nome;
    protected Date validade;
    protected Medico medico;
    protected Paciente paciente;

    //Encapsulamento
    public Integer getIdEm() {return idEm;}
    public void setIdEm(Integer idEm) {this.idEm = idEm;}

    public Paciente getPaciente() {return paciente;}
    public void setPaciente(Paciente paciente) {this.paciente = paciente;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Date getValidade() {return validade;}
    public void setValidade(Date validade) {this.validade = validade;}

    public Medico getMedico() {
        return medico;
    }
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setNomeMedico(String nome){this.medico.setNome(nome);}
    public String getNomeMedico(){return medico.getNome();}

    //medicoDao.buscarMedico(medResp), paciente, descricao, sqlDate, tipoExame, nomeEx
    //Métodos
    public void criarEmissao(String nome, Medico medico, Paciente paciente, Date validade){
        this.nome = nome;
        this.medico = medico;
        this.paciente = paciente;
        this.validade = validade;
    }
}
