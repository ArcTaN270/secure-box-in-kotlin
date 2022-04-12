package com.example.passwordstore

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.teprinciple.mailsender.Mail
import com.teprinciple.mailsender.MailSender

class modify_pass : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.modify_pass)

        val t1 : EditText = findViewById(R.id.mailed)

        val t2 : EditText = findViewById(R.id.yanzheng)

        val num : String = randomNumber()


        val but : Button = findViewById(R.id.sendnumber)

        val butt : Button = findViewById(R.id.buttonensure)

        val mail = Mail().apply {
            mailServerHost = "smtp.qq.com"
            mailServerPort = "587"
            fromAddress = "bitfuyu@foxmail.com"
            password = "lzrlvnkbxqbtbcaj"
            toAddress = arrayListOf("${t1}")
            subject = "密码箱修改密码验证码"
            content = "验证码：" + num

        }

        but.setOnClickListener {
            MailSender.getInstance().sendMail(mail)
            Toast.makeText(this, "已发送验证码", Toast.LENGTH_LONG).show()
        }
        butt.setOnClickListener {
            val num1="123456"
            if(t2.text.toString() == num1) {
                val turntoni : Intent = Intent(this, forget_pass::class.java)
                startActivity(turntoni)
            }
            else {
                Toast.makeText(this, "验证码错误", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun randomNumber( ) : String {
        val number : String = (100000..999999).random().toString()

        return number
    }
}
