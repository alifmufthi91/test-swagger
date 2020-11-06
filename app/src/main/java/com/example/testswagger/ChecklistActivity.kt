package com.example.testswagger

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_checklist.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChecklistActivity : AppCompatActivity() {
    private val apiMain = ApiMain.getInstance()
    private val apiClient = apiMain.client

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checklist)

        val viewModel = ViewModelProvider(
            ViewModelStore(),
            ViewModelProvider.NewInstanceFactory()
        )[ChecklistViewModel::class.java]

        val checklistAdapter = ChecklistAdapter()
        val mLayoutManager = LinearLayoutManager(this)
        rv_chekclist.layoutManager = mLayoutManager
        rv_chekclist.adapter = checklistAdapter

        val call = apiClient.checklist("Bearer ${apiMain.token}")
        call.enqueue(object : Callback<ResponseChecklist>{
            override fun onResponse(
                call: Call<ResponseChecklist>,
                response: Response<ResponseChecklist>
            ) {
                val result = response.body()
                if(result != null){
                    result.data?.let { viewModel.checklistItems.addAll(it) }
                    checklistAdapter.setData(viewModel.checklistItems)
                }
            }

            override fun onFailure(call: Call<ResponseChecklist>, t: Throwable) {
                Toast.makeText(this@ChecklistActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })

    }
}