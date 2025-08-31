package com.example.venturenest.ui.theme.Presentation.Setting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.venturenest.ui.theme.foreground

@Composable
fun SettingElement(modifier: Modifier,
                   text:String,
                   onClick:()->Unit){
    Card(  modifier = modifier.padding(top = 10.dp, bottom = 10.dp)
        .fillMaxWidth(0.9f)
        .height(60.dp)
       // .border(1.dp, Color.Black,RoundedCornerShape(25f))
        .clickable { onClick.invoke() }, shape = RoundedCornerShape(25f)
    , colors = CardDefaults.elevatedCardColors(
        containerColor = foreground

    )
    ) {


        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0x0FA6A5A5)), verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
                Text(
                    text = text,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis, modifier = modifier.fillMaxWidth(0.8f)
                        .padding(start = 10.dp), textAlign = TextAlign.Start
                    , color = Color.Black
                )

            Icon(
                imageVector = Icons.Default.ArrowForwardIos, contentDescription = "",
                modifier = modifier
                    .padding(end = 10.dp)
                    .scale(0.7f)

            )

        }

    }

}