    package com.example.bus_app_e.activities

    import android.content.Intent
    import android.os.Bundle
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.example.bus_app_e.R
    import com.example.bus_app_e.databinding.ActivityMainBinding
    import com.google.firebase.auth.FirebaseAuth
    import com.yandex.mapkit.MapKitFactory


    class MainActivity : AppCompatActivity() {
        private lateinit var binding: ActivityMainBinding
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            MapKitFactory.setApiKey("327c16a3-434f-4ada-bcf6-9f380b92316e")
            setContentView(R.layout.activity_main)
        }
    }