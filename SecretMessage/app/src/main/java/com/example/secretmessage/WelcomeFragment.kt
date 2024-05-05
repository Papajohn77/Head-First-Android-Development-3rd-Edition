package com.example.secretmessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

class WelcomeFragment : Fragment() {

    // Called when the fragment needs to be displayed.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        val startBtn = view.findViewById<Button>(R.id.start_btn)
        startBtn.setOnClickListener {
            val navController = view.findNavController()
            navController.navigate(R.id.action_welcomeFragment_to_messageFragment)
        }

        return view
    }
}