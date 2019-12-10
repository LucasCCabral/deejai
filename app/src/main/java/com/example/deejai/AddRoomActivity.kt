package com.example.deejai

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.deejai.Constants.NEW_ROOM_RESULT_CODE
import com.example.deejai.Constants.ROOM_NAME
import kotlinx.android.synthetic.main.activity_add_room.*


class AddRoomActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)
        toolbar.setNavigationIcon(R.drawable.close)
        new_room_name.requestFocus()
        openKeyboard()
        setSupportActionBar(toolbar)
    }

    fun openKeyboard() {
        val imm  = getSystemService(
            Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    fun closeKeyboard() {
        val imm = getSystemService(
            Context.INPUT_METHOD_SERVICE
        ) as InputMethodManager
        imm.hideSoftInputFromWindow(new_room_name.getWindowToken(), 0)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add_room, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.add -> {
                if(new_room_name.text.toString() != "") {
                    closeKeyboard()
                    val intent = Intent()
                    intent.putExtra(ROOM_NAME, new_room_name.text.toString())
                    setResult(NEW_ROOM_RESULT_CODE, intent)
                    finish()
                }
            }
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
