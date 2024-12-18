package org.reynhart.baksotsel.ui.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.navOptions
import baksotsel.composeapp.generated.resources.Res
import baksotsel.composeapp.generated.resources.logo
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel
import org.reynhart.baksotsel.data.interfaces.repository.IStorageRepository
import org.reynhart.baksotsel.getCurrentLocation
import org.reynhart.baksotsel.models.DialogModel
import org.reynhart.baksotsel.models.LoginUserModel
import org.reynhart.baksotsel.ui.theme.primaryLight
import org.reynhart.baksotsel.ui.widgets.BaksoDialog
import org.reynhart.baksotsel.ui.widgets.BaksoDropdown
import org.reynhart.baksotsel.ui.widgets.BaksoLoadingBox
import org.reynhart.baksotsel.ui.widgets.BaksoTextField
import org.reynhart.baksotsel.viewmodels.LoginViewModel
import org.reynhart.baksotsel.viewmodels.states.LoginStates

@Composable
fun Login(navController: NavController, vm: LoginViewModel= koinViewModel()){
    var nameTxt by remember { mutableStateOf("") }
    var dropdownValue by remember { mutableStateOf("") }
    var agreementChecked by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    val eventState by vm.eventState
    var isPermissionDenied by remember { mutableStateOf(false) }

    if(eventState == LoginStates.Loading){
        isLoading = true
    } else if(eventState == LoginStates.Success){
        navController.navigate(route = "Main"){
            popUpTo("Login",{inclusive=true})
        }
    }

    val factory: PermissionsControllerFactory = rememberPermissionsControllerFactory()
    val controller: PermissionsController = remember(factory) { factory.createPermissionsController() }
    BindEffect(controller)
    val coroutineScope = rememberCoroutineScope()


    MaterialTheme {
        Column (
            modifier = Modifier.fillMaxHeight().padding(24.dp).verticalScroll(rememberScrollState()),
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
                    shape = RoundedCornerShape(24.dp)
                )) {
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
                    Button(onClick = {
                        isPermissionDenied = false
                        coroutineScope.launch {
                            try {
                                controller.providePermission(Permission.LOCATION)
                                vm.onJoinClick(name=nameTxt, type=dropdownValue)
                            } catch(deniedAlways: DeniedAlwaysException) {
                                isPermissionDenied = true
                            } catch(denied: DeniedException) {
                                isPermissionDenied = true
                            }
                        }
                    },
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
            if(isLoading){
                BaksoLoadingBox {  }
            }
            if(isPermissionDenied){
                val dialogOptions = listOf<DialogModel>(
                    DialogModel(label="OK", value = "ok", isPrimaryColor = true),
                )
                BaksoDialog(
                    "Untuk menggunakan aplikasi ini, harap mengizinkan lokasi di perangkat anda",
                    itemMap = dialogOptions,
                    onSelectedItem ={ it ->
                            isPermissionDenied = false
                    },
                    onDismissDialogBox = {
                        isPermissionDenied = false
                    }
                )
            }
    }
}