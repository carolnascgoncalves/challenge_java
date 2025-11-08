package br.com.fiap.service;

import br.com.fiap.dao.AvaliacaoDao;
import br.com.fiap.dto.AvaliacaoCadastroDto;
import br.com.fiap.models.Avaliacao;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.sql.SQLException;
import java.util.List;

@ApplicationScoped
public class AvaliacaoService {
    @Inject
    AvaliacaoDao avaliacaoDao;

    public void cadastrar(AvaliacaoCadastroDto avaliacao) throws SQLException {
        avaliacaoDao.cadastrar(avaliacao);
    }

    public List<Avaliacao> listar() throws SQLException{
        return avaliacaoDao.listar();
    }
}
