package com.mynimef.domain.models.response

interface IClientDataResponse {

    val emotionData: List<IDataResponse>

    val waterData: List<IDataResponse>?

    val weightData: List<IDataResponse>?

}