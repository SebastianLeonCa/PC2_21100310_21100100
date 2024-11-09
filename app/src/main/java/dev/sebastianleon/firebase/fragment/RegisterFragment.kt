package dev.sebastianleon.firebase.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dev.sebastianleon.firebase.LoginActivity
import dev.sebastianleon.firebase.R
import dev.sebastianleon.firebase.model.RegisterModel
import dev.sebastianleon.firebase.model.UserModel
import dev.sebastianleon.firebase.repository.RegisterRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_register, container, false)

        val db = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val etFecha: EditText = view.findViewById(R.id.etFecha)
        val etDesc: EditText = view.findViewById(R.id.etDesc)
        val etMonto: EditText = view.findViewById(R.id.etMonto)
        val btGuardar: Button = view.findViewById(R.id.btGuardar)

        btGuardar.setOnClickListener {
            val fecha = etFecha.text.toString()
            val desc = etDesc.text.toString()
            val monto = etMonto.text.toString()

        }

        return view
    }
}