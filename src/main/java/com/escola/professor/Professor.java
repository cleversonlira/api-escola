package com.escola.professor;

import com.escola.atividade.Atividade;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
@Entity
@Table(name = "professor")
public class Professor extends Atividade {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String nome;
    public String matricula;
    public String disciplina;
}
