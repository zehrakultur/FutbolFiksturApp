package com.kultur.futbolfiksturuygulamasi.Interface

import com.kultur.futbolfiksturuygulamasi.Model.FixtureModel

interface IFirebaseLoadDone {
    fun onCodeLoadSuccess(fixture:List<FixtureModel>)
    fun onCodeLoadFailed(message:String)
}