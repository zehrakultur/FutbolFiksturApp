package com.kultur.futbolfiksturuygulamasi.Activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.kultur.futbolfiksturuygulamasi.AntiClockSpinTransformation
import com.kultur.futbolfiksturuygulamasi.Interface.IFirebaseLoadDone
import com.kultur.futbolfiksturuygulamasi.Model.FixtureModel
import com.kultur.futbolfiksturuygulamasi.R
import com.kultur.futbolfiksturuygulamasi.SaveData
import com.kultur.futbolfiksturuygulamasi.Adapter.ViewPagerAdapter
import com.kultur.futbolfiksturuygulamasi.Model.DataModel
import kotlinx.android.synthetic.main.activity_fikstur.*

class Fikstur:AppCompatActivity(), IFirebaseLoadDone, ValueEventListener {

    lateinit var adapter: ViewPagerAdapter
    lateinit var teamsFixture: DatabaseReference
    lateinit var iFirebaseLoadDone:IFirebaseLoadDone
    private lateinit var saveData: SaveData
    private var dataModels:ArrayList<DataModel>?=null

    override fun onCreate(savedInstanceState: Bundle?) {

        saveData= SaveData(this)
        if(saveData.loadDarkModeState() == true){
            setTheme(R.style.darkTheme)
        }
        else{
            setTheme(R.style.AppTheme)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fikstur)

        teamsFixture= FirebaseDatabase.getInstance().getReference("fixtur")

        iFirebaseLoadDone=this

        loadFixture()
        //takim1.text=takimlar.takim.toString()
        view_pager.setPageTransformer(true, AntiClockSpinTransformation())


//        var veriList = ArrayList<String>()
//        veriList.add("Adana Demirspor")
//        veriList.add("Alanyaspor")
//        veriList.add("Altay")
//        veriList.add("Antalyaspor")
//        veriList.add("Başakşehir FK")
//        veriList.add("Beşiktaş")
//        veriList.add("Çaykur Rizespor")
//        veriList.add("Fatih Karagümrük")
//        veriList.add("Fenerbahçe")
//        veriList.add("Galatasaray")
//        veriList.add("Gaziantep FK")
//        veriList.add("Giresunspor")
//        veriList.add("Göztepe")
//        veriList.add("Hatayspor")
//        veriList.add("Kasımpaşa")
//        veriList.add("Kayserispor")
//        veriList.add("Konyaspor")
//        veriList.add("Sivasspor")
//        veriList.add("Trabzonspor")
//        veriList.add("Yeni Malatyaspor")
//        Log.e("deneme",veriList.toString())


//        veriList.add(dataModels.toString())
//        Log.e("deneme",veriList.toString())

    }

    private fun loadFixture() {
        teamsFixture.addListenerForSingleValueEvent(this)
    }

    override fun onCodeLoadSuccess(fixture: List<FixtureModel>) {
        adapter= ViewPagerAdapter(this,fixture)
        view_pager.adapter=adapter
    }

    override fun onCodeLoadFailed(message: String) {
        Toast.makeText(this,""+message, Toast.LENGTH_SHORT).show()
    }

    override fun onDataChange(snapshot: DataSnapshot) {
        var fix:MutableList<FixtureModel> =ArrayList()
        for (codeSnapshot in snapshot.children){
            val fixT=codeSnapshot.getValue(FixtureModel::class.java)

            fix.add(fixT!!)

        }
        iFirebaseLoadDone.onCodeLoadSuccess(fix)
    }

    override fun onCancelled(error: DatabaseError) {
        iFirebaseLoadDone.onCodeLoadFailed(error.message)
    }

    override fun onResume() {
        super.onResume()
        teamsFixture.addValueEventListener(this)
    }

    override fun onDestroy() {
        teamsFixture.removeEventListener(this)
        super.onDestroy()
    }

    override fun onStop() {
        teamsFixture.removeEventListener(this)
        super.onStop()
    }
}