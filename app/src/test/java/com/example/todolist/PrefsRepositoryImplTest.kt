package com.example.todolist

import android.content.SharedPreferences
import com.example.todolist.data.PrefsRepositoryImpl
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class PrefsRepositoryImplTest {
    private lateinit var subject: PrefsRepositoryImpl
    private val sharedPreferencesMock: SharedPreferences = mock()
    private val editorMock: SharedPreferences.Editor = mock()

    val titleTest = "titleTest"
    val descriptionTest = "descriptionTest"
    val numberTest = "numberTest"

    val keyTest = "keyTest"
    val valueTest = "valueTest"

    @Before
    fun setup() {
        subject = PrefsRepositoryImpl(sharedPreferencesMock)
    }

    @Test
    fun getTodoItem_success() {
        `when`(
            sharedPreferencesMock.getString(
                PrefsRepositoryImpl.PREFS_TITLE_KEY,
                PrefsRepositoryImpl.PREFS_DEFAULT_VALUE
            )
        ).thenReturn(titleTest)

        `when`(
            sharedPreferencesMock.getString(
                PrefsRepositoryImpl.PREFS_DESCRIPTION_KEY,
                PrefsRepositoryImpl.PREFS_DEFAULT_VALUE
            )
        ).thenReturn(descriptionTest)

        `when`(
            sharedPreferencesMock.getString(
                PrefsRepositoryImpl.PREFS_NUMBER_KEY,
                PrefsRepositoryImpl.PREFS_DEFAULT_VALUE
            )
        ).thenReturn(numberTest)

        val result = subject.getTodoItem()
        assertEquals("titleTest", result.title)
        assertEquals("descriptionTest", result.description)
        assertEquals("numberTest", result.number)
    }

    @Test
    fun saveDataInPrefs_success() {
        `when`(
            sharedPreferencesMock.edit()
        ).thenReturn(editorMock)

        subject.saveDataInPrefs(keyTest, valueTest)
        verify(editorMock).putString(keyTest, valueTest)
    }
}