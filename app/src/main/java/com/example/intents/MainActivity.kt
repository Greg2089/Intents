package com.example.intents

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.intents.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var launcher: ActivityResultLauncher<Intent>? = null

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
        launcher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    result: ActivityResult ->
                if (result.resultCode == Constance.REQUEST_CODE_SIGN_IN) {
                    val l = result.data?.getStringExtra(Constance.LOGIN)
                    val p = result.data?.getStringExtra(Constance.PASSWORD)
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

                } else if (result.resultCode == Constance.REQUEST_CODE_SIGN_UP) {

                    login = result.data?.getStringExtra(Constance.LOGIN)!!
                    password = result.data?.getStringExtra(Constance.PASSWORD)!!
                    name = result.data?.getStringExtra(Constance.NAME)!!
                    nameOne = result.data?.getStringExtra(Constance.NAMEONE)!!
                    nameTwo = result.data?.getStringExtra(Constance.NAMETWO)!!
                    avatarImageId = result.data!!.getIntExtra(Constance.AVATAR_ID, 0)
                    binding.imAvatar.visibility = View.VISIBLE
                    binding.imAvatar.setImageResource(avatarImageId)
                    val textInfo = "$name $nameOne $nameTwo"
                    binding.tvInfo.text = textInfo
                    binding.bHide.visibility = View.GONE
                    binding.bExit.text = "Выйти"

                }
            }
    }

   /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)


    }*/

    fun onClickSignIn() {

        if (binding.imAvatar.isVisible && binding.tvInfo.text.toString() != "Такого аккаунта не существует!") {

            binding.imAvatar.visibility = View.INVISIBLE
            binding.tvInfo.text = ""
            binding.bHide.visibility = View.VISIBLE
            binding.bExit.text = getString(R.string.signIn)

        } else {
      launcher?.launch(Intent(this, SignInUpActivity::class.java))
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            /*val intent = Intent(this, SignInUpActivity::class.java)
            intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_IN_STATE)
            startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_IN)*/

        }

    }

    fun onClickSignUp() {
        launcher?.launch(Intent(this, SignInUpActivity::class.java))
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
       /* val intent = Intent(this, SignInUpActivity::class.java)
        intent.putExtra(Constance.SIGN_STATE, Constance.SIGN_UP_STATE)
        startActivityForResult(intent, Constance.REQUEST_CODE_SIGN_UP)*/

    }

}
