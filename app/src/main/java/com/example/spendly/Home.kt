package com.example.spendly

import android.content.Intent
import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.google.android.material.bottomnavigation.BottomNavigationView

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_modern)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        // Highlight the current menu item
        bottomNavigationView.selectedItemId = R.id.nav_home

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> return@setOnItemSelectedListener true
                R.id.nav_profile -> {
                    startActivity(Intent(this, Profile::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.nav_history -> {
                    startActivity(Intent(this, History::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, Settings::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
                R.id.nav_about -> {
                    startActivity(Intent(this, About::class.java))
                    overridePendingTransition(0, 0)
                    return@setOnItemSelectedListener true
                }
            }
            false
        }

        // Example: Food category popup
        val foodCard = findViewById<CardView>(R.id.foodCategory) // set id in XML
        foodCard.setOnClickListener {
            showExpensePopup("Food")
        }

        val transportcard = findViewById<CardView>(R.id.transportCategory) // set id in XML
        transportcard.setOnClickListener {
            showExpensePopup("Transport")
        }
        val entertainmentcard = findViewById<CardView>(R.id.entertainmentCategory) // set id in XML
        entertainmentcard.setOnClickListener {
            showExpensePopup("Entertainment")
        }
        val educationcard = findViewById<CardView>(R.id.educationCategory) // set id in XML
        educationcard.setOnClickListener {
            showExpensePopup("Education")
        }
        val othercard = findViewById<CardView>(R.id.otherCategory) // set id in XML
        othercard.setOnClickListener {
            showExpensePopup("Other")
        }
        
    }

    private fun showExpensePopup(category: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_add_expense)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        val tvCategory = dialog.findViewById<TextView>(R.id.tvCategory)
        val etAmount = dialog.findViewById<EditText>(R.id.etAmount)
        val btnAdd = dialog.findViewById<Button>(R.id.btnAddExpense)
        val btnClose = dialog.findViewById<Button>(R.id.btnClose)

        tvCategory.text = "Add $category Expense"

        btnAdd.setOnClickListener {
            val amount = etAmount.text.toString()
            if (amount.isNotEmpty()) {
                Toast.makeText(this, "$category Expense Added: Rs. $amount", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
            } else {
                etAmount.error = "Enter amount"
            }
        }

        btnClose.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }
}
