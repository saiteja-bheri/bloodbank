package com.bst.keyskotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.compose.AppTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    contraintLayout
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun contraintLayout() {
        var constraints = ConstraintSet {
            var appLogo = createRefFor(id = "appLogo")
            var loginBtn = createRefFor("loginBtn")
            var signinBtn = createRefFor("signinBtn")
            var forgotpasswordBtn = createRefFor("forgotpasswordBtn")
            var nameTextView = createRefFor("nameTextView")
            var emailTextView = createRefFor("emailTextView")
            var nameTextField = createRefFor("nameTextField")
            var emailTextField = createRefFor("emailTextField")

            constrain(loginBtn) {
                top.linkTo(nameTextField.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(signinBtn) {
                top.linkTo(loginBtn.top)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(forgotpasswordBtn) {
                top.linkTo(signinBtn.bottom)
                bottom.linkTo(parent.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }

            constrain(nameTextView) {
                top.linkTo(nameTextField.top)
                end.linkTo(nameTextField.start)
                bottom.linkTo(nameTextField.bottom)

            }

            constrain(emailTextView) {
                top.linkTo(emailTextField.top)
                end.linkTo(emailTextField.start)
                bottom.linkTo(emailTextField.bottom)

            }

            constrain(nameTextField) {
                top.linkTo(parent.top)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
            }

            constrain(emailTextField) {
                top.linkTo(nameTextField.top)
                bottom.linkTo(loginBtn.top)
                end.linkTo(parent.end)
            }

        }
        ConstraintLayout(constraints, modifier = Modifier.padding(all = 5.dp)) {
            val context = LocalContext.current
            var name by remember {
                mutableStateOf("")
            }
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .layoutId("signinBtn")
                    .padding(all = 10.dp)
            ) {
                Text(text = "Sign in")

            }
            TextButton(onClick = { /*TODO*/ },
                modifier = Modifier
                    .layoutId("forgotpasswordBtn")
                    .padding(all = 10.dp)) {
                Text(text = "Forgot Password")
            }
            Button(
                onClick = {
                    Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                    name = ""
                    val intent = Intent(context, MainActivity2::class.java)
                    intent.putExtra("name", name)
                    startActivity(intent)
                }, modifier = Modifier
                    .layoutId("loginBtn")
                    .padding(top = 20.dp)
            ) {
                Text(text = " Log in")
            }
            Text(
                text = "Name", modifier = Modifier
                    .layoutId("nameTextView")
                    .padding(all = 5.dp)
            )
            Text(
                text = "Email", modifier = Modifier
                    .layoutId("emailTextView")
                    .padding(all = 5.dp)
            )
            TextField(value = name, onValueChange = { name = it }, leadingIcon = {
                Icon(imageVector = Icons.Default.Person, contentDescription = "person")
            }, label = {
                Text(text = "Enter Username")
            }, modifier = Modifier
                .layoutId("nameTextField")
                .padding(all = 20.dp)
            )

            TextField(value = name, onValueChange = { name = it }, leadingIcon = {
                Icon(imageVector = Icons.Default.Email, contentDescription = "email")
            }, label = {
                Text(text = "Enter Email")
            }, modifier = Modifier
                .layoutId("emailTextField")
                .padding(all = 20.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.blood_donation),
                contentDescription = "appIcon",
            )

        }
    }

    @Preview
    @Composable
    fun SimpleComposablePreview() {
        AppTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
            ) {
                contraintLayout()
            }
        }
    }

}


