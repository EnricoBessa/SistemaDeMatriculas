# Sistema de Matrículas Universitárias

Este repositório contém o projeto de software desenvolvido para o Laboratório 1 da disciplina de Projeto de Software da PUC Minas.

## 👥 Atores do Sistema
* **Secretaria:** Gerencia o currículo do semestre, disciplinas, professores e alunos.
* **Aluno:** Inscreve-se e cancela matrículas em disciplinas obrigatórias e optativas.
* **Professor:** Consulta a lista de alunos matriculados em suas respectivas disciplinas.
* **Sistema de Cobrança:** Sistema externo notificado após a confirmação da matrícula do aluno.

## 📝 Histórias de Usuário (User Stories)

### US01 - Autenticação de Usuários
**Como** usuário do sistema (Aluno, Professor ou Secretaria)  
**Quero** realizar o login informando minha senha  
**Para** que eu possa acessar as funcionalidades restritas ao meu perfil de forma segura.

* **Critérios de Aceitação:**
  * O sistema deve validar a senha correspondente ao identificador do usuário.
  * Caso as credenciais estejam incorretas, uma mensagem de erro amigável deve ser exibida.

### US02 - Gerenciamento de Currículo e Cadastro (Secretaria)
**Como** funcionário da Secretaria  
**Quero** cadastrar cursos, disciplinas, professores e alunos  
**Para** manter as informações acadêmicas do semestre atualizadas.

* **Critérios de Aceitação:**
  * Cada curso deve possuir obrigatoriamente um nome e uma quantidade de créditos associada.
  * O sistema deve permitir associar disciplinas a um curso específico.

### US03 - Matrícula em Disciplinas (Aluno)
**Como** Aluno da instituição  
**Quero** selecionar minhas disciplinas prioritárias e alternativas dentro do período de matrícula  
**Para** garantir minha grade de estudos no próximo semestre.

* **Critérios de Aceitação:**
  * O aluno pode selecionar até 4 disciplinas como 1ª opção (obrigatórias).
  * O aluno pode selecionar até 2 disciplinas adicionais como alternativas (optativas).
  * A operação só pode ser realizada se o período de matrículas estiver ativamente aberto.

### US04 - Cancelamento de Matrícula (Aluno)
**Como** Aluno da instituição  
**Quero** cancelar a inscrição em uma disciplina previamente selecionada  
**Para** ajustar minha grade horária durante o período permitido.

* **Critérios de Aceitação:**
  * O cancelamento só é permitido se o período de matrículas ainda estiver aberto.

### US05 - Fechamento de Turmas e Notificação Financeira (Sistema)
**Como** Sistema de Matrículas  
**Quero** processar o encerramento das inscrições e notificar o Sistema de Cobrança  
**Para** consolidar as turmas ativas e gerar os boletos dos alunos.

* **Critérios de Aceitação:**
  * Uma disciplina só é ativada se possuir no mínimo 3 alunos matriculados ao fim do período.
  * Se a disciplina tiver menos de 3 alunos, ela deve ser automaticamente cancelada.
  * O limite máximo de vagas por disciplina é de 60 alunos; atingido o limite, novas inscrições são bloqueadas.
  * O Sistema de Cobrança deve ser notificado imediatamente após a confirmação das disciplinas ativas do aluno.

### US06 - Consulta de Alunos por Disciplina (Professor)
**Como** Professor da universidade  
**Quero** visualizar a listagem de alunos inscritos nas minhas disciplinas  
**Para** gerenciar a pauta de chamadas e o andamento da matéria.

* **Critérios de Aceitação:**
  * O professor só visualiza a lista de turmas/disciplinas que estão sob a sua responsabilidade.
