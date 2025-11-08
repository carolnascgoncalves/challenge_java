package br.com.fiap.resource;

import br.com.fiap.dto.ConsultaMostrarDto;
import br.com.fiap.service.ConsultaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/paciente/consultas")
public class ConsultaResource {
    @Inject
    ConsultaService consultaService;

    @GET
    @Path("/{id_paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ConsultaMostrarDto> listar(@PathParam("id_paciente") int id){
        return consultaService.listar(id);
    }
}
