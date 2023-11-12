package br.com.rspinfotec.shared

object Maintenance {
    private var status = false

    fun checkMaintenance() = status

    fun changeStatus(){
        status = !status
    }
}