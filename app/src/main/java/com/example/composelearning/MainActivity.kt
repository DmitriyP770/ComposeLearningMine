package com.example.composelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composelearning.ui.theme.ComposeLearningTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var model = ViewModelProvider(this).get(MainVM::class.java)

        setContent {
            ComposeLearningTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   HomeScreen()
                }
            }
        }
    }
}


@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
//rememberSaveable for saving state when activity recreates
// by remember - only save state when fun recomposing, not when activity recreates
    var count by rememberSaveable {   mutableStateOf(0)    }
    var canShowTask by rememberSaveable {        mutableStateOf(false)    }

    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        if (count > 0){
            Text(text = "Number of drinked glasses is $count")
            canShowTask = true

        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.padding(20.dp)) {
            Button(onClick = { count++ }, enabled = count<10) {
                Text(text = "Add one")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { count=0 }, enabled = count>0) {
                Text(text = "Clear tracker")
            }
        }

        if (canShowTask){
            Spacer(modifier = Modifier.height(8.dp))

            WellnessTaskList(taskName = "Do something useful", onClose = { canShowTask = false })
        }
    }


}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    WaterCounter(modifier)
}

@Composable
fun WellnessTaskList(
    taskName: String,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = taskName, modifier = Modifier
            .weight(1f)
            .padding(start = 16.dp), )
       IconButton(onClick = onClose) {
           Icon(Icons.Filled.Close, contentDescription = "Close")
       }
    }
}