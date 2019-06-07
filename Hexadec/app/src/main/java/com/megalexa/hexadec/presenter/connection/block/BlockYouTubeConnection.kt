package com.megalexa.hexadec.presenter.connection.block

import com.megalexa.hexadec.model.block.BlockText
import com.megalexa.hexadec.model.block.BlockYouTube
import com.megalexa.hexadec.presenter.BlockConnection
import org.json.JSONObject

object BlockYouTubeConnection : BlockConnection() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockYouTube {
        return BlockYouTube(jsonObject.getJSONObject("config").getString("TEXT"))
    }

    override fun <BlockYouTube> convertToJSON(t: BlockYouTube): JSONObject {
        val textBlock = t as com.megalexa.hexadec.model.block.BlockYouTube
        val allBlock = JSONObject()
        allBlock.put("URLValue", textBlock.getConfig())
        return allBlock
    }
    override val resource: String
        get() = "blocks/youtube"
}