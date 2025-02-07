package com.example.unscramble.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface WordDao {
    @Insert
    suspend fun insertWord(word: Word)


//    @Query("SELECT * FROM words_table")
//    suspend fun getAllWords(): List<Word>

    //@Query("SELECT * FROM words_table WHERE word NOT IN (:usedWords) ORDER BY RANDOM() LIMIT 1")
    @Query("SELECT * FROM words_table ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomWord(): Word?

    @Query("DELETE FROM words_table")
    suspend fun deleteAllWords()
}