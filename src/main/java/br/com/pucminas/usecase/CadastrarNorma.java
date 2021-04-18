package br.com.pucminas.usecase;

import br.com.pucminas.domain.Norma;
import io.smallrye.mutiny.Uni;

public interface CadastrarNorma {
    Uni<Norma> cadastra(Norma norma);
}
