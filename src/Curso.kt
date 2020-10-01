class Curso(
        var nomecurso: String,
        val codigoCurso: Int,
        var qtdMaxAlunos: Int,
        var professorTitular: ProfessorTitular? = null,
        var professorAdjunto: ProfessorAdjunto? = null,
        var listaAlunosMatriculados: MutableList<Aluno> = mutableListOf()
) {
    init {
        if (qtdMaxAlunos < 0) qtdMaxAlunos = 0
    }

    fun mudarNomeCurso(novoNome: String?) {
        if (!novoNome.isNullOrEmpty()) {
            nomecurso = novoNome
            println("Mudança de nome do curso ocorreu com sucesso.")
        } else println("Nome inserido inválido. Verifique nome inserido e tente novamente")
    }

    fun podeMatricularAluno(umAluno: Aluno): Boolean {
        var podeAdicionar = false
        if (listaAlunosMatriculados.size < qtdMaxAlunos && !listaAlunosMatriculados.contains(umAluno)) {
            println("Aluno '${umAluno}' matriculado no curso '$nomecurso'.")
            podeAdicionar = true
        }
        return podeAdicionar
    }

    fun desmatricularAluno(umAluno: Aluno?) {
        umAluno?.let {
            if (listaAlunosMatriculados.contains(umAluno)) {
                listaAlunosMatriculados.remove(umAluno)
                println("Aluno $umAluno removido do curso $nomecurso.")
            }
        }
    }

    override fun toString(): String {
        return "\"$nomecurso\" - Max. Alunos: $qtdMaxAlunos | Alunos Matriculados: ${listaAlunosMatriculados.size}" +
                "\n\t\t\t\tProf. Titular: ${professorTitular?.nome ?: "não definido"} ${professorTitular?.sobrenome ?: ""}" +
                " | Prof. Adjunto: ${professorAdjunto?.nome ?: "não definido"}  ${professorAdjunto?.sobrenome ?: ""}"
    }
}