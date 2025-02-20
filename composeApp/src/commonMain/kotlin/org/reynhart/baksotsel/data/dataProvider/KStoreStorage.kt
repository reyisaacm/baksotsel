package org.reynhart.baksotsel.data.dataProvider

import dev.whyoleg.cryptography.CryptographyAlgorithm
import dev.whyoleg.cryptography.CryptographyProvider
import dev.whyoleg.cryptography.algorithms.AES
import io.github.xxfast.kstore.KStore
import io.github.xxfast.kstore.file.extensions.storeOf
import kotlinx.io.bytestring.decodeToString
import kotlinx.io.bytestring.hexToByteString
import kotlinx.io.files.Path
import kotlinx.serialization.json.Json
import org.reynhart.baksotsel.data.interfaces.dataProvider.ILocalStorage
import org.reynhart.baksotsel.getFileDirectory
import org.reynhart.baksotsel.models.LoginUserModel

const val keyString = "f6745058a8294f85b64861dd04a52374";
val provider = CryptographyProvider.Default
val aesCbc = provider.get(AES.CBC)

class KStoreStorage :ILocalStorage{
    private val fileDir = getFileDirectory()
    private val store: KStore<ByteArray> = storeOf(file = Path("${fileDir}/user.json"), version = 0)

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun setUserData(data: LoginUserModel) {
        val stringifyData = Json.encodeToString(LoginUserModel.serializer(),data)

        val key = aesCbc.keyDecoder().decodeFromByteString(AES.Key.Format.RAW, keyString.hexToByteString())
        val cipher = key.cipher()
        val ciphertext: ByteArray = cipher.encrypt(plaintext = stringifyData.encodeToByteArray())
        store.set(ciphertext)
    }

    @OptIn(ExperimentalStdlibApi::class)
    override suspend fun getUserData(): LoginUserModel? {
        val storeData = store.get()

        if(storeData == null){
            return null
        } else {
            val key = aesCbc.keyDecoder().decodeFromByteString(AES.Key.Format.RAW, keyString.hexToByteString())
            val cipher = key.cipher()
            val decryptedData = cipher.decrypt(ciphertext = storeData).decodeToString()
            val stringifiedData = Json.decodeFromString<LoginUserModel>(decryptedData)
            return stringifiedData
        }

    }

    override suspend fun clearUserData() {
        store.delete()
    }

}