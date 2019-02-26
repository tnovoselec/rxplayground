import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import java.util.concurrent.TimeUnit

object Playground {


    fun processLists(firstList: List<Int>, secondList: List<Int>): Single<List<Int>> {
        val l1 = Observable.just(firstList)
            .delay(1, TimeUnit.SECONDS)

        val l2 = Observable.just(secondList)
            .delay(3, TimeUnit.SECONDS)
        return Observable
            .zip(l1, l2, BiFunction<List<Int>, List<Int>, List<Int>> { first, second -> mergeLists(first, second) })
            .flatMap { Observable.fromIterable(it) }
            .distinct()
            .filter { it % 2 == 0 }
            .sorted { first, second -> second - first }
            .toList()
    }

    private fun <T> mergeLists(first: List<T>, second: List<T>): List<T> {
        val result = mutableListOf<T>()
        result.addAll(first)
        result.addAll(second)
        return result
    }


}