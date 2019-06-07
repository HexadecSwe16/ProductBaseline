package com.megalexa.hexadec.presenter.connection.block

import com.megalexa.hexadec.model.block.BLockTelegram
import com.megalexa.hexadec.model.block.BlockText
import com.megalexa.hexadec.presenter.BlockConnection
import org.json.JSONObject

object BlockTelegramConnection : BlockConnection() {

    override fun convertFromJSON(jsonObject: JSONObject): BLockTelegram {
        return BLockTelegram(jsonObject.getJSONObject("config").getString("TEXT"))
    }

    override fun <BLockTelegram> convertToJSON(t: BLockTelegram): JSONObject {
        val textBlock = t as com.megalexa.hexadec.model.block.BLockTelegram
        val allBlock = JSONObject()
        allBlock.put("profile", textBlock.getConfig())
        return allBlock
    }
    override val resource: String
        get() = "blocks/telegram"
}