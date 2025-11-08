package br.com.fiap.dto;

import br.com.fiap.enums.TipoExameEnum;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;

public class ExameResultadosMostrarDto {
    private String nomeExame;
    private TipoExameEnum tipoExame;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Date dataResultado;
    private String nomeMedico;

    public String getNomeExame() {
        return nomeExame;
    }
    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public Date getDataResultado() {
        return dataResultado;
    }
    public void setDataResultado(Date dataResultado) {
        this.dataResultado = dataResultado;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }
    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public TipoExameEnum getTipoExame() {
        return tipoExame;
    }
    public void setTipoExame(TipoExameEnum tipoExame) {
        this.tipoExame = tipoExame;
    }
}
