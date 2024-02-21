package com.example.mominchatapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.klinker.android.link_builder.Link


class Login : AppCompatActivity() {

    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var mAuth:FirebaseAuth
    private  lateinit var signUpLink:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)
        mAuth=FirebaseAuth.getInstance()
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogin = findViewById(R.id.edt_loginBtn)
//        btnSignUp = findViewById(R.id.edt_signUpBtn)
        signUpLink=findViewById(R.id.edt_signUpLink)

        signUpLink.setOnClickListener {
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }



        btnLogin.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password);
        }

        // Declaring and Initializing
        // the TextView from the layout file
        val mTextView = findViewById<TextView>(R.id.linkedIn_id)

        // Finding and displaying the content
        // that consists a URL as a hyperlink
        mTextView.movementMethod = LinkMovementMethod.getInstance()
    }


    private fun login(email:String,password:String){
        //logic for logging user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                  //code for login in user
                    val intent = Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@Login,"Incorrect email/password", Toast.LENGTH_SHORT).show()
                }
            }
    }



}