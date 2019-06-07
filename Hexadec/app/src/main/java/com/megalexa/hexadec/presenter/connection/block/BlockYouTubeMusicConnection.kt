package com.megalexa.hexadec.presenter.connection.block

import com.megalexa.hexadec.model.block.BlockYouTube
import com.megalexa.hexadec.model.block.BlockYouTubeMusic
import com.megalexa.hexadec.presenter.BlockConnection
import org.json.JSONObject

object BlockYouTubeMusicConnection : BlockConnection() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockYouTubeMusic {
        return BlockYouTubeMusic(jsonObject.getJSONObject("config").getString("TEXT"))
    }

    override fun <BlockYouTubeMusic> convertToJSON(t: BlockYouTubeMusic): JSONObject {
        val textBlock = t as com.megalexa.hexadec.model.block.BlockYouTubeMusic
        val allBlock = JSONObject()
        allBlock.put("URLValue", textBlock.getConfig())
        return allBlock
    }
    override val resource: String
        get() = "blocks/youtubemusic"
}