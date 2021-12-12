package mycompany.example.highandlow

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 広告
    lateinit var mAdView : AdView

    private val tag = "high and low"
    private  var yourCard = 0
    private  var droidCard = 0
    private  var hitCount = 0
    private  var loseCount = 0
    private  var gameStart = false
    private  var answered = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //広告
        val adView = AdView(this)

        adView.adSize = AdSize.SMART_BANNER

        adView.adUnitId = "ca-app-pub-3324255919208405/5842739411"
        // 本物ユニットID ca-app-pub-3324255919208405/5842739411
        // サンプル　ca-app-pub-3940256099942544/6300978111
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        MobileAds.initialize(this) {}

        highBtn.setOnClickListener {
            if ((gameStart && !answered)) {
                highAndLow('h')
            }
        }
        lowBtn.setOnClickListener {
            if ((gameStart && !answered)) {
                highAndLow('l')
            }
        }
        nextBtn.setOnClickListener {
            if (gameStart) {
                drawCard()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        hitCount = 0
        loseCount = 0
        hitText.text = getString(R.string.hit_text)
        loseText.text = getString(R.string.lose_text)
        gameStart = true
        drawCard()
    }

    private fun drawCard() {
        yourCardImage.setImageResource(R.drawable.z02)
        droidCarImage.setImageResource(R.drawable.z01)

        // IntRange.random()で乱数を生成
        yourCard = (1..13).random()
        Log.d(tag, "you:"+yourCard)
        when (yourCard) {
            1 -> yourCardImage.setImageResource(R.drawable.d01)
            2 -> yourCardImage.setImageResource(R.drawable.d02)
            3 -> yourCardImage.setImageResource(R.drawable.d03)
            4 -> yourCardImage.setImageResource(R.drawable.d04)
            5 -> yourCardImage.setImageResource(R.drawable.d05)
            6 -> yourCardImage.setImageResource(R.drawable.d06)
            7 -> yourCardImage.setImageResource(R.drawable.d07)
            8 -> yourCardImage.setImageResource(R.drawable.d08)
            9 -> yourCardImage.setImageResource(R.drawable.d09)
            10 -> yourCardImage.setImageResource(R.drawable.d10)
            11 -> yourCardImage.setImageResource(R.drawable.d11)
            12 -> yourCardImage.setImageResource(R.drawable.d12)
            13 -> yourCardImage.setImageResource(R.drawable.d13)
        }
        droidCard = (1..13).random()
        Log.d(tag, "droid:"+droidCard)
        answered = false
    }

    private fun highAndLow(answer:Char) {
        showDroidCard()
        answered = true
        val balance = droidCard - yourCard
        if (balance == 0) {
            // when even do nothing
        }else if ((balance > 0 && answer == 'h')) {
            hitCount++
            resultText.text = "You Win!"
            hitText.text = getString(R.string.hit_text) + " " + hitCount
        }else if ((balance < 0 && answer == 'l')) {
            hitCount++
            resultText.text = "You Win!"
            hitText.text = getString(R.string.hit_text) + " " + hitCount
        }else {
            loseCount++
            resultText.text = "You Low"
            loseText.text = getString(R.string.lose_text) + " " + loseCount
        }
        if (droidCard > yourCard) {
//            resultText.text = "Your Win!"
            gameStart = true
        }else if (yourCard > droidCard) {
//            resultText.text = "Your Low"
            gameStart = true
        }else if (droidCard == yourCard){
            resultText.text = "Draw"
        }else {
            // do nothing
        }
    }

    private fun showDroidCard() {
        when (droidCard) {
            1 -> droidCarImage.setImageResource(R.drawable.c01)
            2 -> droidCarImage.setImageResource(R.drawable.c02)
            3 -> droidCarImage.setImageResource(R.drawable.c03)
            4 -> droidCarImage.setImageResource(R.drawable.c04)
            5 -> droidCarImage.setImageResource(R.drawable.c05)
            6 -> droidCarImage.setImageResource(R.drawable.c06)
            7 -> droidCarImage.setImageResource(R.drawable.c07)
            8 -> droidCarImage.setImageResource(R.drawable.c08)
            9 -> droidCarImage.setImageResource(R.drawable.c09)
            10 -> droidCarImage.setImageResource(R.drawable.c10)
            11 -> droidCarImage.setImageResource(R.drawable.c11)
            12 -> droidCarImage.setImageResource(R.drawable.c12)
            13 -> droidCarImage.setImageResource(R.drawable.c13)
        }
    }
}