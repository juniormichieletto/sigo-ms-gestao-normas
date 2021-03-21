package br.com.pucminas.usecase;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.domain.exception.NormaException;

public interface AlterarNorma {

    void altera(Long normaId, Norma norma) throws NormaException;
}
