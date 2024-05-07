package com.example.bitsandpizzas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class OrderFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_order, container, false)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)

        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val pizzaGroup = view.findViewById<RadioGroup>(R.id.pizza_type_group)
            val pizzaType = pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                val message = "You need to choose a pizza type"
                Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
            } else {
                var message = when (pizzaType) {
                    R.id.radio_diavolo -> "Diavolo pizza"
                    else -> "Funghi pizza"
                }
                val parmesanChip = view.findViewById<Chip>(R.id.chip_parmesan)
                message += if (parmesanChip.isChecked) ", extra parmesan" else ""
                val chiliOilChip = view.findViewById<Chip>(R.id.chip_chili_oil)
                message += if (chiliOilChip.isChecked) ", extra chili oil" else ""
                Snackbar.make(fab, message, Snackbar.LENGTH_LONG).show()
            }
        }

        return view
    }
}