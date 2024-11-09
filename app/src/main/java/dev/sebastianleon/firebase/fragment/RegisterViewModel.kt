package dev.sebastianleon.firebase.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.sebastianleon.firebase.model.RegisterModel
import dev.sebastianleon.firebase.repository.RegisterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {
    private val repository = RegisterRepository()

    private val _isRegisterAdded = MutableLiveData<Boolean>()
    val isRegisterAdded: MutableLiveData<Boolean>
        get() = _isRegisterAdded

    fun createRegister(register: RegisterModel) {
        repository.createRegister(register).enqueue(object: Callback<RegisterModel> {
            override fun onResponse(call: Call<RegisterModel>, response: Response<RegisterModel>) {
                if (response.isSuccessful) {
                    _isRegisterAdded.value = true
                }
            }
            override fun onFailure(call: Call<RegisterModel>, t: Throwable) {
                _isRegisterAdded.value = false
            }
        })
    }
}