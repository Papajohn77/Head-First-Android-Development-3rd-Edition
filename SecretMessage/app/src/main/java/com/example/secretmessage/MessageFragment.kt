package com.example.secretmessage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

class MessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_message, container, false)

        val nextBtn = view.findViewById<Button>(R.id.next_btn)
        nextBtn.setOnClickListener {
            val message = view.findViewById<TextView>(R.id.message)
            val action = MessageFragmentDirections.actionMessageFragmentToEncryptFragment(
                message.text.toString()
            )
            view.findNavController().navigate(action)
        }

        return view
    }
}