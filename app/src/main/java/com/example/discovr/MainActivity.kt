package com.example.discovr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.discovr.navigation.ReaderNavigation
import com.example.discovr.ui.theme.DiscovrTheme
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiscovrTheme {

                Surface(color = MaterialTheme.colors.background,
                    modifier = Modifier.fillMaxSize()
                        .padding(top = 46.dp)) {
                    ReaderApp()
                }

            }
        }
    }

    @Composable
    private fun ReaderApp() {
        Surface( modifier = Modifier
            .fillMaxSize()
            .padding(10.dp))
        {
            Column( verticalArrangement =  Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                //this column and its properties will now apply to every screen
                ReaderNavigation()
            }
        }
    }
}
