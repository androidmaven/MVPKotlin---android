package com.padipady.mvpkotlin.androidmvpkotlin.login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.content.Intent
import android.support.v7.appcompat.R.styleable.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.padipady.mvpkotlin.androidmvpkotlin.R
import com.padipady.mvpkotlin.androidmvpkotlin.main.MainActivity


/**
 * Created by Oluwatobi on 5/31/2017.
 */
public class LoginActivity : AppCompatActivity(), LoginView, View.OnClickListener {
    private var progressBar: ProgressBar? = null
    private var username: EditText? = null
    private var password: EditText? = null
    private var presenter: LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
         username = findViewById(R.id.username) as EditText
         password =  findViewById(R.id.password) as EditText
         progressBar = findViewById(R.id.progress) as ProgressBar
        findViewById(R.id.button).setOnClickListener(this)
         presenter = LoginPresenterImpl(this)

    }
    override fun showProgress() {
        progressBar?.visibility=VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility =GONE;
    }

    override fun setUsernameError() {
        username?.error =getString(R.string.username_error)

    }

    override fun setPasswordError() {
        password?.error = getString(R.string.password_error)
    }

    override fun navigateToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()

    }

    override fun onClick(p0: View?) {
        presenter?.validateCredentials(username?.text.toString(), password?.text.toString());

    }


}
