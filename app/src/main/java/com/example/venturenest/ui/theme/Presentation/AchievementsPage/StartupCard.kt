package com.example.venturenest.ui.theme.Presentation.AchievementsPage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import com.example.venturenest.ui.theme.foreground

@Composable
fun StartUpCard(modifier: Modifier = Modifier, startUp: StartUp) {
    ElevatedCard(
        modifier
            .height(250.dp)
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor =
                foreground
        )
    ) {
        Column(
            modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        )
        {
            val Local = LocalClipboardManager.current
            Column(modifier.fillMaxWidth(0.95f)) {
                Row(
                    modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        startUp.StartupName,
                        fontSize = androidx.compose.material3.MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.SemiBold,
                        letterSpacing = androidx.compose.material3.MaterialTheme.typography.titleLarge.letterSpacing,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = modifier.fillMaxWidth(0.8f)
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowOutward, contentDescription = null,
                        modifier = modifier.clickable { }, tint = Color.Gray)
                }
                Text("founded by ${startUp.FounderName} ", maxLines = 1 , color = Color.Gray)
            }
            Row(
                modifier.fillMaxWidth(0.95f), horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "CIN No. : ${startUp.CIN}",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    fontWeight = FontWeight.SemiBold,
                    modifier = modifier.fillMaxWidth(0.7f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                    , color = Color.Gray
                )
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = null,
                    modifier = modifier.clickable {
                        Local.setText(AnnotatedString("${startUp.CIN}"))

                    }, tint = Color.Gray)
            }
            Text(
                text = "Startup Type : ${startUp.StartupType}",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.fillMaxWidth(0.95f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                , color = Color.Gray
            )
            Text(
                text = "Registration status : ${startUp.RegistrationStatus}",
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.fillMaxWidth(0.95f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                , color = Color.Gray
            )


            Text(
                text = buildAnnotatedString {
                    append("Funding Raised By Startup: "
                    )
                    pushStyle(
                        SpanStyle(
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                    ) // Money green color
                    append(startUp.FundingRaisedStartup)
                    pop()
                },
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.fillMaxWidth(0.95f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis , color = Color.Gray
            )
            Text(
                text = buildAnnotatedString {
                    append("Funding Invested By Incubator: ")
                    pushStyle(
                        SpanStyle(
                            color = Color(0xFF4CAF50),
                            fontWeight = FontWeight.Bold
                        )
                    ) // Money green color
                    append(startUp.InvestmentByIncubator)
                    pop()
                },
                fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                fontWeight = FontWeight.SemiBold,
                modifier = modifier.fillMaxWidth(0.95f),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
                , color = Color.Gray
            )


        }


    }


}

@Preview
@Composable
fun StartupPreview(modifier: Modifier = Modifier) {
    StartUpCard(
        startUp = StartUp(
            _id = "",
            StartupName = "Top and Toen",
            CIN = "585458545985",
            FounderName = "John Doe",
            Website = "httsps//dbebjebkcbekbce.com",
            ProductName = "Cheese Sandwich",
            FundingRaisedStartup = "500000",
            InvestmentByIncubator = "500000",
            RegistrationStatus = "",
            StartupType = ""
        )
    )
}
