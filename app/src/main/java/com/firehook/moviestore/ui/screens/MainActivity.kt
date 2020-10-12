package com.firehook.moviestore.ui.screens

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.firehook.moviestore.MovieStoreApplication
import com.firehook.moviestore.R
import com.firehook.moviestore.di.modules.database.Movie
import com.firehook.moviestore.di.modules.database.MovieStoreDatabase
import com.firehook.moviestore.di.modules.network.MovieEntity
import com.firehook.moviestore.di.modules.network.MovieStoreService
import io.reactivex.Completable
import io.reactivex.CompletableObserver
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_home.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject lateinit var movieStoreService: MovieStoreService
    @Inject lateinit var movieDataBase: MovieStoreDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MovieStoreApplication.appComponent.inject(this)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close)

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        movieStoreService.getMovies()
            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<List<MovieEntity>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

                override fun onNext(t: List<MovieEntity>) {

                    val bundle = Bundle()
                    bundle.putParcelableArrayList(MOVIE_EXTRA, ArrayList(t))

                    val fragment = MovieListFragment()
                    fragment.arguments = bundle

                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.container, fragment)
                        .commit()
                }

            })

//        Timber.d("mainActivity $movieDataBase")
//
//        val movie1 = Movie()
//        movie1.itemId = "10056"
//        movie1.name = "IronMan"
//        movie1.image = "http://s8.hostingkartinok.com/uploads/images/2016/03/b70762d52599ffc44dc7539bf57baa1c.jpg"
//        movie1.time = "1457018867393"
//        movie1.description = "Iron Man is a superhero who wears a suit of armor. His alter ego is Tony Stark. He was created by Stan Lee, Jack Kirby and Larry Lieber for Marvel Comics in Tales of Suspense #39 in the year 1963 and appears in their comic books. He is also one of the main protagonists in the Marvel Cinematic Universe. In the movies and the earlier comic books, Tony Stark is a human. Outside the suit, he does not have any superpowers, however he has an enormous QI, in fact his intelligence gives to him the sufficient power instead of physycal superpowers. He made the suit himself, and nobody else can usually control it. Iron Man can fly and shoot beams from his hands using special technology called \\\"repulsors\\\" in his boots and gloves. He is not hurt by most weapons like guns and cannons. There are many versions of the Iron Man suit, because Stark keeps making improvements.In the later comic books, Stark took an experimental virus called \\\"STD\\\" which allowed him to control his suit with his mind and summon it wherever he was. Stark would eventually develop an armor that he could store in his body. This armor was known as the \\\"Bleeding Edge Model 37\\\"."
//
//        val movie2 = Movie()
//        movie2.itemId = "10054"
//        movie2.name = "Deadpool"
//        movie2.image = "http://s8.hostingkartinok.com/uploads/images/2016/03/3d23f908f56428ac97840acae92c1f50.jpg"
//        movie2.time = "1457018837658"
//        movie2.description = "Deadpool (Wade Winston Wilson) is a fictional character appearing in American comic books published by Marvel Comics. Created by writer Fabian Nicieza and artist/writer Rob Liefeld, the character first appeared in The New Mutants #98 (cover-dated February 1991). Initially Deadpool was depicted as a supervillain when he made his first appearance in The New Mutants and later in issues of X-Force, but later evolved into his more recognizable antiheroic persona. Deadpool, whose real name is Wade Wilson, is a disfigured mercenary with the superhuman ability of an accelerated healing factor and physical prowess. The character is known as the \\\"Merc with a Mouth\\\" because of his tendency to talk and joke constantly, including breaking the fourth wall for humorous effect and running gags.The character's popularity has seen him featured in numerous forms of other media. In the 2004 series Cable & Deadpool, he refers to his own scarred appearance as \\\"Ryan Reynolds crossed with a Shar-Pei\\\" (though in the comic, Reynolds' name is misspelled as \\\"Renolds\\\".)Reynolds himself would eventually portray the character in the X-Men film series, appearing in X-Men Origins: Wolverine (2009), Deadpool (2016), and its sequel Deadpool 2 (2018). He is slated to continue playing the character in the Marvel Cinematic Universe."
//
//        val movie3 = Movie()
//        movie3.itemId = "10028"
//        movie3.name = "Thor"
//        movie3.image = "http://s8.hostingkartinok.com/uploads/images/2016/03/44819c5cf496a059797d43ffcab07508.jpg"
//        movie3.time = "1457018877305"
//        movie3.description = "Thor Odinson is the Asgardian God of Thunder, superhero, self-proclaimed protector of Earth and the king of Asgard. Thor made a name for himself as the mightiest warrior on his homeworld and subsequently became well known for his actions on Earth, which included acting as a founding member of the Avengers. He is the son of Odin Borson and Frigga, and the adopted brother of Loki Laufeyson. Thor wielded the mystical war hammer MjГ¶lnir, which channeled his God of Thunder abilities until it was destroyed by his sister Hela. In an attempt to flee his sister, Thor found himself stranded on Sakaar where he was forced to be a champion in the Grandmaster's games. After escaping Sakaar, he brought about RagnarГ¶k with the help of the Revengers and destroyed his home and Hela with it. He lead the Asgardian refugees away from the destruction in hunt for a new home."
//
//        val movie4 = Movie()
//        movie4.itemId = "10048"
//        movie4.name = "Hulk"
//        movie4.image = "http://s8.hostingkartinok.com/uploads/images/2016/03/dd54db9d3ea626ece12f4123d3b63306.jpg"
//        movie4.time = "1457018788101"
//        movie4.description = "The Hulk is a fictional superhero appearing in publications by the American publisher Marvel Comics. Created by writer Stan Lee and artist Jack Kirby, the character first appeared in the debut issue of The Incredible Hulk (May 1962). In his comic book appearances, the character is both the Hulk, a green-skinned, hulking and muscular humanoid possessing a vast degree of physical strength, and his alter ego Dr. Robert Bruce Banner, a physically weak, socially withdrawn, and emotionally reserved physicist, the two existing as independent personalities and resenting of the other.\n" +
//                "Following his accidental exposure to gamma rays saving the life of Rick Jones during the detonation of an experimental bomb, Banner is physically transformed into the Hulk when subjected to emotional stress, at or against his will, often leading to destructive rampages and conflicts that complicate Banner's civilian life. The Hulk's level of strength is normally conveyed as proportionate to his level of anger. Commonly portrayed as a raging savage, the Hulk has been represented with other personalities based on Banner's fractured psyche, from a mindless, destructive force, to a brilliant warrior, or genius scientist in his own right. Despite both Hulk and Banner's desire for solitude, the character has a large supporting cast, including Banner's lover Betty Ross, his best friend Rick Jones, his cousin She-Hulk, therapist and ally Doc Samson, and his co-founders of the superhero team the Avengers, etc. However, his uncontrollable power has brought him into conflict with his fellow heroes and others. Despite this he tries his best to do what's right while battling villains such as Leader, Abomination, Absorbing Man and more."
//
//        Completable.fromAction{movieDataBase.movieDao().insertMovie(movie1)}
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : CompletableObserver {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onComplete() {
//                    Timber.d("SUCCESS")
//                }
//
//                override fun onError(e: Throwable) {
//                    Timber.d("ERROR: %s", e.message)
//                }
//
//            })
//
//        movieDataBase.movieDao().getAll()
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : SingleObserver<List<Movie>> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onSuccess(t: List<Movie>) {
//                    Timber.d("list: %s", t)
//                }
//
//                override fun onError(e: Throwable) {
//                    Timber.d("err: %s", e.message)
//                }
//
//            })
//
//        movieStoreService.getMovies()
//            .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object : Observer<List<MovieEntity>> {
//                override fun onSubscribe(d: Disposable) {
//
//                }
//
//                override fun onError(e: Throwable) {
//
//                }
//
//                override fun onComplete() {
//
//                }
//
//                override fun onNext(t: List<MovieEntity>) {
//                    Timber.d("network list: %s", t)
//                }
//
//            })
//
//        movieDataBase.movieDao().insertMovie(movie1)
//        movieDataBase.movieDao().insertMovie(movie2)
//        movieDataBase.movieDao().insertMovie(movie3)
//        movieDataBase.movieDao().insertMovie(movie4)

    }

    companion object {
        const val MOVIE_EXTRA = "movie"
    }
}