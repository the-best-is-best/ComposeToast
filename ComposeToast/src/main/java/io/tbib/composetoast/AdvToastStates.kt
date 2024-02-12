package io.tbib.composetoast

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.listSaver
import kotlinx.coroutines.delay

@Composable
fun rememberAdvToastStates(): AdvToastStates {
    return rememberSaveable(saver = AdvToastStates.Saver) {
        AdvToastStates()
    }
}

class AdvToastStates internal constructor(

){
    internal var show by mutableStateOf(false)
    internal var message by mutableStateOf("")
    internal var duration by mutableLongStateOf(3000L)

   suspend fun show(message: String, duration: Long = 3000L) {
        this.show = true
        this.message = message
        this.duration = duration
        delay(duration)
       this.show = false

    }



    companion object {
        val Saver: Saver<AdvToastStates, *> = listSaver(
            save = {
                listOf(
                    it.show,
                    it.message,
                    it.duration
                )
            },
            restore = {
                AdvToastStates(
                    mapOf(
                        "show" to it[0],
                        "message" to it[1],
                        "duration" to it[2]
                    )
                )
            }
        )
    }

    private constructor(map: Map<String, Any>) : this() {
        show = map["show"] as Boolean
        message = map["message"] as String
        duration = map["duration"] as Long
    }


}
