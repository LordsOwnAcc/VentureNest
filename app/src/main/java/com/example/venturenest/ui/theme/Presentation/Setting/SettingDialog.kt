package com.example.venturenest.ui.theme.Presentation.Setting

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.venturenest.ui.theme.DaggerHilt.Events


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog2(
    show:MutableState<Boolean> ,
    selected:Int,
    modifier: Modifier
    ) {
    AnimatedVisibility(visible = show.value) {


        var schroll = rememberScrollState()

        val context = LocalContext.current
        ModalBottomSheet(
            onDismissRequest = { show.value = false },

            shape = RectangleShape,
            containerColor = Color.Transparent, modifier = Modifier,
        ) {
            Box(
                modifier = Modifier
                    .offset(y = -10.dp)
                    .wrapContentHeight().fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopCenter
            ) {
                when(selected){
                    0->{
                        ElevatedCard(
                            modifier
                                .padding(top = 20.dp, bottom = 20.dp)
                                .fillMaxWidth(0.9f)

                                .wrapContentHeight()
                                .shadow(
                                    elevation = 5.dp,
                                    spotColor = Color.Black,
                                    shape = RoundedCornerShape(15f)
                                ), shape = RoundedCornerShape(15f),
                            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                        ) {
                            Text(
                                text = "Privacy and Policy", fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                maxLines = 3, textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                            Text(
                                text = "Loreem ipsum lorem ipsum", fontWeight = FontWeight.Normal,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                maxLines = 3, textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(top = 0.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
                            )

                        }
                    }
                    1->{
                        ElevatedCard(
                            modifier
                                .padding(top = 20.dp, bottom = 20.dp)
                                .fillMaxWidth(0.9f)

                                .wrapContentHeight()
                                .shadow(
                                    elevation = 5.dp,
                                    spotColor = Color.Black,
                                    shape = RoundedCornerShape(15f)
                                ), shape = RoundedCornerShape(15f),
                            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                        ) {

                            Text(
                                text = "Terms and Condition", fontWeight = FontWeight.SemiBold,
                                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                                maxLines = 3, textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(top = 10.dp, bottom = 10.dp)
                            )
                            Text(
                                text = "Loreem ipsum lorem ipsum", fontWeight = FontWeight.Normal,
                                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                maxLines = 3, textAlign = TextAlign.Start,
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(top = 0.dp, bottom = 10.dp,start = 10.dp, end = 10.dp)
                            )

                        }
                    }
                    2->{

                        ElevatedCard(
                            modifier
                                .padding(top = 20.dp, bottom = 20.dp)
                                .fillMaxWidth(0.9f)

                                .wrapContentHeight()
                                .shadow(
                                    elevation = 5.dp,
                                    spotColor = Color.Black,
                                    shape = RoundedCornerShape(15f)
                                ), shape = RoundedCornerShape(15f),
                            colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
                        ) {
                            Row(modifier.fillMaxWidth()) {
                                Text(modifier = modifier.padding(top = 20.dp, bottom = 20.dp)
                                    .fillMaxWidth(), text = "Connect with us", textAlign = TextAlign.Center,
                                    fontSize = MaterialTheme.typography.titleLarge.fontSize.times(1.2),
                                    fontWeight = FontWeight.Medium, color = Color.Gray)
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
                                        fontWeight = FontWeight.Medium, color = Color.Gray
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
                                        fontWeight = FontWeight.Medium, color = Color.Gray
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
                                        .padding(bottom = 20.dp)
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
                                        fontWeight = FontWeight.Medium, color = Color.Gray
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
                                        .padding(bottom = 20.dp)
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
                                        fontWeight = FontWeight.Medium, color = Color.Gray
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
    }
}


