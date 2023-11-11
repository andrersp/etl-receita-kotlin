package br.com.rspinfotec.enums

enum class PorteEmpresaEnum(val code: Int, val descricao: String) {
    NAO_INFORMADO(0, "NÃO INFORMADO"),
    MICRO_EMPRESA(1, "MICRO EMPRESA"),
    PEQUENO_PORTE(3, "EMPRESA DE PEQUENO PORTE"),
    DEMAIS(5, "DEMAIS");

    companion object {
        fun getByCode(code: Int): PorteEmpresaEnum {
            return PorteEmpresaEnum.values().firstOrNull { it.code == code } ?: NAO_INFORMADO

        }
    }
}