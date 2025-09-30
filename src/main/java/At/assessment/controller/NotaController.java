package At.assessment.controller;

import At.assessment.model.Aluno;
import At.assessment.model.Nota;
import At.assessment.service.NotaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaService notaService;

    public NotaController(NotaService notaService) {
        this.notaService = notaService;
    }

    @PostMapping
    public ResponseEntity<Nota> atribuirNota(@RequestParam Long alunoId,
                                             @RequestParam Long disciplinaId,
                                             @RequestParam double valor){
        return ResponseEntity.ok(notaService.atribuirNota(alunoId, disciplinaId, valor));
    }

    @GetMapping("/aprovados")
    public List<Aluno> listarAprovados(@RequestParam Long disciplinaId){
        return notaService.listarAprovados(disciplinaId);
    }

    @GetMapping("/reprovados")
    public List<Aluno> listarReprovados(@RequestParam Long disciplinaId){
        return notaService.listarReprovados(disciplinaId);
    }
}