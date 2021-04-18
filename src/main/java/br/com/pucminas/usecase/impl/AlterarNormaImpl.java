package br.com.pucminas.usecase.impl;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.domain.exception.NormaException;
import br.com.pucminas.repository.NormaRepository;
import br.com.pucminas.usecase.AlterarNorma;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.function.UnaryOperator;

@RequestScoped
@Transactional
public class AlterarNormaImpl implements AlterarNorma {

    private final NormaRepository normaRepository;

    public AlterarNormaImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    public void altera(Long normaId, Norma norma) throws NormaException {
        UnaryOperator<Norma> mapNewValuesToNorma = (normaSalva) -> {
            normaSalva.setNome(norma.getNome());
            normaSalva.setDescricaoCompleta(norma.getDescricaoCompleta());
            normaSalva.setCodigo(norma.getCodigo());
            return normaSalva;
        };

        normaRepository.findById(normaId)
                .map(mapNewValuesToNorma)
                .map(normaRepository::save).
                .orElseThrow(() -> new NormaException("Norma nao encontrada"));
    }
}
