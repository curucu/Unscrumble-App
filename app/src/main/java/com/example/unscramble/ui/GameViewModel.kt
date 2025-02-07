package com.example.unscramble.ui


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.unscramble.data.MAX_NO_OF_ATTEMPTS
import com.example.unscramble.data.MAX_NO_OF_WORDS
import com.example.unscramble.data.SCORE_INCREASE
import com.example.unscramble.data.WordRepository
import com.example.unscramble.data.allWords
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GameViewModel(private val repository: WordRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(GameUiState())
    val uiState : StateFlow<GameUiState> = _uiState.asStateFlow()
    //private lateinit var currentWord: String
    private lateinit var currentWord: String
    // Set of words used in the game
    //private var usedWords: MutableSet<String> = mutableSetOf()

    private val usedWords = mutableListOf<String>()

    var userGuess by mutableStateOf("")
        private set

    init {
        viewModelScope.launch { repository.populateDatabase() }
        resetGame()
    }


    private fun pickRandomWordAndShuffle(callback: (String) -> Unit) {
        // Continue picking up a new random word until you get one that hasn't been used before
//        currentWord = allWords.random()
//        if (usedWords.contains(currentWord)) {
//            return pickRandomWordAndShuffle()
//        } else {
//            usedWords.add(currentWord)
//            Log.d("GameViewModel", currentWord)
//            return shuffleCurrentWord(currentWord)
//        }
//        viewModelScope.launch {
//            Log.d("GameViewModel", usedWords.toString());
//            //currentWord = repository.getRandomWord(usedWords.toList()).word
//            usedWords.add(currentWord)
//            val shuffleWord = shuffleCurrentWord(currentWord)
//            callback(shuffleWord)
//        }

        viewModelScope.launch {
            try {
                // Obtenha uma palavra aleatória do repositório
                currentWord = repository.getRandomWord().word
                usedWords.add(currentWord)

                // Embaralhe a palavra e envie de volta no callback
                val shuffleWord = shuffleCurrentWord(currentWord)
                callback(shuffleWord)
            } catch (e: Exception) {
                Log.e("GameViewModel", "Erro ao buscar palavra: ${e.message}")
            }
        }

    }

    private fun shuffleCurrentWord(word: String): String {
        val tempWord = word.toCharArray()
        // Scramble the word
        tempWord.shuffle()
        while (String(tempWord).equals(word)) {
            tempWord.shuffle()
        }
        return String(tempWord)
    }

    fun updateUserGuess(guessedWord : String){
        userGuess = guessedWord
    }

    fun skipWord(){
        updateGameState(_uiState.value.score)
        updateUserGuess("")
    }

    fun updateGameState(updatedScore: Int){
       if(usedWords.size == MAX_NO_OF_WORDS){
            _uiState.update { currentState ->
                currentState.copy(
                    isGuessedWordWrong = false,
                    isGameOver = true,
                    score = updatedScore,
                )
            }
        }
        else{
            pickRandomWordAndShuffle { shuffleWord ->
                _uiState.update { currentState ->
                    currentState.copy(
                        isGuessedWordWrong = false,
                        currentScrambledWord = shuffleWord,
                        score = updatedScore,
                        currentWordCount = currentState.currentWordCount.inc(),
                        currentAttempt = 0
                    )
                }
            }

        }

    }

    fun checkUserGuess(){
        if(userGuess.equals(currentWord, ignoreCase = true)){
            val updatedScore = _uiState.value.score.plus(SCORE_INCREASE)
            updateGameState(updatedScore)
        }else{
            val newAttemptCount = _uiState.value.currentAttempt.inc()
            if(newAttemptCount == MAX_NO_OF_ATTEMPTS){
                skipWord()
            }
            else{
                _uiState.update { currentState -> currentState.copy(
                    isGuessedWordWrong = true,
                    currentAttempt = newAttemptCount) }
            }

        }
        updateUserGuess("")
    }

    fun resetGame() {
        Log.d("GameViewModel", "entrei aqui")
        usedWords.clear()
        pickRandomWordAndShuffle { scrambledWord ->
            _uiState.value = GameUiState(currentScrambledWord = scrambledWord)
        }

    }

}