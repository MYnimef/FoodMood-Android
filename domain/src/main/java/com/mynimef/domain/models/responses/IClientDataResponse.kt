package com.mynimef.domain.models.responses

interface IClientDataResponse {

    val emotionData: List<IDataResponse>

    val waterData: List<IDataResponse>?

    val weightData: List<IDataResponse>?

}