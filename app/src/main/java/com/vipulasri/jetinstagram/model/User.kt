package com.vipulasri.jetinstagram.model

data class User(
  val name: String,
  val username: String,
  val image: String
)

val currentUser = User(
    name = "Vipul Asri",
    username = "vipulasri",
    image = "https://s.gravatar.com/avatar/62a968f41c1feb83fd1cd142e7c043f3?s=200"
)

val user1 = User(
    name = "name 1",
    username = "vipulasri",
    image = "https://randomuser.me/api/portraits/women/50.jpg"
)

val user2 = User(
    name = "name 2",
    username = "vipulasri",
    image = "https://randomuser.me/api/portraits/women/20.jpg"
)

val user3 = User(
    name = "name 33",
    username = "vipulasri",
    image = "https://randomuser.me/api/portraits/women/33.jpg"
)

val user4 = User(
    name = "name 4",
    username = "vipulasri",
    image = "https://randomuser.me/api/portraits/women/4.jpg"
)

val listUsers: List<User> = listOf(
    currentUser,
    user1,
    user2,
    user3,
    user4
)


val listSocialMedia: List<String> = listOf(
        "Facebook",
        "Twitter",
    "Tik Tok"
        )

