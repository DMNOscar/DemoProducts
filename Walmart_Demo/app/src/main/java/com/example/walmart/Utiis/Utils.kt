package com.example.walmart.Utiis

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.example.walmark.R
import com.google.android.material.snackbar.Snackbar

object Utils {

    fun createCustomSnackbar(view: View, text: String, titel: String, context: Context): Snackbar {

        val snack: Snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
//        val params = view.layoutParams as FrameLayout.LayoutParams
        val inflater = LayoutInflater.from(view.context)

        val customSnackView: View = inflater.inflate(R.layout.item_snack, null)

        val textTitelSnack = customSnackView.findViewById<TextView>(R.id.textTitleSnack)
        val textSnack = customSnackView.findViewById<TextView>(R.id.textBodySnack)
        textTitelSnack.text = titel
        textSnack.text = text
        snack.view.setBackgroundColor(Color.TRANSPARENT);
        val snackbarLayout = snack.view as Snackbar.SnackbarLayout
        snackbarLayout.setPadding(10, 10, 10, 10);
        snackbarLayout.addView(customSnackView, 0)
        snack.show()
        return snack

    }
}