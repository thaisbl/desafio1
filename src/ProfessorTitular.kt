import java.time.LocalDate

class ProfessorTitular(
        nome: String,
        sobrenome: String,
        codigo: Int,
        dataEntrada: LocalDate,
        var especialidade: String?)
    : Professor(nome, sobrenome, codigo, dataEntrada
) {
    override fun toString(): String {
        return super.toString() + " | Especialidade: ${especialidade ?: " "}"
    }
}