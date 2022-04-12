package com.example.passwordstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.SharedLibraryInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import kotlinx.android.synthetic.main.register.*

class Registration : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.register)
        val actionbar : ActionBar? = getSupportActionBar();

        if(actionbar != null) {
            actionbar.hide()
        }

        val pass : SharedPreferences.Editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
        pass.putInt("Flag", 0)


        val ver : SharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
        if(ver.getInt("Flag", 0).toString() == "0") {
            val first : Intent = Intent(this, firstuse::class.java)
            startActivity(first)

            Toast.makeText(this, "First use", Toast.LENGTH_SHORT).show()
        }


        if(ver.getInt("Flag1", 1).toString() == "1" ) {

            val printname : TextView = findViewById(R.id.printname)

            printname.text = "尊敬的" + ver.getString("username","用户")

            val registerButton : Button = findViewById(R.id.register_button)
            val entercode : EditText = findViewById(R.id.enter_password)

            registerButton.setOnClickListener {

                if (ver.getString("Password","") == entercode.text.toString()) {

                    var main : Intent = Intent(this, MainActivity::class.java)

                    startActivity(main)
                    finish()
                }
                else {
                    Toast.makeText(this, "密码错误", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}


