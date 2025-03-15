    package com.example.gemi.ui

    import androidx.compose.foundation.Image
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.material3.Card
    import androidx.compose.material3.CardDefaults
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.LaunchedEffect
    import androidx.compose.runtime.collectAsState
    import androidx.compose.runtime.getValue
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.style.TextAlign
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.gemi.R

    @Composable
    fun ProfileScreen(brevIQViewModel: BrevIQViewModel) {
        val userInfo by brevIQViewModel.userInfo.collectAsState()

        LaunchedEffect(Unit) {
            brevIQViewModel.fetchUserFromDatabase() // Ensure data fetch
        }

        if (userInfo == null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Loading...", fontSize = 20.sp)
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(10.dp)
            ) {
                Spacer(modifier = Modifier.fillMaxWidth().height(30.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().size(300.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFF5EFF1))
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.profile_image),
                            contentDescription = "",
                            modifier = Modifier.size(150.dp).padding(5.dp),
                            contentScale = ContentScale.FillBounds,
                            alignment = Alignment.Center
                        )
                        Text(
                            text = "Name : ${userInfo?.name ?: "N/A"}",
                            modifier = Modifier.fillMaxWidth().padding(3.dp),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Email : ${userInfo?.email ?: "N/A"}",
                            modifier = Modifier.fillMaxWidth().padding(3.dp),
                            fontSize = 20.sp
                        )
                        Text(
                            text = "Password : ${userInfo?.password ?: "N/A"}",
                            modifier = Modifier.fillMaxWidth().padding(3.dp),
                            fontSize = 20.sp
                        )
                    }
                }

                Card(
                    modifier = Modifier.fillMaxWidth().height(50.dp).clickable {
                        brevIQViewModel.setLogoutStatus(true) // Show logout confirmation
                    },
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFBAE75))
                ) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Logout", textAlign = TextAlign.Center, fontSize = 16.sp)
                    }
                }
            }
        }
    }