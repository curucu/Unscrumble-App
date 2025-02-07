package com.example.unscramble.data

class WordRepository(private val wordDao: WordDao) {
    suspend fun populateDatabase(){
        val initialWords: List<Word> = listOf(
            Word(word = "animal"),
            Word(word = "auto"),
            Word(word = "anecdote"),
            Word(word = "alphabet"),
            Word(word = "all"),
            Word(word = "awesome"),
            Word(word = "arise"),
            Word(word = "balloon"),
            Word(word = "basket"),
            Word(word = "bench"),
            Word(word = "best"),
            Word(word = "birthday"),
            Word(word = "book"),
            Word(word = "briefcase"),
            Word(word = "camera"),
            Word(word = "camping"),
            Word(word = "candle"),
            Word(word = "cat"),
            Word(word = "cauliflower"),
            Word(word = "chat"),
            Word(word = "children"),
            Word(word = "class"),
            Word(word = "classic"),
            Word(word = "classroom"),
            Word(word = "coffee"),
            Word(word = "colorful"),
            Word(word = "cookie"),
            Word(word = "creative"),
            Word(word = "cruise"),
            Word(word = "dance"),
            Word(word = "daytime"),
            Word(word = "dinosaur"),
            Word(word = "doorknob"),
            Word(word = "dine"),
            Word(word = "dream"),
            Word(word = "dusk"),
            Word(word = "eating"),
            Word(word = "elephant"),
            Word(word = "emerald"),
            Word(word = "eerie"),
            Word(word = "electric"),
            Word(word = "finish"),
            Word(word = "flowers"),
            Word(word = "follow"),
            Word(word = "fox"),
            Word(word = "frame"),
            Word(word = "free"),
            Word(word = "frequent"),
            Word(word = "funnel"),
            Word(word = "green"),
            Word(word = "guitar"),
            Word(word = "grocery"),
            Word(word = "glass"),
            Word(word = "great"),
            Word(word = "giggle"),
            Word(word = "haircut"),
            Word(word = "half"),
            Word(word = "homemade"),
            Word(word = "happen"),
            Word(word = "honey"),
            Word(word = "hurry"),
            Word(word = "hundred"),
            Word(word = "ice"),
            Word(word = "igloo"),
            Word(word = "invest"),
            Word(word = "invite"),
            Word(word = "icon"),
            Word(word = "introduce"),
            Word(word = "joke"),
            Word(word = "jovial"),
            Word(word = "journal"),
            Word(word = "jump"),
            Word(word = "join"),
            Word(word = "kangaroo"),
            Word(word = "keyboard"),
            Word(word = "kitchen"),
            Word(word = "koala"),
            Word(word = "kind"),
            Word(word = "kaleidoscope"),
            Word(word = "landscape"),
            Word(word = "late"),
            Word(word = "laugh"),
            Word(word = "learning"),
            Word(word = "lemon"),
            Word(word = "letter"),
            Word(word = "lily"),
            Word(word = "magazine"),
            Word(word = "marine"),
            Word(word = "marshmallow"),
            Word(word = "maze"),
            Word(word = "meditate"),
            Word(word = "melody"),
            Word(word = "minute"),
            Word(word = "monument"),
            Word(word = "moon"),
            Word(word = "motorcycle"),
            Word(word = "mountain"),
            Word(word = "music"),
            Word(word = "north"),
            Word(word = "nose"),
            Word(word = "night"),
            Word(word = "name"),
            Word(word = "never"),
            Word(word = "negotiate"),
            Word(word = "number"),
            Word(word = "opposite"),
            Word(word = "octopus"),
            Word(word = "oak"),
            Word(word = "order"),
            Word(word = "open"),
            Word(word = "polar"),
            Word(word = "pack"),
            Word(word = "painting"),
            Word(word = "person"),
            Word(word = "picnic"),
            Word(word = "pillow"),
            Word(word = "pizza"),
            Word(word = "podcast"),
            Word(word = "presentation"),
            Word(word = "puppy"),
            Word(word = "puzzle"),
            Word(word = "recipe"),
            Word(word = "release"),
            Word(word = "restaurant"),
            Word(word = "revolve"),
            Word(word = "rewind"),
            Word(word = "room"),
            Word(word = "run"),
            Word(word = "secret"),
            Word(word = "seed"),
            Word(word = "ship"),
            Word(word = "shirt"),
            Word(word = "should"),
            Word(word = "small"),
            Word(word = "spaceship"),
            Word(word = "stargazing"),
            Word(word = "skill"),
            Word(word = "street"),
            Word(word = "style"),
            Word(word = "sunrise"),
            Word(word = "taxi"),
            Word(word = "tidy"),
            Word(word = "timer"),
            Word(word = "together"),
            Word(word = "tooth"),
            Word(word = "tourist"),
            Word(word = "travel"),
            Word(word = "truck"),
            Word(word = "under"),
            Word(word = "useful"),
            Word(word = "unicorn"),
            Word(word = "unique"),
            Word(word = "uplift"),
            Word(word = "uniform"),
            Word(word = "vase"),
            Word(word = "violin"),
            Word(word = "visitor"),
            Word(word = "vision"),
            Word(word = "volume"),
            Word(word = "view"),
            Word(word = "walrus"),
            Word(word = "wander"),
            Word(word = "world"),
            Word(word = "winter"),
            Word(word = "well"),
            Word(word = "whirlwind"),
            Word(word = "x-ray"),
            Word(word = "xylophone"),
            Word(word = "yoga"),
            Word(word = "yogurt"),
            Word(word = "yoyo"),
            Word(word = "you"),
            Word(word = "year"),
            Word(word = "yummy"),
            Word(word = "zebra"),
            Word(word = "zigzag"),
            Word(word = "zoology"),
            Word(word = "zone"),
            Word(word = "zeal")
        )
        initialWords.forEach { wordDao.insertWord(it) }



    }
   suspend fun getRandomWord(): Word {
       val randomWord = wordDao.getRandomWord()
       if (randomWord != null) {
           return randomWord
       } else {
           throw NoSuchElementException("No words available in the database")
       }
   }
}