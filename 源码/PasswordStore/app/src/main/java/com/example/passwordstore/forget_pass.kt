package com.example.passwordstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class forget_pass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pass)

        val t1 : EditText = findViewById(R.id.oldp)

        val t2 : EditText = findViewById(R.id.newp)

        val t3 : EditText = findViewById(R.id.enewp)

        val enbutton : Button = findViewById(R.id.buttonaa)

        enbutton.setOnClickListener {
            if (t2.text.toString() != t3.text.toString()) {
                Toast.makeText(this, "密码更改前后不一致", Toast.LENGTH_SHORT).show()
            }
            else if(t2.text.toString() =="") {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show()
            }
            else if (t3.text.toString() == "") {
                Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show()
            }
            else {
                val modi : SharedPreferences.Editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()

                modi.putString("Password", t3.text.toString())
                modi.commit()

                Toast.makeText(this, "修改成功", Toast.LENGTH_SHORT).show()

                var turntoreguita : Intent = Intent(this, Registration::class.java)

                startActivity(turntoreguita)
                finish()
            }
        }

    }
}
