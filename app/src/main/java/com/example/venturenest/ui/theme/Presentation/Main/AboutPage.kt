package com.example.venturenest.ui.theme.Presentation.Main

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun AboutPage(
    modifier: Modifier = Modifier,
   window:WindowInsets
) {
    val lightcolor =  Color(0xffffefea)
    val darkcolor =Color(0xffdd1212)
    Box(
        modifier
            .fillMaxSize()
            .windowInsetsPadding(window)
           , contentAlignment = Alignment.BottomCenter
        ) {
var couritine = rememberCoroutineScope()
        val context = LocalContext.current
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {

            Row(
                modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.05f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {





            }
//            Spacer(modifier = modifier.fillMaxWidth().fillMaxHeight(0.03f).clip(
//                RoundedCornerShape(bottomEnd  = 50f, bottomStart = 50f)
//            ).background(Color(0xffdd1212)))
            Column(
                modifier
                    .padding(bottom = 66.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {




            ElevatedCard(
                modifier
                    .padding(top = 20.dp, bottom = 20.dp)
                    .fillMaxWidth(0.9f)

                    .wrapContentHeight()
                    .shadow(
                        elevation = 5.dp,
                        spotColor = Color.Black,
                        shape = RectangleShape
                    ), shape = RectangleShape,
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
            ) {
                Row(modifier.fillMaxWidth()) {
                    Text(modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
                        .fillMaxWidth(), text = "Contact US", textAlign = TextAlign.Center,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize.times(1.2),
                        fontWeight = FontWeight.Bold, color = Color.Black)
                }


                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 0.dp)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        IconButton(onClick = {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:9960292682")
                            context.startActivity(intent)


                        }) {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }

                        Text(
                            text = "Phone",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize,
                            fontWeight = FontWeight.SemiBold, color = Color.Black
                        )
                        Text(
                            text = "+91-99602926xx",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                        )
                        Text(
                            text = "Dr. Ati Priye",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                        )
                    }
                    Column(
                        modifier
                            .fillMaxWidth()
                            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            val phoneNumber = "+919960292682"  // Include country code (+91 for India)
                            val url = "https://wa.me/$phoneNumber?text=Hello"
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(url)
                            intent.setPackage("com.whatsapp")  // Ensure WhatsApp is used
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                // Handle the case where WhatsApp is not installed
                                Toast.makeText(context, "WhatsApp is not installed", Toast.LENGTH_SHORT).show()
                            }

                        }) {
                            Icon(
                                imageVector = Icons.Default.Chat,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }

                        Text(
                            text = "Whatsapp",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.SemiBold, color = Color.Black
                        )
                        Text(
                            text = "+91-99602926xx",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                        )
                        Text(
                            text = "Dr. Ati Priye",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                        )
                    }


                }
//
                Row(
                    modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp)
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        modifier
                            .fillMaxWidth()
                            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {  val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                            data = Uri.parse("mailto:sumity12019@gmail.com") // Replace with the recipient's email
                            putExtra(Intent.EXTRA_SUBJECT, "APP@!0 : ") // Optional: Add email subject
                            putExtra(Intent.EXTRA_TEXT, "From App : ") // Optional: Add email body
                        }
                            try {
                                context.startActivity(emailIntent)
                            } catch (e: Exception) {
                                // Handle the case where no email app is installed
                                Toast.makeText(context, "No email app installed", Toast.LENGTH_SHORT).show()
                            } }) {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }
                        Text(
                            text = "Email",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.SemiBold, color = Color.Black
                        )
                        Text(
                            text = "venturenest@Venture.com",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight

                        )
                    }
                    Column(
                        modifier
                            .fillMaxWidth()
                            .weight(1f), horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        IconButton(onClick = {
                            val location = "CGC Jhanjheri , Punjab"
                            val geoUri = Uri.parse("geo:0,0?q=$location")
                            val mapIntent = Intent(Intent.ACTION_VIEW, geoUri)
                            mapIntent.setPackage("com.google.android.apps.maps") // Ensure Google Maps is used
                            try {
                                context.startActivity(mapIntent)
                            } catch (e: Exception) {
                                // Handle case where Google Maps is not installed
                                Toast.makeText(context, "Google Maps is not installed", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "",
                                tint = Color.Red
                            )
                        }
                        Text(
                            text = "Location",
                            textAlign = TextAlign.Center,
                            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                            fontWeight = FontWeight.SemiBold, color = Color.Black
                        )
                        Text(
                            text = "VentureNest CGC",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize,
                            lineHeight = MaterialTheme.typography.labelSmall.lineHeight
                        )
                    }
                }


            }

        }
        }
    }

}

//@Preview
//@Composable
//private fun PreviewAboutPage() {
//    AboutPage()
//}