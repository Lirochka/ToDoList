package com.example.todolist

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class CustomDialog(
    var activity: MainActivity,
    private val isNewItem: Boolean,
    private val item: ToDoItem?,
) : Dialog(activity), View.OnClickListener {

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText
    private lateinit var inputFieldNumber: EditText
    private lateinit var dialogLabel: TextView
    private lateinit var sharedPref: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_template)

        inputFieldTitle = findViewById(R.id.dialog_input_title)
        inputFieldDescription = findViewById(R.id.dialog_input_description)
        inputFieldNumber = findViewById(R.id.dialog_input_number)
        dialogLabel = findViewById(R.id.dialog_label)

        if (isNewItem) {
            createNewItem()
        } else {
            updateExistingItem()
        }

        initViews()
        dialogSizeControl()
    }

    private fun updateExistingItem() {
        Log.d("testLog", "updateExistingItem")
        dialogLabel.text = context.getString(R.string.update_item)
        inputFieldTitle.setText(item?.title)
        inputFieldDescription.setText(item?.description)
        inputFieldNumber.setText(item?.number.toString())
    }

    private fun createNewItem() {
        val sharedPref = activity.getPreferences(Context.MODE_PRIVATE)

        val titleFromPref = sharedPref.getString("titleKey", "")
        val descriptionFromPref = sharedPref.getString("descriptionKey", "")
        val numberFromPref = sharedPref.getString("numberKey", "")

        inputFieldTitle.setText(titleFromPref)
        inputFieldDescription.setText(descriptionFromPref)
        inputFieldNumber.setText(numberFromPref)
    }

    private fun dialogSizeControl() {
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(this.window?.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        lp.gravity = Gravity.CENTER
        this.window?.attributes = lp
    }

    private fun initViews() {
        okButton = findViewById(R.id.dialog_ok_button)
        cancelButton = findViewById(R.id.dialog_cancel_button)
        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.dialog_ok_button -> {
                okButtonClicker()
            }
            R.id.dialog_cancel_button -> dismiss()
            else -> {
                elseBeenClicked()
            }
        }
    }

    private fun elseBeenClicked() {
        TODO("Not yet implemented")
    }

    private fun okButtonClicker() {
        if (isNewItem) {
            okNewItemBeenClicked()
        } else {
            okUpdateItemBeenClicked()
        }
        dismiss()
    }

    private fun okUpdateItemBeenClicked() {
        val inputResultTitle = inputFieldTitle.text.toString()
        val inputResultDescription = inputFieldDescription.text.toString()
        val inputResultNumber = inputFieldNumber.text.toString().toInt()
        item?.id?.let { ToDoItem(it, inputResultTitle, inputResultDescription, inputResultNumber) }
            ?.let { activity.updateItem(it) }
    }

    private fun okNewItemBeenClicked() {
        //№2 Отправляем данные в БД
        //2.1 Вытаскиваем данные из полей ввода
        val inputResultTitle = inputFieldTitle.text.toString()
        val inputResultDescription = inputFieldDescription.text.toString()
        val inputResultNumber = inputFieldNumber.text.toString().toInt()
        activity.addItem(ToDoItem(0, inputResultTitle, inputResultDescription, inputResultNumber))

        inputFieldTitle.text.clear()
        inputFieldDescription.text.clear()
        inputFieldNumber.text.clear()
    }
    override fun onStop() {
        super.onStop()
        if (isNewItem) {
            sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
            with(sharedPref.edit()) {
                val inputTitleResult = inputFieldTitle.text.toString()
                val inputTitleDescription = inputFieldDescription.text.toString()
                val inputTitleNumber = inputFieldNumber.text.toString()

                putString("numberKey", inputTitleNumber)
                putString("titleKey", inputTitleResult)
                putString("descriptionKey", inputTitleDescription)
                apply()
            }
        }

    }
}

