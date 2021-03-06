package br.com.pucminas.resource.http;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.domain.exception.NormaException;
import br.com.pucminas.resource.common.ExceptionResponse;
import br.com.pucminas.resource.dto.NormaRequest;
import br.com.pucminas.resource.dto.NormaResponse;
import br.com.pucminas.resource.mapper.NormaMapper;
import br.com.pucminas.usecase.AlterarNorma;
import br.com.pucminas.usecase.BuscarNorma;
import br.com.pucminas.usecase.CadastrarNorma;
import br.com.pucminas.usecase.ListarNormas;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.ok;

@Path("/api/v1/normas")
@Produces(APPLICATION_JSON)
public class NormaResource {

    private final BuscarNorma buscarNorma;
    private final AlterarNorma alterarNorma;
    private final CadastrarNorma cadastrarNorma;
    private final ListarNormas listarNormas;
    private final NormaMapper normaMapper;

    public NormaResource(BuscarNorma buscarNorma,
                         AlterarNorma alterarNorma,
                         CadastrarNorma cadastrarNorma,
                         ListarNormas listarNormas,
                         NormaMapper normaMapper) {
        this.buscarNorma = buscarNorma;
        this.alterarNorma = alterarNorma;
        this.cadastrarNorma = cadastrarNorma;
        this.listarNormas = listarNormas;
        this.normaMapper = normaMapper;
    }

    @GET
    public List<NormaResponse> listarTodas() {
        return listarNormas.listar()
            .stream()
            .map(normaMapper::toResponse)
            .collect(toList());
    }

    @GET
    @Path("{id}")
    @Produces(APPLICATION_JSON)
    public Response buscarNormaPorId(@PathParam("id") Long normaId) {
        return buscarNorma.buscar(normaId)
                .map(normaMapper::toResponse)
                .map(normaResponse -> ok(normaResponse).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @POST
    public Response cadastrar(@RequestBody NormaRequest normaRequest) throws URISyntaxException {
        Norma norma = normaMapper.toObject(normaRequest);
        var normaSalva = cadastrarNorma.cadastra(norma);
        URI uri = new URI("/api/v1/normas/" + normaSalva.getId());
        return Response.created(uri).build();
    }

    @PUT
    @Path("{id}")
    public Response put(@PathParam("id") Long normaId, NormaRequest normaRequest) {
        var norma = normaMapper.toObject(normaRequest);
        try {
            alterarNorma.altera(normaId, norma);
        } catch (NormaException exception) {
            return Response.status(NOT_FOUND)
                    .entity(new ExceptionResponse(exception.getMessage()))
                    .build();
        }
        return Response.noContent().build();
    }
}
