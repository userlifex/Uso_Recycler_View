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

    private var auxList = ArrayList<Cancion>()

    private var _binding: FragmentWordListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var album: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Retrieve the LETTER from the Fragment arguments
        arguments?.let {
            album = it.getString(LETTER).toString()
        }
    }

    private fun yhlqsmdlg() {
        auxList.add(Cancion("Si veo a tu mama","https://image.europafm.com/clipping/cmsimages02/2020/03/02/233BFB93-443F-4BB4-A34E-EA4B155C29C0/58.jpg", "CPK_IdHe1Yg"))
        auxList.add(Cancion("La dificil","https://i.ytimg.com/vi/fEYUoBgYKzw/maxresdefault.jpg", "fEYUoBgYKzw"))
        auxList.add(Cancion("Yo perreo sola","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSdZvW0uUnjcB_IgOk9fg7p4wfqWoz0hst-3Q&usqp=CAU", "GtSRKwDCaZM"))
    }

    private fun tour(){
        auxList.add(Cancion("Dakiti","https://www.lahiguera.net/musicalia/artistas/bad_bunny/disco/10999/tema/24149/bad_bunny_dakiti-portada.jpg", "TmKh7lAwnBI"))
        auxList.add(Cancion("El mundo es mio","https://i.pinimg.com/736x/a2/ec/bc/a2ecbcf0e0cb39da0b3d82707afbc25d.jpg", "crP1orWqBpo"))
        auxList.add(Cancion("Te mudaste","https://i1.sndcdn.com/artworks-nu0edPQ3WvUoXWJ3-f8mvGw-t500x500.jpg", "4qt2P9Tcnhs"))
    }

    private fun salir() {
      auxList.add(Cancion("Si ella sabe","https://picsum.photos/200/300?random=2", "L88MenfjtTI"))
      auxList.add(Cancion("Mas de una cita","https://picsum.photos/200/300?random=3", "UkC78ZKHn_k"))
      auxList.add(Cancion("Bye bye me fui","https://picsum.photos/200/300?random=4", "Ts_pOxNJtVg"))
      auxList.add(Cancion("Bendiciones","https://picsum.photos/200/300?random=5", "z2Mp_jPkAYs"))
    }


    private fun next() {

      auxList.add(Cancion("Ni bien ni mal","https://picsum.photos/200/300?random=6", "kvct5Xfd92g"))
      auxList.add(Cancion("200 mph","https://picsum.photos/200/300?random=7", "gvO8Jrw7Gnw"))
    }
    private fun oasis() {
      auxList.add(Cancion("La cancion","https://picsum.photos/200/300?random=13", "LxOTsiV4tkQ"))
      auxList.add(Cancion("Mojadita","https://picsum.photos/200/300?random=10", "hnm3IxupbgU"))
      auxList.add(Cancion("Un peso","https://picsum.photos/200/300?random=14", "7o2_OnTFmmA"))
      auxList.add(Cancion("Como un bebe","https://picsum.photos/200/300?random=15", "G1l5mjoh-MY"))
    }

    private fun badBunny() {

      auxList.add(Cancion("Me acostumbre","https://picsum.photos/200/300?random=22", "xKKeqlBQ3Js"))
      auxList.add(Cancion("Me llamas","https://picsum.photos/200/300?random=23", "AuLw5LL0l1E"))
      auxList.add(Cancion("Soy peor","https://picsum.photos/200/300?random=24", "ws00k_lIQ9U"))
      auxList.add(Cancion("Para ti","https://picsum.photos/200/300?random=25", "fUN2dGKxHFU"))
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

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        auxList.clear()
        when(album){
            "YHLQSMDLG"-> yhlqsmdlg()
            "El ultimo tour del mundo"-> tour()
            "Las que no iban a salir"-> salir()
            "UP NEXT"-> next()
            "Oasis"-> oasis()
            "El conejo Malo"-> badBunny()

        }
        
        recyclerView.adapter = WordAdapter(auxList, requireContext())

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
