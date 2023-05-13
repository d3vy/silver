import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ru.devy.silver.App

fun main() = application {
    Window(
        title = "silver",
        state = rememberWindowState(width = 400.dp, height = 900.dp),
        onCloseRequest = ::exitApplication,
    ) { App() }
}