package br.com.fiap.dto;
import br.com.fiap.models.Paciente;

public class PacienteLoginDto {
    private String cpf;
    private String senha;

    public Paciente convertToPaciente(PacienteLoginDto dto){
        Paciente paciente = new Paciente();
        paciente.setCpf(dto.getCpf());
        paciente.setSenha(dto.getSenha());

        return paciente;
    }

    public PacienteLoginDto convertToDto(Paciente paciente){
        PacienteLoginDto dto = new PacienteLoginDto();
        dto.setCpf(paciente.getCpf());
        dto.setSenha(paciente.getSenha());
        return dto;
    }


    public String getCpf() {
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
