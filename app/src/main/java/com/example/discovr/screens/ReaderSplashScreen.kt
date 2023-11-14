package com.example.discovr.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.discovr.navigation.ReaderScreens
import kotlinx.coroutines.delay
import com.example.discovr.components.ReaderLogo
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ReaderSplashScreen(navController: NavController? = null) {
    //for animation
    val scale = remember{ androidx.compose.animation.core.Animatable(0f) }

    //Animation effect
    LaunchedEffect(key1 = true){
        scale.animateTo( targetValue = 0.9f, animationSpec = tween(durationMillis = 800, easing = {
            OvershootInterpolator(8f).getInterpolation(it)
        }))
        delay(2000L)
        //if there's a fb user, take user to Home Screen
        if (FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) navController?.navigate(ReaderScreens.LoginScreen.name)
        else navController?.navigate(ReaderScreens.ReaderHomeScreen.name)
    }
    //Logo intro stuff
    Surface(modifier = Modifier
        .padding(15.dp)
        .size(330.dp)
        .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(width = 2.dp, color = Color.LightGray)) {
        Column(modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {

            ReaderLogo()

            Text(text = "\"Discover new reads together \"",
                modifier = Modifier.scale(scale.value),
                style = MaterialTheme.typography.h5, color = Color.LightGray)

            Spacer(modifier = Modifier.height(15.dp))

        }
    }
}

