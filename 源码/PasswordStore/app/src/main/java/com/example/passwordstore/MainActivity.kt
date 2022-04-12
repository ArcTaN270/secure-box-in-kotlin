package com.example.passwordstore

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity() : AppCompatActivity() {

    private var mdata = arrayOf("")

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.inthebox)
        val actionbar: ActionBar? = supportActionBar;

        if (actionbar != null) {
            actionbar.hide()
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.addbutton)
        }

        val toolbar: Toolbar = findViewById(R.id.passwordtoolbar)
        setSupportActionBar(toolbar) //版本问题导致的不兼容,我杀他全家

        val floatpic: FloatingActionButton = findViewById(R.id.addpassword)
        floatpic.setOnClickListener {
            val created: Intent = Intent(this, creatword::class.java)

            val toupdate = getSharedPreferences("data", Context.MODE_PRIVATE)
            val num = toupdate.getInt("numofpass", 0) + 1


            val upthenum: SharedPreferences.Editor =
                getSharedPreferences("data", Context.MODE_PRIVATE).edit()
            upthenum.putInt("numofpass", num)
            upthenum.commit()

            startActivity(created)
        }
        val drawerlayout123: DrawerLayout = findViewById(R.id.draw_layout)

        val navView: NavigationView = findViewById(R.id.nav_view)

        val modi : Intent = Intent(this, modify_pass::class.java)

        val forget : Intent = Intent(this, forget_pass::class.java)
        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_call -> startActivity(modi)
                R.id.nav_mail -> startActivity(forget)
            }

            drawerlayout123.closeDrawers()
            true
        }

        val getNew = getSharedPreferences("data", Context.MODE_PRIVATE)
        val getbox = getSharedPreferences("box", Context.MODE_PRIVATE)

        val lastNum = getNew.getInt("numofpass", 1)
        val lastNumS = getNew.getInt("numofpass", 1).toString()
        val str = getbox.getString("tip" + lastNumS, "").toString()
        val newBox: Array<String> = Array(mdata.size + 1) { "" }
        for (i in mdata.indices) {
            newBox[i] = mdata[i]
            newBox[mdata.size] = str
        }
        mdata = newBox
        val adapter = ArrayAdapter(
            this@MainActivity, android.R.layout.simple_list_item_1, mdata
        )
        val listview = findViewById<View>(R.id.list_view) as ListView

        listview.adapter = adapter
        listview.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position)
            for (i in 0..lastNum) {

                 val element = adapter.getItem(position)

                if (element == mdata[i]) {
                    val ver : SharedPreferences = getSharedPreferences("box", Context.MODE_PRIVATE)
                    Toast.makeText(this, ver.getString("tip0",""), Toast.LENGTH_SHORT).show()
                    Toast.makeText(this, "123456", Toast.LENGTH_LONG).show()
                }
            }
        }

    }
}