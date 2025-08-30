package com.example.venturenest.ui.theme.Presentation.Login

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.venturenest.R


@Composable
fun GoogleSignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    loadingText: String = "Signing in...",
    borderColor: Color = Color(0xFFDDDDDD),
    backgroundColor: Color = Color.White,
    contentColor: Color = Color(0xFF757575)
) {
    // Create a custom button with Google's design guidelines
    Surface(
        modifier = modifier
            .clickable(
                enabled = enabled && !loading,
                onClick = onClick
            ),
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(1.dp, borderColor),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier
                .padding(
                    start = 12.dp,
                    end = 16.dp,
                    top = 12.dp,
                    bottom = 12.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // Google Logo
            GoogleLogo(contentColor = contentColor)

            Spacer(modifier = Modifier.width(24.dp))

            // Text or Loading Indicator
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(16.dp),
                    color = contentColor,
                    strokeWidth = 2.dp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = loadingText,
                    color = contentColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            } else {
                Text(
                    text = "Sign in with Google",
                    color = contentColor,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * A more modern styled version of the Google Sign-In button
 */
@Composable
fun ModernGoogleSignInButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && !loading,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFFDDDDDD)),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color(0xFF757575)
        )
    ) {
        Row(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(20.dp),
                    color = Color(0xFF757575),
                    strokeWidth = 2.dp
                )
            } else {
                GoogleLogo(contentColor = Color(0xFF757575))
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = "Sign in with Google",
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * Google logo composable
 * Note: In a real app, you would use a resource like R.drawable.ic_google_logo
 * This is a placeholder that creates a simplified "G" icon
 */
@Composable
fun GoogleLogo(
    contentColor: Color = Color(0xFF757575),
    modifier: Modifier = Modifier
) {
    // In a real app, you would use:
    // Icon(
    //     painter = painterResource(id = R.drawable.ic_google_logo),
    //     contentDescription = "Google logo",
    //     tint = Color.Unspecified,
    //     modifier = modifier.size(18.dp)
    // )

    // Since we can't include actual resources, here's a text-based placeholder
    // Replace this with the actual Google logo in your app
    Image(painter = painterResource(R.drawable.google_icon_icons_com_62736)
    ,contentDescription = null,modifier.size(20.dp))
}

@Preview(showBackground = true)
@Composable
fun GoogleSignInButtonPreview() {
    MaterialTheme {
        var loading by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Standard Google Sign-In button
            GoogleSignInButton(
                onClick = { loading = !loading },
                loading = loading,
                modifier = Modifier.fillMaxWidth()
            )

            // Modern styled Google Sign-In button
            ModernGoogleSignInButton(
                onClick = { loading = !loading },
                loading = loading,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Helper composable for preview
@Composable
private fun Column(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    androidx.compose.foundation.layout.Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement
    ) {
        content()
    }
}