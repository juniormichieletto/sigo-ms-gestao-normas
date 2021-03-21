package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import java.util.List;
import java.util.Optional;

@RequestScoped
class NormaRepositoryImpl implements NormaRepository {

    public final NormaPanacheRepository normaPanacheRepository;

    public NormaRepositoryImpl(NormaPanacheRepository normaPanacheRepository) {
        this.normaPanacheRepository = normaPanacheRepository;
    }

    @Override
    public List<Norma> findAll() {
        return normaPanacheRepository.findAll().list();
    }

    @Override
    public Norma save(Norma norma) {
        normaPanacheRepository.persist(norma);
        return norma;
    }

    @Override
    public Optional<Norma> findById(Long normaId) {
        return normaPanacheRepository.findByIdOptional(normaId);
    }
}
