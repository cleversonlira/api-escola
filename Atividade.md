classDiagram
direction BT
class Aluno {
  + Aluno() 
  + Long id
  + String nome
  + String matricula
}
class Atividade {
  + Atividade() 
  + Long id
  + String nome
  + BigDecimal nota
  + Aluno aluno
}
class Professor {
  + Professor() 
  + Long id
  + String disciplina
  + String matricula
  + String nome
}

Aluno  -->  Atividade 
Professor  -->  Atividade 
