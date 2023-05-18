package com.example.todolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todolist.domain.CustomDialogViewModel
import com.example.todolist.model.ToDoItem
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CustomDialogViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: CustomDialogViewModel
    private val prefsRepositoryMock: PrefsRepository = mock()

    private val todoItemFake : ToDoItem =
        ToDoItem(0, "testTitle", " testDescription", "testNumber")
    private val keyTestValue: String = "testKey"
    private val valueTestValue: String = "valueTest"

    @Before
    fun setup() {
        subject = CustomDialogViewModel(prefsRepositoryMock)
    }

    @Test
    fun getToDoItemFromPrefs_success() {
        `when`(prefsRepositoryMock.getTodoItem()).thenReturn(todoItemFake)
        subject.getToDoItemFromPrefs()
        val expectedResult = subject.toDoItemResult.value?.title
        assertEquals("testTitle", expectedResult)
    }

    @Test
    fun saveDataInPrefs_success() {
        subject.saveDataInPrefs(keyTestValue, valueTestValue)
        verify(prefsRepositoryMock).saveDataInPrefs(keyTestValue, valueTestValue)
    }
}