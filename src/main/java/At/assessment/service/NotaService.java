package At.assessment.service;

import At.assessment.model.Aluno;
import At.assessment.model.Disciplina;
import At.assessment.model.Nota;
import At.assessment.repository.AlunoRepository;
import At.assessment.repository.DisciplinaRepository;
import At.assessment.repository.NotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NotaService {

    private final NotaRepository notaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public NotaService(NotaRepository notaRepository, AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.notaRepository = notaRepository;
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    // Atribuir nota a um aluno em uma disciplina
    public Nota atribuirNota(Long alunoId, Long disciplinaId, double valor) {
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);

        if (alunoOpt.isPresent() && disciplinaOpt.isPresent()) {
            Nota nota = new Nota();
            nota.setAluno(alunoOpt.get());
            nota.setDisciplina(disciplinaOpt.get());
            nota.setValor(valor);
            return notaRepository.save(nota);
        }

        throw new RuntimeException("Aluno ou Disciplina não encontrado");
    }

    // Listar alunos aprovados em uma disciplina (nota >= 7)
    public List<Aluno> listarAprovados(Long disciplinaId) {
        return notaRepository.findByDisciplinaIdAndValorGreaterThanEqual(disciplinaId, 7)
                .stream()
                .map(Nota::getAluno)
                .collect(Collectors.toList());
    }

    // Listar alunos reprovados em uma disciplina (nota < 7)
    public List<Aluno> listarReprovados(Long disciplinaId) {
        return notaRepository.findByDisciplinaIdAndValorLessThan(disciplinaId, 7)
                .stream()
                .map(Nota::getAluno)
                .collect(Collectors.toList());
    }
}
