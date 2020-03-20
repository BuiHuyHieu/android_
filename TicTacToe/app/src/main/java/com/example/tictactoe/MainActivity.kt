package com.example.tictactoe

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    val TAG="compile"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }


    fun clickButton(view : View) {
        val buttonChoice = view as Button?
        var cellID:Int=0
        when(buttonChoice)
        {
            button_1-> cellID=1
            button_2-> cellID=2
            button_3-> cellID=3
            button_4-> cellID=4
            button_5-> cellID=5
            button_6-> cellID=6
            button_7-> cellID=7
            button_8-> cellID=8
            button_9-> cellID=9
        }
        playGame(buttonChoice,cellID)

    }

    val Player_1 = ArrayList<Int>()
    val Player_2 = ArrayList<Int>()

    var turnPlayer:Int = 1;

    private fun playGame(button: Button?, cellID: Int) {

            if(turnPlayer==1)
            {
                button!!.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCellPlayer_1))
                button.text = "X"
                button.setTextColor(Color.BLACK)
                Player_1.add(cellID)
                turnPlayer=2
                button.isEnabled = false;
                if(checkPlayerWinner(Player_1))
                Toast.makeText(this,"Player_1 Win",LENGTH_LONG).show();
                AutoPlay()
            }
        else
        {
            button!!.setBackgroundColor(ContextCompat.getColor(this,R.color.colorCellPlayer_2))
            button.text = "O"
            button.setTextColor(Color.BLACK)
            Player_2.add(cellID)
            turnPlayer=1
            button.isEnabled = false;
            if(checkPlayerWinner(Player_2))
            Toast.makeText(this,"Player_2 Win",LENGTH_LONG).show();
        }


    }

    private fun AutoPlay() {
        val emptyCellID = ArrayList<Int>()
        var button: Button? = null
        for (item in 1..9) {
            if (!Player_1.contains(item) && !Player_2.contains(item))
                emptyCellID.add(item)
        }
        if (emptyCellID.size == 0) {

        } else {
            Log.d(TAG, emptyCellID.toString())
            val randomIndex = Random.nextInt(emptyCellID.size)
            val cellID = emptyCellID[randomIndex]
            when (cellID) {
                1 -> button = button_1
                2 -> button = button_2
                3 -> button = button_3
                4 -> button = button_4
                5 -> button = button_5
                6 -> button = button_6
                7 -> button = button_7
                8 -> button = button_8
                9 -> button = button_9
            }
            playGame(button, cellID)
        }
    }

    fun checkPlayerWinner(arrayList:ArrayList<Int>):Boolean
    {
        if( arrayList.contains(1)&&arrayList.contains(2)&&arrayList.contains(3)||
            arrayList.contains(4)&&arrayList.contains(5)&&arrayList.contains(6)||
            arrayList.contains(7)&&arrayList.contains(8)&&arrayList.contains(9)||
            arrayList.contains(1)&&arrayList.contains(4)&&arrayList.contains(7)||
            arrayList.contains(2)&&arrayList.contains(5)&&arrayList.contains(8)||
            arrayList.contains(3)&&arrayList.contains(6)&&arrayList.contains(9)||
            arrayList.contains(1)&&arrayList.contains(5)&&arrayList.contains(9)||
            arrayList.contains(3)&&arrayList.contains(5)&&arrayList.contains(7)
                )
        {
            endGame();
            return true
        };
        return false;
    }

    private fun endGame() {
        button_1.isEnabled = false
        button_2.isEnabled = false
        button_3.isEnabled = false
        button_4.isEnabled = false
        button_5.isEnabled = false
        button_6.isEnabled = false
        button_7.isEnabled = false
        button_8.isEnabled = false
        button_9.isEnabled = false
    }



}


