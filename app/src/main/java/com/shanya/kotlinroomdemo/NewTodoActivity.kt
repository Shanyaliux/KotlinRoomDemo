package com.shanya.kotlinroomdemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText

class NewTodoActivity : AppCompatActivity() {

    private lateinit var editTodoTitleView: EditText
    private lateinit var editTodoContentView: EditText

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_todo)
        editTodoTitleView = findViewById(R.id.edit_title)
        editTodoContentView = findViewById(R.id.edit_content)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTodoTitleView.text) or TextUtils.isEmpty(editTodoContentView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val title = editTodoTitleView.text.toString()
                val content = editTodoContentView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, "$title!$content")
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.shanya.kotlinroomdemo.REPLY"
    }
}
