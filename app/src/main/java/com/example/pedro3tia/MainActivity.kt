package com.example.pedro3tia

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pedro3tia.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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

        // Ambil data dari halaman Login
        val user = intent.getStringExtra("username")
        binding.txtUsername.text = "Halo, $user"

        // Intent: MainActivity -> DetailActivity
        binding.btnDetail.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("judul", binding.txtJudul.text.toString())
            startActivity(intent)
        }

        // AlertDialog Log Out
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(this)
                .setTitle("Log Out")
                .setMessage("Apakah kamu yakin ingin keluar?")
                .setNegativeButton("Tidak") { dialog, _ ->
                    // tetap di MainActivity
                    dialog.dismiss()
                }
                .setPositiveButton("Ya") { _, _ ->
                    // pindah ke halaman Login
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                .setCancelable(false)
                .show()
        }
    }
}
