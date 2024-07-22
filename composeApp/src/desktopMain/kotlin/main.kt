import androidx.compose.material.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.theme.CasaTheme
import ui.window.CustomWindow
import ui.window.WindowTitlebarCenter
import ui.window.WindowTitle

fun main() = application {
    val state = rememberWindowState()
    CasaTheme {
        CustomWindow(
            state = state,
            onCloseRequest = ::exitApplication,
            defaultTitle = "Casa"
        ) {
            var i by remember { mutableStateOf("") }
            WindowTitle("Casa")
            App()
        }
    }
}