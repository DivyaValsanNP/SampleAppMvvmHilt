package com.example.sampleappmvvmhilt.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.sampleappmvvmhilt.R
import com.example.sampleappmvvmhilt.data.entity.Article
import com.example.sampleappmvvmhilt.data.entity.NewsResponse
import com.example.sampleappmvvmhilt.data.entity.Source
import com.example.sampleappmvvmhilt.ui.theme.viewmodel.NewsViewModel

@Composable
fun Loader() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(60.dp)
                .padding(10.dp),
            color = Color.Blue
        )
    }
}

@Composable
fun NewsList(response: NewsResponse) {
    LazyColumn {
        items(response.articles) { article ->
            NewsRowItemComponent(article)
        }
    }
}

@Composable
fun NewsRowItemComponent(article: Article, viewModel: NewsViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 8.dp, end = 8.dp)
            .background(Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        onClick = {
            viewModel.showMyToast("clicked")
        }
    ) {
        Column (modifier = Modifier.background(Color.White)){
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                model = article.urlToImage,
                contentDescription = "",
                placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.size(8.dp).background(Color.White))
            NormalTextComponent(textValue = article.title ?: "Title not available")
        }
    }
}
@Preview
@Composable
fun NewsRowItemComponentPreview() {
    val article = Article("Test", "Test", "Test", "Test", Source("", ""), "Test", "Test", "Test")
    NewsRowItemComponent(article)
}


@Composable
fun NormalTextComponent(textValue: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .background(Color.White),
        text = textValue,
        color = Color.DarkGray,
        style = TextStyle(
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
    )
}

@Composable
fun NewsRowComponent(article: Article) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 8.dp, end = 8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            model = article.urlToImage,
            contentDescription = "",
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.size(20.dp))
        NormalTextComponent(textValue = article.title ?: "Title not available")
        NormalTextComponent(textValue = article.source.name ?: "Source not available")
        NormalTextComponent(textValue = article.author ?: "Author not available")
        NormalTextComponent(textValue = article.content ?: "Content not available")
        NormalTextComponent(textValue = article.publishedAt ?: "Date not available")
    }
}


