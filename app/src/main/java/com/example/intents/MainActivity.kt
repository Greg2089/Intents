package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private var login: String = "empty"
    private var password: String = "empty"
    private var name: String = "empty"
    private var nameOne: String = "empty"
    private var nameTwo: String = "empty"
    private var avatarImageId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bExit.setOnClickListener {
            onClickSignIn()
        }
        binding.bHide.setOnClickListener {
            onClickSignUp()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Constance.REQUEST_CODE_SIGN_IN) {
            val l = data?.getStringExtra(Constance.LOGIN)
            val p = data?.getStringExtra(Constance.PASSWORD)
            if (login == l && password == p) {

                binding.imAvatar.visibility = View.VISIBLE
                binding.imAvatar.setImageResource(avatarImageId)
                val textInfo = "$name $nameOne $nameTwo"
                binding.tvInfo.text = textInfo
                binding.bHide.visibility = View.GONE
                binding.bExit.text = "Выйти"

            } else {

                binding.imAvatar.visibility = View.VISIBLE
                binding.imAvatar.setImageResource(R.drawable.face_co)
                binding.tvInfo.text = "Такого аккаунта не существует!"

            }

        } else if (requestCode == Constance.REQUEST_CODE_SIGN_UP) {

            login = data?.getStringExtra(Constance.LOGIN)!!
            password = data.getStringExtra(Constance.PASSWORD)!!
            name = data.getStringExtra(Constance.NAME)!!
            nameOne = data.getStringExtra(Constance.NAMEONE)!!
            nameTwo = data.getStringExtra(Constance.NAMETWO)!!
            avatarImageId = data.getIntExtra(Constance.AVATAR_ID, 0)
            binding.imAvatar.visibility = View.VISIBLE
            binding.imAvatar.setImageResource(avatarImageId)
            val textInfo = "$name $nameOne $nameTwo"
            binding.tvInfo.text = textInfo
            binding.bHide.visibility = View.GONE
            binding.bExit.text = "Выйти"

        }
    }

    fun onClickSignIn() {

        if (binding.imAvatar.isVisible && binding.tvInfo.text.toString() != "Такого аккаунта не существует!") {

            binding.imAvatar.visibility = View.INVISIBLE
            binding.tvInfo.text = ""
            binding.bHide.visibility = View.VISIBLE
            binding.bExit.text = getString(R.string.signIn)

        } else {

            val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)

        }

    }

    fun onClickSignUp() {

        val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)

    }

}
