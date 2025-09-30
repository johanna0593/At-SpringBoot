package At.assessment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Disciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    @ManyToMany(mappedBy = "disciplinas")
    @JsonBackReference
    private Set<Aluno> alunos = new HashSet<>();

    @OneToMany(mappedBy = "disciplina")
    private Set<Nota> notas = new HashSet<>();

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public Set<Aluno> getAlunos() { return alunos; }
    public void setAlunos(Set<Aluno> alunos) { this.alunos = alunos; }

    public Set<Nota> getNotas() { return notas; }
    public void setNotas(Set<Nota> notas) { this.notas = notas; }
}
