import java.time.LocalDate

class DigitalHouseManager(
        private val listaMapAlunos: MutableMap<Int, Aluno> = mutableMapOf(),
        private val listaMapProfessores: MutableMap<Int, Professor> = mutableMapOf(),
        private val listaMapCursos: MutableMap<Int, Curso> = mutableMapOf(),
        private val listaMatriculas: MutableList<Matricula> = mutableListOf()
) {
    private var contCodigoProf = 20000
    private var contCodigoCurso = 30000
    private var contCodigoAluno = 50000

    fun registrarCurso(nome: String?, qtdMaxAlunos: Int?) {
        if (nome.isNullOrEmpty() || qtdMaxAlunos == null) {
            println("Registro do curso não realizado. Verifique parâmetros inseridos e tente novamente.")
        } else {
            val nomeCursoExiste = listaMapCursos.any { it.value.nomecurso == nome }
            if (!nomeCursoExiste) {
                val curso = Curso(nome, contCodigoCurso, qtdMaxAlunos)
                listaMapCursos[curso.codigoCurso] = curso
                println("Curso '$nome' adicionado com sucesso.")
                contCodigoCurso++
            } else println("Já existe curso com este nome.")
        }
    }

    fun excluirCurso(codigoCurso: Int?) {
        val cursoComMatricula = listaMatriculas.filter { it.curso.codigoCurso == codigoCurso }
        if (cursoComMatricula.isNotEmpty())
            cursoComMatricula.forEach {
                listaMatriculas.remove(it)
            }
        if (listaMapCursos.containsKey(codigoCurso)) {
            listaMapCursos.remove(codigoCurso)
            println("Curso removido com sucesso.")
        } else println("Código inválido.")
    }

    fun mudarQtdMaxAlunosCurso(codigoCurso: Int?, novaQtdMax: Int?) {
        if (novaQtdMax != null && novaQtdMax >= 0)
            if (listaMapCursos[codigoCurso] == null)
                println("Código inserido inválido.")
            else {
                val qtdAlunosMatriculados = listaMapCursos[codigoCurso]?.listaAlunosMatriculados?.size ?: 0
                if (novaQtdMax >= qtdAlunosMatriculados)
                    listaMapCursos[codigoCurso]?.qtdMaxAlunos = novaQtdMax
            }
        else println("Quantidade inserida inválida.")
    }

    fun registrarProfAdjunto(nome: String?, sobrenome: String?, dataEntrada: String? = "", qtdDeHoras: Int? = 0) {
        if (nome.isNullOrEmpty() || sobrenome.isNullOrEmpty())
            println("Registro do professor não realizado. Verifique parâmetros inseridos e tente novamente.")
        else {
            val professorAdjunto = ProfessorAdjunto(nome, sobrenome, contCodigoProf, LocalDate.now(), qtdDeHoras ?: 0)
            professorAdjunto.mudarDataEntrada(dataEntrada)
            listaMapProfessores[contCodigoProf] = professorAdjunto
            println("Professor ${professorAdjunto.nomeCompleto()} adicionado com sucesso.")
            contCodigoProf++
        }
    }

    fun registrarProfTitular(nome: String?, sobrenome: String?, dataEntrada: String? = "", especialidade: String? = "") {
        if (nome.isNullOrEmpty() || sobrenome.isNullOrEmpty())
            println("Registro do professor não realizado. Verifique parâmetros inseridos e tente novamente.")
        else {
            val professorTitular = ProfessorTitular(nome, sobrenome, contCodigoProf, LocalDate.now(), especialidade)
            professorTitular.mudarDataEntrada(dataEntrada)
            listaMapProfessores[contCodigoProf] = professorTitular
            println("Professor ${professorTitular.nomeCompleto()} adicionado com sucesso.")
            contCodigoProf++
        }
    }

    fun excluirProfessor(codigoProfessor: Int?) {
        if (listaMapProfessores.containsKey(codigoProfessor)) {
            val alocadoTitular = listaMapCursos.filterValues { it.professorTitular?.codigo == codigoProfessor }
            if (alocadoTitular.isNotEmpty())
                alocadoTitular.forEach { it.value.professorTitular = null }

            val alocadoAdjunto = listaMapCursos.filterValues { it.professorAdjunto?.codigo == codigoProfessor }
            if (alocadoAdjunto.isNotEmpty())
                alocadoTitular.forEach { it.value.professorTitular = null }

            listaMapProfessores.remove(codigoProfessor)
            println("Professor removido com sucesso.")
        } else println("Código inválido.")
    }

    fun mudarDataEntradaProf(codigoProfessor: Int?, dataEntrada: String? = "") {
        if (listaMapProfessores.containsKey(codigoProfessor)) {
            listaMapProfessores[codigoProfessor]?.mudarDataEntrada(dataEntrada)
        } else println("Código do professor não encontrado.")
    }

    fun mudarNome(codigo: Int?, nome: String? = "", sobrenome: String? = "") {
        codigo?.let {
            val digit = codigo / 10000
            when {
                nome.isNullOrEmpty() && sobrenome.isNullOrEmpty()
                -> println("Mudança de nome não realizada. Insira nome/sobrenome válidos e tente novamente.")
                digit == 2 -> listaMapProfessores[codigo]?.mudarNomeProf(nome, sobrenome)
                        ?: println("Código não encontrado.")
                digit == 3 -> listaMapCursos[codigo]?.mudarNomeCurso(nome)
                        ?: println("Código não encontrado.")
                digit >= 5 -> listaMapAlunos[codigo]?.mudarNomeAluno(nome, sobrenome)
                        ?: println("Código não encontrado.")
                else -> println("Código não encontrado.")
            }
        }
    }

    fun registrarAluno(nome: String?, sobrenome: String?) {
        if (nome.isNullOrEmpty() || sobrenome.isNullOrEmpty()) {
            println("Registro do aluno não realizado. Verifique parâmetros inseridos e tente novamente.")
        } else {
            val aluno = Aluno(nome, sobrenome, contCodigoAluno)
            listaMapAlunos[contCodigoAluno] = aluno
            println("Aluno '$aluno' matriculado com sucesso.")
            contCodigoAluno++
        }
    }

    fun excluirAluno(codigoAluno: Int?) {
        if (listaMapAlunos.containsKey(codigoAluno)) {
            val alunoMatriculado = listaMatriculas.filter { it.aluno.codigo == codigoAluno }
            if (alunoMatriculado.isNotEmpty())
                alunoMatriculado.forEach {
                    listaMapCursos[it.curso.codigoCurso]?.desmatricularAluno(listaMapAlunos[codigoAluno])
                    listaMatriculas.remove(it)
                }
            listaMapAlunos.remove(codigoAluno)
            println("Aluno removido com sucesso.")

        } else println("Código do aluno não registrado.")
    }

    fun matricularAlunoCurso(codigoAluno: Int?, codigoCurso: Int?) {
        val aluno = listaMapAlunos[codigoAluno]
        val curso = listaMapCursos[codigoCurso]
        if (aluno == null || curso == null) {
            println("Código inválido. Verifique e tente novamente.")
        } else if (curso.podeMatricularAluno(aluno)) {
            curso.listaAlunosMatriculados.add(aluno)
            val matricula = Matricula(aluno, curso)
            listaMatriculas.add(matricula)
        } else println("Não foi possível matricular aluno. " +
                "Turma cheia e/ou aluno já matriculado neste curso")
    }

    fun alocarProfessores(codigoCurso: Int?, codigoProfTitular: Int?, codigoProfAdjunto: Int?): MutableMap<Int, Professor> {
        val curso = listaMapCursos[codigoCurso]
        curso?.let {
            val profTitular: Professor? = listaMapProfessores[codigoProfTitular]
            profTitular?.let {
                if (profTitular is ProfessorTitular) {
                    curso.professorTitular = profTitular
                    println("Professor Titular ${profTitular.nomeCompleto()} alocado no curso '${curso.nomecurso}'")
                } else println("Não é possível alocar Professor Adjunto para Professor Titular do curso.")
            } ?: println("Código do professor adjunto inválido. Verifique e tente novamente.")
            val profAdjunto: Professor? = listaMapProfessores[codigoProfAdjunto]
            profAdjunto?.let {
                if (profAdjunto is ProfessorAdjunto) {
                    curso.professorAdjunto = profAdjunto
                    println("Professor Adjunto ${profAdjunto.nomeCompleto()} alocado no curso '${curso.nomecurso}'")
                } else println("Não é possível alocar Professor Titular para Professor Adjunto do curso.")
            } ?: println("Código do professor adjunto inválido. Verifique e tente novamente.")
        } ?: println("Código do curso inválido. Verifique e tente novamente.")
        return listaMapProfessores
    }

    // Bloco de funções para imprimir informações:
    fun imprimirListaMapProfessores() {
        if (listaMapCursos.isEmpty())
            println("Nenhum professor registrado")
        else {
            println("----------")
            println("Os professores registrados são: ")
            listaMapProfessores.forEach {
                println("\tCódigo: ${it.key} | ${it.value}")
            }
        }
    }

    fun imprimirListaMapCursos() {
        if (listaMapCursos.isEmpty())
            println("Nenhum curso registrado")
        else {
            println("----------")
            println("Os cursos registrados são: ")
            listaMapCursos.forEach { println("\tCódigo: ${it.key} | ${it.value}") }
        }
    }

    fun imprimirListaMapAlunos() {
        if (listaMapCursos.isEmpty())
            println("Nenhum aluno registrado")
        else {
            println("----------")
            println("Os alunos registrados são:")
            listaMapAlunos.forEach { println("\tCódigo: ${it.key} | ${it.value}") }
        }
    }

    fun imprimirListaMatriculas() {
        println("----------")
        println("Lista de Matrículas da Digital House:")
        if (listaMatriculas.isEmpty()) {
            println("Nenhuma matrícula ativa")
        }
        listaMatriculas.forEach {
            println(it.toString())
        }
    }

    fun listaDeAlunosCurso(codigoCurso: Int?) {
        val curso = listaMapCursos[codigoCurso]
        curso?.let {
            if (curso.listaAlunosMatriculados.isNullOrEmpty())
                println("Nenhum aluno matriculado neste curso.")
            else {
                println("----------")
                println("Alunos matriculados no curso ${curso.nomecurso}:")
                listaMapCursos[codigoCurso]?.listaAlunosMatriculados?.forEach {
                    println("\t$it | Código: ${it.codigo}")
                }
            }
        } ?: println("Código do curso inválido.")
    }

    fun tempoDeCasaProf(codigoProfessor: Int?) {
        if (listaMapProfessores.containsKey(codigoProfessor)) {
            val prof = listaMapProfessores[codigoProfessor]
            println(prof?.tempoDeCasa())
        } else {
            println("Código do professor não encontrado.")
        }
    }

    fun tempoDeCasaTodosProfs() {
        println("----------")
        println("O tempo de casa dos professores cadastrados é:")
        listaMapProfessores.forEach { println(it.value.tempoDeCasa()) }
    }

    fun pesquisar(codigo: Int?) {
        println("----------")
        codigo?.let {
            if (codigo / 10000 == 2) {          // codigo começando com 2 -> professor
                if (listaMapProfessores.containsKey(codigo)) {
                    val prof = listaMapProfessores[codigo]
                    if (prof is ProfessorAdjunto) {
                        println("Código $codigo corresponde ao Professor Adjunto:")
                    } else println("Código $codigo corresponde ao Professor Titular:")
                    println("\t" + prof.toString())
                }
            } else if ((codigo / 10000 == 3)) {          // codigo começando com 3 -> curso
                if (listaMapCursos.containsKey(codigo)) {
                    val curso = listaMapCursos[codigo]
                    println("Código $codigo corresponde ao Curso:")
                    println(curso.toString())
                }
            } else if (codigo / 10000 >= 5 && codigo < contCodigoAluno) {   // codigo começando com 5 ou + -> aluno
                if (listaMapAlunos.containsKey(codigo)) {
                    val aluno = listaMapAlunos[codigo]
                    println("Código $codigo corresponde ao Aluno:")
                    println(aluno.toString())
                }
            } else println("Código não registrado no sistema.")
        } ?: println("Código inválido.")
    }

    fun pesquisar(nome: String?) {
        println("----------")
        nome?.let {
            val temCursos = listaMapCursos.filterValues { it.nomecurso.contains(nome, ignoreCase = true) }
            val temProfessor = listaMapProfessores.filterValues { it.toString().contains(nome, ignoreCase = true) }
            val temAluno = listaMapAlunos.filterValues { it.toString().contains(nome, ignoreCase = true) }
            if (temAluno.isNullOrEmpty() && temCursos.isNullOrEmpty() && temProfessor.isNullOrEmpty()) {
                println("Nenhuma correspondência para '$nome'.")
            } else {
                println("Resultados da pesquisa:")
                if (temCursos.isNotEmpty()) {
                    println("Curso(s) Encontrado(s):")
                    temCursos.forEach { println("\tCódigo ${it.key} | ${it.value}") }
                }
                if (temProfessor.isNotEmpty()) {
                    println("Professor(es) Encontrado(s):")
                    temProfessor.forEach {
                        if (it.value is ProfessorAdjunto) {
                            print("\tProfessor Adjunto | ")
                        } else print("\tProfessor Titular | ")
                        println("Código ${it.key} | ${it.value}")
                    }
                }
                if (temAluno.isNotEmpty()) {
                    println("Aluno(s) Encontrado(s):")
                    temAluno.forEach { println("\tCódigo ${it.key} | ${it.value}") }
                }
            }
        } ?: println("Nome inválido.")
    }
}