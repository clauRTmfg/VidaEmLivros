package br.com.crtalmeida.vidaemlivros.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Apps
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import br.com.crtalmeida.vidaemlivros.R
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun VidaEmLivrosTopAppBar(
//    onClickMenu: (String) -> Unit,
//    onClickBusca: () -> Unit,
//    iconAndTextColor: Color = Color.DarkGray
//) {
//
//    val listItems = getMenuItemsList()
//
//    val contextForToast = LocalContext.current.applicationContext
//
//    var expanded by remember { mutableStateOf(false) }
//    var selectedItem by remember { mutableStateOf<MenuItemData?>(null) }
//
//    TopAppBar(
//        title = { Text(text = stringResource(id = R.string.nome_do_app)) },
//        actions = {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                IconButton(onClick = onClickBusca) {
//                    Icon(
//                        Icons.Default.Search, contentDescription = stringResource(R.string.buscar)
//                    )
//                }
//
//                Spacer(modifier = Modifier.size(8.dp))
//
//                IconButton(onClick = {
//                    expanded = true
//                }) {
//                    Icon(
//                        imageVector = Icons.Default.MoreVert,
//                        contentDescription = "Open Options"
//                    )
//                }
//
//                DropdownMenu(
//                    modifier = Modifier.width(width = 150.dp),
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false },
//                    // adjust the position
//                    offset = DpOffset(x = (-102).dp, y = (-64).dp),
//                    properties = PopupProperties()
//                ) {
//
//                    // adding each menu item
//                    listItems.forEach { menuItemData ->
//                        DropdownMenuItem(
//                            text = {
//                                Text(
//                                    text = menuItemData.text,
//                                    fontWeight = FontWeight.Medium,
//                                    fontSize = 16.sp,
//                                    color = iconAndTextColor
//                                )
//                            },
//                            onClick = {
////                                Toast.makeText(contextForToast, menuItemData.text, Toast.LENGTH_SHORT)
////                                    .show()
//                                expanded = false
//                                selectedItem = menuItemData
//                                onClickMenu(menuItemData.text)
//                            },
//                            leadingIcon = {
//                                if (selectedItem == menuItemData) {
//                                    Icon(Icons.Default.Check, contentDescription = null)
//                                }
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    )
//}
//
//fun getMenuItemsList(): ArrayList<MenuItemData> {
//    val listItems = ArrayList<MenuItemData>()
//
//    listItems.add(MenuItemData(text = "Notes", icon = Icons.Outlined.Bookmarks))
//    listItems.add(MenuItemData(text = "Options", icon = Icons.Outlined.Apps))
//    listItems.add(MenuItemData(text = "Mail", icon = Icons.Outlined.Mail))
//    listItems.add(MenuItemData(text = "About", icon = Icons.Outlined.Info))
//
//    return listItems
//}
//
//data class MenuItemData(val text: String, val icon: ImageVector)