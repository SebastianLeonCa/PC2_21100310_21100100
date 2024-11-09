package dev.sebastianleon.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dev.sebastianleon.firebase.model.UserModel

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val etUser: EditText = findViewById(R.id.etUser)
        val etPassword: EditText = findViewById(R.id.etPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnSignup: Button = findViewById(R.id.btnSignup)
        val auth = FirebaseAuth.getInstance()
        val db = FirebaseFirestore.getInstance()

        btnLogin.setOnClickListener {
            val email = etUser.text.toString()
            val password = etPassword.text.toString()

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if(task.isSuccessful){
                        //Inicio de sesión correcto
                        Snackbar.make(findViewById(android.R.id.content),
                            "Login correcto",
                            Snackbar.LENGTH_SHORT).show()
                        startActivity(Intent(this, PrincipalActivity::class.java))
                    }else{
                        //Inicio de sesión incorrecto
                        Snackbar.make(findViewById(android.R.id.content),
                            "Login incorrecto",
                            Snackbar.LENGTH_SHORT).show()
                    }
                    }
        }

        btnSignup.setOnClickListener {
            val email = etUser.text.toString()
            val password = etPassword.text.toString()

            //Generar el usuario en FirebaseAuth
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this){task ->
                    if(task.isSuccessful){
                        val user: FirebaseUser? = auth.currentUser
                        val uid = user?.uid
                        val userModel: UserModel = UserModel(email, uid)

                        db.collection("users")
                            .add(userModel)
                            .addOnCompleteListener{
                                Snackbar
                                    .make(findViewById(android.R.id.content),
                                        "Registro exitoso",
                                        Snackbar.LENGTH_SHORT).show()
                                startActivity(Intent(this, LoginActivity::class.java))
                            }
                    }
                }.addOnFailureListener{error ->
                    Snackbar
                        .make(findViewById(android.R.id.content),
                            "Ocurrió un error al registrar el usuario: ${error.message}",
                            Snackbar.LENGTH_SHORT).show()
                    startActivity(Intent(this, LoginActivity::class.java))
                }
        }

    }
}