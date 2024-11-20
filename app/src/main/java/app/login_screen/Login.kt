package app.login_screen

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginForm(name: String, modifier: Modifier = Modifier) {

    val context = LocalContext.current
    val userName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val isPasswordError = remember { mutableStateOf(false) }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Hello,\nWelcome to $name page!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(5.dp),
            color = Color.Blue
        )

        Spacer(modifier = Modifier.height(20.dp))


        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = userName.value,
            onValueChange = {
                userName.value = it
                isError.value = it.isEmpty()
            },
            label = { Text(text = "Username") },
            isError = isError.value,
            supportingText = {
                if (isError.value) {
                    Text(text = "Please Enter Username")
                }
            },
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = "Username")
            }
        )


        Spacer(modifier = Modifier.padding(10.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password.value,
            onValueChange = {
                password.value = it
                isPasswordError.value = it.isEmpty()
            },
            label = { Text(text = "Password") },
            isError = isPasswordError.value,
            supportingText = {
                if (isPasswordError.value) {
                    Text(text = "Please Enter Password")
                }
            },
            leadingIcon = {
                Icon(Icons.Default.Lock, contentDescription = "Password")
            },

            trailingIcon = {
                val image = if (passwordVisible)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(imageVector = image, contentDescription = description)
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()

        )

        Spacer(modifier = Modifier.padding(15.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (userName.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Username", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                if (password.value.isEmpty()) {
                    Toast.makeText(context, "Please Enter Password", Toast.LENGTH_SHORT).show()
                    return@OutlinedButton
                }

                Toast.makeText(context, "User Logged in Successfully", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = "Login",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.Blue
            )
        }
    }
}


