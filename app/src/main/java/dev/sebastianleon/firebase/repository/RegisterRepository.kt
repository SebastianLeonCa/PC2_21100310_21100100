package dev.sebastianleon.firebase.repository

import dev.sebastianleon.firebase.model.RegisterModel
import dev.sebastianleon.firebase.service.RetrofitInstance
import dev.sebastianleon.firebase.service.RegisterService

class RegisterRepository {
    private val registerService: RegisterService = RetrofitInstance.api

    fun getRegisters() = registerService.getRegisters()
    fun createRegister(register: RegisterModel) = registerService.createRegister(register)
}