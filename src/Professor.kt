import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

abstract class Professor(
        var nome: String,
        var sobrenome: String,
        val codigo: Int,
        private var dataEntrada: LocalDate = LocalDate.now()
) {
    private val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy") // para formatar a data ☺

    fun tempoDeCasa(): String {
        return String.format("Tempo de Casa de ${nomeCompleto()} é de: " +
                "${dataEntrada.until(LocalDate.now()).years} anos, " +
                "${dataEntrada.until(LocalDate.now()).months} meses e " +
                "${dataEntrada.until(LocalDate.now()).days} dias")
    }

    fun mudarDataEntrada(novaDataEntrada: String? = "") {
        try {
            if (!novaDataEntrada.isNullOrEmpty()) {
                val dataEntradaFormatada = LocalDate.parse(novaDataEntrada, formatter)
                dataEntrada = dataEntradaFormatada
                println("Data modificada com sucesso. Nova data é $novaDataEntrada")
                tempoDeCasa()
            } else println("Formato de data inválido. Formato aceito: dd/mm/aaaa")
        } catch (ex: DateTimeParseException) {
            println("Formato de data inválido. Formato aceito: dd/mm/aaaa")
            println("Para mudar data de entrada, utilize a função mudarDataEntradaProf.")
        }
    }

    fun nomeCompleto(): String {
        return "$nome $sobrenome"
    }

    fun mudarNomeProf(novoNome: String?, novoSobrenome: String?) {
        if (!novoNome.isNullOrEmpty() && !novoSobrenome.isNullOrEmpty()) {
            nome = novoNome
            sobrenome = novoSobrenome
            println("Mudança de nome e sobrenome do professor ocorreu com sucesso.")
        } else if (!novoNome.isNullOrEmpty()) {
            nome = novoNome
            println("Mudança de nome do professor ocorreu com sucesso.")
        } else if (!novoSobrenome.isNullOrEmpty()) {
            sobrenome = novoSobrenome
            println("Mudança de sobrenome do professor ocorreu com sucesso.")
        } else println("Nome/Sobrenome inseridos inválidos. Verifique e tente novamente")
    }

    override fun toString(): String {
        return ("$nome $sobrenome | " +
                "Data de Entrada: ${"%02d".format(dataEntrada.dayOfMonth)}" +
                "/${"%02d".format(dataEntrada.monthValue)}" +
                "/${"%04d".format(dataEntrada.year)}")
    }
}