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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.example.waysofcooking.ui.components.DrawerMenuContent
import com.example.waysofcooking.ui.components.MainScaffold
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.util.BoundingBox
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun StoreScreen(navController: NavHostController) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    val defaultLocation = remember { GeoPoint(4.598056, -74.075833) }

    var searchText by remember { mutableStateOf("") }
    var zoomLevel by remember { mutableStateOf(18.0) }

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

            if (mapView.overlays.none { it is Marker && it.title == "Ubicación inicial" }) {
                addMarker(mapView, defaultLocation, "Ubicación inicial")
            }

            addMarker(mapView, punto, "📍 $nombreTienda")

            val boundingBox = BoundingBox(
                maxOf(defaultLocation.latitude, punto.latitude),
                maxOf(defaultLocation.longitude, punto.longitude),
                minOf(defaultLocation.latitude, punto.latitude),
                minOf(defaultLocation.longitude, punto.longitude)
            )

            mapView.zoomToBoundingBox(boundingBox, true)
            mapView.invalidate()
        }
    }

    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, context.getSharedPreferences("osm_prefs", 0))
        Configuration.getInstance().userAgentValue = context.packageName

        mapView.apply {
            setTileSource(TileSourceFactory.MAPNIK)
            setMultiTouchControls(true)
            controller.setZoom(zoomLevel)
            controller.setCenter(defaultLocation)
            addMarker(this, defaultLocation, "Ubicación inicial")
            invalidate()
        }
    }

    LaunchedEffect(zoomLevel) {
        mapView.controller.setZoom(zoomLevel)
    }

    MainScaffold(
        navController = navController,
        drawerContent = { scope, drawerState ->
            DrawerMenuContent(
                navController = navController,
                scope = scope,
                drawerState = drawerState
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(16.dp)
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
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

                Spacer(modifier = Modifier.height(16.dp))

                AndroidView(
                    factory = { mapView },
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.55f)
                        .padding(top = 5.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .border(1.dp, MaterialTheme.colorScheme.primary)
                )
            }
        }
    )
}

fun addMarker(mapView: MapView, point: GeoPoint, title: String): Marker {
    return Marker(mapView).apply {
        position = point
        setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM)
        this.title = title
        mapView.overlays.add(this)
    }
}
