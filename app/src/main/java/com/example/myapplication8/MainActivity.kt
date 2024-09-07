package com.example.myapplication8

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.PixelCopy.Request
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import android.Manifest
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadImageButton.setOnClickListener {
            checkPermission()
        }

    }

    private fun checkPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED -> {
                loadImage()
            }
            shouldShowRequestPermissionRationale(
                Manifest.permission.READ_EXTERNAL_STORAGE)->{
                    showPermissionInfoDialog()
                }
            else -> {
                requestReadExternalStorage()
            }
        }

    }

        private fun showPermissionInfoDialog() {
            AlertDialog.Builder(this).apply {
                setMessage("이미지를 가져오기 위해서, 외부 저장소 읽기 권한이 필요합니다.")
                setNegativeButton("취소", null)
                setPositiveButton("동의",){_,_ ->
                    requestReadExternalStorage()
                }
            }.show()
        }

        private fun loadImage() {
            Toast.makeText(this,"이미지를 가져올 예정", Toast.LENGTH_SHORT).show()
        }

        private fun requestReadExternalStorage() {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),REQUEST_READ_EXTERNAL_STORAGE)

        }

        companion object{
            const val REQUEST_READ_EXTERNAL_STORAGE = 100
        }
    }