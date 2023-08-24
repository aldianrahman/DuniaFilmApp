package com.example.duniafilmapp.login.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.duniafilmapp.shared.ScreenRoutes
import com.example.ecommercemvvm.R
import com.example.ecommercemvvm.databinding.LoginSignupFragmentBinding

class LoginFragment:Fragment() {
    private lateinit var binding: LoginSignupFragmentBinding
    private val viewModel: LoginViewModel by viewModels()
    private val args : LoginFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LoginSignupFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner){viewState->
            updateUI(viewState)
        }
        when (args.myAge) {
            ScreenRoutes.SignupScreen.routes -> {
                viewModel.signup()
            }
            ScreenRoutes.LoginScreen.routes -> {
                viewModel.login()
            }
            else -> {
                viewModel.error()
            }
        }
    }

    @SuppressLint("UseRequireInsteadOfGet")
    private fun updateUI(viewState: LoginViewState?) {
        when (viewState){
            is LoginViewState.Login -> {
                val word = "Masuk"
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                binding.headerText.text = word
                binding.nameText.isVisible = false
                binding.nameEditText.isVisible = false
                binding.phoneText.isVisible = false
                binding.phoneEditText.isVisible = false
                binding.loginSignupButton.text = word
                binding.forgetPasswordText.isVisible = true
                binding.forgetPasswordText.setOnClickListener{
                    viewModel.forget()
                }
                signupText(word)
                binding.signupTextDescription.setOnClickListener{
                    viewModel.signup()
                }
                binding.loginSignupButton.setOnClickListener {
                    viewModel.error()
                }
            }
            is LoginViewState.Signup ->{
                val word = "Daftar"
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                binding.headerText.text = word
                binding.nameText.isVisible = true
                binding.nameEditText.isVisible = true
                binding.phoneText.isVisible = true
                binding.phoneEditText.isVisible = true
                binding.loginSignupButton.text = word
                binding.forgetPasswordText.isVisible = false
                signupText(word)
                binding.signupTextDescription.setOnClickListener{
                    viewModel.login()
                }
                binding.loginSignupButton.setOnClickListener {
                    viewModel.loading()
                }
            }
            is LoginViewState.Forget ->{
                val word = "Lupa Password"
                binding.headerText.text = word
                binding.passwordText.isVisible = false
                binding.passwordEditText.isVisible = false
                binding.signupTextDescription.isVisible = false
                binding.forgetPasswordText.isVisible = false
                binding.titleTextLogin.text = "Masukan Email Anda"
                binding.loginSignupButton.text = "Submit"
            }
            LoginViewState.Error -> {
                binding.errorView.isVisible = true
            }
            LoginViewState.Loading -> {
                binding.loadingView.isVisible = true
            }

            else -> {}
        }
    }

    private fun signupText(myAge: String) {
        var firstWord = "Belum"
        var lastWord = "Daftar"

        if (myAge == "Daftar"){
            firstWord = "Sudah"
            lastWord = "Masuk"
        }

        // Mendapatkan teks dari TextView
        binding.signupTextDescription.text = "$firstWord punya akun? $lastWord"
        val text = binding.signupTextDescription.text.toString()

        // Membuat SpannableString dari teks
        val spannableString = SpannableString(text)

        // Menentukan indeks awal dan akhir dari teks "Daftar"
        val startIndex = text.indexOf(lastWord)
        val endIndex = startIndex + lastWord.length


        // Mengubah warna teks pada indeks yang ditentukan
        val colorSpan = ForegroundColorSpan(resources.getColor(R.color.blue_forget_password)) // Ganti dengan warna yang diinginkan
        spannableString.setSpan(colorSpan, startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)




        // Set SpannableString ke TextView
        binding.signupTextDescription.text = spannableString
    }

}