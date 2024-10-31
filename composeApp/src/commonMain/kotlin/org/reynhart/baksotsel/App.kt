package org.reynhart.baksotsel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import baksotsel.composeapp.generated.resources.Res
import baksotsel.composeapp.generated.resources.compose_multiplatform
import org.reynhart.baksotsel.ui.theme.backgroundDark
import org.reynhart.baksotsel.ui.theme.backgroundLight
import org.reynhart.baksotsel.ui.theme.primaryLight
import org.reynhart.baksotsel.widgets.BaksoDropdown
import org.reynhart.baksotsel.widgets.BaksoTextField

@Composable
@Preview
fun App() {
    var nameTxt by remember { mutableStateOf("") }

    MaterialTheme {
        Column (
            modifier = Modifier.fillMaxHeight().padding(24.dp)) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text("Verifikasi", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text("Masukan nama dan role Anda di bawah ini", fontSize = 14.sp)
            }

            Column(modifier = Modifier.padding(16.dp)) {
                BaksoTextField(
                    label = "Nama",
                    value = nameTxt,
                    placeholder = "Masukan nama",
                    onValueChange = { nameTxt = it },
                )
                BaksoDropdown(
                    label = "Role",
                )
                Button(onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
                ){
                    Text("Join")
                }
            }


        }
    }
}