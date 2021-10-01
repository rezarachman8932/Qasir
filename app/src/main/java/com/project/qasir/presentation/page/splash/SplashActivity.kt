package com.project.qasir.presentation.page.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.project.qasir.R
import com.project.qasir.bonus.BonusTest
import com.project.qasir.presentation.page.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        println(BonusTest.getCamelCaseCount("oneTwoThreeFourFiveSix").toString())
        println(BonusTest.isBalancedBrackets("abc[](){}").toString())

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }

}