package io.github.tbib.compose_toast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.tbib.compose_toast.toast_ui.EnumToastType
import io.github.tbib.compose_toast.toast_ui.ToastUi

class AdvToast {
    companion object {
        @Composable
        fun MakeToast(
            toastType: EnumToastType,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,

            ) {
            when (toastType) {
                EnumToastType.SUCCESS -> MakeSuccessText(
                    state = state,
                    align = align,
                    paddingTop = paddingTop,
                    paddingBottom = paddingBottom
                )

                EnumToastType.ERROR -> MakeErrorText(
                    state = state,
                    align = align,
                    paddingTop = paddingTop,
                    paddingBottom = paddingBottom
                )

                EnumToastType.WARNING -> MakeWarningText(
                    state = state,
                    align = align,
                    paddingTop = paddingTop,
                    paddingBottom = paddingBottom
                )

                EnumToastType.INFO -> MakeInfoText(
                    state = state,
                    align = align,
                    paddingTop = paddingTop,
                    paddingBottom = paddingBottom
                )
            }

        }

        @Composable
        private fun MakeErrorText(
            modifier: Modifier? = null,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,

            ) {
            ToastUi(
                state = state,
                modifier = modifier ?: Modifier,
                backgroundColor = Color.Red,
                align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom, paddingTop = paddingTop
            )
        }

        @Composable
        private fun MakeSuccessText(
            modifier: Modifier? = null,
            state: AdvToastStates,
            align:
            Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
        ) {
            ToastUi(
                state = state,
                modifier = modifier ?: Modifier,
                backgroundColor = Color.Green, align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop
            )
        }

        @Composable
        private fun MakeWarningText(
            modifier: Modifier? = null,

            state: AdvToastStates,
            align:
            Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
        ) {
            ToastUi(
                state = state,
                modifier = modifier ?: Modifier,
                backgroundColor = Color.Yellow,
                align = align, textColor = Color.Black,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop
            )
        }

        @Composable
        private fun MakeInfoText(
            modifier: Modifier? = null,
            state: AdvToastStates,
            align: Arrangement.Vertical = Arrangement.Bottom,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
        ) {
            ToastUi(
                state = state,
                modifier = modifier ?: Modifier,
                backgroundColor = Color(0xff2196F3), align = align,
                textColor = Color.White,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop
            )
        }

        @Composable
        fun MakeCustomToast(
            state: AdvToastStates,
            modifier: Modifier = Modifier,
            textColor: Color,
            paddingTop: Int = 0,
            paddingBottom: Int = 0,
            // backgroundColor: Color,
            align: Arrangement.Vertical = Arrangement.Bottom
        ) {
            ToastUi(
                state = state,
                modifier = modifier,
                paddingBottom = paddingBottom,
                paddingTop = paddingTop,
                //  backgroundColor= backgroundColor,
                align = align, textColor = textColor,

            )
        }
    }
}