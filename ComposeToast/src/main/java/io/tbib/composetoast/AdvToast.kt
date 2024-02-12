package io.tbib.composetoast
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.tbib.composetoast.toast_ui.EnumToastType
import io.tbib.composetoast.toast_ui.ToastUi

class AdvToast {
    companion object {
        @Composable
        fun MakeToast(
            toastType: EnumToastType,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,

            ){
            when(toastType){
                EnumToastType.SUCCESS -> MakeSuccessText(state = state,align = align, paddingTop = paddingTop, paddingBottom = paddingBottom)
                EnumToastType.ERROR -> MakeErrorText(state = state,align = align ,paddingTop = paddingTop, paddingBottom = paddingBottom)
                EnumToastType.WARNING -> MakeWarningText(state = state,align = align ,paddingTop = paddingTop, paddingBottom = paddingBottom)
                EnumToastType.INFO -> MakeInfoText(state = state,align = align ,paddingTop = paddingTop, paddingBottom = paddingBottom)
            }

        }
        @Composable
     private   fun MakeErrorText(
            @SuppressLint("ModifierParameter") modifier: Modifier? = null,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,

            ) {
            ToastUi(
                state = state,
                modifier = modifier,
                backgroundColor = Color.Red,
                align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom, paddingTop = paddingTop)
        }
        @Composable
        private   fun  MakeSuccessText(
            @SuppressLint("ModifierParameter") modifier: Modifier? = null,
            state: AdvToastStates,align:
            Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
            ) {
            ToastUi(
                state = state,
                modifier = modifier,
                backgroundColor = Color.Green, align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop)
        }
        @Composable
        private    fun MakeWarningText(
            @SuppressLint("ModifierParameter") modifier: Modifier? = null,

            state: AdvToastStates,align:
            Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,) {
            ToastUi(
                state = state,
                modifier = modifier,
                backgroundColor = Color.Yellow,
                align = align, textColor = Color.Black,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop)
        }
        @Composable
        private    fun MakeInfoText(
            @SuppressLint("ModifierParameter") modifier: Modifier? = null,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
            ) {
            ToastUi(
                state = state,
                modifier = modifier,
                backgroundColor = Color.Blue, align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop)
        }
        @Composable
        fun MakeCustomToast(
            state: AdvToastStates,
            modifier: Modifier = Modifier,
            backgroundColor: Color, textColor: Color,
            align:Arrangement.Vertical = Arrangement.Bottom) {
            ToastUi(
                state = state,
                modifier = modifier,
                backgroundColor = backgroundColor,
                align = align, textColor = textColor)
        }
    }
}