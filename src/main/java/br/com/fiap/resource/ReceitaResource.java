package br.com.fiap.resource;

import br.com.fiap.dto.ReceitaMostrarDto;
import br.com.fiap.service.ReceitasService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/paciente/receitas")
public class ReceitaResource {
    @Inject
    ReceitasService receitasService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id_paciente}/{tipo}")
    //novas-receitas || historico-receitas
    public List<ReceitaMostrarDto> listar(@PathParam("tipo") String tipo, @PathParam("id_paciente") int id){
        return receitasService.listar(tipo, id);
    }
}
