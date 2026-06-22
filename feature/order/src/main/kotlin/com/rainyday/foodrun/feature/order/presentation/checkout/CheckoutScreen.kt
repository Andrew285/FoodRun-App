package com.rainyday.foodrun.feature.order.presentation.checkout

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(
    restaurantId: String,
    onBack: () -> Unit,
    onOrderPlaced: (orderId: String) -> Unit,
    viewModel: CheckoutViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    // Коли замовлення створено — переходимо на tracking
    LaunchedEffect(uiState.order) {
        uiState.order?.let { onOrderPlaced(it.id) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Оформлення замовлення") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Назад")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Адреса доставки
            Text("Адреса доставки", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            OutlinedTextField(
                value = uiState.address,
                onValueChange = viewModel::onAddressChange,
                label = { Text("Вулиця, будинок, квартира") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.error != null && uiState.address.isBlank()
            )

            // Підсумок
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("Підсумок", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    HorizontalDivider()
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Товари (${uiState.totalItems})")
                        Text("${"%.0f".format(uiState.subtotal)} грн")
                    }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Доставка")
                        Text("${"%.0f".format(uiState.deliveryFee)} грн")
                    }
                    HorizontalDivider()
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text("Разом", fontWeight = FontWeight.Bold)
                        Text("${"%.0f".format(uiState.total)} грн", fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary)
                    }
                }
            }

            // Помилка
            uiState.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error, style = MaterialTheme.typography.bodySmall)
            }

            Spacer(modifier = Modifier.weight(1f))

            // Кнопка оплати (mock)
            Button(
                onClick = { viewModel.placeOrder(restaurantId) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                enabled = !uiState.isLoading
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                } else {
                    Text("Підтвердити замовлення • ${"%.0f".format(uiState.total)} грн",
                        style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}