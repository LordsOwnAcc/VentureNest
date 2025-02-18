package com.example.venturenest.ui.theme.Presentation.Main.ExtraPages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.DragInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material.icons.filled.SwapVert
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.aay.compose.baseComponents.model.LegendPosition
import com.aay.compose.donutChart.DonutChart
import com.aay.compose.donutChart.model.PieChartData
import com.example.venturenest.ui.theme.DaggerHilt.Patents
import com.example.venturenest.ui.theme.DaggerHilt.StartUp
import java.util.Date

@Composable
fun StatisticsPage(
    modifier: Modifier = Modifier
    ,navHostController: NavHostController
    ,windowInsets: WindowInsets
) {
    var patentsSelected by remember {
        mutableStateOf(true)
    }
    var list = listOf<Patents>(
        Patents(_id = "sdbh","Zero-x","Elon Musk","00112233", "1995")
    )
    var list2 = listOf<StartUp>(
        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")
,        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")
,        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")
,        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")
,        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")
,        StartUp("skss","Sip and Bite" ,"cinn","Akash Gupta","xyz","Food and bee")

    )
    Column(
        modifier
            .windowInsetsPadding(windowInsets)
            .fillMaxSize()
            .background(Color.White), verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f), contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                modifier
                    .padding(start = 10.dp)
                    .size(40.dp)
                    .background(Color.White)
                    .clickable {

                        navHostController.popBackStack()
                    }
                    .border(2.dp, Color.Black, RectangleShape)
                    .padding(8.dp),
                tint = Color.Black
            )
            Row(
                modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Statistics",
                    fontSize = MaterialTheme.typography.displaySmall.fontSize,
                    fontWeight = FontWeight.Black,
                    letterSpacing = MaterialTheme.typography.titleLarge.letterSpacing,
                    modifier = modifier.padding(end = 20.dp)
                )
            }

        }

Spacer(modifier = modifier.fillMaxHeight(0.07f))
        ElevatedCard(
            onClick = { /*TODO*/ }, modifier = modifier
                .fillMaxWidth(0.9f)
                .fillMaxHeight(0.4f)
                .shadow(elevation = 5.dp, RoundedCornerShape(25f), false)


        , shape = RoundedCornerShape(25f)
, colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
        ) {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                DonutChartSample()
Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.End) {
Icon(imageVector = Icons.Default.SwapHoriz, contentDescription = "",modifier.padding(end = 10.dp, top = 10.dp))
}
            }
            }
        Row (
            modifier
                .padding(top = 20.dp)
                .fillMaxWidth(0.85f)
                .fillMaxHeight(0.1f), verticalAlignment = Alignment.CenterVertically){
            Text(text = "Show")
            Row (modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End){
Icon(imageVector = Icons.Default.Shuffle, contentDescription ="" ,modifier.clickable { patentsSelected = !patentsSelected })
            }
        }

        Column(
            modifier
                .padding(top = 10.dp, bottom = 1.dp)
                .fillMaxWidth(0.9f)
                .fillMaxHeight(1f)
                .verticalScroll(rememberScrollState())
                .horizontalScroll(rememberScrollState())
                ) {

            AnimatedVisibility(visible = patentsSelected) {
                Column() {
                    StartUps(
                        StartUpName = "Startup Name",
                        FounderName = "FounderName",
                        Cinn = "Cinn",
                        Website = "Website",
                        ProduceName = "Product Name"
                        , isTittle = true
                        , action = {}
                        , Color = Color.LightGray
, fontWeight = FontWeight.Bold
                    )

                    list2.forEachIndexed { index, Startup ->
                        StartUps(
                            StartUpName = Startup.StartupName,
                            Cinn = Startup.CIN,
                            FounderName = Startup.FounderName,
                            Website = Startup.Website,
                            ProduceName = Startup.ProductName,
                            Color = if (index%2 !=0  )Color.LightGray else Color.White,

                            isTittle =false ,
                            action = { /*TODO*/ },
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
            androidx.compose.animation.AnimatedVisibility(visible = !patentsSelected) {
                Column() {
                    com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.Patents(
                        PatentTittle = "PatentTittle",
                        Inventor = " Inventor",
                        ApplicationNo = "Application No.",
                        Year = "Year",
                        Color = Color.LightGray,
                        fontWeight = FontWeight.Bold
                    )

                    list.forEachIndexed { index, patents ->
                        com.example.venturenest.ui.theme.Presentation.Main.ExtraPages.Patents(
                            PatentTittle = patents.PatentTitle,
                            Inventor = patents.Inventor,
                            ApplicationNo = patents.ApplicationNo,
                            Year = patents.PatentYear.toString(),
                            Color = if (index % 2 != 0) Color.LightGray else Color.White,
                            fontWeight = FontWeight.Normal
                        )

                    }
                }
            }

        }
    }

}

//@Preview
//@Composable
//private fun PreviewStatisticsPage() {
//    StatisticsPage()
//}
@Composable
fun DonutChartSample() {

    val testPieChartData: List<PieChartData> = listOf(
        PieChartData(
            partName = "StartUps Incubated",
            data = 100.0,
            color = Color(0xFF0B666A),
        ),
        PieChartData(
            partName = "StartUps Mentored",
            data = 400.0,
            color = Color(0xFF35A29F),
        )
    )

    DonutChart(
        modifier = Modifier.fillMaxSize(0.9f),
        pieChartData = testPieChartData,
        centerTitle = "StartUps",
        centerTitleStyle = TextStyle(color = Color(0xFF071952), fontSize = MaterialTheme.typography.labelSmall.fontSize),
        outerCircularColor = Color.LightGray,
        innerCircularColor = Color.Gray,
        ratioLineColor = Color.LightGray,
        legendPosition = LegendPosition.BOTTOM

    )
}


@Composable
fun Patents(
    modifier: Modifier = Modifier,
    PatentTittle: String,
    Inventor: String,
    ApplicationNo: String,
    Year: String,
    Color: Color,
    fontWeight: FontWeight
) {
    Row(
        modifier = Modifier

            .wrapContentWidth()
            .background(Color)
            .padding(top = 10.dp, bottom = 10.dp)
    ) {

        Text(
            text = PatentTittle,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = Inventor,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = ApplicationNo,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = Year,
            modifier.width(50.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )


    }

}


@Composable
fun StartUps(
    modifier: Modifier = Modifier,
    StartUpName: String,
    Cinn:String,
    FounderName: String,
    Website: String,
    ProduceName: String,
    Color: Color,
    isTittle:Boolean,
    action:()->Unit,
    fontWeight: FontWeight
) {
    Row(
        modifier = Modifier

            .wrapContentWidth()
            .background(Color)
            .padding(top = 10.dp, bottom = 10.dp)
    ) {

        Text(
            text =StartUpName,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = FounderName,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = Cinn,
            modifier.width(100.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        Text(
            text = ProduceName,
            modifier.width(50.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )
        if (isTittle){


        Text(
            text = "Visit",
            modifier.width(50.dp),
            textAlign = TextAlign.Center,
            fontWeight = fontWeight
        )}else {
            Icon(
                imageVector = Icons.Default.ArrowOutward,
                contentDescription = ""
                ,
                modifier
                    .padding(start = 20.dp)
                    .size(20.dp)
                    .clickable { action.invoke() }
            )
        }

    }

}

