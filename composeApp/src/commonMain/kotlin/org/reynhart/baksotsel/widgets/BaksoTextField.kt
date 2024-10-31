package org.reynhart.baksotsel.widgets

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaksoTextField(
    label: String,
    value: String,
    placeholder: String,
    onValueChange: (String)->Unit
){
    Text(label, fontSize = 14.sp)
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        value = value,
        placeholder = { Text(placeholder) },
        onValueChange = onValueChange,
    )
}