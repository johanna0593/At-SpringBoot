package At.assessment.controller;

import At.assessment.model.Aluno;
import At.assessment.service.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    // Cadastrar aluno
    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody Aluno aluno){
        return ResponseEntity.ok(alunoService.cadastrar(aluno));
    }

    // Listar todos os alunos
    @GetMapping
    public List<Aluno> listarTodos(){
        return alunoService.listarTodos();
    }

    // Alocar aluno em disciplina
    @PostMapping("/{alunoId}/disciplinas/{disciplinaId}")
    public ResponseEntity<Aluno> alocarDisciplina(
            @PathVariable Long alunoId,
            @PathVariable Long disciplinaId) {
        Aluno alunoAtualizado = alunoService.alocarDisciplina(alunoId, disciplinaId);
        return ResponseEntity.ok(alunoAtualizado);
    }
}
