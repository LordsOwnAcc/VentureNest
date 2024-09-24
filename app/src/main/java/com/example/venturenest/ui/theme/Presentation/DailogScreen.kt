package com.example.venturenest.ui.theme.Presentation

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.VectorProperty
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Dialog(
 show:MutableState<Boolean>) {
    AnimatedVisibility(visible = show.value) {
        

    var schroll = rememberScrollState()

    var showBotom = show
    ModalBottomSheet(onDismissRequest = { show.value=false }

    , scrimColor = Color.Transparent,
        shape = RoundedCornerShape(topEnd = 100f, topStart = 100f),
        containerColor = BottomSheetDefaults.ContainerColor, modifier = Modifier.offset(y = 20.dp) ) {
Box(modifier =Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter ) {


    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 50f, topEnd = 50f))
            .fillMaxHeight()
            .fillMaxWidth(0.9f),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)


    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent), verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start) {
//
//Row (modifier = Modifier
//    .fillMaxWidth()
//    .fillMaxHeight(0.08f), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start){
//IconButton(onClick = { /*TODO*/ }, modifier = Modifier.offset(x=-10.dp)) {
// Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
//}
//}
            AsyncImage(
                model = "https://www.cgc.ac.in/public/tiny-uploads/_DSC_2835.JPG",
                contentDescription = "", modifier = Modifier
                    .clip(RoundedCornerShape(50f))
                    .fillMaxWidth()
                    .height(300.dp - schroll.value.dp / 2)

                , contentScale = ContentScale.Crop
            )
            Text(text = "Chandigarh Engineering College, CGC Jhanjeri, Hosts Successful Internal Smart India Hackathon 2024", modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,fontWeight = FontWeight.Bold)

            Column(modifier = Modifier
                .padding(top = 20.dp, bottom = 20.dp)
                .fillMaxHeight()
                .fillMaxWidth()
                .verticalScroll(schroll)) {
                Text(text = "The Department of Computer Science and Engineering at Chandigarh Engineering College, part of the Chandigarh Group of Colleges-Jhanjeri (CGC-J), organized a highly successful Internal Smart India Hackathon (SIH). Under the dynamic leadership of Mr. Rashpal Singh Dhaliwal, Honourable Chairman, and Mr. Arsh Dhaliwal, Esteemed Managing Director, CGC-J, the event showcased the exceptional technical skills and innovative spirit of its participants.\n" +
                        "\n" +
                        "The hackathon attracted 75 teams, comprising approximately 450 students, who presented a diverse array of innovative ideas. A distinguished panel of judges, including Mr. Vardaan, CEO of Skill to Education; Mr. Gaurav Madaan, CTO of Niwi.ai; and Prof. Ati Priye, CEO of the Incubation Center at CGC-J, meticulously evaluated the projects. Following a rigorous review, 30 teams were selected to upload their presentations to the SIH portal.\n" +
                        "\n" +
                        "Among the top five teams recognized for their exceptional ideas, the first three positions were secured by Lovedeep Singh’s team, Divyansh Dhiman’s team, and Deepika’s team, respectively. Notably, a female team member from the first-place team received a job offer, highlighting the career-enhancing opportunities presented by the hackathon. The special prize, a cash award of ?2000, was generously provided by Mr. Gaurav Madaan and awarded to a team nominated by the judges.\n" +
                        "\n" +
                        "The event concluded with enthusiastic feedback from participants and praise from faculty for the students' creativity and dedication. The Internal Smart India Hackathon at CGC-Jhanjeri has once again demonstrated the institution's commitment to fostering innovation, entrepreneurship, and technological excellence.\n" +
                        "\n" +
                        "This hackathon reaffirms CGC-Jhanjeri’s vision of creating a nurturing environment where students are encouraged to tackle real-world problems, develop cutting-edge solutions, and prepare for future careers in technology and entrepreneurship.", modifier = Modifier.fillMaxWidth(),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize, fontWeight = FontWeight.Bold)
            }

            Column(modifier = Modifier.fillMaxSize()) {



            }
        }
    }
}
    }
    }
}

