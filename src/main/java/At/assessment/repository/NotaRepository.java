package At.assessment.repository;

import At.assessment.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByDisciplinaIdAndValorGreaterThanEqual(Long disciplinaId, double valor);
    List<Nota> findByDisciplinaIdAndValorLessThan(Long disciplinaId, double valor);
}
