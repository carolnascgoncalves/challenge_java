package br.com.fiap.dto;

import br.com.fiap.models.Endereco;

public class EnderecoResponseDto {
    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;
    private String estado;
    private int id;

    public EnderecoResponseDto convertToDto(Endereco endereco){
        EnderecoResponseDto dto = new EnderecoResponseDto();
        dto.setCep(endereco.getCep());
        dto.setLogradouro(endereco.getLogradouro());
        dto.setBairro(endereco.getLogradouro());
        dto.setCidade(endereco.getCidade());
        dto.setUf(endereco.getUf());
        dto.setEstado(endereco.getEstado());
        dto.setId(endereco.getIdEnd());

        return dto;
    }

    public Endereco convertToEndereco(EnderecoResponseDto enderecoDto){
        Endereco endereco = new Endereco();

        endereco.setCep(enderecoDto.getCep());
        endereco.setLogradouro(enderecoDto.getLogradouro());
        endereco.setBairro(enderecoDto.getLogradouro());
        endereco.setCidade(enderecoDto.getCidade());
        endereco.setUf(enderecoDto.getUf());
        endereco.setEstado(enderecoDto.getEstado());

        return endereco;
    }

    //encapsulamento
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getUf() {
        return uf;
    }
    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
