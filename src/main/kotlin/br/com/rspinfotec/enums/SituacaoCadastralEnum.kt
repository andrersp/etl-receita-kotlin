package br.com.rspinfotec.enums

enum class SituacaoCadastralEnum(val code: Int) {
    DESCONHECIDA(0),
    NULA(1),
    ATIVA(2),
    SUSPENSA(3),
    INAPTA(4),
    BAIXADA(8);

    companion object {
        fun getByCode(code: Int): SituacaoCadastralEnum? {
            return SituacaoCadastralEnum.values().firstOrNull() { it.code == code }
        }
    }

}