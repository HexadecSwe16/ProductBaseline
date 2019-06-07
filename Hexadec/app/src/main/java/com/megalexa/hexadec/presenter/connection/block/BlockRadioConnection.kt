package com.megalexa.hexadec.presenter.connection.block

import com.megalexa.hexadec.model.block.BlockKindle
import com.megalexa.hexadec.model.block.BlockRadio
import com.megalexa.hexadec.model.block.BlockText
import com.megalexa.hexadec.presenter.BlockConnection
import org.json.JSONObject

object BlockRadioConnection :BlockConnection(){
    override fun convertFromJSON(jsonObject: JSONObject): BlockRadio {
    return BlockRadio(jsonObject.getJSONObject("config").getString("TEXT"))
    }
    override fun <BlockRadio> convertToJSON(t: BlockRadio): JSONObject {
        val textBlock = t as com.megalexa.hexadec.model.block.BlockRadio
        val allBlock = JSONObject()
        allBlock.put("TextValue", textBlock.getConfig())
        return allBlock
    }
    override val resource: String
        get() = "blocks/radio"
}