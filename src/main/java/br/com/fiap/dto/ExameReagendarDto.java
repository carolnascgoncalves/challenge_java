package br.com.fiap.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class ExameReagendarDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm", timezone = "America/Sao_Paulo")
    private Timestamp novaData;

    public Timestamp getNovaData() {
        return novaData;
    }
    public void setNovaData(Timestamp novaData) {
        this.novaData = novaData;
    }
}
