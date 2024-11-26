package org.reynhart.baksotsel.ui.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.ui.theme.onPrimaryContainerLight
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaksoDialog(
    message: String,
    itemMap: List<DialogModel>,
    onSelectedItem: (String)-> Unit,
    onDismissDialogBox: ()-> Unit
){
    var isShowBottomSheet by remember { mutableStateOf(false) }
    val modalSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(onDismissRequest = {
        isShowBottomSheet=false
        onDismissDialogBox()
    }, sheetState = modalSheetState, containerColor = onPrimaryContainerLight ){
        Column (modifier = Modifier.padding(16.dp)) {
            Text(text=message, fontSize =16.sp, textAlign = TextAlign.Center)
            Spacer(Modifier.size(16.dp))
            itemMap.forEach { it ->
                if(it.isPrimaryColor){
                    OutlinedButton(onClick = {
                        isShowBottomSheet = false
                        onSelectedItem(it.value)
                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = primaryLight,
                            contentColor = onPrimaryLight
                        )
                    ){
                        Text(it.label)
                    }
                } else {
                    OutlinedButton(onClick = {
                        isShowBottomSheet = false
                        onSelectedItem(it.value)
                    },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = onPrimaryLight,
                            contentColor = primaryLight
                        )
                    ){
                        Text(it.label)
                    }
                }

            }

        }

    }
}