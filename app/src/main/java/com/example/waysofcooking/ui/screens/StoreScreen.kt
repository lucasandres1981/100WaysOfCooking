package com.example.waysofcooking.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.viewinterop.AndroidView
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import com.google.android.gms.location.LocationServices

@SuppressLint("MissingPermission")
@Composable
fun StoreScreen(navController: NavHostController) {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val mapView = remember { MapView(context) }

    var userLocation by remember { mutableStateOf<GeoPoint?>(null) }
    var searchText by remember { mutableStateOf("") }
    var zoomLevel by remember { mutableStateOf(16.0) }

    val tiendas = listOf(
        GeoPoint(4.5981, -74.0760) to "Frutas",
        GeoPoint(4.5990, -74.0775) to "Hierbas",
        GeoPoint(4.6005, -74.0750) to "Granos",
        GeoPoint(4.6020, -74.0720) to "Lácteos",
        GeoPoint(4.5970, -74.0792) to "Panadería",
        GeoPoint(4.6300, -74.0960) to "Supermercado"
    )

    fun buscarTienda(nombre: String) {
        tiendas.firstOrNull { it.second.equals(nombre, ignoreCase = true) }?.let { (punto, nombreTienda) ->
            mapView.overlays.removeAll { overlay ->
                overlay is Marker && overlay.title?.startsWith("📍") == true
            }
            addMarker(mapView, punto, "📍 $nombreTienda")
            mapView.controller.animateTo(punto)
        }
    }

    // Inicializar mapa y obtener ubicación
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osm_prefs", 0))
        Configuration.getInstance().userAgentValue = context.packageName

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            val point = if (location != null) {
                GeoPoint(location.latitude, location.longitude)
            } else {
                GeoPoint(4.598056, -74.075833)
            }
            userLocation = point
            mapView.apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)
                controller.setZoom(zoomLevel)
                controller.setCenter(point)
                addMarker(this, point, "📍 Mi Ubicación")
                invalidate()
            }
        }
    }

    LaunchedEffect(zoomLevel) {
        mapView.controller.setZoom(zoomLevel)
    }

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(navController = navController, scope = scope, drawerState = drawerState)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar tienda por nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        IconButton(onClick = { buscarTienda(searchText) }) {
                            Icon(Icons.Default.Search, contentDescription = "Buscar")
                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Nivel de zoom: ${zoomLevel.toInt()}")

                Slider(
                    value = zoomLevel.toFloat(),
                    onValueChange = { zoomLevel = it.toDouble() },
                    valueRange = 4f..20f,
                    modifier = Modifier.fillMaxWidth()
                )

                AndroidView(
                    factory = { mapView },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f) // Ocupa el espacio restante dentro de la Column
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                )
            }
        }
    }
}

// Función para agregar marcador al mapa
fun addMarker(mapView: MapView, point: GeoPoint, title: String): Marker {
    return Marker(mapView).apply {
        position = point
        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        this.title = title
        mapView.overlays.add(this)
    }
}
