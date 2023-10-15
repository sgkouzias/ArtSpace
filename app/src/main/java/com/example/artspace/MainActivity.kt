package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    pageView()
                }
            }
        }
    }
}

val imageOne = imageInfo(R.drawable.bodhidharmayoshitoshi1887,
    "Bodhidharma",
    "Tsukioka Yoshitoshi",
    "1887")

val imageTwo = imageInfo(R.drawable.lao,
    "Lao Tzŭ",
    "Ikarashi Shunmei",
    "1750")

val imageThree = imageInfo(R.drawable.be_one_with_nature,
    "Be one with nature",
    "Bing AI",
    "2023")

val imageFour = imageInfo(R.drawable.the_grand_master,
    "Sifu",
    "Bing AI",
    "2023")

val images = listOf(imageOne, imageTwo, imageThree, imageFour)
val numberOfSamples: Int = images.size

@Composable
fun contentDisplay(drawableResourceId: Int,
                   title: String,
                   artist: String,
                   year: String,
                   modifier: Modifier = Modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier) {
            Row(modifier = modifier) {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = null,
                    modifier = modifier
                        .width(350.dp)
                        .height(500.dp))
            }
            Spacer(modifier = modifier.height(20.dp))
            Row(modifier = modifier) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = modifier.wrapContentWidth()
                )
            }
            Row(modifier = modifier) {
                Text(
                    text = artist,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = " ($year)",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = modifier.height(20.dp))
        }
}

@Composable
fun pageView() {

    var currentStep by remember { mutableStateOf(0) }

    val image = images.get(currentStep)
    val title = image.title
    val year = image.year
    val drawableResourceId = image.imageResource
    val artist = image.artist
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier =  Modifier) {
        contentDisplay(drawableResourceId = drawableResourceId,
            title = title, artist = artist, year = year)
        Row(modifier = Modifier) {
            Button(onClick = { if(currentStep != 0) currentStep-- }) {
                Text(
                    text = "Previous",
                    fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.width(30.dp))
            Button(onClick = { if(currentStep < numberOfSamples) currentStep ++ }) {
                Text(
                    text = "Next",
                    fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ArtPreview() {
    ArtSpaceTheme {
        pageView()
    }
}