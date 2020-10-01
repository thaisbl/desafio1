class Aluno(var nome: String, var sobrenome: String, val codigo: Int) {

    override fun toString(): String {
        return "$nome $sobrenome"
    }

    fun mudarNomeAluno(novoNome: String?, novoSobrenome: String?) {
        if (!novoNome.isNullOrEmpty() && !novoSobrenome.isNullOrEmpty()) {
            nome = novoNome
            sobrenome = novoSobrenome
            println("Mudança de nome e sobrenome do aluno ocorreu com sucesso.")
        } else if (!novoNome.isNullOrEmpty()) {
            nome = novoNome
            println("Mudança de sobrenome do aluno ocorreu com sucesso.")
        } else if (!novoSobrenome.isNullOrEmpty()) {
            sobrenome = novoSobrenome
            println("Mudança de nome do aluno ocorreu com sucesso.")
        } else println("Nome/Sobrenome inseridos inválidos. Verifique e tente novamente")
    }
}