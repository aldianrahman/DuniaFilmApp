package com.example.duniafilmapp.home.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.duniafilmapp.shared.ScreenRoutes
import com.example.ecommercemvvm.R
import com.example.ecommercemvvm.databinding.HomeFragmentBinding
import com.squareup.picasso.Picasso

class HomeFragment: Fragment() {
    private lateinit var binding: HomeFragmentBinding
    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState.observe(viewLifecycleOwner){viewState->
            updateUI(viewState)
        }
        viewModel.loadUI()
    }

    private fun updateUI(viewState: HomeViewState?) {
        when (viewState){
            is HomeViewState.Content -> {
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = false
                binding.homeContent.isVisible = true
                binding.enterButton.setOnClickListener{
                    val action = HomeFragmentDirections.homeFragmentToLoginFragment().setMyAge(ScreenRoutes.LoginScreen.routes)
                    findNavController().navigate(action)
                }
                signupText()
                backgroundHome()
                contentOne()
            }
            HomeViewState.Error -> {
                binding.errorView.isVisible = true
                binding.loadingView.isVisible = false
                binding.homeContent.isVisible = false
            }
            HomeViewState.Loading -> {
                binding.errorView.isVisible = false
                binding.loadingView.isVisible = true
                binding.homeContent.isVisible = false
            }

            else -> {}
        }
    }

    private fun backgroundHome() {
        Picasso.get().load("https://image.tmdb.org/t/p/original/6oH378KUfCEitzJkm07r97L0RsZ.jpg").into(binding.backgroundHome)
    }

    private fun signupText() {
        val mSpannableString = SpannableString("Daftar Sekarang")
        mSpannableString.setSpan(UnderlineSpan(),0,mSpannableString.length,0)
        binding.signupText.text = mSpannableString
        binding.signupText.setOnClickListener{
            val action = HomeFragmentDirections.homeFragmentToSignupFragment().setMyAge(ScreenRoutes.SignupScreen.routes)
            findNavController().navigate(action)
        }
    }

    private fun contentOne() {
        // Access the LinearLayout view from your binding object
        val linearLayout = binding.contentOne // Replace with the ID of your LinearLayout

        // Set the height programmatically (e.g., to half the parent's height)
        val parentHeight = resources.displayMetrics.heightPixels // Get the parent's height
        val layoutParams = linearLayout.layoutParams
        layoutParams.height = parentHeight / 2 // Set height to half the parent's height
        linearLayout.layoutParams = layoutParams


    }
}