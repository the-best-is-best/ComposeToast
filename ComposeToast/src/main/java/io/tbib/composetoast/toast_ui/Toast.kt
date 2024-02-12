package io.tbib.composetoast.toast_ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.core.graphics.drawable.toBitmap
import io.tbib.composetoast.AdvToastStates

@Composable
internal fun ToastUi(
    @SuppressLint("ModifierParameter") modifier: Modifier? = null,
    state: AdvToastStates,
    align: Arrangement.Vertical,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    backgroundColor: Color,
    textColor: Color
){

    val context = LocalContext.current
    val iconDrawable: Drawable =
        context.packageManager.getApplicationIcon(context.applicationInfo.packageName)
    val bitmapDrawable = iconDrawable.toBitmap()
    val imageBitmap = bitmapDrawable.asImageBitmap()

    if (state.show)

        Column(
            modifier = Modifier.fillMaxSize().zIndex(Int.MAX_VALUE.toFloat()), // Parent container fills the screen
            verticalArrangement = align // Vertically align content to the bottom

        ) {
            Box(
                modifier = modifier
                    ?: Modifier
                        .fillMaxWidth() // Fill the width of the parent
                        .padding(top = paddingTop.dp, bottom = paddingBottom.dp, start = 0.dp, end = 0.dp)
                        .padding(horizontal = 16.dp, vertical = 8.dp) // Provide margin
                        .background(
                            backgroundColor,
                            shape = RoundedCornerShape(8.dp)
                        ), // Apply background and rounded corners
                contentAlignment = Alignment.Center // Align content to the center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        bitmap = imageBitmap, contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Text(
                        lineHeight = 50.sp,
                        text = state.message,
                        color = textColor, // Set text color to white
                        fontSize = 16.sp, // Adjust font size as needed
                        fontWeight = FontWeight.Bold, // Make the text bold
                    )
                }

            }
        }
}