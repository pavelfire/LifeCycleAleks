package com.vk.directop.lifecyclealeks.rx_java_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.vk.directop.lifecyclealeks.R
import com.vk.directop.lifecyclealeks.recycler_user.UsersListFragment
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class RxJavaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_java)
        callRxJavaExample()

        if (savedInstanceState == null){
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, DebounceExampleFragment())
                .commit()
        }
    }

    private fun callRxJavaExample(){

        Observable.just("1", "2", "3", "4", "5", "6", "7")
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation()) // переключаемся на другой поток
            // если вместо flatMap использовать concatMap следующий объект будет обрабатываться
            // только после того как будет обработан предыдущий
            // switchMap вернёт только последний элемент
            .flatMap {  query ->
                // имитируем отправку запроса на сервер
                sendServerRequest(query)
            }
            .observeOn(AndroidSchedulers.mainThread()) // исполнение данных в главном потоке
            .subscribe{
                Log.d("TAG", "subscribe message: $it")
            }
    }

    private fun sendServerRequest(query: String): Observable<String> {
        val delay = Random.nextInt(7).toLong()
        return Observable.just("пришёл ответ поиска по запросу $query")
            .delay(delay, TimeUnit.SECONDS)
    }
}