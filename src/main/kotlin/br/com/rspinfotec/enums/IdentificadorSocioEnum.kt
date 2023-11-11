package br.com.rspinfotec.enums

enum class IdentificadorSocioEnum(val identificadorId: Int, val descricao: String) {
    PESSOA_JURIDICA(1, "PESSOA JURÍDICA"),
    PESSOA_FISICA(2, "PESSOA FÍSICA"),
    ESTRANGEIRO(3, "ESTRANGEIRO");

    companion object {
        fun getByIdentificadorID(identificadorId: Int): IdentificadorSocioEnum? {
            return IdentificadorSocioEnum.values().firstOrNull() { it.identificadorId == identificadorId }
        }
    }
}