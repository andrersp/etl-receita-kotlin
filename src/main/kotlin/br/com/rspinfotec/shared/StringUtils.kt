package br.com.rspinfotec.shared

object StringUtils {

    fun clearString(stringValue: String): String =
        stringValue.replace("\u0000", "").replace("^@", "").trim()

    fun clearCpf(cpfString: String): String? {
        val onlyDigits = cpfString.filter { it.isDigit() }
        return if (onlyDigits != "000000") {
            onlyDigits
        } else {
            null
        }
    }

    fun parseCnpjToString(cnpjBase: Int, cnpjOrdem: Int, cnpjDv: Int): String {

        val stringBuilder = StringBuilder()
        stringBuilder.append(cnpjBase)
        while (stringBuilder.length < 8) {
            stringBuilder.insert(0, 0)
        }
        val cnpjOrdemString = cnpjOrdem.toString().padStart(4, '0')
        val cnpjDvString = cnpjDv.toString().padStart(2, '0')
        stringBuilder.append(cnpjOrdemString, cnpjDvString)
        stringBuilder.insert(2, '.')
        stringBuilder.insert(6, '.')
        stringBuilder.insert(10, '/')
        stringBuilder.insert(15, '-')
        return stringBuilder.toString()

    }


}