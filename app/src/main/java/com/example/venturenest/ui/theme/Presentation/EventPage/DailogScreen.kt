package com.example.venturenest.ui.theme.Presentation.EventPage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.venturenest.R
import com.example.venturenest.ui.theme.DaggerHilt.Events

// Define the color palette
private val SlateBlue = Color(0xFF6B8299)
private val DarkSlateBlue = Color(0xFF5A7088)
private val LightSlateBlue = Color(0xFF7A92AB)
private val CardWhite = Color(0xFFFDFAF6)
private val AccentGold = Color(0xFFFFD700)
private val TextWhite = Color(0xFFF5F5F5)
private val SubtitleGray = Color(0xFFB8C5D4)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
    show: MutableState<Boolean>,
    event: Events,
    modifier: Modifier,
    color: Long
) {
    AnimatedVisibility(visible = show.value) {
        ModalBottomSheet(
            onDismissRequest = {
                show.value = false
            },
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
            containerColor = SlateBlue,
            modifier = Modifier,
        ) {
            val scrollState = rememberScrollState()
            
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(SlateBlue)
                    .verticalScroll(scrollState)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Event Image
                AsyncImage(
                    model = event.imageUrl,
                    contentDescription = "Event Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .shadow(
                            elevation = 8.dp,
                            shape = RoundedCornerShape(16.dp),
                            ambientColor = Color.Black.copy(alpha = 0.3f),
                            spotColor = Color.Black.copy(alpha = 0.3f)
                        ),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Event Name
                Text(
                    text = event.eventName.uppercase(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = TextWhite,
                    letterSpacing = 1.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                // Official Entry Pass subtitle
                Text(
                    text = "OFFICIAL ENTRY PASS",
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth(),
                    color = SubtitleGray,
                    letterSpacing = 2.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Info Card with Date, Location, Status
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = CardWhite),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 16.dp, horizontal = 8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Date Column
                        InfoColumn(
                            icon = Icons.Outlined.CalendarMonth,
                            iconTint = SlateBlue,
                            label = "DATE",
                            labelColor = SlateBlue,
                            value = formatEventDate(event.eventDate),
                            valueColor = Color.Black
                        )

                        // Vertical Divider
                        VerticalDivider()

                        // Location Column
                        InfoColumn(
                            icon = Icons.Outlined.LocationOn,
                            iconTint = SlateBlue,
                            label = "LOCATION",
                            labelColor = SlateBlue,
                            value = "CGC Mohali",
                            valueColor = Color.Black
                        )

                        // Vertical Divider
                        VerticalDivider()

                        // Status Column
                        InfoColumn(
                            icon = if (event.isStarred) Icons.Filled.Star else Icons.Outlined.StarOutline,
                            iconTint = if (event.isStarred) AccentGold else SlateBlue,
                            label = "STATUS",
                            labelColor = SlateBlue,
                            value = if (event.isStarred) "Starred" else "Not Starred",
                            valueColor = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Decorative cutout section with dashed line
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    // Left semicircle cutout
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = (-20).dp)
                            .clip(CircleShape)
                            .background(SlateBlue)
                            .align(Alignment.CenterStart)
                    )
                    
                    // Dashed line in the middle
                    Canvas(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                            .height(2.dp)
                            .align(Alignment.Center)
                    ) {
                        drawLine(
                            color = SubtitleGray,
                            start = Offset(0f, 0f),
                            end = Offset(size.width, 0f),
                            strokeWidth = 2f,
                            pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                        )
                    }
                    
                    // Right semicircle cutout
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .offset(x = 20.dp)
                            .clip(CircleShape)
                            .background(SlateBlue)
                            .align(Alignment.CenterEnd)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Event Description
                Text(
                    text = event.eventTitle,
                    fontWeight = FontWeight.Normal,
                    fontSize = 15.sp,
                    maxLines = 4,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    color = TextWhite,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Barcode Card
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(R.drawable.barcode),
                            contentDescription = "Barcode",
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .height(80.dp),
                            contentScale = ContentScale.FillBounds
                        )
                        
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // ID number below barcode
                        Text(
                            text = generateEventId(event.eventDate),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.Gray,
                            letterSpacing = 3.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
private fun InfoColumn(
    icon: ImageVector,
    iconTint: Color,
    label: String,
    labelColor: Color,
    value: String,
    valueColor: Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.width(90.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            modifier = Modifier.size(24.dp),
            tint = iconTint
        )
        
        Spacer(modifier = Modifier.height(6.dp))
        
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = labelColor,
            letterSpacing = 1.sp
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            color = valueColor,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun VerticalDivider(
    height: Dp = 40.dp,
    color: Color = Color.LightGray.copy(alpha = 0.5f)
) {
    Spacer(
        modifier = Modifier
            .width(1.dp)
            .height(height)
            .background(color)
    )
}

private fun formatEventDate(dateString: String): String {
    return try {
        if (dateString.length >= 10) {
            val parts = dateString.substring(0, 10).split("-")
            if (parts.size == 3) {
                "${parts[2]}-${parts[1]}-${parts[0]}"
            } else {
                dateString.take(10)
            }
        } else {
            dateString
        }
    } catch (e: Exception) {
        dateString.take(10)
    }
}

private fun generateEventId(dateString: String): String {
    return try {
        val cleanDate = dateString.replace("-", "").take(8)
        "ID - ${cleanDate.take(4)} - ${cleanDate.takeLast(4)} - 2024"
    } catch (e: Exception) {
        "ID - 0000 - 0000 - 2024"
    }
}
