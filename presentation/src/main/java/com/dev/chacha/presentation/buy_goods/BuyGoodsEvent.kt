package com.dev.chacha.presentation.buy_goods

sealed class BuyGoodsEvent {
    object ShowDialog : BuyGoodsEvent()
    object DismissDialog : BuyGoodsEvent()
    object ClickSend : BuyGoodsEvent()
    data class SelectItem(val item: BuyGoods) : BuyGoodsEvent()
}