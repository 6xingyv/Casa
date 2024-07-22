import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.haze
import dev.chrisbanes.haze.hazeChild
import org.jetbrains.compose.ui.tooling.preview.Preview
import sv.lib.squircleshape.SquircleShape
import ui.theme.CasaTheme
import kotlin.random.Random

@Composable
fun Int.toDp(): Dp {
    return with(LocalDensity.current) {
        this@toDp.toDp()
    }
}

@Composable
fun Float.toDp(): Dp {
    return with(LocalDensity.current) {
        this@toDp.toDp()
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App(gridState: LazyGridState = rememberLazyGridState()) {
    CasaTheme {
        val isDark = isSystemInDarkTheme()
        val statusBarHeight = if (WindowInsets.statusBars.getTop(LocalDensity.current).toDp() ==0.dp) 16.dp else WindowInsets.statusBars.getTop(LocalDensity.current).toDp()
        val navigationBarHeight = WindowInsets.navigationBars.getBottom(LocalDensity.current).toDp()
        var hazeState = remember { HazeState() }



        Box(Modifier.background(MaterialTheme.colors.background)) {
            Box(Modifier.haze(hazeState)) {
                LazyVerticalGrid(
                    GridCells.Adaptive(96.dp),
                    contentPadding = PaddingValues(
                        top = statusBarHeight + 8.dp,
                        bottom = navigationBarHeight + 64.dp + 64.dp
                    ),
                    state = gridState
                ) {
                    item(0, span = { GridItemSpan(maxLineSpan) }) {
                        Surface(
                            color = Color.Transparent,
                            contentColor = MaterialTheme.colors.onSurface
                        ) {
                            Box(Modifier.fillMaxWidth().padding(16.dp, 32.dp)) {
                                Text(
                                    "主页",
                                    fontSize = 32.sp
                                )
                            }
                        }
                    }
                    repeat(21) { date ->
                        item(span = { GridItemSpan(maxLineSpan) }) {
                            Surface(
                                color = Color.Transparent,
                                contentColor = MaterialTheme.colors.onSurface
                            ) {
                                Box(Modifier.fillMaxWidth().padding(16.dp, 16.dp, 16.dp, 8.dp)) {
                                    Text(
                                        "7月${21 - date}日",
                                        fontSize = 16.sp
                                    )
                                }
                            }
                        }
                        repeat(Random.nextInt(3, 10)) { index ->
                            item {
                                Spacer(
                                    Modifier
                                        .fillMaxWidth()
                                        .height(100.dp)
                                        .background(Color(0xFF000000 + index * 0x5042 + 0xfffc))
                                )
                            }
                        }
                    }
                }
            }


            // Navigation Bar
            Surface(
                Modifier.align(Alignment.BottomCenter).hazeChild(
                    hazeState, style = HazeStyle(
                        blurRadius = 30.dp,
                        backgroundColor = MaterialTheme.colors.surface,
                        tint = MaterialTheme.colors.surface.copy(alpha = if (isDark) 0.75f else 0.9f),
                    )
                ).fillMaxWidth().padding(bottom = navigationBarHeight).height(64.dp).drawBehind {
                    val strokeWidth = 0.5f * density
                    drawLine(
                        (if (isDark) Color.White else Color.Black).copy(0.2f),
                        Offset(0f, 0f),
                        Offset(size.width, 0f),
                        strokeWidth
                    )
                },
                color = Color.Transparent,
                contentColor = MaterialTheme.colors.onSurface.copy(0.8f)
            ) {
                Row(
                    Modifier.padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy((-4).dp)
                    ) {
                        Icon(Icons.Rounded.Home, "", Modifier.size(24.dp))
                        Text("主页", fontSize = 10.sp)
                    }
                    Column(
                        Modifier.alpha(0.6f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy((-4).dp)
                    ) {
                        Icon(Icons.Rounded.Favorite, "", Modifier.size(24.dp))
                        Text("最爱", fontSize = 10.sp)
                    }
                    Column(
                        Modifier.alpha(0.6f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy((-4).dp)
                    ) {
                        Icon(Icons.Rounded.Place, "", Modifier.size(24.dp))
                        Text("地点", fontSize = 10.sp)
                    }
                }
            }

            // Vignette
            AnimatedVisibility(
                gridState.firstVisibleItemIndex != 0,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                var isExpanded by remember { mutableStateOf(false) }
                Surface(color = Color.Transparent, contentColor = Color.White) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(statusBarHeight * 4)
                            .hazeChild(hazeState, style = HazeStyle(backgroundColor = MaterialTheme.colors.surface, blurRadius = 30.dp), mask = Brush.verticalGradient(
                                0.2f to Color.Black,
                                1f to Color.Transparent
                            ))
                            .statusBarsPadding()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.End)
                    ) {

                        Box(Modifier.clip(CircleShape).clickable { }) {
                            Icon(Icons.Rounded.Search, "", Modifier.size(28.dp))
                        }

                        Box(Modifier.clip(CircleShape).clickable {
                            hazeState = HazeState()
                        }) {
                            Icon(Icons.Rounded.Search, "", Modifier.size(28.dp))
                        }
                        Box(Modifier.clip(CircleShape).clickable { isExpanded = true }) {
                            Icon(Icons.Rounded.MoreVert, "", Modifier.size(28.dp))

                            DropdownMenu(
                                expanded = isExpanded,
                                onDismissRequest = { isExpanded = false },
                                modifier = Modifier
                            ) {
                                DropdownMenuItem(
                                    onClick = { }
                                ) { Text("地图相册") }

                                DropdownMenuItem(
                                    onClick = { }
                                ) { Text("释放空间") }
                                DropdownMenuItem(
                                    onClick = { }
                                ) { Text("设置") }
                            }

                        }
                    }

                }
            }
        }
    }
}