package com.example.venturenest.ui.theme.Presentation.Main.ExtraPages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StatisticsPage(
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.1f)
            ,
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = "Statistics",
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.Bold)
            Row(modifier.fillMaxWidth()  ,
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top) {
                IconButton(onClick = {} , modifier.offset(x=10.dp)) {
                    Icon(imageVector = Icons.Default.Tune, contentDescription = "", tint = Color.Black)
                }
            }
        }
    }

}

@Preview
@Composable
private fun PreviewStatisticsPage() {
    StatisticsPage()
}