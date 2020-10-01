import java.time.LocalDate

class Matricula(val aluno: Aluno, val curso: Curso, val dataMatricula: LocalDate = LocalDate.now()) {

    val data = "${"%02d".format(dataMatricula.dayOfMonth)}/${"%02d".format(dataMatricula.monthValue)}/${dataMatricula.year}"

    override fun toString(): String {
        return "Aluno: $aluno | Curso: ${curso.nomecurso} | Data da Matricula: $data"
    }
}