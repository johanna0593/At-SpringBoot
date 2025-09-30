package At.assessment.service;

import At.assessment.model.Aluno;
import At.assessment.model.Disciplina;
import At.assessment.repository.AlunoRepository;
import At.assessment.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final AlunoRepository alunoRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository, AlunoRepository alunoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.alunoRepository = alunoRepository;
    }

    // Cadastrar disciplina
    public Disciplina cadastrar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }

    // Listar todas as disciplinas
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    // Alocar aluno em disciplina
    public Disciplina alocarAluno(Long disciplinaId, Long alunoId) {
        Optional<Disciplina> disciplinaOpt = disciplinaRepository.findById(disciplinaId);
        Optional<Aluno> alunoOpt = alunoRepository.findById(alunoId);

        if (disciplinaOpt.isPresent() && alunoOpt.isPresent()) {
            Disciplina disciplina = disciplinaOpt.get();
            Aluno aluno = alunoOpt.get();

            Set<Aluno> alunosDisciplina = disciplina.getAlunos();
            Set<Disciplina> disciplinasAluno = aluno.getDisciplinas();

            alunosDisciplina.add(aluno);
            disciplinasAluno.add(disciplina);

            alunoRepository.save(aluno); // atualiza relação bidirecional
            return disciplinaRepository.save(disciplina);
        }

        throw new RuntimeException("Disciplina ou Aluno não encontrado");
    }
}
