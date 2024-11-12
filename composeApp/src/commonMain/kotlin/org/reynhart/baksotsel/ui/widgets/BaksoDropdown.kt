package org.reynhart.baksotsel.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.reynhart.baksotsel.ui.theme.onPrimaryContainerLight
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaksoDropdown(
    label: String,
    placeholder: String,
    dropdownMap: Map<String, String>,
    onDropdownChange: (String)-> Unit
){
    var isShowBottomSheet by remember { mutableStateOf(false) }
    var currentValue by remember{ mutableStateOf("") }
    var currentLabel by remember{ mutableStateOf(placeholder) }
    val modalSheetState = rememberModalBottomSheetState()

    Text(label, fontSize = 14.sp)
    OutlinedButton(onClick = {
        isShowBottomSheet = true
    }, modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = onPrimaryContainerLight,
            contentColor = primaryLight)
    ){
        Text(currentLabel)
    }

    if(isShowBottomSheet){
        ModalBottomSheet(onDismissRequest = {
            isShowBottomSheet=false
        }, sheetState = modalSheetState, containerColor = onPrimaryContainerLight ){
            Column (modifier = Modifier.padding(16.dp)) {
                dropdownMap.forEach { it ->
                    OutlinedButton(onClick = {
                        isShowBottomSheet = false
                        currentValue = it.value
                        currentLabel = it.key
                        onDropdownChange(currentValue)
                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = onPrimaryContainerLight,
                            contentColor = primaryLight)
                    ){
                        Text(it.key)
                    }
                }

            }

        }
    }

}