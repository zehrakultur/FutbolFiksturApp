package com.kultur.futbolfiksturuygulamasi.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Switch
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kultur.futbolfiksturuygulamasi.*
import com.kultur.futbolfiksturuygulamasi.Adapter.RecyclerViewAdapter
import com.kultur.futbolfiksturuygulamasi.Interface.ApiInterface
import com.kultur.futbolfiksturuygulamasi.Model.DataModel
import com.mahfa.dnswitch.DayNightSwitch
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ma.apps.widgets.daynightswitch.OnSwitchListener
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() , RecyclerViewAdapter.Listener {

    private var dataModels:ArrayList<DataModel>?=null
    private var recyclerViewAdapter: RecyclerViewAdapter?=null
    //Disposable
    private var compositeDisposable: CompositeDisposable? = null

    private var switch: Switch?=null
    private lateinit var saveData: SaveData
    private  var dataList : ArrayList<DataModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData= SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }
        else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        compositeDisposable = CompositeDisposable()

        val layoutManager: RecyclerView.LayoutManager= LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager

        loadData()

        btnFikstur.setOnClickListener {
            val intent=Intent(applicationContext, ProgressAnimation::class.java)
            startActivity(intent)
        }

        switch=findViewById<View>(R.id.dayNight) as Switch?
        if(saveData.loadDarkModeState() == true){
            switch!!.isChecked=true
        }

        
        switch!!.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                saveData.setDarkModeState(true)
                restartApplication()
            }
            else{
                saveData.setDarkModeState(false)
                restartApplication()
            }


        }

//        var veriList = ArrayList<DataModel>()


//        val mac=veriList.size - 1
//        var sabitTakim= veriList.random()
//        veriList.remove(sabitTakim)
//        sabitTakim+=veriList[0]
//        Toast.makeText(this,"deneme"+sabitTakim,Toast.LENGTH_SHORT).show()

//            for (i in veriList.indices step 1) {
//
//                for (j in veriList.indices step 1) {
//
//                    Toast.makeText(this, veriList[i] + " ve " + veriList[j], Toast.LENGTH_SHORT)
//                        .show()
//
//                }
//            sabitTakim+=veriList[0]
//            veriList.removeAt(0)

     //       }

    }

    private fun restartApplication() {
        val i=Intent(applicationContext, MainActivity::class.java)
        startActivity(i)
        finish()
    }

    private fun loadData() {

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(ApiInterface::class.java)

        compositeDisposable?.add(
            retrofit.takimListesi()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }

    private fun handleResponse(teamList: List<DataModel>){
        dataModels = ArrayList(teamList)

        dataModels?.let {
            recyclerViewAdapter = RecyclerViewAdapter(it, this@MainActivity)
            recyclerView.adapter = recyclerViewAdapter
        }
    }

    override fun onItemClick(dataModel: DataModel) {

    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        System.exit(0)
    }
}