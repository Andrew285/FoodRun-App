package com.rainyday.foodrun.feature.order.presentation.order_tracking

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rainyday.foodrun.feature.order.domain.model.OrderStatus

@Composable
fun OrderTrackingScreen(
    onBackToHome: () -> Unit,
    viewModel: OrderTrackingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.error != null -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(uiState.error!!, color = MaterialTheme.colorScheme.error)
            }
        }
        uiState.order != null -> {
            val order = uiState.order!!
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(order.status.emoji(), fontSize = 80.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = order.status.displayName(),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Замовлення #${order.id.take(8)}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Прогрес статусів
                OrderStatusStepper(currentStatus = order.status)

                Spacer(modifier = Modifier.height(32.dp))

                if (uiState.isDelivered) {
                    Button(onClick = onBackToHome, modifier = Modifier.fillMaxWidth()) {
                        Text("Повернутись на головну")
                    }
                }
            }
        }
    }
}

@Composable
private fun OrderStatusStepper(currentStatus: OrderStatus) {
    val steps = listOf(
        OrderStatus.PENDING,
        OrderStatus.PREPARING,
        OrderStatus.ON_THE_WAY,
        OrderStatus.DELIVERED
    )

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        steps.forEachIndexed { index, status ->
            val isCompleted = steps.indexOf(currentStatus) >= index
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = status.emoji(),
                    fontSize = 24.sp,
                    color = if (isCompleted) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                )
                Text(
                    text = status.displayName(),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = if (isCompleted) MaterialTheme.colorScheme.primary
                    else MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.4f)
                )
            }
        }
    }
}