package At.assessment.controller;

import At.assessment.model.Disciplina;
import At.assessment.service.DisciplinaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @PostMapping
    public ResponseEntity<Disciplina> cadastrar(@RequestBody Disciplina disciplina){
        return ResponseEntity.ok(disciplinaService.cadastrar(disciplina));
    }

    @GetMapping
    public List<Disciplina> listarTodas(){
        return disciplinaService.listarTodas();
    }

    @PostMapping("/{disciplinaId}/alunos/{alunoId}")
    public ResponseEntity<Disciplina> alocarAluno(@PathVariable Long disciplinaId, @PathVariable Long alunoId){
        return ResponseEntity.ok(disciplinaService.alocarAluno(disciplinaId, alunoId));
    }
}