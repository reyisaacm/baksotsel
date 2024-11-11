package org.reynhart.baksotsel.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.reynhart.baksotsel.ui.theme.onPrimaryLight
import org.reynhart.baksotsel.ui.theme.primaryLight

@Composable
fun BaksoLoadingBox(onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardColors(containerColor = onPrimaryLight,
                contentColor = primaryLight,
                disabledContentColor = onPrimaryLight,
                disabledContainerColor = onPrimaryLight)
        ) {
            Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator(
                    modifier = Modifier.width(48.dp),
                    color = primaryLight,
                    trackColor = onPrimaryLight,
                )
//                Text("Please wait")
            }

        }
    }

}