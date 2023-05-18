package com.example.todolist.presentation

import android.app.ActionBar
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.todolist.R
import com.example.todolist.data.PrefsRepositoryImpl.Companion.PREFS_DESCRIPTION_KEY
import com.example.todolist.data.PrefsRepositoryImpl.Companion.PREFS_NUMBER_KEY
import com.example.todolist.data.PrefsRepositoryImpl.Companion.PREFS_TITLE_KEY
import com.example.todolist.domain.CustomDialogViewModel
import com.example.todolist.domain.MainViewModel
import com.example.todolist.model.ToDoItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomDialog(
    private val isNewItem: Boolean,
    private val item: ToDoItem?,
) : DialogFragment(), View.OnClickListener {

    private val customDialogViewModel: CustomDialogViewModel by activityViewModels()
    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var okButton: Button
    private lateinit var cancelButton: Button

    private lateinit var inputFieldTitle: EditText
    private lateinit var inputFieldDescription: EditText
    private lateinit var inputFieldNumber: EditText
    private lateinit var dialogLabel: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.dialog_template, container, false)

        initViews(view)

        if (isNewItem) {
            createNewItem()
        } else {
            updateExistingItem()
        }
        return view
    }
    override fun onResume() {
        super.onResume()
        dialogSizeControl()
        observe()
    }
    private fun observe() {
        customDialogViewModel.toDoItemResult.observe(this, Observer {
            if (isNewItem) {
                inputFieldTitle.setText(it.title)
                inputFieldDescription.setText(it.description)
                inputFieldNumber.setText(it.number)
            }
        })
    }
    private fun initViews(view: View) {
        inputFieldTitle = view.findViewById(R.id.dialog_input_title)
        inputFieldDescription = view.findViewById(R.id.dialog_input_description)
        inputFieldNumber = view.findViewById(R.id.dialog_input_number)
        dialogLabel = view.findViewById(R.id.dialog_label)

        okButton = view.findViewById(R.id.dialog_ok_button)
        cancelButton = view.findViewById(R.id.dialog_cancel_button)
        okButton.setOnClickListener(this)
        cancelButton.setOnClickListener(this)
    }
    private fun updateExistingItem() {
        dialogLabel.text = context?.getString(R.string.update_item)
        inputFieldTitle.setText(item?.title)
        inputFieldDescription.setText(item?.description)
        inputFieldNumber.setText(item?.number.toString())
    }

    private fun createNewItem() {
        customDialogViewModel.getToDoItemFromPrefs()
    }
    private fun dialogSizeControl() {
        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ActionBar.LayoutParams.MATCH_PARENT
        params.height = ActionBar.LayoutParams.WRAP_CONTENT
      dialog!!.window!!.attributes = params as LayoutParams
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
        val inputResultNumber = inputFieldNumber.text.toString()
        item?.id?.let { ToDoItem(it, inputResultTitle, inputResultDescription, inputResultNumber) }
            ?.let { mainViewModel.updateItem(it) }
    }
    private fun okNewItemBeenClicked() {
        val inputResultTitle = inputFieldTitle.text.toString()
        val inputResultDescription = inputFieldDescription.text.toString()
        val inputResultNumber = inputFieldNumber.text.toString()
        mainViewModel.insertItem(ToDoItem(0, inputResultTitle, inputResultDescription, inputResultNumber))

        inputFieldTitle.text.clear()
        inputFieldDescription.text.clear()
        inputFieldNumber.text.clear()
    }
    override fun onStop() {
        super.onStop()
        if (isNewItem) {
            val inputTitleResult = inputFieldTitle.text.toString()
            val inputDescriptionResult = inputFieldDescription.text.toString()
            val inputNumberResult = inputFieldNumber.text.toString()
            customDialogViewModel.saveDataInPrefs(PREFS_TITLE_KEY, inputTitleResult)
            customDialogViewModel.saveDataInPrefs(PREFS_DESCRIPTION_KEY, inputDescriptionResult)
            customDialogViewModel.saveDataInPrefs(PREFS_NUMBER_KEY, inputNumberResult)
        }
    }
}

