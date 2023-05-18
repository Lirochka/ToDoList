package com.example.todolist

import com.example.todolist.data.RoomRepositoryImpl
import com.example.todolist.model.ToDoItem
import com.example.todolist.room.ToDoDao
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class RoomRepositoryImplTest {
    private lateinit var subject: RoomRepositoryImpl

    private val toDoDaoMock : ToDoDao = mock()

    private val mockItemOne = ToDoItem(0, "titleTestOne", "descriptionTestOne", "numberTestOne")
    private val mockItemTwo = ToDoItem(1, "titleTestTwo", "descriptionTestTwo", "numberTestTwo")

    private val mockList : List<ToDoItem> = listOf(
        mockItemOne,
        mockItemTwo
    )

    @Before
    fun setup() {
        subject = RoomRepositoryImpl(toDoDaoMock)
    }

    @Test
    fun getAll_success() {
        Mockito.`when`(toDoDaoMock.getAll()).thenReturn(mockList)
        val expectedResult = subject.getAll()
        TestCase.assertEquals(2, expectedResult.size)
    }

    @Test
    fun insertItem_success() {
        subject.insertItem(mockItemOne)
        verify(toDoDaoMock).insertItem(mockItemOne)
    }

    @Test
    fun updateItem_success() {
        subject.updateItem(mockItemOne)
        verify(toDoDaoMock).updateItem(mockItemOne)
    }

    @Test
    fun deleteItem_success() {
        subject.deleteItem(mockItemOne)
        verify(toDoDaoMock).deleteItem(mockItemOne)
    }
}