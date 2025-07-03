package com.ajdev.getitdone.ui.todoscreen

import app.cash.turbine.test
import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.usecases.GetAllTaskUseCase
import com.ajdev.getitdone.domain.usecases.InsertTaskUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

class ToDoViewModelTest {

    @RelaxedMockK
    private lateinit var useCase: GetAllTaskUseCase

    @RelaxedMockK
    private lateinit var insertTaskUseCase: InsertTaskUseCase

    @InjectMockKs
    private lateinit var viewModel: ToDoViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        MockKAnnotations.init(this)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllTask should emit Success state with tasks`() = runTest {
        val tasks = listOf(TaskModel(0, "Tarea de prueba", false, LocalDate.now()))

        coEvery { useCase.invoke() } returns tasks

        viewModel.state.test {
            assert(awaitItem() is ToDoViewState.Loading)
            viewModel.getAllTask()
            assert(awaitItem() == ToDoViewState.Success(tasks))
            cancelAndIgnoreRemainingEvents()
        }
    }
}