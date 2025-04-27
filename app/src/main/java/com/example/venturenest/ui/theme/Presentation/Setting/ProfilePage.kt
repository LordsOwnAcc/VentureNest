package com.example.venturenest.ui.theme.Presentation.Setting

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Camera
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.venturenest.ui.theme.DaggerHilt.ViewModels.AuthViewModel
import com.example.venturenest.ui.theme.Navigation.StartScreen
import com.example.venturenest.ui.theme.auth.AuthStateCompanion
import kotlinx.coroutines.launch

@Composable
fun ProfilePage(modifier: Modifier = Modifier
,navController: NavController) {

    var selected by remember { mutableStateOf(false) }
    var actionText by remember {
        mutableStateOf("Log-Out")
    }
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf("Amanda Jane") }
    var email by remember { mutableStateOf("amanda@gmail.com") }
    var phone by remember { mutableStateOf("+65 2311 333") }
    var dateOfBirth by remember { mutableStateOf("20/05/1990") }
    var address by remember { mutableStateOf("123 Royal Street, New York") }
    val authViewModel: AuthViewModel = hiltViewModel()
    val currentUser = authViewModel.repository.firebaseAuth.currentUser
    val profileImageScale by animateFloatAsState(
        targetValue = if (scrollState.value > 100) 0.8f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "profileImageScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF5F7FA),
                        Color(0xFFE4EDF5)
                    )
                )
            )
    ) {
        // Decorative background elements
        Box(
            modifier = Modifier
                .size(200.dp)
                .offset((-50).dp, (-50).dp)
                .alpha(0.1f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF6A11CB),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Box(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.BottomEnd)
                .offset(y = 50.dp, x = 50.dp)
                .alpha(0.1f)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF2575FC),
                            Color.Transparent
                        )
                    ),
                    shape = CircleShape
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            // Top App Bar


            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Profile",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Profile Image
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .scale(profileImageScale)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFF6A11CB),
                                        Color(0xFF2575FC)
                                    )
                                ),
                                shape = CircleShape
                            )
                            .padding(4.dp)
                            .clip(CircleShape)
                            .clickable {  }
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(currentUser!!.photoUrl)
                                .crossfade(true)
                                .build(),
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )


                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Form Fields
                ProfileField(
                    icon = Icons.Outlined.Person,
                    label = "Name",
                    value = currentUser?.displayName.toString(),
                    onValueChange = { username = it }
                )

                ProfileField(
                    icon = Icons.Outlined.Email,
                    label = "Email",
                    value = currentUser?.email.toString(),
                    onValueChange = { email = it }
                )

                ProfileField(
                    icon = Icons.Outlined.VerifiedUser,
                    label = "Authentication",
                    value = "Google",
                    onValueChange = { phone = it }
                )

                Text(text = "Danger Zone",
                    modifier
                        .padding(top = 10.dp, bottom = 15.dp, start = 5.dp)
                        .fillMaxWidth(1f)
                    , textAlign = TextAlign.Start,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    , lineHeight = MaterialTheme.typography.bodyMedium.lineHeight,
                    letterSpacing = MaterialTheme.typography.bodyMedium
                        .letterSpacing, color = Color.Red)



                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                    ,
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Row(modifier.clickable {
                        actionText="Logout"
                    selected=true
                    }.
                    padding(start = 16.dp).fillMaxWidth().fillMaxHeight()
                    , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                      Icon(imageVector = Icons.Default.Logout,
                          contentDescription = null,
                          modifier = modifier.scale(0.8f).padding(end=10.dp)
                      , tint = Color.Red)

                        Text(
                            text = "Log-out",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF333333),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                        .height(65.dp)
                    ,
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 2.dp
                    )
                ) {
                    Row(modifier.clickable{
                        actionText="Delete Account"
                        selected=true
                    }.padding(start = 16.dp).fillMaxWidth().fillMaxHeight()
                        , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start) {
                        Icon(imageVector = Icons.Outlined.DeleteOutline,
                            contentDescription = null,
                            modifier = modifier.scale(0.8f).padding(end=10.dp)
                            , tint = Color.Red)
                       Text(
                            text = "Delete Account",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color(0xFF333333),
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

            }
        ConfirmationDialog(isVisible = selected, actionText = actionText, onProceed = {
            if (actionText=="Logout"){
                authViewModel.SignOut()
                authViewModel._authState.value=authViewModel._authState.value.copy(
                    state = AuthStateCompanion.NoUser
                )
                navController.navigate(StartScreen){
                    popUpTo(StartScreen){
                        inclusive = true
                    }
                }
            }else{
                currentUser?.delete()
                authViewModel._authState.value=authViewModel._authState.value.copy(
                    state = AuthStateCompanion.NoUser
                )
                navController.navigate(StartScreen){
                    popUpTo(StartScreen){
                        inclusive = true
                    }
                }
            }
        }, onCancel = {selected=false} ) {selected=false }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileField(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isLast: Boolean = false
) {
    var isEditing by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.White.copy(alpha = 0.8f)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color(0xFF6A11CB),
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 4.dp)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                AnimatedVisibility(
                    visible = !isEditing,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Text(
                        text = value,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF333333),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                AnimatedVisibility(
                    visible = isEditing,
                    enter = fadeIn() + slideInVertically(),
                    exit = fadeOut()
                ) {
                    TextField(
                        value = value,
                        onValueChange = onValueChange,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            focusedIndicatorColor = Color.White.copy(alpha = 0.8f),
                            unfocusedIndicatorColor = Color.LightGray
                        ),
                        textStyle = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    )
                }
            }


        }
    }
}

