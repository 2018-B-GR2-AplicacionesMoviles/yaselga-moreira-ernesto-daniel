package ernesteins.deber161118

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_user.setOnClickListener{
            this.irAMenu()
        }

    }

    fun irAMenu(){
        val irAMenuP = Intent(this, MenuActivity::class.java)
        this.startActivity(irAMenuP)
    }

}
