// SPDX-License-Identifier: GPL-3.0-only
package helium314.keyboard.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import helium314.keyboard.latin.utils.JniUtils
import helium314.keyboard.latin.utils.SubtypeLocaleUtils.displayName
import helium314.keyboard.latin.utils.SubtypeSettings
import helium314.keyboard.latin.utils.Theme
import helium314.keyboard.settings.SearchSettingsScreen
import helium314.keyboard.settings.initPreview
import helium314.keyboard.latin.utils.previewDark
import helium314.keyboard.settings.screens.gesturedata.END_DATE_EPOCH_MILLIS
import helium314.keyboard.settings.screens.gesturedata.TWO_WEEKS_IN_MILLIS

private val SbBackground = Color(0xFF080312)
private val SbPanel = Color(0xFF180A2A)
private val SbPanelLight = Color(0xFF27103F)
private val SbPurple = Color(0xFF9D28FF)
private val SbPink = Color(0xFFFF42D6)
private val SbBlue = Color(0xFF4B6DFF)
private val SbWhite = Color(0xFFFFF7FF)
private val SbMuted = Color(0xFFD6C4E8)

@Composable
fun MainSettingsScreen(
    onClickAbout: () -> Unit,
    onClickTextCorrection: () -> Unit,
    onClickPreferences: () -> Unit,
    onClickToolbar: () -> Unit,
    onClickGestureTyping: () -> Unit,
    onClickDataGathering: () -> Unit,
    onClickAdvanced: () -> Unit,
    onClickAppearance: () -> Unit,
    onClickLanguage: () -> Unit,
    onClickLayouts: () -> Unit,
    onClickDictionaries: () -> Unit,
    onClickBack: () -> Unit,
) {
    SearchSettingsScreen(
        onClickBack = onClickBack,
        title = "SoftBoard",
        settings = emptyList(),
    ) {
        val enabledSubtypes = SubtypeSettings.getEnabledSubtypes(true)
        Scaffold(
            containerColor = SbBackground,
            contentWindowInsets = WindowInsets.safeDrawing.only(WindowInsetsSides.Bottom)
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                // Brand / hero panel
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF4D087D),
                                    Color(0xFF1A0735),
                                    Color(0xFF080D35),
                                    Color(0xFF4A075E)
                                )
                            )
                        )
                        .border(
                            1.dp,
                            Brush.linearGradient(listOf(SbPink, SbPurple, SbBlue)),
                            RoundedCornerShape(30.dp)
                        )
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("✦  YOUR KEYBOARD EXPERIENCE", color = SbMuted, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                        Text(
                            "✦ PRO",
                            color = Color(0xFFFFD76A),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.ExtraBold,
                            modifier = Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xFF35104F))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Box(
                        modifier = Modifier
                            .size(82.dp)
                            .clip(RoundedCornerShape(26.dp))
                            .background(
                                Brush.linearGradient(listOf(SbPink, SbPurple, SbBlue))
                            )
                            .border(1.dp, Color.White.copy(alpha = 0.65f), RoundedCornerShape(26.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("⌨", color = Color.White, fontSize = 48.sp, fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "SoftBoard",
                        color = SbWhite,
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Black
                    )
                    Text(
                        "Smart Keyboard, Smarter You",
                        color = SbMuted,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(5.dp))
                    Text("MADE BY YASHI  💜", color = SbPink, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(18.dp))
                    SoftKeyboardPreview()
                    Spacer(Modifier.height(17.dp))
                    Button(
                        onClick = onClickLanguage,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = SbPurple,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(50)
                    ) {
                        Text("GET STARTED   ➜", fontSize = 15.sp, fontWeight = FontWeight.ExtraBold)
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        enabledSubtypes.joinToString("  •  ") { it.displayName() },
                        color = SbMuted,
                        fontSize = 11.sp,
                        textAlign = TextAlign.Center
                    )
                }

                // Feature strip
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(22.dp))
                        .background(SbPanel)
                        .border(1.dp, SbPurple.copy(alpha = 0.35f), RoundedCornerShape(22.dp))
                        .padding(vertical = 14.dp, horizontal = 5.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    SoftFeature("⚡", "FAST", "Typing")
                    SoftFeature("🛡", "PRIVATE", "Keyboard")
                    SoftFeature("🎨", "BEAUTIFUL", "Themes")
                    SoftFeature("⚙", "POWERFUL", "Tools")
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Make it yours", color = SbWhite, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold)
                        Text("Personalize your typing experience", color = SbMuted, fontSize = 12.sp)
                    }
                    Text("✦ ✦ ✦", color = SbPink, fontSize = 15.sp)
                }

                // Featured theme tiles
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SoftThemeTile(
                        "PURPLE NEON", "💜", listOf(Color(0xFF5D0AB5), Color(0xFF17052E)),
                        onClickAppearance, Modifier.weight(1f)
                    )
                    SoftThemeTile(
                        "BLUE GALAXY", "💙", listOf(Color(0xFF0752A4), Color(0xFF07152F)),
                        onClickAppearance, Modifier.weight(1f)
                    )
                }
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    SoftThemeTile(
                        "PINK DREAM", "💗", listOf(Color(0xFFFF4BBE), Color(0xFF68135D)),
                        onClickAppearance, Modifier.weight(1f)
                    )
                    SoftThemeTile(
                        "BLACK GOLD", "👑", listOf(Color(0xFF594014), Color(0xFF100D0A)),
                        onClickAppearance, Modifier.weight(1f)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Keyboard tools", color = SbWhite, fontSize = 21.sp, fontWeight = FontWeight.ExtraBold)
                    Text("EXPLORE", color = SbPink, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                    SoftActionTile("✍️", "Text correction", "Smart typing", onClickTextCorrection, Modifier.weight(1f))
                    SoftActionTile("🧰", "Toolbar", "Quick tools", onClickToolbar, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                    SoftActionTile("🌐", "Languages", "Add layouts", onClickLanguage, Modifier.weight(1f))
                    SoftActionTile("📚", "Dictionary", "Your words", onClickDictionaries, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                    SoftActionTile("⌨️", "Layouts", "Keyboard styles", onClickLayouts, Modifier.weight(1f))
                    SoftActionTile("⚙️", "Preferences", "Make changes", onClickPreferences, Modifier.weight(1f))
                }
                Row(horizontalArrangement = Arrangement.spacedBy(11.dp)) {
                    SoftActionTile("🛠️", "Advanced", "More controls", onClickAdvanced, Modifier.weight(1f))
                    SoftActionTile("ⓘ", "About", "SoftBoard info", onClickAbout, Modifier.weight(1f))
                }
                if (JniUtils.sHaveGestureLib) {
                    SoftActionTile(
                        "👆", "Gesture typing", "Swipe to type",
                        onClickGestureTyping, Modifier.fillMaxWidth()
                    )
                    if (System.currentTimeMillis() < END_DATE_EPOCH_MILLIS + TWO_WEEKS_IN_MILLIS) {
                        SoftActionTile(
                            "📊", "Gesture data", "Data options",
                            onClickDataGathering, Modifier.fillMaxWidth()
                        )
                    }
                }

                Text(
                    "SOFTBOARD  •  MADE WITH 💜 BY YASHI",
                    color = SbMuted,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun SoftKeyboardPreview() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color(0xCC090515))
            .border(1.dp, SbPink.copy(alpha = 0.75f), RoundedCornerShape(20.dp))
            .padding(9.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        val rows = listOf(
            listOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
            listOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
            listOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
            listOf("⇧", "Z", "X", "C", "V", "B", "N", "M", "⌫")
        )
        rows.forEach { row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                row.forEach { key ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(27.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFF7020C8), Color(0xFF25084D))
                                )
                            )
                            .border(0.7.dp, SbPink.copy(alpha = 0.65f), RoundedCornerShape(6.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(key, color = Color.White, fontSize = 11.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            listOf("123", "☻", "English", "↵").forEachIndexed { index, key ->
                Box(
                    modifier = Modifier
                        .weight(if (index == 2) 3f else 1f)
                        .height(26.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(Color(0xFF381064))
                        .border(0.7.dp, SbPurple, RoundedCornerShape(6.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(key, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun SoftFeature(icon: String, title: String, subtitle: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(icon, fontSize = 23.sp)
        Spacer(Modifier.height(4.dp))
        Text(title, color = SbWhite, fontSize = 9.sp, fontWeight = FontWeight.ExtraBold)
        Text(subtitle, color = SbMuted, fontSize = 9.sp)
    }
}

@Composable
private fun SoftThemeTile(
    title: String,
    icon: String,
    colors: List<Color>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Brush.linearGradient(colors))
            .border(1.dp, Color.White.copy(alpha = 0.24f), RoundedCornerShape(20.dp))
            .clickable(onClick = onClick)
            .padding(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(icon, fontSize = 27.sp)
        Spacer(Modifier.height(5.dp))
        Text(title, color = Color.White, fontSize = 10.sp, fontWeight = FontWeight.ExtraBold)
        Text("VIEW THEME  ›", color = Color.White.copy(alpha = 0.8f), fontSize = 8.sp)
    }
}

@Composable
private fun SoftActionTile(
    icon: String,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
        shape = RoundedCornerShape(19.dp),
        colors = CardDefaults.cardColors(containerColor = SbPanel),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(13.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.linearGradient(listOf(Color(0xFF6720A0), Color(0xFF251044)))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(icon, fontSize = 21.sp)
            }
            Spacer(Modifier.width(10.dp))
            Column(Modifier.weight(1f)) {
                Text(title, color = SbWhite, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(subtitle, color = SbMuted, fontSize = 10.sp)
            }
            Text("›", color = SbPink, fontSize = 23.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
private fun PreviewScreen() {
    initPreview(LocalContext.current)
    Theme(previewDark) {
        Surface {
            MainSettingsScreen({}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {})
        }
    }
}
