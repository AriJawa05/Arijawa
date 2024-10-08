package com.example.arijawa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.arijawa.ui.theme.AriJawaTheme
import com.example.arijawa.ui.theme.Artwork

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AriJawaTheme {
                AriJawaApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AriJawaApp() {
    var currentArtworkIndex by remember { mutableStateOf(0) }
    val artworks = listOf(
        Artwork(R.drawable.ari1, "Mobil SUV ", "5 M","2024"),
        Artwork(R.drawable.ari2, "Mobil Crossoverr", "7 M","2024"),
        Artwork(R.drawable.ari3, "Mobil Sedan", "8 M","2024"),
        Artwork(R.drawable.ari4, "Mobil Convertible", "10 M","2024"),
        Artwork(R.drawable.ari5, "Mobil Station Wagon", "12 M","2024"),
        Artwork(R.drawable.ari6, "Mobil Off road", "15 M","2024")
        // Add more artworks here
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("PENJUALAN MOBIL")
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray)
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDisplay(artwork = artworks[currentArtworkIndex])

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = {
                    currentArtworkIndex = (currentArtworkIndex - 1 + artworks.size) % artworks.size
                }) {
                    Text("Previous")
                }

                Button(onClick = {
                    currentArtworkIndex = (currentArtworkIndex + 1) % artworks.size
                }) {
                    Text("Next")
                }
            }
        }
    }
}

@Composable
fun ArtworkDisplay(artwork: Artwork) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = artwork.imageResourceId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = artwork.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Text(
            text = "$ ${artwork.artist}",
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )

        Text(
            text = artwork.year,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}
