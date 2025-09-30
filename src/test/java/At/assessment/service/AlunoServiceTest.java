package At.assessment.service;

import At.assessment.model.Aluno;
import At.assessment.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AlunoServiceTest {

    @Mock
    private AlunoRepository alunoRepository;

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aluno = new Aluno();
        aluno.setNome("Maria");
    }

    @Test
    void deveCadastrarAluno() {
        when(alunoRepository.save(aluno)).thenReturn(aluno);

        Aluno salvo = alunoService.cadastrar(aluno);

        assertEquals("Maria", salvo.getNome());
        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    void deveListarTodosAlunos() {
        when(alunoRepository.findAll()).thenReturn(Arrays.asList(aluno));

        List<Aluno> lista = alunoService.listarTodos();

        assertEquals(1, lista.size());
        verify(alunoRepository, times(1)).findAll();
    }
}
