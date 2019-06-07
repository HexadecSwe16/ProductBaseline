package com.megalexa.hexadec.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.amazon.identity.auth.device.AuthError
import com.amazon.identity.auth.device.api.Listener
import com.amazon.identity.auth.device.api.authorization.*
import com.amazon.identity.auth.device.api.workflow.RequestContext
import com.megalexa.hexadec.R
import com.amazon.identity.auth.device.api.authorization.User
import com.megalexa.hexadec.presenter.LoginPresenter
import com.megalexa.hexadec.presenter.contract.MainContract


class MainActivity : AppCompatActivity(), MainContract.LoginView {
    private lateinit var requestContext: RequestContext
    internal lateinit var presenter: MainContract.LoginContract

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setPresenter(LoginPresenter(this@MainActivity))
        supportActionBar?.hide()
        requestContext= RequestContext.create(this)
        requestContext.registerListener( object :
            AuthorizeListener(){
            /* Authorization was completed successfully. */
            override fun onSuccess(result : AuthorizeResult) {
                startToast("Registration Success")
                startActivity(Intent(this@MainActivity, ViewActivity::class.java))
            }
            /* There was an error during the attempt to authorize the application. */
            override fun onError(ae : AuthError) {
                /* Inform the user of the error */
            }
            /* Authorization was cancelled before it could be completed. */
            override fun onCancel(cancellation : AuthCancellation){
                /* Reset the UI to a ready-to-login state */
            }
        })

        val loginButton : View = findViewById(R.id.login_with_amazon)
        loginButton.setOnClickListener {
            AuthorizationManager.authorize(
                AuthorizeRequest.Builder(requestContext).addScopes(ProfileScope.profile(), ProfileScope.postalCode()).build()
            )
        }
    }

    override fun onResume(){
        super.onResume()
        requestContext.onResume()
    }

    override fun onStart() {
        super.onStart()
        val scopes:  Array<Scope> = arrayOf(
            ProfileScope.profile(),
            ProfileScope.postalCode()
        )
        AuthorizationManager.getToken(this, scopes, object : Listener<AuthorizeResult, AuthError> {
            override fun onSuccess(result: AuthorizeResult) {
                if (result.accessToken != null) {
                    User.fetch(this@MainActivity, object : Listener<User, AuthError> {
                        override fun onSuccess(user: User) {
                            presenter.updateUser(user.userId, user.userName, user.userEmail)
                            presenter.setUpWorkflow()
                            startToast("Authentication Success")
                            startActivity(Intent(this@MainActivity, ViewActivity::class.java))
                        }
                        override fun onError(ae: AuthError) {
                            val errorMessage = "Error retrieving profile information.\nPlease log in again"
                            startToast(errorMessage)
                        }
                    })

                } else {
                    //Toast.makeText(this@MainActivity,"Authentication Failed",Toast.LENGTH_LONG).show()
                    return
                }
            }
            override fun onError(ae: AuthError) {
                return
            }
        })
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun setPresenter(presenter: MainContract.LoginContract) {
        this.presenter=presenter
    }

    fun startToast(message:String) {
        runOnUiThread {
            Toast.makeText(this@MainActivity,message,Toast.LENGTH_LONG).show()
        }
    }
}
