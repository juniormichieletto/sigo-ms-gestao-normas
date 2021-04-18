package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
class NormaRepositoryImpl implements NormaRepository {

    public final NormaPanacheRepository normaPanacheRepository;

    public NormaRepositoryImpl(NormaPanacheRepository normaPanacheRepository) {
        this.normaPanacheRepository = normaPanacheRepository;
    }

    @Override
    public Uni<List<Norma>> findAll() {
        return normaPanacheRepository.findAll()
                .list();
    }

    @Override
    public Uni<Norma> save(Norma norma) {
        return normaPanacheRepository.persist(norma)
                .onItem().transform(a -> norma);
    }

    @Override
    public Uni<Norma> findById(Long normaId) {
        return normaPanacheRepository.findById(normaId);
    }
}
