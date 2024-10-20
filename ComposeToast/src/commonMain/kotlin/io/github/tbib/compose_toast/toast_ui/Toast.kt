package io.github.tbib.compose_toast.toast_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import io.github.tbib.compose_toast.AdvToastStates
import io.github.tbib.compose_toast.getAppLogo


@Composable
internal fun ToastUi(
    modifier: Modifier = Modifier,
    state: AdvToastStates,
    align: Arrangement.Vertical,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    backgroundColor: Color,
    textColor: Color
) {
    val imageBitmap = getAppLogo()

    if (state.show) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingTop.dp,
                    bottom = paddingBottom.dp,
                    start = 16.dp, // Padding from the sides
                    end = 16.dp
                ).zIndex(1f) // Ensure it's on top of other content
            ,
            verticalArrangement = align,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Provide margin
                    .background(
                        backgroundColor,
                        shape = RoundedCornerShape(8.dp)
                    ), // Apply background and rounded corners
                contentAlignment = Alignment.Center // Align content to the center
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imageBitmap != null) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = state.message,
                        color = textColor, // Set text color to white
                        fontSize = 16.sp, // Adjust font size as needed
                        fontWeight = FontWeight.Bold // Make the text bold
                    )
                }
            }
        }
    }
}


@Composable
internal fun ToastUi(
    modifier: Modifier = Modifier,
    state: AdvToastStates,
    align: Arrangement.Vertical,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    textColor: Color
) {
    val imageBitmap = getAppLogo()

    if (state.show) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingTop.dp,
                    bottom = paddingBottom.dp,
                    start = 16.dp, // Padding from the sides
                    end = 16.dp
                ).zIndex(1f) // Ensure it's on top of other content
            ,
            verticalArrangement = align,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Provide margin
                , // Apply background and rounded corners
                contentAlignment = Alignment.Center // Align content to the center
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (imageBitmap != null) {
                        Image(
                            bitmap = imageBitmap,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = state.message,
                        color = textColor, // Set text color to white
                        fontSize = 16.sp, // Adjust font size as needed
                        fontWeight = FontWeight.Bold // Make the text bold
                    )
                }
            }
        }
    }
}

