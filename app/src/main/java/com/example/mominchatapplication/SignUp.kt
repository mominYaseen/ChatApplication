package com.example.mominchatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var edtName : EditText
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText

    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef : DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        
        mAuth = FirebaseAuth.getInstance()
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)

        btnSignUp = findViewById(R.id.edt_signUpBtn)

        btnSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val email=edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(name,email,password)
        }

    }


    private fun signUp(name:String,email:String,password:String){
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home activity
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    val intent = Intent(this@SignUp,Login::class.java)
                    Toast.makeText(this@SignUp,"Account has been created",Toast.LENGTH_SHORT).show()
                    finish()
                    startActivity(intent)

//                    val intent = Intent(this@SignUp,MainActivity::class.java)
//                    startActivity(intent)
                } else {
                  Toast.makeText(this@SignUp,"Some error occured",Toast.LENGTH_SHORT).show()
                }
            }
    }

//add user to the database

    private fun addUserToDatabase(name:String,email:String,uid:String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }
}