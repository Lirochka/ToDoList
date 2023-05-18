package com.example.todolist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.todolist.domain.MainViewModel
import com.example.todolist.model.ToDoItem
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class MainViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var subject: MainViewModel
    private val roomRepositoryMock: RoomRepository = mock()

    private val mockItemOne = ToDoItem(0, "titleTestOne", "descriptionTestOne", "numberTestOne")
    private val mockItemTwo = ToDoItem(1, "titleTestTwo", "descriptionTestTwo", "numberTestTwo")
    private val mockItemThree = ToDoItem(2, "titleTestThree", "descriptionTestThree", "numberTestThree")
    private val mockItemFour = ToDoItem(0, "titleTestFour", "descriptionTestFour", "numberTestFour")

    private val mockList : List<ToDoItem> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup() {
        subject = MainViewModel(roomRepositoryMock)
    }

    @Test
    fun getAllData_success() {
        `when`(roomRepositoryMock.getAll()).thenReturn(mockList)
        subject.getAllData()
        val expectedResult = subject.toDoItemListResult.value?.size
        val firstItem = subject.toDoItemListResult.value?.get(0)
        assertEquals(2, expectedResult)
        assertEquals("titleTestOne", firstItem?.title)
        assertEquals("descriptionTestOne", firstItem?.description)
    }

    @Test
    fun insertItem_success() {
        `when`(roomRepositoryMock.getAll()).thenReturn(mockList)
        subject.getAllData()
        val expectedResult = subject.toDoItemListResult.value?.size
        assertEquals(2, expectedResult)

        subject.insertItem(mockItemThree)
        val expectedResultAfterInsert = subject.toDoItemListResult.value?.size
        val lastItem = subject.toDoItemListResult.value?.last()
        assertEquals(3, expectedResultAfterInsert)
        assertEquals("titleTestThree", lastItem?.title)
        assertEquals("descriptionTestThree", lastItem?.description)
    }

    @Test
    fun updateItem_success() {
        `when`(roomRepositoryMock.getAll()).thenReturn(mockList)
        subject.getAllData()

        subject.updateItem(mockItemFour)
        val updateItem = subject.toDoItemListResult.value?.first()
        assertEquals("titleTestFour", updateItem?.title)
        assertEquals("descriptionTestFour", updateItem?.description)
    }

    @Test
    fun deleteItem_success() {
        `when`(roomRepositoryMock.getAll()).thenReturn(mockList)
        subject.getAllData()
        val expectedResult = subject.toDoItemListResult.value?.size
        assertEquals(2, expectedResult)

        subject.deleteItem(mockItemOne)
        val expectedResultAfterDelete = subject.toDoItemListResult.value?.size
        assertEquals(1, expectedResultAfterDelete)
    }
}