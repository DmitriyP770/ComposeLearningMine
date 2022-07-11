package com.example.composelearning

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
fun HomeScreen (modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StatefulCounter()
        WellnessTaskList()
    }

}
@Composable
fun StatefulCounter() {
    var count by rememberSaveable {   mutableStateOf(0)    }
    var canShowTask by rememberSaveable {        mutableStateOf(false)    }
    StatelessCounter(count = count, onIncrement = { count++}, onClrear = { count=0 })
}

@Composable
fun StatelessCounter(count: Int, onIncrement: ()->Unit, onClrear: ()->Unit ,modifier: Modifier = Modifier) {
    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally)
    {
        if (count > 0){
            Text(text = "Number of drinked glasses is $count")


        }
        Spacer(modifier = Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.padding(20.dp)) {
            Button(onClick = onIncrement, enabled = count<10) {
                Text(text = "Add one")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = onClrear, enabled = count>0) {
                Text(text = "Clear tracker")
            }
        }
}
}
//@Composable
//fun WaterCounter(modifier: Modifier = Modifier) {
////rememberSaveable for saving state when activity recreates
//// by remember - only save state when fun recomposing, not when activity recreates
//    var count by rememberSaveable {   mutableStateOf(0)    }
//    var canShowTask by rememberSaveable {        mutableStateOf(false)    }
//
//    Column(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally)
//    {
//        if (count > 0){
//            Text(text = "Number of drinked glasses is $count")
//            canShowTask = true
//
//        }
//        Spacer(modifier = Modifier.height(8.dp))
//        Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.padding(20.dp)) {
//            Button(onClick = { count++ }, enabled = count<10) {
//                Text(text = "Add one")
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Button(onClick = { count=0 }, enabled = count>0) {
//                Text(text = "Clear tracker")
//            }
//        }
//
//        if (canShowTask){
//            Spacer(modifier = Modifier.height(8.dp))
//
//        }
//    }
//
//
//}

@Composable
 fun WelnessTaskStatefull(
    name:String
 ) {
  var isCheked by rememberSaveable {  mutableStateOf(false)     }

        WellnessTaskStateless(taskName = name, onClose = { /*TODO*/ }, isCheked = isCheked, onCheck ={isCheked = !isCheked } )
    }

@Composable
fun WellnessTaskStateless(
    taskName: String,
    onClose: () -> Unit,
    isCheked: Boolean,
    onCheck:(Boolean)-> Unit,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = taskName,
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp),
        )
        Checkbox(checked = isCheked, onCheckedChange = onCheck)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }
    }
}
data class WellnessTask(val id: Int, val label: String)
fun getWellnessTasks() = List(30) { i -> WellnessTask(i, "Task # $i") }
fun getTasks():List<WellnessTask>{
    var list : ArrayList<WellnessTask> = ArrayList()
    for (i in 1..30){
        list.add(WellnessTask(i, "Task #$i"))

    }
    return list
}
@Composable
fun WellnessTaskList(
    modifier: Modifier = Modifier,
    list: List<WellnessTask> = remember { getTasks().toMutableStateList() }

) {
    LazyColumn{
        items(list){
            item ->  WelnessTaskStatefull(name = item.label)
        }
    }
}

