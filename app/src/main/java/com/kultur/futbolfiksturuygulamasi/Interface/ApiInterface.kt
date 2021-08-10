package com.kultur.futbolfiksturuygulamasi.Interface

import com.kultur.futbolfiksturuygulamasi.Model.DataModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("json/superlig")
    fun takimListesi(): Observable<List<DataModel>>
}