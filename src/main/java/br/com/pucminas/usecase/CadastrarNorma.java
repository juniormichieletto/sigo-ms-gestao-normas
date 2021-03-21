package br.com.pucminas.usecase;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.resource.dto.NormaRequest;

public interface CadastrarNorma {
    Norma cadastra(Norma norma);
}
