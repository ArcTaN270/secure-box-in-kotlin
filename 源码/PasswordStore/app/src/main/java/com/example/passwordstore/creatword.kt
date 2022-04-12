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

class creatword : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creatword)

        val text1 : EditText = findViewById(R.id.aboutpassword)
        val text2 : EditText = findViewById(R.id.username)
        val text3 : EditText = findViewById(R.id.password)
        val savebutton : Button = findViewById(R.id.login)

        savebutton.setOnClickListener {
            if(text1.text.toString() == "") {
                Toast.makeText(this, "请输入相关信息", Toast.LENGTH_SHORT).show()
            }

            else if(text2.text.toString() == "") {
                Toast.makeText(this, "请输入用户名",Toast.LENGTH_SHORT).show()
            }

            else if(text3.text.toString() == "") {
                Toast.makeText(this, "请输入密码",Toast.LENGTH_SHORT).show()
            }

            else if(text3.text.toString().length < 6) {
                Toast.makeText(this, "密码安全性过低，尝试用复杂字符加长密钥",Toast.LENGTH_SHORT).show()
            }

            else {
                val creatmanner : SharedPreferences.Editor = getSharedPreferences("box", Context.MODE_PRIVATE).edit()
                val uptheNum = getSharedPreferences("data",Context.MODE_PRIVATE)

                val num=uptheNum.getInt("numofpass", 0).toString()
                creatmanner.putString("tip"+num, text2.text.toString())
                creatmanner.putString("pass"+num, text3.text.toString())
                creatmanner.commit()

                Toast.makeText(this, "创建成功",Toast.LENGTH_SHORT).show()
                val backtomain : Intent = Intent(this, MainActivity::class.java)
                startActivity(backtomain)

                finish()

            }

        }

    }
}
