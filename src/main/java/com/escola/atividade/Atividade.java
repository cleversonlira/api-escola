package com.escola.atividade;


import com.escola.aluno.Aluno;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "atividade")
public class Atividade extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public BigDecimal nota;
    @ManyToMany
    @JoinTable(
            name = "atividade_aluno",
            joinColumns = @JoinColumn(name = "atividade_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    public List<Aluno> alunos = new ArrayList<>();

}
