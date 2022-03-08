package cn.seabornlee.dogify.android

import android.os.Bundle
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.lifecycle.lifecycleScope
import cn.seabornlee.dogify.model.Breed
import cn.seabornlee.dogify.model.FetchBreedsUseCase
import cn.seabornlee.dogify.model.GetBreedsUseCase
import cn.seabornlee.dogify.model.ToggleFavouriteStateUseCase
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                MainScreen(viewModel)
            }
        }
    }
}