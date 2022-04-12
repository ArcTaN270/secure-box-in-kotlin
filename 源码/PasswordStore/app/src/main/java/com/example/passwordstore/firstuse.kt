package com.example.passwordstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.ActionBar

class firstuse : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.firstuse)
        val actionbar : ActionBar? = getSupportActionBar();

        if(actionbar != null) {
            actionbar.hide()
        }

        val creat_password : EditText = findViewById(R.id.creatpass)
        val ensure_password : EditText = findViewById(R.id.ensurepass)
        val user_name : EditText = findViewById(R.id.userName)

        val ensureButton : Button = findViewById(R.id.ensuretocreat)

        ensureButton.setOnClickListener {
            if(user_name.text.toString() == "") {
                Toast.makeText(this, "请输入用户名",Toast.LENGTH_SHORT).show()
            }
            else {
                if (creat_password.text.toString() == ensure_password.text.toString()) {

                    val creat : SharedPreferences.Editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()

                    creat.putString("Password", ensure_password.text.toString())
                    creat.putString("username", user_name.text.toString())
                    creat.putInt("Flag1", 1)
                    creat.putInt("Flag", 1)
                    creat.putInt("numofpass", 0)
                    creat.commit() //查询好久

                    val ver : SharedPreferences = getSharedPreferences("data", Context.MODE_PRIVATE)
                    //Toast.makeText(this, ver.getString("Password","").toString() , Toast.LENGTH_SHORT).show()
                    val created : Intent = Intent(this, Registration::class.java)
                    startActivity(created)

                }
                else {
                    Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}
