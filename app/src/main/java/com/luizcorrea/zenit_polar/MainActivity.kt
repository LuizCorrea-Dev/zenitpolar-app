package com.luizcorrea.zenit_polar

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.luizcorrea.zenit_polar.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var txt: TextView
    private lateinit var result: TextView
    private lateinit var toEncryptBtn: Button
    private lateinit var toDecryptBtn: Button

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        txt = findViewById<View>(R.id.et_input) as TextView
        result = findViewById<View>(R.id.tv_result) as TextView

        toDecryptBtn= findViewById<View>(R.id.shareBtn) as Button
        toEncryptBtn = findViewById<View>(R.id.encryptBtn) as Button

        toEncryptBtn.setOnClickListener(View.OnClickListener {
            val txtToConvert = txt.text.toString()
            val convertedTxt: String = ZenitPolar.encrypt(txtToConvert)
            closeKeyboard()
            result.text = convertedTxt
        })
   

        binding.etInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.e("tag","after")
                campo()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.e("tag","before")
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.e("tag","on")
            }
        })

        binding.shareBtn.setOnClickListener {
            Log.e("tag","share")
            compartilhar()

        }


    }

    private fun compartilhar () {
        val intent = Intent()

        intent.action = Intent.ACTION_SEND
        intent.putExtra( Intent.EXTRA_TEXT, result.text.toString() )
        intent.type = "text/plain"

        if( intent.resolveActivity( packageManager ) != null ) {
            val intentChooser = Intent.createChooser( intent, "Compartilhar com:" )
            startActivity( intentChooser )
        }
    }


    private fun campo(){

        if(binding.etInput.text?.isEmpty() == true) {
            btn_clear.visibility = View.GONE
        } else {
            btn_clear.visibility = View.VISIBLE
        }
        btn_clear.setOnClickListener{
            tv_result.text = ""
            txt.text = ""
        }
    }

    private fun closeKeyboard() {
        val view = currentFocus
        view?.let {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }





}




