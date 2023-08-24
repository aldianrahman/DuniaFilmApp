package com.example.duniafilmapp.splashscreen.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.ecommercemvvm.databinding.SplashScreenFragmentBinding

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment: Fragment() {
    private lateinit var binding: SplashScreenFragmentBinding
    private val viewModel:SplashScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SplashScreenFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.postDelayed({
            findNavController().navigate(SplashScreenFragmentDirections.splashScreenFragmentToHomeFragment())
        }, 3000) // 3000 milidetik (3 detik)
    }



}