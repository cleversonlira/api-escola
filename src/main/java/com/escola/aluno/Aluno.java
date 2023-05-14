package com.escola.aluno;


import com.escola.atividade.Atividade;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno  extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String matricula;
    @ManyToMany
    @JoinTable(
            name = "atividade_aluno",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "atividade_id")
    )
    public List<Atividade> atividades;

}
