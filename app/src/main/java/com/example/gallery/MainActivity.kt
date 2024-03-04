package com.example.gallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gallery.ui.theme.GalleryTheme

data class ImageInfo(
    val resourceId: Int,
    val author: String,
    val date: String
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GalleryTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ImageExample()
                }
            }
        }
    }
}

@Composable
fun ImageExample() {
    val images = listOf(
        ImageInfo(R.drawable.image, "Auteur 1", "1998"),
        ImageInfo(R.drawable.image1, "Auteur 2", "2001")
    )

    var imageIndex by remember { mutableStateOf(0) }

    val currentImage = images[imageIndex]

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    )
    {
        Spacer(modifier = Modifier.height(100.dp))

        Box(
            modifier = Modifier
                .size(450.dp)
                .height(350.dp)
                .clip(RoundedCornerShape(8.dp))
                .border(width = 3.dp, color = Color.White)
                .background(Color.White)
                .padding(16.dp)
                .shadow(10.dp, RoundedCornerShape(8.dp)) // Ajouter une ombre pour l'effet 3D
        ) {
            Image(
                painter = painterResource(id = currentImage.resourceId),
                contentDescription = "Image",
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6495ED).copy(alpha = 0.5f)) // Couleur bleue claire avec transparence
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center, // Centrer verticalement le contenu
                horizontalAlignment = Alignment.CenterHorizontally, // Centrer horizontalement le contenu
                modifier = Modifier.fillMaxWidth()
            )
            {
                Text(
                    text = "Auteur: ${currentImage.author}",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Date d'apparition: ${currentImage.date}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(
                onClick = { imageIndex = (imageIndex - 1 + images.size) % images.size },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text(
                    "Previous",
                    color = Color.Blue,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
            Button(
                onClick = { imageIndex = (imageIndex + 1) % images.size },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
            ) {
                Text(
                    "Next",
                    color = Color.Blue,
                    style = TextStyle(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Medium
                    )
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GalleryTheme {
        ImageExample()
    }
}







