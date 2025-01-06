package org.reynhart.baksotsel.ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.reynhart.baksotsel.getScreenWidth
import org.reynhart.baksotsel.ui.theme.onPrimaryContainerLight
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight

@Composable
fun BaksoErrorBox(
    message: String,
    onDismissRequest: () -> Unit
){

    Dialog(onDismissRequest =  onDismissRequest) {
        Card(
            modifier = Modifier.requiredWidth(width = getScreenWidth()).padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardColors(containerColor = onPrimaryLight,
                contentColor = primaryLight,
                disabledContentColor = onPrimaryLight,
                disabledContainerColor = onPrimaryLight)
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                    Row (horizontalArrangement = Arrangement.Center) {
                        Icon(Icons.Filled.Close, contentDescription = "", modifier = Modifier.size(64.dp))
                    }
                    Row (horizontalArrangement = Arrangement.Center) {
                        Text(text = message, textAlign = TextAlign.Center)
                    }
                Spacer(modifier = Modifier.height(16.dp))
                    Row {
                        OutlinedButton(onClick = onDismissRequest,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = onPrimaryContainerLight,
                                contentColor = primaryLight),
                            content = {
                            Text("Ok")
                        })
                    }
            }

        }
    }

}