package com.androiddevs.ucanbit.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.androiddevs.ucanbit.getOrAwaitValue
import com.androiddevs.ucanbit.models.CoinsResponseItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CoinsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CoinDatabase
    private lateinit var dao: CoinDao

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CoinDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.getCoinDao()

    }

    @After
    fun teardown(){
        database.close()
    }



    @Test
    fun insertCoin() = runBlockingTest {
        val coinItem = CoinsResponseItem(
            "bitcoin",
            132.00,3123.32,"kdalsk",23.23,232.23,"adsa",132.00,2.2,222,2.2,"https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
            "2021-04-27T22:22:49.424Z",132.2,123,2.2,2.2,2,23.2,"chtacoin",123.2,123.2,123.2,232.2,232.2,"cht",22.2,232

            )
        dao.upsert(coinItem)

        val allCoins = dao.getAllCoins().getOrAwaitValue()



        assertThat(allCoins).contains(coinItem)


    }

    @Test
    fun deleteCoin()  = runBlockingTest {
        val coinItem = CoinsResponseItem(
            "bitcoin",
            132.00,3123.32,"kdalsk",23.23,232.23,"adsa",132.00,2.2,222,2.2,"https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579",
            "2021-04-27T22:22:49.424Z",132.2,123,2.2,2.2,2,23.2,"chtacoin",123.2,123.2,123.2,232.2,232.2,"cht",22.2,232

        )
        dao.upsert(coinItem)
        dao.deleteCoin(coinItem)

        val allCoins = dao.getAllCoins().getOrAwaitValue()

        assertThat(allCoins).doesNotContain(coinItem)


    }

}