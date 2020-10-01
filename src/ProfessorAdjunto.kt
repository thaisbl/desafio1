import java.time.LocalDate

class ProfessorAdjunto(
        nome: String,
        sobrenome: String,
        codigo: Int,
        dataEntrada: LocalDate = LocalDate.now(),
        var qtdHorasMonitoria: Int?)
    : Professor(nome, sobrenome, codigo, dataEntrada) {
    override fun toString(): String {
        return super.toString() + " | Horas Monitoria: ${qtdHorasMonitoria ?: 0}"
    }
}