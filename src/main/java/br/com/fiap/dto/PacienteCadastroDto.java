package br.com.fiap.dto;

import br.com.fiap.enums.SexoEnum;
import br.com.fiap.enums.TipoUsuarioEnum;
import br.com.fiap.models.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

//Request
public class PacienteCadastroDto {
    private String cpf;
    private String nome;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataNascimento;
    private String email;
    private SexoEnum sexo;
    private String telefone;
    private String senha;
    private String nomeMae;
    private TipoUsuarioEnum tipo;
    private String cepEndereco;

    public Paciente convertToPaciente(PacienteCadastroDto dto){return new Paciente(dto.getCpf(), dto.getNome(), dto.getDataNascimento(), dto.getEmail(), dto.getSexo(), dto.getTelefone(), dto.getSenha(), dto.getSenha(), dto.getTipo(), dto.getCepEndereco());}

    public String getCpf() {return cpf;}
    public void setCpf(String cpf) {this.cpf = cpf;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public Date getDataNascimento() {return dataNascimento;}
    public void setDataNascimento(Date dataNascimento) {this.dataNascimento = dataNascimento;}

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public SexoEnum getSexo() {return sexo;}
    public void setSexo(SexoEnum sexo) {this.sexo = sexo;}

    public String getTelefone() {return telefone;}
    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getSenha() {return senha;}
    public void setSenha(String senha) {this.senha = senha;}

    public String getNomeMae() {return nomeMae;}
    public void setNomeMae(String nomeMae) {this.nomeMae = nomeMae;}

    public TipoUsuarioEnum getTipo() {return tipo;}
    public void setTipo(TipoUsuarioEnum tipo) {this.tipo = tipo;}

    public String getCepEndereco() {return cepEndereco;}
    public void getCepEndereco(String endereco) {this.cepEndereco = endereco;}
}
