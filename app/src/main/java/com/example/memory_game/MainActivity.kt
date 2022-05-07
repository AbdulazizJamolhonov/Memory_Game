package com.example.memory_game

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {
    lateinit var timer: CountDownTimer
    private val listImgOpenClose =
        arrayOf(false, false, false, false, false, false, false, false, false, false, false, false)
    private val listImageIndex = arrayOfNulls<Int>(5)
    private val listImageId = arrayOfNulls<Int>(5)
    private var openImage = 0
    private var finish = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var index = 0L
        when (MyData.index) {
            1 -> index = 20000
            2 -> index = 17000
            3 -> index = 15000
        }

        timer = object : CountDownTimer(index, 1000) {
            override fun onTick(finish: Long) {
                txt_time.text = "${finish / 1000}"
            }

            override fun onFinish() {
                if (listImgOpenClose[0] && listImgOpenClose[1] && listImgOpenClose[2] && listImgOpenClose[3] && listImgOpenClose[4] && listImgOpenClose[5] && listImgOpenClose[6] && listImgOpenClose[7] && listImgOpenClose[8] && listImgOpenClose[9] && listImgOpenClose[10] && listImgOpenClose[11]) {
                    txt_time.text = "You won"
                } else {
                    txt_time.text = "You lost"
                }
                finish = false
                exit.visibility = View.VISIBLE
            }
        }

        exit.setOnClickListener { finish() }

        timer.start()
        starts()
    }

    private fun starts() {
        image_1.setOnClickListener { imageJoyla(image_1, R.drawable.apple, 0) }
        image_2.setOnClickListener { imageJoyla(image_2, R.drawable.bugatti, 1) }
        image_3.setOnClickListener { imageJoyla(image_3, R.drawable.ic_plus, 2) }
        image_4.setOnClickListener { imageJoyla(image_4, R.drawable.win_7, 3) }
        image_5.setOnClickListener { imageJoyla(image_5, R.drawable.ic_minus, 4) }
        image_6.setOnClickListener { imageJoyla(image_6, R.drawable.play, 5) }
        image_7.setOnClickListener { imageJoyla(image_7, R.drawable.play, 6) }
        image_8.setOnClickListener { imageJoyla(image_8, R.drawable.ic_minus, 7) }
        image_9.setOnClickListener { imageJoyla(image_9, R.drawable.win_7, 8) }
        image_10.setOnClickListener { imageJoyla(image_10, R.drawable.ic_plus, 9) }
        image_11.setOnClickListener { imageJoyla(image_11, R.drawable.bugatti, 10) }
        image_12.setOnClickListener { imageJoyla(image_12, R.drawable.apple, 11) }
    }

    private fun imageJoyla(image_view: ImageView, image: Int, index: Int) {
        if (finish) {
            imageClick(image_view, image, index)
        }
    }

    private fun imageClick(image_view: ImageView, image: Int, index: Int) {
        if (!listImgOpenClose[index]) {
            animOpen(image_view, image, index)
        } else {
            animClose(image_view, index)
        }
    }

    private fun animOpen(image_view: ImageView, image: Int, index: Int) {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        image_view.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val anim2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                image_view.setImageResource(image)
                image_view.startAnimation(anim2)
                anim2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(p0: Animation?) {

                    }

                    override fun onAnimationEnd(p0: Animation?) {
                        listImgOpenClose[index] = true
                        listImageIndex[openImage] = index
                        listImageId[openImage] = image
                        openImage++
                        if (openImage == 2) {
                            if (listImageId[0] == listImageId[1]) {
                                imageSearch(listImageIndex[0]).visibility = View.INVISIBLE
                                openImage--
                                imageSearch(listImageIndex[1]).visibility = View.INVISIBLE
                                openImage--
                            } else {
                                animClose(imageSearch(listImageIndex[0]), listImageIndex[0])
                                animClose(imageSearch(listImageIndex[1]), listImageIndex[1])
                            }
                        }
                    }

                    override fun onAnimationRepeat(p0: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
    }

    private fun animClose(image_view: ImageView, index: Int?) {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        image_view.startAnimation(anim)
        anim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {

            }

            override fun onAnimationEnd(p0: Animation?) {
                val anim2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                image_view.setImageResource(R.drawable.start)
                image_view.startAnimation(anim2)
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
        listImgOpenClose[index!!] = false
        openImage--
    }

    private fun imageSearch(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6
            6 -> imageView = image_7
            7 -> imageView = image_8
            8 -> imageView = image_9
            9 -> imageView = image_10
            10 -> imageView = image_11
            11 -> imageView = image_12
        }
        return imageView!!
    }
}