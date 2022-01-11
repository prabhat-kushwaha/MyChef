package com.prabhatkushwaha.mychef

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.paging.ExperimentalPagingApi
import com.google.firebase.auth.FirebaseAuth
import com.prabhatkushwaha.mychef.framework.presentation.ui.common.AppFragmentFactory
import com.prabhatkushwaha.mychef.framework.presentation.ui.signin.SignInFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.InternalCoroutinesApi
import javax.inject.Inject
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.AuthResult
import com.prabhatkushwaha.mychef.framework.presentation.ui.onboarding.OnBoardingFragment


@FlowPreview
@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@ExperimentalPagingApi
@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {


    @Inject
    lateinit var fragmentFactory: AppFragmentFactory
    private val mAuth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_auth)
        supportActionBar?.hide()

    }

    override fun onStart() {
        super.onStart()
        val currentUser: FirebaseUser? = mAuth.currentUser
        if (currentUser != null) {
            Log.d("TAG", "onStart:-----------> ${currentUser.displayName}")
        }
    }


    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        isCreated: (isCreated: Boolean, message: String?) -> Unit
    ) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = mAuth.currentUser
                isCreated.invoke(true, null)
            }
        }.addOnFailureListener {
            isCreated.invoke(false, it.localizedMessage)
        }
    }

    fun singInWithEmailAndPassword(
        email: String,
        password: String,
        isLoggedIn: (loggedIn: Boolean,user:FirebaseUser?) -> Unit
    ) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                isLoggedIn.invoke(true,mAuth.currentUser)
            } else {
                isLoggedIn.invoke(false,null)
            }
        }

    }

    override fun onBackPressed() {
        val navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val fragment = navHostFragment.childFragmentManager.fragments[0]
        Log.d("TAG", "onBackPressed: $fragment")
        if (fragment is SignInFragment|| fragment is OnBoardingFragment) {
            //exitConfirmation
            finish()

        } else super.onBackPressed()
    }

    fun sendPasswordResetMail(
        email: String,
        isSent: (status: Boolean) -> Unit
    ) {
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful)
                isSent.invoke(true)

        }.addOnFailureListener {
            isSent.invoke(false)
        }
    }

}