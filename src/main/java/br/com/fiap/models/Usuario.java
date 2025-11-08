package br.com.fiap.models;

import br.com.fiap.enums.SexoEnum;
import br.com.fiap.enums.TipoUsuarioEnum;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.sql.Date;

@XmlRootElement
public class Usuario {
    protected String cpf;
    protected String nome;
    protected Date dataNascimento;
    protected String email;
    protected SexoEnum sexo;
    protected String telefone;
    protected String senha;

    protected TipoUsuarioEnum tipo;

    protected Endereco endereco;

    //Encapsulamento
    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(Date dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public SexoEnum getSexo() {
        return sexo;
    }
    public void setSexo(SexoEnum sexo) {
        this.sexo = sexo;
    }

    public String getTelefone() {
        return telefone;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public TipoUsuarioEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuarioEnum tipo) {
        this.tipo = tipo;
    }

    public Endereco getEndereco() {
        return endereco;
    }
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
