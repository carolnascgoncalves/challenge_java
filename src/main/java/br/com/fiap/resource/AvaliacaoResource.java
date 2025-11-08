package br.com.fiap.resource;

import br.com.fiap.dto.AvaliacaoCadastroDto;
import br.com.fiap.models.Avaliacao;
import br.com.fiap.service.AvaliacaoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/avaliacoes")
public class AvaliacaoResource {
    @Inject
    AvaliacaoService avaliacaoService;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(AvaliacaoCadastroDto avaliacao){
        try{
            avaliacaoService.cadastrar(avaliacao);

            return Response.status(Response.Status.CREATED)
                    .entity("Avaliação cadastrada com sucesso!")
                    .build();

        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar avaliação!")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Avaliacao> listar(){
        try{
            return avaliacaoService.listar();
        }catch (SQLException e){
            return null;
        }
    }
}
