package com.android.sportnmedc.network.request

import com.google.gson.annotations.Expose

/*
Trade - Setting trade

{
    "giveCards" : [
                    {123}, ...
                   ],
    "needCards" : [
                    {123}, ...
                   ]
}
*/
data class ReqTradeRegister(@Expose var giveCards: List<Long>, @Expose var needCards: List<Long>)