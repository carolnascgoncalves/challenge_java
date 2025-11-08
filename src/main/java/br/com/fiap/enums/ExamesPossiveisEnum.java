package br.com.fiap.enums;

public enum ExamesPossiveisEnum {
    RADIOGRAFIA, //imagem (0)
    ULTRASSONOGRAFIA, //imagem (1)
    TOMOGRAFIA_COMPUTADORIZADA, //imagem (2)
    RESSONÂNCIA_MAGNÉTICA, //imagem (3)
    MAMOGRAFIA, //imagem (4)
    HEMOGRAMA_COMPLETO, //laboratorial (0)
    GLICEMIA_DE_JEJUM, //laboratorial (1)
    CREATININA_SÉRICA, //laboratorial (2)
    TGO_TGP, //laboratorial (3)
    URINA_TIPO_1; //laboratorial (4)

    public String getTipo() {
        return this.ordinal() < 5 ? "IMAGEM" : "LABORATORIAL";
    }
}
