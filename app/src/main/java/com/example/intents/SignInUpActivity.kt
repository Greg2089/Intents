package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.intents.databinding.ActivitySignInUpBinding

class SignInUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInUpBinding
    private var signState = "empty"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bAvatar.setOnClickListener {
            onClickAvatar()
        }
        binding.button2.setOnClickListener {
            onClickDone()
        }
        signState = intent.getStringExtra(Constance.SIGN_STATE)!!
        if (signState == Constance.SIGN_IN_STATE) {

            binding.edName.visibility = View.GONE
            binding.ediName2.visibility = View.GONE
            binding.edName3.visibility = View.GONE
            binding.bAvatar.visibility = View.INVISIBLE

        }
    }

    fun onClickDone() {

        if (signState == Constance.SIGN_UP_STATE) {

            //val intent = Intent()
            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            intent.putExtra(Constance.NAME, binding.edName.text.toString())
            intent.putExtra(Constance.NAMEONE, binding.ediName2.text.toString())
            intent.putExtra(Constance.NAMETWO, binding.edName3.text.toString())
            if (binding.imAvatar.isVisible) intent.putExtra(Constance.AVATAR_ID, R.drawable.face_co)
            setResult(RESULT_OK, intent)
            finish()

        } else if (signState == Constance.SIGN_IN_STATE) {

            intent.putExtra(Constance.LOGIN, binding.edLogin.text.toString())
            intent.putExtra(Constance.PASSWORD, binding.edPassword.text.toString())
            setResult(RESULT_OK, intent)
            finish()

        }

    }

    fun onClickAvatar() {
        binding.imAvatar.visibility = View.VISIBLE
    }


}
