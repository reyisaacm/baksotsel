package org.reynhart.baksotsel

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import baksotsel.composeapp.generated.resources.Res
import baksotsel.composeapp.generated.resources.compose_multiplatform
import baksotsel.composeapp.generated.resources.logo
import org.reynhart.baksotsel.ui.theme.backgroundDark
import org.reynhart.baksotsel.ui.theme.backgroundLight
import org.reynhart.baksotsel.ui.theme.primaryLight
import org.reynhart.baksotsel.widgets.BaksoDropdown
import org.reynhart.baksotsel.widgets.BaksoTextField

@Composable
@Preview
fun App() {
    var nameTxt by remember { mutableStateOf("") }
    var dropdownValue by remember { mutableStateOf("") }
    var agreementChecked by remember { mutableStateOf(false) }

    MaterialTheme {
        Column (
            modifier = Modifier.fillMaxHeight().padding(24.dp),
            verticalArrangement = Arrangement.Bottom
            ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(resource = Res.drawable.logo),
                    contentDescription = "Logo")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text("Verifikasi", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text("Masukan nama dan role Anda di bawah ini", fontSize = 14.sp)
            }

            Column(modifier = Modifier.padding(10.dp)
                .background(color = Color(red = 108f, green = 122f, blue = 137f),
                    shape = RoundedCornerShape(24.dp))) {
                Column(modifier = Modifier.padding(14.dp)) {
                    BaksoTextField(
                        label = "Nama",
                        value = nameTxt,
                        placeholder = "Masukan nama",
                        onValueChange = { nameTxt = it },
                    )
                    BaksoDropdown(
                        label = "Role",
                        placeholder = "Pilih Role",
                        dropdownMap = mapOf("Tukang Bakso" to "tb", "Customer" to "c"),
                        onDropdownChange = {dropdownValue=it}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(thickness = 2.dp)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        enabled = nameTxt.trim() != "" && dropdownValue != "" && agreementChecked,
                        colors = ButtonDefaults.buttonColors(containerColor = primaryLight)
                    ){
                        Text("Join")
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        Checkbox(
                            checked = agreementChecked,
                            onCheckedChange = { agreementChecked = it },
                            modifier = Modifier.padding(0.dp)
                        )
                        Text(
                            "Dengan menggunakan aplikasi ini, Anda telah setuju untuk membagikan lokasi Anda ke tukang bakso keliling.",
                            fontSize = 14.sp
                        )
                    }
                }



            }


        }
    }
}