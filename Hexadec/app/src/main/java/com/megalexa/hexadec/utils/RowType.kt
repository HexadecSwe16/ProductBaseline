package com.megalexa.hexadec.utils


interface RowType
class TextRow(val nome: String, val positionW: String, val textM: String) : RowType
class FeedRssRow(val nome: String, val positionW: String, val feedType: String, val feedUrl: String) : RowType
