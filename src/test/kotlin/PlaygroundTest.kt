import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import java.util.concurrent.TimeUnit

class PlaygroundTest {


    @Test
    fun testProcessList() {

        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        val first = listOf(1, 1, 2, 3, 4, 5)
        val second = listOf(1, 3, 4, 5, 6, 7)

        val processedLists = Playground.processLists(first, second).test()

        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)


        processedLists.assertComplete()
        processedLists.assertValue(listOf(6, 4, 2))
    }
}