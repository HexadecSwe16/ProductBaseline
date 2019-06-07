package com.megalexa.hexadec.presenter

import com.megalexa.hexadec.model.block.BlockFeedRss
import com.megalexa.hexadec.model.block.Filtrable
import org.json.JSONObject

object BlockFeedRssConnection :BlockConnection() {

    override fun convertFromJSON(jsonObject: JSONObject): BlockFeedRss {
        return BlockFeedRss(jsonObject.getJSONObject("config").getString("URL"))
    }

    override fun <BlockFeedRss> convertToJSON(t: BlockFeedRss): JSONObject {
        val blockFeedRss= t as com.megalexa.hexadec.model.block.BlockFeedRss
        val allBlock = JSONObject()
        allBlock.put("URLValue", blockFeedRss.getConfig())
        return allBlock
    }

    override val resource: String
        get() = "block/feedrssblock"
}
