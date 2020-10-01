fun main() {
    val dhManager = DigitalHouseManager()
// Testes de criar, remover e alterar profs, cursos, alunos, matrículas
    dhManager.registrarCurso("Santander Coders Mobile", 40)             //registrar curso
    dhManager.registrarCurso("Santander Coders Mobile - IOS", 40)       //registrar curso
    dhManager.registrarCurso("Aloha", 40)                               //registrar curso
    dhManager.registrarCurso("Santander Coders Mobile", 40)             //registrar curso - nome já existe
    dhManager.excluirCurso(30002)                                       //excluir curso
    dhManager.mudarQtdMaxAlunosCurso(30000, 1)                          //mudar qtd Max de alunos do curso
    dhManager.registrarProfAdjunto("Thais", "Brasil Lenhard")           //criar prof só nome
    dhManager.registrarProfAdjunto("Edu", "Misina", "", 10)             //criar prof c/qtd de horas Monitoria
    dhManager.registrarProfTitular("Cesar", "Rodrigues", "13/04/2015")  //criar prof c/dataEntrada
    dhManager.mudarDataEntradaProf(20001, "15/05/2017")                 //mudar data de entrada do prof
    dhManager.excluirProfessor(20000)                                   //excluir professor
    dhManager.registrarAluno("Thais", "Brasil Lenhard")                 //registrar aluno
    dhManager.registrarAluno("João", "Silva")                           //registrar aluno
    dhManager.registrarAluno("Pabllo", "Vittar")                         //registrar aluno
    dhManager.excluirAluno(50002)                                       //excluir aluno
    dhManager.mudarNome(20001, "Eduardo")                               //mudar nome prof
    dhManager.mudarNome(20001, "", "Akio Misina")                       //mudar sobrenome prof
    dhManager.mudarNome(50000, null, "Brasil Lenhard")                  //mudar sobrenome aluno
    dhManager.mudarNome(30000, "Santander Coders Mobile - Android")     //mudar nome curso
    dhManager.mudarNome(600, "Não Vai", "Funcionar")                    //mudar nome - código não exite
    dhManager.mudarNome(50001, "", null)                                //mudar nome - nome vazio ou nulo
// Testes de interação - alocar professores, matricular alunos
    println("--------------------------------------------------------")
    dhManager.alocarProfessores(30000, 20002, 20001)                    //alocar profs em curso
    dhManager.alocarProfessores(30001, 0, 20001)                        //alocar 1 prof em curso
    dhManager.alocarProfessores(30007, 20002, 20001)                    //alocar profs em curso que não existe
    dhManager.alocarProfessores(30001, 20001, 20002)                    //alocar prof Titular em Adjunto, e Adjunto em titular
    dhManager.matricularAlunoCurso(50000, 30000)                        //Matricular aluno em curso
    dhManager.matricularAlunoCurso(50000, 30004)                        //Matricular aluno em curso que não existe
    dhManager.matricularAlunoCurso(50007, 30000)                        //Matricular aluno que não existe em um curso
    dhManager.matricularAlunoCurso(50000, 30000)                        //Matricular aluno já matriculado em curso
    dhManager.matricularAlunoCurso(50001, 30000)                        //Matricular aluno em curso lotado
// Testes de impressão de informações: (e verificação se mudanças acima foram registradas em todos os lugares)
    println("--------------------------------------------------------------------")
    dhManager.imprimirListaMapCursos()                                  //imprimir lista de cursos
    dhManager.imprimirListaMapProfessores()                             //imprimir lista de professores
    dhManager.imprimirListaMapAlunos()                                  //imprimir lista de alunos
    dhManager.imprimirListaMatriculas()                                 //imprimir lista de matrículas
    dhManager.listaDeAlunosCurso(30000)                                 //imprimir lista de alunos de um curso
    dhManager.listaDeAlunosCurso(30001)                                 //imprimir lista de alunos de um curso vazio
    dhManager.tempoDeCasaProf(20001)                                    //imprimir tempo de casa de prof p/ codigo
    dhManager.tempoDeCasaTodosProfs()                                   //imprimir tempo de casa de todos os profs
    dhManager.pesquisar(30000)                                          //pesquisa por codigo
    dhManager.pesquisar(60001)                                          //pesquisa por codigo (nao encontrado)
    dhManager.pesquisar("Cesar")                                        //pesquisa por nome (professor)
    dhManager.pesquisar("mobile")                                       //pesquisa por nome (curso)
    dhManager.pesquisar("brasil")                                       //pesquisa por nome (aluno)
    dhManager.pesquisar("aloha")                                        //pesquisa por nome (nao encontrado)
// Testes com null/vazio
    println("--------------------------------------------------------------------")
    dhManager.registrarCurso(null, 40)                                  //registrar curso - nome null
    dhManager.registrarCurso("Teste", null)                             //registrar curso - qtdMaxAlunos null
    dhManager.excluirCurso(null)                                        //excluir curso - codigo null
    dhManager.mudarQtdMaxAlunosCurso(null, 1)                           //mudar qtdMax de alunos do curso - codigo null
    dhManager.mudarQtdMaxAlunosCurso(30000, null)                       //mudar qtdMax de alunos do curso - qtd null
    dhManager.registrarProfTitular(null, "Testador")                    //criar prof nome null
    dhManager.registrarProfAdjunto("Testinho", null, "", 10)            //criar prof sobrenome null
    dhManager.mudarDataEntradaProf(null, "15/05/2017")                  //mudar data de entrada do prof - codigo null
    dhManager.mudarDataEntradaProf(20001, null)                         //mudar data de entrada do prof - data null/vazio
    dhManager.excluirProfessor(null)                                    //excluir professor - codigo null
    dhManager.registrarAluno(null, "Brasil Lenhard")                    //registrar aluno - nome null
    dhManager.registrarAluno("João", "")                                //registrar aluno - sobrenome null/vazio
    dhManager.excluirAluno(null)                                        //excluir aluno - codigo null
    dhManager.mudarNome(20001, "")                                      //mudar nome prof - nome null
    dhManager.mudarNome(20001, "", "")                                  //mudar sobrenome prof - nome/sobrenome null/vazio
    dhManager.mudarNome(30000, "")                                      //mudar nome curso - nome null/vazio
    dhManager.alocarProfessores(null, 20002, 20001)                     //alocar profs em curso - codigo curso null
    dhManager.alocarProfessores(30001, null, null)                      //alocar profs em curso - codigo prof null
    dhManager.matricularAlunoCurso(null, 30000)                         //Matricular aluno em curso - codigo curso null
    dhManager.matricularAlunoCurso(50000, null)                         //Matricular aluno em curso - codigo aluno null

}
