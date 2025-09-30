package At.assessment.service;

import At.assessment.model.Aluno;
import At.assessment.model.Disciplina;
import At.assessment.repository.AlunoRepository;
import At.assessment.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public AlunoService(AlunoRepository alunoRepository, DisciplinaRepository disciplinaRepository) {
        this.alunoRepository = alunoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    // Cadastrar aluno
    public Aluno cadastrar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    // Listar todos os alunos
    public List<Aluno> listarTodos() {
        return alunoRepository.findAll();
    }

    // Alocar aluno em disciplina
    public Aluno alocarDisciplina(Long alunoId, Long disciplinaId) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new RuntimeException("Disciplina não encontrada"));

        aluno.getDisciplinas().add(disciplina);
        disciplina.getAlunos().add(aluno);

        alunoRepository.save(aluno);
        disciplinaRepository.save(disciplina);

        return aluno;
    }
}
