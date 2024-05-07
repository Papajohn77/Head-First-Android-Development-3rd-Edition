package com.example.stopwatch

import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.Chronometer
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var stopwatch: Chronometer
    private var isRunning = false
    private var offset: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState != null) {
            isRunning = savedInstanceState.getBoolean("isRunning")
            offset = savedInstanceState.getLong("offset")

            if (isRunning) {
                binding.stopwatch.base = savedInstanceState.getLong("base")
                binding.stopwatch.start()
            } else {
                setBaseTime()
            }
        }

        binding.startBtn.setOnClickListener {
            if (!isRunning) {
                setBaseTime()
                binding.stopwatch.start()
                isRunning = true
            }
        }

        binding.pauseBtn.setOnClickListener {
            if (isRunning) {
                saveOffset()
                binding.stopwatch.stop()
                isRunning = false
            }
        }

        binding.resetBtn.setOnClickListener {
            offset = 0
            setBaseTime()
        }
    }

    private fun setBaseTime() {
        binding.stopwatch.base = SystemClock.elapsedRealtime() - offset
    }

    private fun saveOffset() {
        offset = SystemClock.elapsedRealtime() - binding.stopwatch.base
    }

    // Save state when screen rotates
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("isRunning", isRunning)
        outState.putLong("offset", offset)
        outState.putLong("base", binding.stopwatch.base)
        super.onSaveInstanceState(outState)
    }

    // Stop the stopwatch when the activity becomes invisible
    override fun onStop() {
        super.onStop()
        if (isRunning) {
            saveOffset()
            binding.stopwatch.stop()
        }
    }

    // We don't use onStart() because it also runs when the activity becomes
    // visible for the first time. onRestart() only gets called when the activity
    // becomes visible again after losing visibility.

    // Start the stopwatch when the activity becomes visible again
    override fun onRestart() {
        super.onRestart()
        if (isRunning) {
            setBaseTime()
            binding.stopwatch.start()
            offset = 0
        }
    }

    // We could use onPause() & onResume() instead, to enforce the same
    // behavior either when the activity is partially visible (doesn't
    // have the focus) or it is invisible.

//    override fun onPause() {
//        super.onPause()
//        if (isRunning) {
//            saveOffset()
//            binding.stopwatch.stop()
//        }
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (isRunning) {
//            setBaseTime()
//            binding.stopwatch.start()
//            offset = 0
//        }
//    }
}