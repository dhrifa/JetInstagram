package com.vipulasri.jetinstagram.ui.post

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vipulasri.jetinstagram.R

//import androidx.compose.material.IconButton


@Composable
fun ToolBar() {
    Row(
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

        Text(text = "New Post", style = MaterialTheme.typography.h4)
        Text(
            text = "Share", style = MaterialTheme.typography.h4,
            color = Color.Blue//color = MaterialTheme.colors.onBackground
        )
    }
    Divider()
}

@Composable
fun Post(/*post: Post*/) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_test), //ImagePainter(post.image),
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )
        Text(
            text = "Write a caption",// stringResource(text),
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}

@Composable
fun TagPeople(
    titleHeaderPost: String,
    function: () -> Unit//for the click
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = titleHeaderPost, style = MaterialTheme.typography.h4)
        IconButton(
            onClick = function
            /*Toast.makeText(withContext(cont), "", Toast.LENGTH_SHORT).show()*/
        ) {
            Icon(
                ImageVector.vectorResource(id = R.drawable.ic_post_header_collapse),
                contentDescription = ""
            )
        }
    }

    @Composable
    fun AddFundraiser(
        titleHeaderPost: String,//Add Fundraiser
        function: () -> Unit
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TagPeople(titleHeaderPost = titleHeaderPost, function)
        }
    }
}

@Composable
fun AddLocation(//=> could be a slot
    titleHeaderPost: String,//Add Fundraiser
    function: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TagPeople(titleHeaderPost = titleHeaderPost, function)
        }
        Text(
            text = "Tap here to show locations suggestions",// stringResource(text),
            color = Color.LightGray,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ToggleRow(
    toggleText: String,
    onCheckChange: ((Boolean) -> Unit)?,
    checked: Boolean,
    @DrawableRes image: Int,//should be post's image => i don't know yet how to make it :(
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(88.dp)
                    .clip(CircleShape)
            )
            Text(toggleText)
            Switch(checked = checked,
                onCheckedChange = onCheckChange,
               // modifier = modifier.align(alignment = Alignment.End)
            )
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
fun PostPreview() {
    Post()
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
fun ToggleRowPreview(){
    ToggleRow(toggleText = "ecaviar", onCheckChange = {}, checked = true, image = R.drawable.ic_test_2)
}