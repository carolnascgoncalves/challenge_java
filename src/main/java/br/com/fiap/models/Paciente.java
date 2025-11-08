package br.com.fiap.models;

import br.com.fiap.enums.SexoEnum;
import br.com.fiap.enums.TipoUsuarioEnum;
import br.com.fiap.service.ViaCepService;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.Date;

@XmlRootElement
public class Paciente extends Usuario {
    private String nomeMae;
    private Integer idPac;

    //Encapsulamento
    public Integer getIdPac() {return idPac;}
    public void setIdPac(Integer idPac) {this.idPac = idPac;}

    public String getNomeMae() {
        return nomeMae;
    }
    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public Paciente(){}

    public Paciente(String cpf, String nome, Date dataNascimento, String email, SexoEnum sexo, String telefone, String senha, String nomeMae, TipoUsuarioEnum tipo, String cep) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.sexo = sexo;
        this.telefone = telefone;
        this.senha = senha;
        this.nomeMae = nomeMae;
        this.tipo = tipo;
        this.endereco = ViaCepService.buscaEndereco(cep);
    }
}