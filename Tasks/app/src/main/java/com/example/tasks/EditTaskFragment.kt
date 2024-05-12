package com.example.tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.tasks.databinding.FragmentEditTaskBinding

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId

        val application = requireNotNull(activity).application
        val taskDAO = TaskDatabase.getInstance(application).taskDAO
        val viewModelFactory = EditTaskViewModelFactory(taskId, taskDAO)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(EditTaskViewModel::class.java)

        binding.editTaskViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.navigateToList.observe(viewLifecycleOwner, Observer { navigateToList ->
            if (navigateToList) {
                view.findNavController().navigate(R.id.action_editTaskFragment_to_tasksFragment)
            }
        })

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}