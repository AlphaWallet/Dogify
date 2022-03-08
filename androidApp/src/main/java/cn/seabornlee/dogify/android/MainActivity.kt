package cn.seabornlee.dogify.android

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.seabornlee.dogify.model.Breed
import cn.seabornlee.dogify.model.FetchBreedsUseCase
import cn.seabornlee.dogify.model.GetBreedsUseCase
import cn.seabornlee.dogify.model.ToggleFavouriteStateUseCase
import kotlinx.coroutines.launch

suspend fun greet() =
    "${FetchBreedsUseCase().invoke()}\n" +
            "${GetBreedsUseCase().invoke()}\n" +
            "${
                ToggleFavouriteStateUseCase().invoke(
                    Breed("toggle favourite state test", "")
                )
            }\n"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tv: TextView =
            findViewById(R.id.text_view)
        lifecycleScope.launch {
            tv.text = greet()
        }
    }
}