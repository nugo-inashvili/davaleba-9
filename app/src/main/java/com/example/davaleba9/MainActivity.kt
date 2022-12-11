package com.example.davaleba9
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

import androidx.core.content.getSystemService
class MainActivity : AppCompatActivity() {
    private lateinit var button:Button
    private lateinit var button2:Button
    companion object{
        private const val CHANNEL_ID="MY_CHANNEL"
    }
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button=findViewById(R.id.button)
        button2=findViewById(R.id.button2)
        button2.setOnClickListener {
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.cancel(1)
        }
        this.createNotificationChannel()
//        val resultIntent=Intent(this,MainActivity::class.java)
//        intent.putExtra("NAME","qwertyuiop")
//        val snoozeIntent=Intent(this,MainActivity::class.java)
//        val resultPendingIntent:PendingIntent?=TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack((resultIntent)
//                    getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        }
//
//        val snoozePendingIntent:PendingIntent=TaskStackBuilder.create(this).run {
//            addNextIntentWithParentStack((snoozeIntent)
//                    getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
//        }
        val notification=NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.img))
            .setContentTitle("Title")
            .setContentText("Message")
            //.setContentIntent(resultPendingIntent)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.img)))
            //.addAction(R.drawable.ic_launcher_background,"click me",snoozePendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        var counter=1
        button.setOnClickListener {
            val nbc=NotificationManagerCompat.from(this)
            nbc.notify(counter,notification.build())
            counter++
        }
    }
    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val name="NAME"
            val descriptionText="Desc"
            val importance=NotificationManager.IMPORTANCE_DEFAULT
            val channel=NotificationChannel(CHANNEL_ID,name, importance)
            channel.description=descriptionText
            val notificationManager:NotificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}