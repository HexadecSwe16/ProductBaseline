package com.megalexa.hexadec.presenter.connection.block

import com.megalexa.hexadec.model.block.BlockText
import com.megalexa.hexadec.presenter.BlockConnection
import org.json.JSONObject

object BlockSlackConnection : BlockConnection() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockText {
        return BlockText(jsonObject.getJSONObject("config").getString("TEXT"))
    }

    override fun <BlockText> convertToJSON(t: BlockText): JSONObject {
        val textBlock = t as com.megalexa.hexadec.model.block.BlockText
        val allBlock = JSONObject()
        allBlock.put("TextValue", textBlock.getConfig())
        return allBlock
    }
    override val resource: String
        get() = "blocks/text"
}