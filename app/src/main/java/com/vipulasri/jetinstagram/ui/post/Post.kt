package com.vipulasri.jetinstagram.ui.post

import android.graphics.drawable.Icon
import android.inputmethodservice.Keyboard
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R
import com.vipulasri.jetinstagram.model.*
import com.vipulasri.jetinstagram.ui.theme.JetInstagramTheme

//import androidx.compose.material.IconButton


@Composable
fun ToolBar() {
    Keyboard.Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            ImageVector.vectorResource(id = R.drawable.ic_post_back),
            contentDescription = ""
        )

        Text(text = "New Post")
        Text(
            text = "Share",
            color = Color.Blue//color = MaterialTheme.colors.onBackground
        )
    }
    Divider()
}

@Composable
fun PostPic(/*post: Post*/) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_test), //ImagePainter(post.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .heightIn(min = 56.dp),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text("Write a caption...")
            }
        )
    }
    Divider()
}

@Composable
fun TagPeople(
    titleHeaderPost: String,
    function: () -> Unit//for the click
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = titleHeaderPost)
        IconButton(
            onClick = { expanded = !expanded }
        ) {
            Icon(
//                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                if (expanded)
                    ImageVector.vectorResource(id = R.drawable.ic_keyboard_arrow_down)
                else ImageVector.vectorResource(id = R.drawable.ic_post_header_collapse),
                contentDescription = ""
            )
        }
    }
}


@Composable
fun AddLocation(//=> could be a slot
    titleHeaderPost: String,//Add Fundraiser
    function: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TagPeople(titleHeaderPost = titleHeaderPost, function)
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text("Tap here to show locations suggestions")
            }
        )
        Divider()
    }
}

@Composable
fun ToggleRow(
    toggleText: String,
    onCheckChange: ((Boolean) -> Unit)?,
    checked: Boolean,
    @DrawableRes image: Int?,//should be post's image => i don't know yet how to make it :(
    modifier: Modifier = Modifier.padding(top = 2.dp, bottom = 2.dp),
) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = modifier.padding(end = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            image?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = null,
                    //contentScale = ContentScale.Crop,
                    modifier = modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            }
            Text(toggleText, modifier = modifier.weight(1f))
            Switch(
                checked = checked,
                onCheckedChange = onCheckChange,
                modifier = modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun PostToOther(
    users: List<User>,
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .size(200.dp)
) {
    Surface(modifier = modifier) {
        Column(modifier = modifier) {
            TagPeople(titleHeaderPost = "Post to Other Instagram Accounts") {
                //on click event
            }
            LazyColumn() {
                itemsIndexed(users) { index, user ->
                    ToggleRow(
                        toggleText = user.name,
                        onCheckChange = {},
                        checked = true,
                        image = R.drawable.ic_profile_image/*user.image*/
                        // , modifier = modifier.padding(16.dp)
                    )
                }
            }
            Divider()
        }
    }
}

@Composable
fun SocialMedia(
    socials: List<String>,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        LazyColumn() {
            itemsIndexed(socials) { _, social ->
                ToggleRow(
                    toggleText = social,
                    onCheckChange = {},
                    checked = true,
                    image = null/*user.image*/
                )
            }
        }
    }
}

@Composable
fun PostScreen(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(modifier = modifier) {
            ToolBar()
            PostPic()
            TagPeople(titleHeaderPost = "Tag People") {}
            Divider()
//            AddLocation(titleHeaderPost = "Add Location", function = {  }, modifier = modifier)
            TagPeople(titleHeaderPost = "Add Location") {}
            TextField(
                value = "",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(), colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colors.surface
                ),
                placeholder = {
                    Text("Tap here to show locations suggestions")
                }
            )
            Divider()
            TagPeople(titleHeaderPost = "Add Fundraiser") {}
            Divider()
            PostToOther(users = listUsers)
            Divider()
            SocialMedia(socials = listSocialMedia)
        }
    }
}

@Composable
private fun InstaBottom(modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp)
                .height(56.dp),
            //  .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Advanced Settings", color = Color.LightGray)
            IconButton(
                onClick = {}
            ) {
                Icon(
                    ImageVector.vectorResource(id = R.drawable.ic_post_header_collapse),
                    contentDescription = ""
                )
            }
        }
    }
}

@Composable
fun Post() {
    JetInstagramTheme() {
        Scaffold(
            bottomBar = { InstaBottom() }
        ) {
            PostScreen(Modifier)
        }
    }
}

@Preview
@Composable
fun ToolBarPreview() {
    ToolBar()
}

@Preview
@Composable
fun PostPicPreview() {
    PostPic()
}


@Preview
@Composable
fun TagPeoplePreview() {
    TagPeople("Tag People", {})
}

@Preview
@Composable
fun AddLocationPreview() {
    AddLocation("Add Location", {}, modifier = Modifier)
}

@Preview
@Composable
fun AddFundraiserPreview() {
    TagPeople("Add Fundraiser", {})
}

@Preview
@Composable
fun ToggleRowPreview() {
    ToggleRow(
        toggleText = "ecaviar",
        onCheckChange = {},
        checked = true,
        image = R.drawable.ic_profile_image
    )
}

@Preview
@Composable
fun PostToOtherPreview() {
    PostToOther(users = listUsers)
}

@Preview
@Composable
fun SocialMediaPreview() {
    SocialMedia(socials = listSocialMedia)
}

@Preview
@Composable
fun InstaBottomPreview() {
    InstaBottom()
}

@Preview
@Composable
fun PostScreenPreview() {
    PostScreen()
}

@Preview
@Composable
fun PostPreview() {
    Post()
}