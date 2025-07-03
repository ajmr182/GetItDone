package com.ajdev.getitdone.domain.usecases

import com.ajdev.getitdone.domain.model.TaskModel
import com.ajdev.getitdone.domain.repository.TaskRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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

class GetAllTaskUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: TaskRepository

    @InjectMockKs
    private lateinit var useCase: GetAllTaskUseCase

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
    fun `invoke should return a list of task from repository`() = runTest {
        val listOfTask = listOf<TaskModel>(
            TaskModel(
                id = 0,
                title = "tarea de prueba 1",
                isDone = false,
                date = LocalDate.now()
            )
        )

        coEvery { repository.getAllTasks() } returns listOfTask
        val result: List<TaskModel> = useCase()

        assert(listOfTask == result)
        coVerify(exactly = 1) { repository.getAllTasks() }
    }
}