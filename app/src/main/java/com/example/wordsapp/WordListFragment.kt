/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.wordsapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

/**
 * Displays a [RecyclerView] of words with search buttons to look them up.
 */
class WordListFragment : Fragment() {

    /**
     * Provides global access to these variables from anywhere in the app
     * via DetailListFragment.<variable> without needing to create
     * a DetailListFragment instance.
     */
    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    private var _binding: FragmentWordListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var genero: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the LETTER from the Fragment arguments
        arguments?.let {
            genero = it.getString(LETTER).toString()
        }
    }

    private fun PeliculasComedia():ArrayList<Pelicula>{
        var lista = ArrayList<Pelicula>()
        lista.add(Pelicula(R.drawable.american_pie,"American Pie"))
        lista.add(Pelicula(R.drawable.chiquito_pero_peligroso,"Chiquito pero peligroso"))
        lista.add(Pelicula(R.drawable.y_donde_estan_las_rubias,"¿Y donde están las Rubias?"))
        lista.add(Pelicula(R.drawable.no_manches_frida,"No manches Frida"))
        lista.add(Pelicula(R.drawable.no_se_aceptan_devoluciones,"No se aceptan devoluciones"))
        lista.add(Pelicula(R.drawable.somos_los_miller,"Somos los miller"))
        return lista
    }
    private fun PeliculasAccion():ArrayList<Pelicula>{
        var lista = ArrayList<Pelicula>()
        lista.add(Pelicula(R.drawable.el_rey_escorpion,"El Rey Escorpion"))
        lista.add(Pelicula(R.drawable.john_wick_2,"John Wick 2"))
        lista.add(Pelicula(R.drawable.rapido_y_furiosos,"Rapido y Furiosos"))
        return lista
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve and inflate the layout for this fragment
        _binding = FragmentWordListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        var list : ArrayList<Pelicula> =  ArrayList<Pelicula>();
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        Log.d("asas",genero.toString())
        if (genero.equals("Acción")){
            list = PeliculasAccion()
        }else if(genero.equals("Comedia")){
            list = PeliculasComedia()
        }else if(genero.equals("Ciencia Ficción")){

        }else if(genero.equals("Documentales")){

        }else if(genero.equals("Drama")){

        }else if(genero.equals("Fantasia")){

        }else if(genero.equals("Romance")){

        }else if(genero.equals("Terror")){

        }
        
        recyclerView.adapter = WordAdapter(list, requireContext())

        // Adds a [DividerItemDecoration] between items
        recyclerView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
    }

    /**
     * Frees the binding object when the Fragment is destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
