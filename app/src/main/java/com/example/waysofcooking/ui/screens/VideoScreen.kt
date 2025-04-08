import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import com.example.waysofcooking.ui.screens.YouTubeWebViewActivity

@Composable
fun VideoScreen(navController: NavHostController) {
    val context = LocalContext.current

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(
                scope = scope,
                drawerState = drawerState,
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Página de Videos",
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            VideoButton(
                videoId = "tf34QAHiono",
                label = "Arroz Chaufa"
            )

            Spacer(modifier = Modifier.height(16.dp))

            VideoButton(
                videoId = "8X_h2A4Qbdg",
                label = "Ajiaco Santafereño"
            )

            Spacer(modifier = Modifier.height(16.dp))

            VideoButton(
                videoId = "Z8ZBRjLMpb8",
                label = "Albóndigas en Salsa"
            )
        }
    }
}

@Composable
private fun VideoButton(videoId: String, label: String) {
    val context = LocalContext.current

    Button(
        onClick = {
            val intent = Intent(context, YouTubeWebViewActivity::class.java).apply {
                putExtra("video_url", "https://www.youtube.com/embed/$videoId")
            }
            context.startActivity(intent)
        }
    ) {
        Text(label)
    }
}
