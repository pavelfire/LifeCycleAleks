package com.vk.directop.lifecyclealeks

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.disposables.Disposable

class RxCreateExample {

}

fun main() {

    val single = Single.just("string example")

    val itemsList =
        listOf("first observable item", "second observable item", "third observable item")
    val fromObservable = Observable.fromIterable(itemsList)

    // этот метод равнозначен предыдущим двум строкам, просто тут сразу внутрь добавляются элементы
    val justObservable = Observable.just(
        "first observable item",
        "second observable item",
        "third observable item"
    )

    val flowable =
        Flowable.just("flowable first item", "flowable second item", "flowable third item")

    val createObservable = Observable.create<String> { emitter ->
        emitter.onNext("observable first item")
        emitter.onNext("observable second item")
        //emitter.onError() // прервёт цепочку вызовов
        emitter.onNext("observable third item")
        emitter.onComplete()
    }

    observableExample()

}

fun observableExample() {
    val observable = Observable.create<String> { emitter ->
        emitter.onNext("observable first item")
        emitter.onNext("observable second item")
        emitter.onNext("observable third item")
        emitter.onComplete()
    }

    val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable) {
            println("onSubscribe " + Thread.currentThread().name)
        }

        override fun onNext(t: String) {
            println(t + " " + Thread.currentThread().name)
        }

        override fun onError(e: Throwable) {
            println("onError $e")
        }

        override fun onComplete() {
            println("onComplete " + Thread.currentThread().name)
        }
    }

    observable
        .subscribe(observer)

//    observable.observeOn(Schedulers.io())
//        .subscribe(observer)
}