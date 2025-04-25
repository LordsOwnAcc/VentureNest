package com.example.venturenest.ui.theme.Presentation.AchievementsPage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.venturenest.ui.theme.DaggerHilt.Patents

@Composable
fun PatentCard(modifier: Modifier = Modifier,patents: Patents) {
    ElevatedCard(modifier.height(200.dp)
        .width(250.dp)
        .fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor =
        Color.White)) {
        Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            val Local = LocalClipboardManager.current
            Column(modifier.fillMaxWidth(0.95f)) {
                Text(patents.PatentTitle,
                    fontSize = androidx.compose.material3.MaterialTheme.typography.titleLarge.fontSize,
                     fontWeight = FontWeight.SemiBold,
                    letterSpacing = androidx.compose.material3.MaterialTheme.typography.titleLarge.letterSpacing,
                    maxLines = 2, overflow = TextOverflow.Ellipsis)
                Text("held by ${patents.Inventor} ", maxLines = 1)
            }
            Row (modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){
                Text("Application No. : ${patents.ApplicationNo}",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold, modifier = modifier.fillMaxWidth(0.7f)
                        , maxLines = 2, overflow = TextOverflow.Ellipsis)
Icon(imageVector = Icons.Default.ContentCopy, contentDescription = null, modifier = modifier.clickable {
    Local.setText(AnnotatedString("${patents.ApplicationNo}"))

})
            }
            Text("Applied on ${patents.PatentYear}", maxLines = 1)


        }



    }




}

