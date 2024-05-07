package com.example.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bitsandpizzas.databinding.FragmentOrderBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaTypeGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                val message = "You need to choose a pizza type"
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            } else {
                var message = when (pizzaType) {
                    R.id.radio_diavolo -> "Diavolo pizza"
                    else -> "Funghi pizza"
                }
                message += if (binding.chipParmesan.isChecked) ", extra parmesan" else ""
                message += if (binding.chipChiliOil.isChecked) ", extra chili oil" else ""
                Snackbar.make(binding.fab, message, Snackbar.LENGTH_LONG).show()
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}