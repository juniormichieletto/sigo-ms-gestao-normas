package br.com.pucminas.resource.mapper;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.resource.dto.NormaRequest;
import br.com.pucminas.resource.dto.NormaResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface NormaMapper {

    NormaResponse toResponse(Norma norma);

    Norma toObject(NormaRequest normaRequest);
}
