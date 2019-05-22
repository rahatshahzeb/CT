package com.android.task.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.android.task.R
import com.android.task.databinding.ActivityLoginBinding
import com.android.task.ui.main.MainActivity
import dagger.android.AndroidInjection
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        loginViewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

        binding.viewModel = loginViewModel

        binding.lifecycleOwner = this

        prePopulateUser()
        subscribeObserver()
    }

    private fun prePopulateUser() {
        loginViewModel.prePopulateUser()
    }

    private fun subscribeObserver() {
        // Not needed for this assignment
        subscribeAccountValueObserver()
        subscribePasswordValueObserver()

        subscribeUserDataObserver()
        subscribeIsLoginErrorObserver()
        subscribeNavHomeObserver()
    }

    private fun subscribeAccountValueObserver() {
        loginViewModel.accountValue.observe(this, Observer {
            // Get the account value here if needed
        })
    }

    private fun subscribePasswordValueObserver() {
        loginViewModel.passwordValue.observe(this, Observer {
            // Get the password value here if needed
        })
    }

    private fun subscribeIsLoginErrorObserver() {
        loginViewModel.isLoginError.observe(this, Observer {

        })
    }

    private fun subscribeNavHomeObserver() {
        loginViewModel.navHome.observe(this, Observer {
            navHome()
        })
    }

    private fun subscribeUserDataObserver() {
        loginViewModel.userData.observe(this, Observer {
            loginViewModel.handleLoginResult(it)
        })
    }

    private fun navHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
