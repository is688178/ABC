package com.example.abc.fragments.mainBottom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.abc.R
import com.example.abc.adapters.AdapterMaterial
import com.parse.ParseObject
import com.parse.ParseQuery
import org.jetbrains.anko.find

class FragmentMaterials : Fragment() {
    private lateinit var mRecyclerView: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main_materials, container, false)

        mRecyclerView = view.find(R.id.fragment_materials_rv)

        val query = ParseQuery.getQuery<ParseObject>("Materials")
        query.findInBackground { objects, _ ->
            mRecyclerView.adapter = AdapterMaterial(objects)
            mRecyclerView.layoutManager = LinearLayoutManager(view.context)
        }

        return view
    }
}
