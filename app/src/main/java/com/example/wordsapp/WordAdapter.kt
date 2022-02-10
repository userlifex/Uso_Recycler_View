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

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_cancion.view.*

/**
 * Adapter for the [RecyclerView] in [DetailActivity].
 */
class WordAdapter(private var lista: ArrayList<Cancion>, private var contexto: Context) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }

    class ViewHolder(var vista:View, var contexto: Context):RecyclerView.ViewHolder(vista){
        fun bind(cancion: Cancion) {
            vista.btnGoogle.setOnClickListener{
                val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${cancion.titulo}")
                val intent = Intent(Intent.ACTION_VIEW, queryUrl)
                contexto.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cancion,parent,false),contexto)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lista[position]
        holder.vista.titulo.text = item.titulo
        Picasso.get().load(item.urlImage).into(holder.vista.imagen)

        holder.vista.btnGoogle.setOnClickListener{
            val queryUrl: Uri = Uri.parse("${WordListFragment.SEARCH_PREFIX}${item.titulo}")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            contexto.startActivity(intent)
        }

        holder.vista.btnYoutube.setOnClickListener{
            openYoutube(item.yotubeId)
        }

    }

    override fun getItemCount(): Int {
        return lista.size
    }

    fun openYoutube(id: String){
        val intentApp = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id))
        val intentBrowser = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + id))
        try {
            contexto.startActivity(intentApp)
        } catch (ex: ActivityNotFoundException) {
            contexto.startActivity(intentBrowser)
        }

    }
}